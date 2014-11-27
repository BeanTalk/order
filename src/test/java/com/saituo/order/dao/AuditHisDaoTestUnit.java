package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.entity.user.Address;
import com.saituo.order.entity.user.AuditHis;
import com.saituo.order.service.ServiceTestCaseSupport;

public class AuditHisDaoTestUnit extends ServiceTestCaseSupport {
	@Autowired
	private AuditHisDao auditHisDao;

	@Test
	public void addAuditHis() {
		AuditHis auditHis = new AuditHis();
		auditHis.setAuditPerson("admin1");
		auditHis.setAuditResult("11");
		auditHis.setRegisterNumber(111l);
		auditHis.setStatusCd("1");
		auditHis.setTurnDownNote("asdasd");
		auditHis.setTurnDownReason("asasasd");
		auditHisDao.insert(auditHis);
		System.out.println("id:" + auditHis.getAuditId());

	}

	@Test
	public void queryAudit() {
		AuditHis auditHis = new AuditHis();
		auditHis.setRegisterNumber(111l);
		List<AuditHis> list = auditHisDao.queryList(auditHis);
		for (AuditHis a : list) {
			System.out.println(a.getAuditId());
		}
	}
}
