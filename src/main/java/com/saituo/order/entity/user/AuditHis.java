package com.saituo.order.entity.user;

public class AuditHis {
	//审批记录ID
	private Long auditId;
	//产品订单编码
	private Long  registerNumber;
	//审批人
	private String auditPerson;
	//审批时间
	private String acceptDate;
	//审批结果：1.通过 2不通过
	private String auditResult;
	//驳回原因:价格原因；品牌原因；存货原因；其他原因等
	private String turnDownReason;
	//驳回描述
	private String turnDownNote;
	
	//状态:1.代表当前最新审批结果。0代表旧版本审批结果
	private String statusCd;
	
	public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public Long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getTurnDownReason() {
		return turnDownReason;
	}
	public void setTurnDownReason(String turnDownReason) {
		this.turnDownReason = turnDownReason;
	}
	public String getTurnDownNote() {
		return turnDownNote;
	}
	public void setTurnDownNote(String turnDownNote) {
		this.turnDownNote = turnDownNote;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
}
