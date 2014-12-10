package com.saituo.order.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.entity.user.AuditHis;

@Service
@Transactional
public class AuditHisService {

	@Autowired
	private AuditHisDao auditHisDao;

	public AuditHis getAuditHisByProductOrderId(String productOrderId) {
		return auditHisDao.getAuditByProductOrderId(productOrderId);
	}
}
