package com.saituo.order.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.RejectReasonStatus;
import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.entity.user.AuditHis;

@Service
@Transactional
public class AuditHisService {

	@Autowired
	private AuditHisDao auditHisDao;

	private Map<String, Object> rejectReasonMapping = VariableUtils.getVariables(RejectReasonStatus.class);

	public AuditHis getAuditHisByProductOrderId(String productOrderId) {

		AuditHis auditHis = auditHisDao.getAuditByProductOrderId(productOrderId);
		auditHis.setTurnDownReason((String) rejectReasonMapping.get(auditHis.getAuditResult()));
		return auditHis;
	}
}
