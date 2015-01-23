package com.saituo.order.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.user.OrderComplaintDao;
import com.saituo.order.entity.user.OrderComplaint;

@Service
@Transactional
public class OrderComplainService {
	@Autowired
	private OrderComplaintDao orderComplaintDao;

	/**
	 *投诉订单提交方法
	 */
	public Map<String, String> doCreateOrderComplaint(Map<String, Object> filter) {
		Map<String, String> returnMap = new HashMap<String, String>();
		// 投诉信息:
		String userId = "1000";/*
								 * VariableUtils.typeCast(SessionVariable.
								 * getCurrentSessionVariable
								 * ().getUser().get("id"), String.class); //
								 * 客户编码
								 */
		// 受理地市
		String areaId = "2299"; // SessionVariable.getCurrentSessionVariable().getAreaId();
		Long userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Long.class); // 客户订单编码
		Long registerNumber = VariableUtils.typeCast(filter.get("registerNumber"), Long.class); // 产品订单编码
		String complaintType = VariableUtils.typeCast(filter.get("complaintType"), String.class); // 投诉类型
		String complaintNote = VariableUtils.typeCast(filter.get("complaintNote"), String.class); // 投诉内容
		
		OrderComplaint orderComplaint = new OrderComplaint();
		orderComplaint.setUserOrderId(userOrderId);//客户订单编码
		orderComplaint.setRegisterNumber(registerNumber);// 产品订单编码
		orderComplaint.setUserId(userId);// 客户编码
		orderComplaint.setAreaId(areaId);//受理地市
		orderComplaint.setComplaintType(complaintType);	//投诉类型：1.产品质量问题、2.产品剂量问题、3.产品包装问题、4.产品保质期问题
		orderComplaint.setComplaintNote(complaintNote);//投诉内容
		orderComplaint.setStatusCd("0");//状态：0.未完成、1.处理中、2.已完成、-1.已取消
		orderComplaintDao.insert(orderComplaint);
		
		// 返回投诉编码
		returnMap.put("Id", VariableUtils.typeCast(orderComplaint.getId(), String.class));
		return returnMap;
	}
	
	/**
	 *技术支持更新投诉结果
	 */
	public void  doUpdateOrderComplaint(Map<String, Object> filter) {
		// 投诉信息:
				String userId = "1000";/*
										 * VariableUtils.typeCast(SessionVariable.
										 * getCurrentSessionVariable
										 * ().getUser().get("id"), String.class); //
										 * 技术支持
										 */
		Long id = VariableUtils.typeCast(filter.get("id"), Long.class); // 投诉订单编号
		String statusCd = VariableUtils.typeCast(filter.get("statusCd"), String.class); // 状态：0.未完成、1.处理中、2.已完成、-1.已取消
		String handleResult = VariableUtils.typeCast(filter.get("handleResult"), String.class); //处理结果：1.协商解决、2.换货、3.退货、4.投诉取消
		String handleNote = VariableUtils.typeCast(filter.get("handleNote"), String.class); //  处理意见
		
		OrderComplaint orderComplaint = new OrderComplaint();
		orderComplaint.setId(id);//投诉订单编号
		orderComplaint.setHandleResult(handleResult);  //处理结果：1.协商解决、2.换货、3.退货、4.投诉取消
		orderComplaint.setHandlePersonId(userId); //	处理人编码
		orderComplaint.setStatusCd(statusCd);//状态：0.未完成、1.处理中、2.已完成、-1.已取消
		orderComplaint.setHandleNote(handleNote);//  //处理意见
		orderComplaintDao.update(orderComplaint);
	}
	
	/**
	 * 查询投诉订单信息总数 分页使用 地市、投诉状态、客户编号、投诉类型
	 */
	public int getOrderComplaintCount(Map<String, Object> filter) {

		OrderComplaint orderComplaint = new OrderComplaint();
		  //当前地市
		 orderComplaint.setAreaId ("2299");//(SessionVariable.getCurrentSessionVariable().getAreaId());
		// 状态
		String  statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd =String.valueOf(filter.get("statusCd"));
			orderComplaint.setStatusCd(statusCd);
		}
		// 客户编码
				String userId = null;
				if (filter.get("userId") != null && !filter.get("userId").equals("")) {
					userId = String.valueOf(filter.get("userId"));
					orderComplaint.setUserId(userId);
				}
		// 投诉类型
		String complaintType = null;
		if (filter.get("complaintType") != null && !filter.get("complaintType").equals("")) {
			complaintType = String.valueOf(filter.get("complaintType"));
			orderComplaint.setComplaintType(complaintType);
		}
		
		return orderComplaintDao.count(orderComplaint, filter);
	}

	/**
	 * 查询客户订单信息列表
	 */
	public List<OrderComplaint> getOrderComplaintList(Map<String, Object> filter) {
		OrderComplaint orderComplaint = new OrderComplaint();
		  //当前地市
		 orderComplaint.setAreaId ("2299");//(SessionVariable.getCurrentSessionVariable().getAreaId());
		// 状态
		String  statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd =String.valueOf(filter.get("statusCd"));
			orderComplaint.setStatusCd(statusCd);
		}
		// 客户编码
				String userId = null;
				if (filter.get("userId") != null && !filter.get("userId").equals("")) {
					userId = String.valueOf(filter.get("userId"));
					orderComplaint.setUserId(userId);
				}
		// 投诉类型
		String complaintType = null;
		if (filter.get("complaintType") != null && !filter.get("complaintType").equals("")) {
			complaintType = String.valueOf(filter.get("complaintType"));
			orderComplaint.setComplaintType(complaintType);
		}

		return orderComplaintDao.queryList(orderComplaint, filter);
	}
	
	
}
