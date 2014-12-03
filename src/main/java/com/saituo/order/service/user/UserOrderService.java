package com.saituo.order.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.dao.user.AddressDao;
import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.dao.user.ProductOrderDao;
import com.saituo.order.dao.user.ProductOrderHisDao;
import com.saituo.order.dao.user.UserOrderDao;
import com.saituo.order.entity.order.Product;
import com.saituo.order.entity.user.Address;
import com.saituo.order.entity.user.AuditHis;
import com.saituo.order.entity.user.ProductOrder;
import com.saituo.order.entity.user.ProductOrderHis;
import com.saituo.order.entity.user.UserOrder;

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

	/**
	 * 学生订单保存方法
	 */
	public Map<String, String> doCreateUserOrder(Map<String, Object> filter) {

		Map<String, String> returnMap = new HashMap<String, String>();
		// 客户订单信息:
		// 客户编码
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		// 受理地市
		String areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		String groupId = SessionVariable.getCurrentSessionVariable().getGroupId();
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
				productOrder.setIfValid("1");// 是否有效 1是有效，0无效
				productOrder.setInvoiceStatus("0"); // 状态:0.初始 1.未开具发票
													// 2.已开具发票3.已送发票
				productOrder.setOrderFee(VariableUtils.typeCast(prodcutString[1], Double.class)); // 目录价
				productOrder.setOrderNum(VariableUtils.typeCast(prodcutString[2], Long.class)); // 订购数量
				productOrder.setProductId(VariableUtils.typeCast(prodcutString[0], Long.class));// 产品编码
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
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
		}
		// 客户组别编码
		String groupId = null;
		if (filter.get("groupId") != null && !filter.get("groupId").equals("")) {
			groupId = (String) filter.get("groupId");
			userOrderQuery.setGroupId(groupId);
		}
		// 客户编码
		String userId = null;
		if (filter.get("userId") != null && !filter.get("userId").equals("")) {
			userId = (String) filter.get("userId");
			userOrderQuery.setUserId(userId);
		}
		// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
		String statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd = (String) filter.get("statusCd");
			userOrderQuery.setStatusCd(statusCd);
		}
		userOrderQuery.setAreaId(SessionVariable.getCurrentSessionVariable().getAreaId());
		return userOrderDao.queryList(userOrderQuery);
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
		String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);

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
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
			userOrderQuery.setStatusCd("2");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
											// 已取消
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);

			for (ProductOrder returnValue : productOrderReturn) {
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(returnValue.getRegisterNumber());
				productOrderUpdate.setAuditCd("1");// 审批状态:0未处理
													// 1.待审批;2.已驳回;3.审批通过;
				// 更新产品订单项的订单状态
				productOrderDao.update(productOrderUpdate);
				// 记录产品订单项的操作历史
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
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
		List<String> productOrderList = (List<String>) filter.get("productOrderList");
		// 前台页面传的订购的产品订单串，格式：产品订单编号~审批结果~驳回原因～处理意见
		String prodcutString[];

		if (productOrderList != null && productOrderList.size() > 0) {
			ProductOrder productOrder = null;
			AuditHis auditHis = null;

			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);

			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrder.setAuditCd(VariableUtils.typeCast(prodcutString[1], String.class));// 审批状态:0未处理
																								// 1.待审批;2.已驳回;3.审批通过;
				// 根据产品订单编码更新审批结果
				productOrderDao.update(productOrder);

				// 记录审批结果记录表
				auditHis = new AuditHis();
				// auditHis.setAuditPerson(VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				// String.class));// 审批人
				auditHis.setAuditPerson("admin");
				auditHis.setAuditResult(VariableUtils.typeCast(prodcutString[1], String.class));
				auditHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				auditHis.setStatusCd("1");// 状态:1.代表当前最新审批结果。0代表旧版本审批结果
				if (prodcutString.length > 2) {
					auditHis.setTurnDownReason(VariableUtils.typeCast(prodcutString[2], String.class));
					auditHis.setTurnDownNote(VariableUtils.typeCast(prodcutString[3], String.class));
				}
				auditHisDao.insert(auditHis);

				// 记录产品订单项的操作历史
				orderResult = "审批结果为:" + VariableUtils.typeCast(prodcutString[1], String.class);
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrderHisDao.insert(productOrderHis);
			}
		}

		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = (Long) filter.get("userOrderId");
		}

		// 根据客户订单编码查询产品订单项信息列表
		ProductOrder productOrderQuery = new ProductOrder();
		productOrderQuery.setUserOrderId(userOrderId);
		List<ProductOrder> productOrderReturnList = productOrderDao.queryListByUserOrderId(productOrderQuery);

		if (productOrderReturnList != null && productOrderReturnList.size() > 0) {
			boolean flag = true;
			// 判断是否全部审批完毕
			for (ProductOrder productOrder2 : productOrderReturnList) {
				if (productOrder2.getAuditCd().equals("0")) {
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
					userOrderQuery.setStatusCd("4");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
													// 已取消
				} else {
					userOrderQuery.setStatusCd("3");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
													// 已取消
				}
				// 更新客户订单的状态
				userOrderQuery.setUserOrderId(userOrderId);
				userOrderDao.update(userOrderQuery);
			}
		}
	}

	/**
	 * 根据客户订单编码更新客户订单状态为已下单
	 */
	public void doUpdateUserOrderStatusFive(Map<String, Object> filter) {
		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
			userOrderQuery.setStatusCd("5");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
											// 已取消
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
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
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
			userOrderQuery.setStatusCd("-1");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
												// 已取消
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (ProductOrder returnValue : productOrderReturn) {
				productOrderUpdate = new ProductOrder();
				productOrderUpdate.setRegisterNumber(returnValue.getRegisterNumber());
				productOrderUpdate.setStatusCd("-1");// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
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

		UserOrder userOrderQuery = new UserOrder();
		// 客户订单编号
		Long userOrderId = null;
		if (filter.get("userOrderId") != null && !filter.get("userOrderId").equals("")) {
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
			userOrderQuery.setStatusCd("2");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
											// 已取消
			// 更新客户订单的状态
			userOrderDao.update(userOrderQuery);
		}

		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号~数量
		List<String> productOrderModifyList = (List<String>) filter.get("productOrderModifyList");
		// 产品订单项列表 前台页面传的订购的产品订单串，格式：产品订单编号
		List<String> productOrderDeleteList = (List<String>) filter.get("productOrderDeleteList");

		String prodcutStringModify[];
		String prodcutStringDelete[];
		if (productOrderModifyList != null && productOrderModifyList.size() > 0) {
			ProductOrder productOrder = null;
			ProductOrderHis productOrderHis = null;
			String orderResult = "";// 历史信息结果
			// 客户编码
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (String productOrderString : productOrderModifyList) {
				prodcutStringModify = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutStringModify[0], Long.class));
				productOrder.setOrderNum(VariableUtils.typeCast(prodcutStringModify[1], Long.class));
				productOrder.setAuditCd("1");// 审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "修改产品订单项，将数量更改为：" + VariableUtils.typeCast(prodcutStringModify[1], String.class);
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (String productOrderString : productOrderDeleteList) {
				prodcutStringDelete = productOrderString.split("~");
				productOrderDelete = new ProductOrder();
				productOrderDelete.setRegisterNumber(VariableUtils.typeCast(prodcutStringDelete[0], Long.class));
				productOrderDelete.setIfValid("0");
				productOrderDao.update(productOrderDelete);

				// 记录产品订单项的操作历史
				orderResult = "删除产品订单项!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(prodcutStringDelete[0], Long.class));
				productOrderHisDao.insert(productOrderHis);
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
			userOrderId = (Long) filter.get("userOrderId");
			userOrderQuery.setUserOrderId(userOrderId);
			userOrderQuery.setStatusCd("6");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
											// 已取消
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
				productOrderUpdate.setInvoiceStatus("1");// 状态:0.初始 1.未开具发票
															// 2.已开具发票3.已送发票
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			String prodcutString[];
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrder.setStatusCd("1");// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单状态更改为已出单!";
				productOrderHis = new ProductOrderHis();
				productOrderHis.setAcceptPerson(userId);// 当前操作人
				productOrderHis.setOrderResult(orderResult);
				productOrderHis.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrderHisDao.insert(productOrderHis);

				// 记录销售人员送货
				orderResult = "销售人员送货";
				productOrderHis.setAcceptPerson(VariableUtils.typeCast(prodcutString[1], String.class));// 当前操作人
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (String productOrderString : productOrderList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrder.setStatusCd("2");// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			String prodcutString[];
			for (String productOrderString : productOrderList) {
				prodcutString = productOrderString.split("~");
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(prodcutString[0], Long.class));
				productOrder.setInvoiceStatus("2"); // 状态:0.初始 1.未开具发票
													// 2.已开具发票3.已送发票
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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (String productOrderString : productOrderList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrder.setInvoiceStatus("3"); // 状态:0.初始 1.未开具发票
													// 2.已开具发票3.已送发票
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
	 * 根据产品订单编号修改发票状态为已送 此方法适用于财务已送发票：productOrderInvoiceStatus传3
	 */
	public void doProductOrderReceivables(Map<String, Object> filter) {

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
			String userId = "901";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),String.class);
			for (String productOrderString : productOrderList) {
				productOrder = new ProductOrder();
				productOrder.setRegisterNumber(VariableUtils.typeCast(productOrderString, Long.class));
				productOrder.setStatusCd("3");// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
				productOrderDao.update(productOrder);

				// 记录产品订单项的操作历史
				orderResult = "产品订单状态更改为已结款";
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
			userOrderId = (Long) filter.get("userOrderId");
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
				userOrderQuery.setStatusCd("7");// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1
												// 已取消
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
		}
		return productOrderList;
	}
}
