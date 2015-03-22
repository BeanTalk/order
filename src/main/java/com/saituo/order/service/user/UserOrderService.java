package com.saituo.order.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.utils.DateBetweenUtils;
import com.saituo.order.commons.utils.MathUtils;
import com.saituo.order.dao.account.GroupDao;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.dao.user.AddressDao;
import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.dao.user.ProductOrderDao;
import com.saituo.order.dao.user.ProductOrderHisDao;
import com.saituo.order.dao.user.UserBeansDao;
import com.saituo.order.dao.user.UserGroupPointAccountDao;
import com.saituo.order.dao.user.UserGroupPointHisDao;
import com.saituo.order.dao.user.UserOrderDao;
import com.saituo.order.dao.user.UserPeasHisDao;
import com.saituo.order.entity.order.Product;
import com.saituo.order.entity.user.Address;
import com.saituo.order.entity.user.Audit;
import com.saituo.order.entity.user.AuditHis;
import com.saituo.order.entity.user.ProductOrder;
import com.saituo.order.entity.user.ProductOrderHis;
import com.saituo.order.entity.user.UserBeans;
import com.saituo.order.entity.user.UserGroupPointAccount;
import com.saituo.order.entity.user.UserGroupPointHis;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.entity.user.UserPeasHis;

/**
 * 客户订单业务逻辑
 */
@Service
@Transactional
public class UserOrderService {

	@Autowired
	private UserOrderDao userOrderDao;
	@Autowired
	private ProductOrderDao productOrderDao;
	@Autowired
	private AuditHisDao auditHisDao;
	@Autowired
	private ProductOrderHisDao productOrderHisDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserGroupPointAccountDao userGroupPointAccountDao;
	@Autowired
	private UserGroupPointHisDao userGroupPointHisDao;
	@Autowired
	private UserBeansDao userBeansDao;
	@Autowired
	private UserPeasHisDao userPeasHisDao;
	@Autowired
	private GroupDao groupDao;

	/**
	 * 学生订单保存方法
	 */
	public Map<String, String> doCreateUserOrder(Map<String, Object> filter) {

		Map<String, String> returnMap = new HashMap<String, String>();
		// 客户订单信息:
		// 客户编码
		Integer userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);
		// 受理地市
		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		Integer groupId = SessionVariable.getCurrentSessionVariable().getGroupId();
		// 地址编码
		Long addressId = 0l;
		if (filter.get("addressId") != null && !filter.get("addressId").equals("")) {
			addressId = Long.valueOf(String.valueOf(filter.get("addressId")));
		}

		UserOrder userOrder = new UserOrder();
		userOrder.setAddressId(addressId);
		userOrder.setAreaId(areaId);
		userOrder.setStatusCd("1");// //
									// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
									// 已取消
		userOrder.setUserId(userId);
		userOrder.setGroupId(groupId);
		userOrderDao.insert(userOrder);
		// 客户订单编码
		Long userOrderId = userOrder.getUserOrderId();

		// 产品订单项列表
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		// 前台页面传的订购的产品订单串，格式：产品编号~订购价格～数量
		String prodcutString[];

		ProductOrder productOrder = null;
		if (productOrderList != null && productOrderList.size() > 0) {
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setAreaId(areaId);// 受理地市
				productOrder.setAuditCd("0");// 审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;
				productOrder.setInvoiceStatus("0"); // 状态:0.初始 1.未开具发票
													// 2.已开具发票3.已送发票
				productOrder.setOrderFee(VariableUtils.typeCast(prodcutString[1], Double.class)); // 目录价
				productOrder.setOrderNum(VariableUtils.typeCast(prodcutString[2], Long.class)); // 订购数量
				productOrder.setProductId(VariableUtils.typeCast(prodcutString[0], Long.class));// 产品编码
				Integer productId = VariableUtils.typeCast(prodcutString[0], Integer.class);
				Product product = productDao.getProductByProductId(productId);
				if (product == null) {
					continue;
				}
				productOrder.setBrandId(VariableUtils.typeCast(product.getBrandId(), Integer.class));
				productOrder.setProductNum(product.getProductNum());
				productOrder.setProductName(product.getProductName());
				productOrder.setGroupId(groupId);
				productOrder.setStatusCd("0"); // 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrder.setUserId(userId); // 客户编码
				productOrder.setUserOrderId(userOrderId);// 客户订单编码
				productOrderDao.insert(productOrder);
			}
		}
		// 返回客户订单编码
		returnMap.put("userOrderId", VariableUtils.typeCast(userOrderId, String.class));
		return returnMap;
	}
	/**
	 * 查询客户订单信息列表
	 */
	public List<UserOrder> getUserOrderList(Map<String, Object> filter) {

		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
			userOrderQuery.setUserOrderId(userOrderId);
		}
		// 客户组别编码
		Integer groupId = null;
		if (filter.get("groupId") != null && !filter.get("groupId").equals("")) {
			groupId = VariableUtils.typeCast(filter.get("groupId"), Integer.class);
			userOrderQuery.setGroupId(groupId);
		}
		// 客户编码
		Integer userId = null;
		if (filter.get("userId") != null && !filter.get("userId").equals("")) {
			userId = VariableUtils.typeCast(filter.get("userId"), Integer.class);
			userOrderQuery.setUserId(userId);
		}
		// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
		String statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd = String.valueOf(filter.get("statusCd"));
			userOrderQuery.setStatusCd(statusCd);
		}
		userOrderQuery.setAreaId(SessionVariable.getCurrentSessionVariable().getAreaId());
		return userOrderDao.queryList(userOrderQuery, filter);
	}

	/**
	 * 查询客户订单信息总数 分页使用
	 */
	public int getUserOrderCount(Map<String, Object> filter) {

		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
			userOrderQuery.setUserOrderId(userOrderId);
		}
		// 客户组别编码
		Integer groupId = null;
		if (filter.get("groupId") != null && !filter.get("groupId").equals("")) {
			groupId = VariableUtils.typeCast(filter.get("groupId"), Integer.class);
			userOrderQuery.setGroupId(groupId);
		}
		// 客户编码
		Integer userId = null;
		if (filter.get("userId") != null && !filter.get("userId").equals("")) {
			userId = VariableUtils.typeCast(filter.get("userId"), Integer.class);
			userOrderQuery.setUserId(userId);
		}
		// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
		String statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd = String.valueOf(filter.get("statusCd"));
			userOrderQuery.setStatusCd(statusCd);
		}
		userOrderQuery.setAreaId(SessionVariable.getCurrentSessionVariable().getAreaId());
		return userOrderDao.count(userOrderQuery, filter);
	}

	/**
	 * 获取客户订单（单条），地址信息，以及客户订单对应的产品订单项信息列表
	 */
	public Map<String, Object> getDeatilOrderInfo(Map<String, Object> filter) {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
			userOrderQuery.setUserOrderId(userOrderId);
		}

		// 根据客户订单编号查询客户订单信息
		UserOrder userOrderReturn = userOrderDao.query(userOrderQuery);
		userOrderReturn.setUserName(String.valueOf(filter.get("userName")));
		// 根据地址编码查询地址信息
		Address address = new Address();
		address.setAddressId(userOrderReturn.getAddressId());
		Address addressReturn = addressDao.query(address);

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserOrderId(userOrderId);
		// 审批状态:1.待审批,2已驳回,3审批通过
		String auditCd = null;
		if (filter.get("auditCd") != null && !filter.get("auditCd").equals("")) {
			auditCd = (String) filter.get("auditCd");
			productOrder.setAuditCd(auditCd);
		}
		// 订单状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消; -2.已退货
		String productStatusCd = null;
		if (filter.get("productStatusCd") != null && !filter.get("productStatusCd").equals("")) {
			productStatusCd = String.valueOf(filter.get("productStatusCd"));
			productOrder.setStatusCd(productStatusCd);
		}

		// 订单状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
		String invoiceStatus = null;
		if (filter.get("invoiceStatus") != null && !filter.get("invoiceStatus").equals("")) {
			invoiceStatus = String.valueOf(filter.get("invoiceStatus"));
			productOrder.setInvoiceStatus(invoiceStatus);
		}

		List<ProductOrder> productOrderReturn = this.combineProductInfo(productOrderDao
				.queryListByUserOrderId(productOrder));

		returnMap.put("userOrderReturn", userOrderReturn);
		returnMap.put("addressReturn", addressReturn);
		returnMap.put("productOrderReturn", productOrderReturn);
		return returnMap;
	}

	/**
	 * 根据产品订单编号更新订购价格
	 */
	public void doUpdateProductOrderFee(Map<String, Object> filter) {

		// 产品订单项列表
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		// 前台页面传的订购的产品订单串，格式：产品订单编号~订购价格新~订购价格旧
		String prodcutString[];
		// 客户编码
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrder.setOrderFee(VariableUtils.typeCast(prodcutString[1], Double.class)); // 目录价

				productOrderDao.update(productOrder);
				// 记录产品订单项的操作历史
				orderResult = "修改订购价格：修改前：" + VariableUtils.typeCast(prodcutString[2], String.class) + " 修改后："
						+ VariableUtils.typeCast(prodcutString[1], String.class);
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}
	}

	/**
	 * 根据客户订单编码更新客户订单和产品订单项状态为待审批
	 */
	public void doUpdateUserOrderStatus(Map<String, Object> filter) {
		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
			userOrderQuery.setUserOrderId(userOrderId);
			// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1.已取消
			userOrderQuery.setStatusCd("2");
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturn = productOrderDao.queryListByUserOrderId(productOrder);
		if (productOrderReturn != null && productOrderReturn.size() > 0) {
			ProductOrder productOrderUpdate = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "产品订单状态更改为待审批";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);

			for (ProductOrder returnValue : productOrderReturn) {
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(returnValue.getRegisterNumber());
				// 审批状态:0未处理;1.待审批;2.已驳回;3.审批通过;
				productOrderUpdate.setAuditCd("1");
				// 更新产品订单项的订单状态
				productOrderDao.update(productOrderUpdate);
				// 记录产品订单项的操作历史
				productOrderHis = new ProductOrderHis();
				// 当前操作人
				productOrderHis.setAcceptPerson(userId);
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(returnValue.getRegisterNumber());
				productOrderHisDao.insert(productOrderHis);
			}

		}

	}

	/**
	 * 根据产品订单项编号进行订单审批
	 */
	public void doAuditProductOrder(Map<String, Object> filter) {

		// 产品订单项列表
		List<Audit> auditInfoList = (List<Audit>) filter.get("auditInfoList");
		// 前台页面传的订购的产品订单串，格式：产品订单编号~审批结果~驳回原因～处理意见

		if (auditInfoList != null && auditInfoList.size() > 0) {
			ProductOrder productOrder = null;
			AuditHis auditHis = null;

			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);

			for (Audit auditInfo : auditInfoList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(auditInfo.getProductOrderId(), Long.class));
				// 审批状态:0未处理;1.待审批;2.已驳回;3.审批通过;
				productOrder.setAuditCd(VariableUtils.typeCast(auditInfo.getAuditStatus(), String.class));
				// 根据产品订单编码更新审批结果
				productOrderDao.update(productOrder);

				// 记录审批结果记录表
				auditHis = new AuditHis();
				auditHis.setAuditPerson(VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser()
						.get("id"), String.class));// 审批人
				auditHis.setAuditResult(VariableUtils.typeCast(auditInfo.getAuditStatus(), String.class));
				auditHis.setRegisterNumber(VariableUtils.typeCast(auditInfo.getProductOrderId(), Long.class));
				// 状态:1.代表当前最新审批结果。0代表旧版本审批结果
				auditHis.setStatusCd("1");
				auditHis.setTurnDownReason(VariableUtils.typeCast(auditInfo.getTurnDownReason(), String.class));
				auditHis.setTurnDownNote(VariableUtils.typeCast(auditInfo.getTurnDownNote(), String.class));
				auditHisDao.insert(auditHis);

				// 记录产品订单项的操作历史
				orderResult = "审批结果为:" + VariableUtils.typeCast(auditInfo.getAuditStatus(), String.class);
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(auditInfo.getProductOrderId(), Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}

		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrderQuery = new ProductOrder();
		productOrderQuery.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturnList = productOrderDao.queryListByUserOrderId(productOrderQuery);

		if (productOrderReturnList != null && productOrderReturnList.size() > 0) {
			boolean flag = true;
			// 判断是否全部审批完毕
			for (ProductOrder productOrder2 : productOrderReturnList) {
				if (productOrder2.getAuditCd().equals("1")) {
					flag = false;
					break;
				}
			}
			// 如果所有产品订单项都没有未处理状态，则认为客户订单全部审批完毕
			if (flag) {
				// 在全部审批完毕的前提下 判断是否有审批不通过的单子
				for (ProductOrder productOrder2 : productOrderReturnList) {
					if (productOrder2.getAuditCd().equals("2")) {
						flag = false;
						break;
					}
				}

				// 如果flag=true 则代表所有单子都审批通过，如果=false则代表已驳回，更新客户订单信息
				UserOrder userOrderQuery = new UserOrder();
				if (flag) {
					// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1已取消
					userOrderQuery.setStatusCd("4");
				} else {
					// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1已取消
					userOrderQuery.setStatusCd("3");
				}
				// 更新客户订单的状态
				userOrderQuery.setUserOrderId(userOrderId);
				userOrderDao.update(userOrderQuery);
			}
		}
	}

	/**
	 * 根据客户订单编码更新客户订单状态为已下单 导师下单
	 */
	public void doUpdateUserOrderStatusFive(Map<String, Object> filter) {
		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			Long userOrderId = Long.valueOf(String.valueOf(filter.get("userOrderId")));
			userOrderQuery.setUserOrderId(userOrderId);
			// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1已取消
			userOrderQuery.setStatusCd("5");
			userOrderQuery.setTeacherOrderDate("validDate");
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
		}

		// 0125-by-liurong
		// 产品订单项列表-记录实收价和使用积分
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		// 前台页面传的订购的产品订单串，格式：产品订单编号~实收价~使用积分
		String prodcutString[];
		// 客户编码
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		// 客户组编号
		String userGroupId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getGroupId(),
				String.class);

		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrder.setPricePaidFee(VariableUtils.typeCast(prodcutString[1], Double.class)); // 实收价
				productOrder.setPointBalanceFee(VariableUtils.typeCast(prodcutString[2], Long.class));// 使用积分

				// 如果使用积分需要扣除积分
				if (null != VariableUtils.typeCast(prodcutString[2], int.class)
						&& VariableUtils.typeCast(prodcutString[2], int.class) > 0) {

					UserGroupPointAccount userGroupPointAccount = new UserGroupPointAccount();
					userGroupPointAccount.setPointBalance(VariableUtils.typeCast(prodcutString[2], Integer.class));
					userGroupPointAccount.setUserGroupId(userGroupId);
					userGroupPointAccountDao.updateReduction(userGroupPointAccount);

					// 记录积分历史表
					UserGroupPointHis userGroupPointHis = new UserGroupPointHis();
					userGroupPointHis.setAcceptPerson(userId);// 创建者
					userGroupPointHis.setGroupId(userGroupId);// 客户组别编码
					userGroupPointHis.setPointBalance(VariableUtils.typeCast(prodcutString[2], Integer.class)); // 本次使用或累计豆豆数
					userGroupPointHis.setPointType("1"); // 操作类型:1.使用积分 2.累积积分
					userGroupPointHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
					userGroupPointHisDao.insert(userGroupPointHis);
				}

				productOrder.setTeacherOrderTime("now");
				productOrderDao.update(productOrder);
				// 记录产品订单项的操作历史
				orderResult = "导师下单，实收价：" + VariableUtils.typeCast(prodcutString[1], String.class) + " 使用积分："
						+ VariableUtils.typeCast(prodcutString[2], String.class);
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}

	}

	/**
	 * 根据客户订单编码更新客户订单状态为取消
	 */
	public void doUpdateUserOrderStatusCancel(Map<String, Object> filter) {

		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Long.class);
			userOrderQuery.setUserOrderId(userOrderId);
			// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1.已取消;
			userOrderQuery.setStatusCd("-1");
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturn = productOrderDao.queryListByUserOrderId(productOrder);
		if (productOrderReturn != null && productOrderReturn.size() > 0) {
			ProductOrder productOrderUpdate = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			for (ProductOrder returnValue : productOrderReturn) {
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(returnValue.getRegisterNumber());
				// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrderUpdate.setStatusCd("-1");
				// 更新产品订单项的订单状态
				productOrderDao.update(productOrderUpdate);

				// 记录产品订单项的操作历史
				orderResult = "产品订单项状态更改为已取消!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(returnValue.getRegisterNumber());
				productOrderHisDao.insert(productOrderHis);
			}
		}
	}

	/**
	 * 根据产品订单编号修改产品订单信息，并将客户订单状态更改为待审批状态
	 */
	public void doModifyUserOrder(Map<String, Object> filter) {

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号~数量
		List<String> productOrderModifyList = (List<String>) filter.get("productOrderModifyList");
		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号
		List<String> productOrderDeleteList = (List<String>) filter.get("productOrderDeleteList");

		String prodcutStringModify[];
		if (productOrderModifyList != null && productOrderModifyList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			for (String productOrderString : productOrderModifyList) {
				prodcutStringModify = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutStringModify[0], Long.class));
				productOrder.setOrderNum(VariableUtils.typeCast(prodcutStringModify[1], Long.class));

				// 审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;
				productOrder.setAuditCd("1");
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "修改产品订单项，将数量更改为：" + VariableUtils.typeCast(prodcutStringModify[1], String.class);
				productOrderHis = new ProductOrderHis();
				// 当前操作人
				productOrderHis.setAcceptPerson(userId);
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutStringModify[0], Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}

		if (productOrderDeleteList != null && productOrderDeleteList.size() > 0) {
			ProductOrder productOrderDelete = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			for (String productOrderString : productOrderDeleteList) {
				productOrderDelete = new ProductOrder();
				productOrderDelete.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderDao.delete(productOrderDelete);

				// 记录产品订单项的操作历史
				orderResult = "删除产品订单项!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}

		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Long.class);
		}
		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrderQuery = new ProductOrder();
		productOrderQuery.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturnList = productOrderDao.queryListByUserOrderId(productOrderQuery);

		if (productOrderReturnList != null && productOrderReturnList.size() > 0) {
			boolean flag = true;
			// 判断是否全部修改为待审批
			for (ProductOrder productOrder2 : productOrderReturnList) {
				// 审批状态:0未处理;1.待审批;2.已驳回;3.审批通过;
				if (!productOrder2.getAuditCd().equals("1")) {
					if (!productOrder2.getAuditCd().equals("3")) {
						flag = false;
						break;
					}
				}
			}
			// 只有全部产品订单的审批状态更改为待审批时，才将客户订单的状态改为待审批
			if (flag) {
				UserOrder userOrderQuery = new UserOrder();
				userOrderQuery.setUserOrderId(userOrderId);
				// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1.已取消
				userOrderQuery.setStatusCd("2");
				// 更新客户订单的状态
				userOrderDao.update(userOrderQuery);
			}
		}
	}

	/**
	 * 根据客户订单编码更新客户订单状态为已接单,将产品订单项的发票状态改为未开具发票状态
	 */
	public void doUpdateUserOrderStatusAccept(Map<String, Object> filter) {

		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Long.class);
			userOrderQuery.setUserOrderId(userOrderId);
			// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1.已取消
			userOrderQuery.setStatusCd("6");
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrder = new ProductOrder();
		productOrder.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturn = productOrderDao.queryListByUserOrderId(productOrder);

		if (productOrderReturn != null && productOrderReturn.size() > 0) {
			ProductOrder productOrderUpdate = null;

			for (ProductOrder returnValue : productOrderReturn) {
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(returnValue.getRegisterNumber());
				// 状态:0.初始 1.未开具发票;2.已开具发票3.已送发票
				productOrderUpdate.setInvoiceStatus("1");
				// 更新产品订单项的订单状态
				productOrderDao.update(productOrderUpdate);
			}
		}
	}

	/**
	 * 根据产品订单编号修改订单状态为内勤出单 此方法适用于内勤出单：出单时productOrderStatusCd传1
	 */
	public void doProductOrderShipment(Map<String, Object> filter) {

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号~销售人员ID
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		/*
		 * // 产品订单状态 String productOrderStatusCd = ""; if
		 * (filter.get("productOrderStatusCd") != null &&
		 * !filter.get("productOrderStatusCd").equals("")) {
		 * productOrderStatusCd = (String) filter.get("productOrderStatusCd");
		 * 
		 * }
		 */
		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			String prodcutString[];
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrder.setStatusCd("1");
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单状态更改为已出单!";
				productOrderHis = new ProductOrderHis();
				// 当前操作人
				productOrderHis.setAcceptPerson(userId);
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);

				// 记录销售人员送货
				orderResult = "销售人员送货";
				// 当前操作人
				productOrderHis.setAcceptPerson(VariableUtils.typeCast(prodcutString[1], String.class));
				productOrderHis.setOrderResult(orderResult);
				productOrderHisDao.insert(productOrderHis);
			}
		}
	}

	/**
	 * 根据产品订单编号修改订单状态为内勤出单 此方法适用于内勤已收货：出单时productOrderStatusCd传2
	 */
	public void doProductOrderReceipt(Map<String, Object> filter) {

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		/*
		 * // 产品订单状态 String productOrderStatusCd = ""; if
		 * (filter.get("productOrderStatusCd") != null &&
		 * !filter.get("productOrderStatusCd").equals("")) {
		 * productOrderStatusCd = (String) filter.get("productOrderStatusCd");
		 * 
		 * }
		 */
		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			for (String productOrderString : productOrderList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrder.setStatusCd("2");
				productOrder.setDeliveryDate("");
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单状态更改为已收货!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);

			}
		}
	}

	/**
	 * 根据产品订单编号修改发票状态为已开发票 此方法适用于财务已开发票：productOrderInvoiceStatus传2
	 */
	public void doProductOrderInvoiced(Map<String, Object> filter) {

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号~销售人员ID
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		/*
		 * // 产品订单状态 String productOrderInvoiceStatus = ""; if
		 * (filter.get("productOrderInvoiceStatus") != null &&
		 * !filter.get("productOrderInvoiceStatus").equals("")) {
		 * productOrderInvoiceStatus = (String)
		 * filter.get("productOrderInvoiceStatus");
		 * 
		 * }
		 */
		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			String prodcutString[];
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				// 状态:0.初始 1.未开具发票 2.已开具发票3.已送发票
				productOrder.setInvoiceStatus("2");
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单发票状态更改为已开具发票!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrderHisDao.insert(productOrderHis);

				// 记录销售人员送发票
				orderResult = "销售人员送发票";
				productOrderHis.setAcceptPerson(VariableUtils.typeCast(prodcutString[1], String.class));// 销售人员
				productOrderHis.setOrderResult(orderResult);
				productOrderHisDao.insert(productOrderHis);
			}
		}
	}

	/**
	 * 根据产品订单编号修改订单状态为已送发票 此方法适用于财务已送发票：productOrderInvoiceStatus传3
	 */
	public void doProductOrderAlreadySend(Map<String, Object> filter) {

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		/*
		 * // 产品订单状态 String productOrderInvoiceStatus = ""; if
		 * (filter.get("productOrderInvoiceStatus") != null &&
		 * !filter.get("productOrderInvoiceStatus").equals("")) {
		 * productOrderInvoiceStatus = (String)
		 * filter.get("productOrderInvoiceStatus");
		 * 
		 * }
		 */
		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			for (String productOrderString : productOrderList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				// 状态:0.初始 1.未开具发票 2.已开具发票3.已送发票
				productOrder.setInvoiceStatus("3");
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单发票状态更改为已送发票!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}
	}

	/**
	 * 财务结款
	 */
	public void doProductOrderReceivables(Map<String, Object> filter) {

		// 产品订单项列表
		// 前台页面传的订购的产品订单串，格式：产品订单编号
		// ~实收价～客户编码（提交订单的客户）~客户组编号~客户收货时间(deliveryDate) 不用传了
		List<String> productOrderList = (List<String>) filter.get("productOrderList");

		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrderQuery = null;
			ProductOrder productOrderUpdate = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 财务编码
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);

			Double consumptionPoints = 0d;// 消费积分
			Double collectionPoints = 0d;// 回款积分
			UserBeans userBeans = null;// 豆豆类
			UserPeasHis userPeasHis = null;// 豆豆历史
			UserGroupPointAccount userGroupPointAccount = null;// 回款积分
			Date date = new Date();// 当前时间
			Date custDate = null; // 客户收货时间
			String deliveryDate = ""; // 客户收货时间
			int dayFlag = 0;// 计算当前时间-客户收货时间的时间差天数。
			Map<String, Object> map = null;// 查询客户组编号
			for (String productOrderString : productOrderList) {
				productOrderQuery = new ProductOrder();
				productOrderQuery.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderQuery = productOrderDao.query(productOrderQuery);
				// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderUpdate.setStatusCd("3");
				productOrderDao.update(productOrderUpdate);

				// 查询客户组ID
				map = groupDao.getUserGroup(productOrderQuery.getUserId());

				// 记录产品订单项的操作历史
				orderResult = "产品订单状态更改为已结款";
				productOrderHis = new ProductOrderHis();
				// 当前操作人
				productOrderHis.setAcceptPerson(userId);
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);

				// 豆豆 客户豆豆=实收金额*1 取整
				userBeans = new UserBeans();
				userBeans.setBeansNum(VariableUtils.typeCast(
						Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(), float.class)),
						Long.class));// 豆豆数量
				userBeans.setUserId(VariableUtils.typeCast(productOrderQuery.getUserId(), String.class));// 客户
				if (userBeansDao.updateAdd(userBeans) == 0) {
					userBeansDao.insert(userBeans);
				}

				// 增加豆豆历史信息
				userPeasHis = new UserPeasHis();
				userPeasHis.setAcceptPerson(userId);
				userPeasHis.setPeasBalance(VariableUtils.typeCast(
						Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(), float.class)),
						Long.class)); // 本次累计豆豆数
				userPeasHis.setPeasType("1");// 操作类型:1.累积积分2.使用积分
				userPeasHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));// 累积豆豆时是客户订购产品的订单编号；
				userPeasHis.setUserId(VariableUtils.typeCast(productOrderQuery.getUserId(), String.class));// 客户编码
				userPeasHisDao.insert(userPeasHis);

				// 积分 本次组积分=回款积分+消费积分
				/*
				 * 根据实收价扣除组账户余额，记录费用历史，（产品订单编号、扣除金额、应收款、实收款、使用积分、受理人、受理时间等）
				 * 判断如果是预付款客户，回款积分=实收金额*4 如果是普通客户并且回款日期-客户收货日期《=30天
				 * ，回款积分=实收金额*2，》=30《=90的，回款积分=实收金额*1.
				 */
				// 消费积分 消费积分=实收金额*1
				consumptionPoints = Math
						.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(), float.class));// 根据实收价算消费积分
				// 回款积分
				userGroupPointAccount = new UserGroupPointAccount();
				userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
				userGroupPointAccount = userGroupPointAccountDao.query(userGroupPointAccount);

				if (userGroupPointAccount == null) {
					userGroupPointAccount = new UserGroupPointAccount();
					userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
					userGroupPointAccount.setPointBalance(0);
					userGroupPointAccount.setAccountFee(0d);
					userGroupPointAccountDao.insert(userGroupPointAccount);
				}

				boolean ifPre = false;// 是否预付标识
				// 判断客户组账户是否有钱
				if (null != userGroupPointAccount && null != userGroupPointAccount.getAccountFee()
						&& userGroupPointAccount.getAccountFee() > 0) {
					// 判断客户组账户钱是否大于实收价，如果大于实收价则认为是预付费客户。
					if (userGroupPointAccount.getAccountFee() > VariableUtils
							.typeCast(productOrderString, Double.class)) {
						ifPre = true;
					}
				}
				collectionPoints = 0d;// 回款积分
				// 是预付费客户
				if (ifPre) {
					// 判断如果是预付款客户，回款积分=实收金额*4
					collectionPoints = Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(),
							float.class)) * 4;
				} else {
					deliveryDate = VariableUtils.typeCast(productOrderQuery.getDeliveryDate(), String.class);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						custDate = sdf.parse(deliveryDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// 计算当前时间和客户收货时间差
					dayFlag = DateBetweenUtils.getDays(custDate, date);
					// 回款积分=实收金额*2，》=30《=90的，回款积分=实收金额*1.
					if (dayFlag <= 30) {
						collectionPoints = Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(),
								float.class)) * 2;
					} else if (30 < dayFlag && dayFlag <= 90) {
						collectionPoints = Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(),
								float.class));
					}
				}

				// 最终积分时消费积分+回款积分
				userGroupPointAccount.setPointBalance(VariableUtils.typeCast(collectionPoints + consumptionPoints,
						Integer.class));
				userGroupPointAccount.setAccountFee(null);
				userGroupPointAccountDao.updateAdd(userGroupPointAccount);

				// 记录积分历史表
				UserGroupPointHis userGroupPointHis = new UserGroupPointHis();
				userGroupPointHis.setAcceptPerson(userId);// 创建者
				userGroupPointHis.setGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
				userGroupPointHis.setPointBalance(VariableUtils.typeCast(collectionPoints + consumptionPoints,
						Integer.class)); // 本次使用或累计豆豆数
				userGroupPointHis.setPointType("2"); // 操作类型:1.使用积分 2.累积积分
				userGroupPointHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				userGroupPointHisDao.insert(userGroupPointHis);
			}
		}

		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Long.class);
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrderQuery = new ProductOrder();
		productOrderQuery.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturnList = productOrderDao.queryListByUserOrderId(productOrderQuery);

		if (productOrderReturnList != null && productOrderReturnList.size() > 0) {
			boolean flag = true;
			// 判断是否全部已结款
			for (ProductOrder productOrder2 : productOrderReturnList) {
				if (!productOrder2.getStatusCd().equals("3")) {
					flag = false;
					break;
				}
			}
			// 如果flag=true 则代表所有单子都已结款，更新客户订单信息状态为已完成
			UserOrder userOrderQuery = new UserOrder();
			if (flag) {
				// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1.已取消
				userOrderQuery.setStatusCd("7");
				// 更新客户订单的状态
				userOrderQuery.setUserOrderId(userOrderId);
				userOrderDao.update(userOrderQuery);
			}
		}
	}

	private List<ProductOrder> combineProductInfo(List<ProductOrder> productOrderList) {
		for (ProductOrder productOrder : productOrderList) {
			Integer productId = VariableUtils.typeCast(productOrder.getProductId(), Integer.class);
			Product product = productDao.getProductByProductId(productId);
			productOrder.setProduct(product);
			productOrder
					.setTotalPrice(MathUtils.getDoublePoint(productOrder.getOrderFee() * productOrder.getOrderNum()));
		}
		return productOrderList;
	}

	public List<Product> getProductList(Long userOrderId) {

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrderQuery = new ProductOrder();
		productOrderQuery.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderList = productOrderDao.queryListByUserOrderId(productOrderQuery);
		List<String> productIds = Lists.newArrayList();
		for (ProductOrder productOrder : productOrderList) {
			productIds.add(String.valueOf(productOrder.getProductId()));
		}
		return productDao.getProductListByProductIds(productIds);
	}

	/**
	 * 内勤给客户退货
	 */
	public void doProductOrderReturn(Map<String, Object> filter) {
		// 产品订单项列表
		// 前台页面传的订购的产品订单串，格式：产品订单编号
		List<String> productOrderList = (List<String>) filter.get("productOrderList");

		// 内勤客户编号
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrderQuery = null;
			UserGroupPointAccount userGroupPointAccount = null;
			Map<String, Object> map = null;// 查询客户组编号
			UserGroupPointHis userGroupPointHis = null;
			UserGroupPointHis userGroupPointHisReduction = null;
			ProductOrder productOrderUpdate = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			UserPeasHis userPeasHis = null;// 豆豆历史
			UserBeans userBeans = null;
			for (String productOrderString : productOrderList) {
				productOrderQuery = new ProductOrder();
				productOrderQuery.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderQuery = productOrderDao.query(productOrderQuery);
				// 查询客户组ID
				map = groupDao.getUserGroup(productOrderQuery.getUserId());

				// 判断如果产品订单状态为已收货，则退货时如下
				if (productOrderQuery.getStatusCd().equals("2")) { // 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消;-2退货

					// 判断是否使用积分,>0说明使用积分 退积分
					if (productOrderQuery.getPointBalanceFee() > 0) {// 使用积分
						// 将积分回退
						userGroupPointAccount = new UserGroupPointAccount();
						userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
						userGroupPointAccount.setPointBalance(VariableUtils.typeCast(
								productOrderQuery.getPointBalanceFee(), Integer.class));// 使用积分数量
						userGroupPointAccount.setAccountFee(null);
						userGroupPointAccountDao.updateAdd(userGroupPointAccount);

						// 记录积分历史表
						userGroupPointHis = new UserGroupPointHis();
						userGroupPointHis.setAcceptPerson(userId);// 创建者
						userGroupPointHis.setGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
						userGroupPointHis.setPointBalance(VariableUtils.typeCast(
								productOrderQuery.getPointBalanceFee(), Integer.class)); // 本次使用或累计豆豆数
						userGroupPointHis.setPointType("3"); // 操作类型:1.使用积分
																// 2.累积积分 3.回退
						userGroupPointHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
						userGroupPointHisDao.insert(userGroupPointHis);
					}

					// 产品订单项回退
					// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消; -2.退货
					productOrderUpdate = new ProductOrder();
					productOrderUpdate.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					productOrderUpdate.setStatusCd("-2");
					productOrderDao.update(productOrderUpdate);

					// 记录产品订单项的操作历史
					orderResult = "产品订单状态更改为退货";
					productOrderHis = new ProductOrderHis();
					// 当前操作人
					productOrderHis.setAcceptPerson(userId);
					productOrderHis.setOrderResult(orderResult);
					productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					productOrderHisDao.insert(productOrderHis);
				}

				// 判断如果产品订单状态为已结款，则退货时如下
				if (productOrderQuery.getStatusCd().equals("3")) { // 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消;-2退货

					// 判断是否使用积分,>0说明使用积分 退积分
					if (productOrderQuery.getPointBalanceFee() > 0) {// 使用积分
						// 将积分回退
						userGroupPointAccount = new UserGroupPointAccount();
						userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
						userGroupPointAccount.setPointBalance(VariableUtils.typeCast(
								productOrderQuery.getPointBalanceFee(), Integer.class));// 使用积分数量
						userGroupPointAccount.setAccountFee(null);
						userGroupPointAccountDao.updateAdd(userGroupPointAccount);

						// 记录积分历史表
						userGroupPointHis = new UserGroupPointHis();
						userGroupPointHis.setAcceptPerson(userId);// 创建者
						userGroupPointHis.setGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
						userGroupPointHis.setPointBalance(VariableUtils.typeCast(
								productOrderQuery.getPointBalanceFee(), Integer.class)); // 本次使用或累计豆豆数
						userGroupPointHis.setPointType("3"); // 操作类型:1.使用积分
																// 2.累积积分 3.回退
						userGroupPointHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
						userGroupPointHisDao.insert(userGroupPointHis);
					}

					// 查询结款时生成多少豆豆
					userGroupPointHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					userGroupPointHis.setPointType("2"); // 操作类型:1.使用积分 2.累积积分
															// 3.回退
					userGroupPointHis = userGroupPointHisDao.queryByRegisterNumber(userGroupPointHisReduction);

					// 将实收价回退--退钱
					userGroupPointAccount = new UserGroupPointAccount();
					userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
					userGroupPointAccount.setPointBalance(userGroupPointHis.getPointBalance());// 当时生成多少积分
					userGroupPointAccount.setAccountFee(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(),
							Double.class));// 实收价
					userGroupPointAccountDao.updateAdd(userGroupPointAccount);

					// 减积分
					userGroupPointAccount.setUserGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
					userGroupPointAccount.setPointBalance(userGroupPointHis.getPointBalance());// 当时生成多少积分
					userGroupPointAccount.setAccountFee(null);
					userGroupPointAccountDao.updateReduction(userGroupPointAccount);

					// 记录积分历史表
					userGroupPointHisReduction = new UserGroupPointHis();
					userGroupPointHisReduction.setAcceptPerson(userId);// 创建者
					userGroupPointHisReduction.setGroupId(VariableUtils.typeCast(map.get("groupId"), String.class));// 客户组编号
					userGroupPointHisReduction.setPointBalance(userGroupPointHis.getPointBalance());// 当时生成多少积分
					userGroupPointHisReduction.setPointType("3"); // 操作类型:1.使用积分
																	// 2.累积积分3.回退
					userGroupPointHisReduction
							.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					userGroupPointHisDao.insert(userGroupPointHisReduction);

					// 退豆豆
					userBeans = new UserBeans();
					userBeans.setBeansNum(VariableUtils.typeCast(
							Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(), float.class)),
							Long.class));// 豆豆数量
					userBeans.setUserId(VariableUtils.typeCast(productOrderQuery.getUserId(), String.class));// 客户
					userBeansDao.updateReduction(userBeans);

					// 豆豆历史信息
					userPeasHis = new UserPeasHis();
					userPeasHis.setAcceptPerson(userId);
					userPeasHis.setPeasBalance(VariableUtils.typeCast(
							Math.floor(VariableUtils.typeCast(productOrderQuery.getPricePaidFee(), float.class)),
							Long.class)); // 本次累计豆豆数
					userPeasHis.setPeasType("3");// 操作类型:1.累积积分2.使用积分3回退
					userPeasHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));// 累积豆豆时是客户订购产品的订单编号；
					userPeasHis.setUserId(VariableUtils.typeCast(productOrderQuery.getUserId(), String.class));// 客户编码
					userPeasHisDao.insert(userPeasHis);

					// 产品订单项回退
					// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消; -2.退货
					productOrderUpdate = new ProductOrder();
					productOrderUpdate.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					productOrderUpdate.setStatusCd("-2");
					productOrderDao.update(productOrderUpdate);

					// 记录产品订单项的操作历史
					orderResult = "产品订单状态更改为退货";
					productOrderHis = new ProductOrderHis();
					// 当前操作人
					productOrderHis.setAcceptPerson(userId);
					productOrderHis.setOrderResult(orderResult);
					productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
					productOrderHisDao.insert(productOrderHis);
				}
			}
		}
	}

	public UserGroupPointAccount queryGroupPointAccount(Integer groupId) {
		UserGroupPointAccount userGroupPointAccount = new UserGroupPointAccount();
		userGroupPointAccount.setUserGroupId(String.valueOf(groupId));
		return userGroupPointAccountDao.query(userGroupPointAccount);
	}
}
