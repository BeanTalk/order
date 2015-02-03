package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.AuditHis;
/**
 * 审批结果历史记录表维护
 */
public interface AuditHisDao {
	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param UserGroupPointAccount
	 * @return void
	 * @throws
	 */
	public void insert(AuditHis auditHis);
	/**
	 * <p>
	 * Description: 查询数据:根据产品订单编码查询集合
	 * </p>
	 * 
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount
	 * @throws
	 */
	public List<AuditHis> queryList(AuditHis auditHis);

	/**
	 * 获取最新的审批结果
	 * 
	 * @param productOrderId
	 * @return
	 */
	public AuditHis getAuditByProductOrderId(String productOrderId);
}
