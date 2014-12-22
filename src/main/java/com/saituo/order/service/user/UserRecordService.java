package com.saituo.order.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.user.ProductRecordDao;
import com.saituo.order.dao.user.UserRecordDao;
import com.saituo.order.entity.user.ProductRecord;
import com.saituo.order.entity.user.UserRecord;

public class UserRecordService {
	@Autowired
	private UserRecordDao userRecordDao;
	@Autowired
	private ProductRecordDao productRecordDao;

	/**
	 * 记录保存方法
	 */
	public Map<String, String> doCreateUserRecord(Map<String, Object> filter) {

		Map<String, String> returnMap = new HashMap<String, String>();
		// 记录订单信息:
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

		UserRecord userRecord = new UserRecord();
		userRecord.setAddressId(addressId);
		userRecord.setAreaId(areaId);
		userRecord.setUserId(userId);
		userRecord.setGroupId(groupId);
		userRecordDao.insert(userRecord);
		// 记录订单编码
		Long userRecordId = userRecord.getUserRecordId();

		// 记录产品订单项列表
		List<String> productRecordList = (List<String>) filter.get("productRecordList");
		// 前台页面传的订购的产品订单串，格式：产品编号~订购价格～数量~供应商id
		String prodcutString[];

		ProductRecord productRecord = null;
		if (productRecordList != null && productRecordList.size() > 0) {
			for (String productRecordString : productRecordList) {
				prodcutString = productRecordString.split("~");
				productRecord = new ProductRecord();
				productRecord.setAreaId(areaId);// 受理地市

				productRecord.setOrderFee(VariableUtils.typeCast(prodcutString[1], Double.class)); // 目录价
				productRecord.setOrderNum(VariableUtils.typeCast(prodcutString[2], Long.class)); // 订购数量
				productRecord.setProductId(VariableUtils.typeCast(prodcutString[0], Long.class));// 产品编码
				productRecord.setSupplierId(VariableUtils.typeCast(prodcutString[3], Long.class));// 供应商id
				productRecord.setUserId(userId); // 客户编码
				productRecord.setUserRecordId(userRecordId);// 记录订单编码
				productRecordDao.insert(productRecord);
			}
		}
		// 返回记录订单编码
		returnMap.put("userRecordId", VariableUtils.typeCast(userRecordId, String.class));
		return returnMap;
	}

	/**
	 * 查询记录订单信息总数 分页使用
	 */
	public int getUserRecordCount(Map<String, Object> filter) {

		UserRecord userRecord = new UserRecord();
		// 记录订单编号
		Long userRecordId = null;
		if (filter.get("userRecordId") != null && !filter.get("userRecordId").equals("")) {
			userRecordId = Long.valueOf(String.valueOf(filter.get("userRecordId")));
			userRecord.setUserRecordId(userRecordId);
		}
		// 客户组别编码
		Integer groupId = null;
		if (filter.get("groupId") != null && !filter.get("groupId").equals("")) {
			groupId = VariableUtils.typeCast(filter.get("groupId"), Integer.class);
			userRecord.setGroupId(groupId);
		}
		// 客户编码
		Integer userId = null;
		if (filter.get("userId") != null && !filter.get("userId").equals("")) {
			userId = VariableUtils.typeCast(filter.get("userId"), Integer.class);
			userRecord.setUserId(userId);
		}
		// 当前地市
		userRecord.setAreaId(SessionVariable.getCurrentSessionVariable().getAreaId());
		return userRecordDao.count(userRecord, filter);
	}

	/**
	 * 查询客户订单信息列表
	 */
	public List<UserRecord> getUserRecordList(Map<String, Object> filter) {
		UserRecord userRecord = new UserRecord();
		// 记录订单编号
		Long userRecordId = null;
		if (filter.get("userRecordId") != null && !filter.get("userRecordId").equals("")) {
			userRecordId = Long.valueOf(String.valueOf(filter.get("userRecordId")));
			userRecord.setUserRecordId(userRecordId);
		}
		// 客户组别编码
		Integer groupId = null;
		if (filter.get("groupId") != null && !filter.get("groupId").equals("")) {
			groupId = VariableUtils.typeCast(filter.get("groupId"), Integer.class);
			userRecord.setGroupId(groupId);
		}
		// 客户编码
		Integer userId = null;
		if (filter.get("userId") != null && !filter.get("userId").equals("")) {
			userId = VariableUtils.typeCast(filter.get("userId"), Integer.class);
			userRecord.setUserId(userId);
		}
		// 当前地市
		userRecord.setAreaId(SessionVariable.getCurrentSessionVariable().getAreaId());

		return userRecordDao.queryList(userRecord, filter);
	}

}
