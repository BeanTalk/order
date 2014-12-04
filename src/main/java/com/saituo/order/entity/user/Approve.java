package com.saituo.order.entity.user;

public class Approve {

	// 产品订单
	private String productOrderId;

	// 审批结果：1.通过 2不通过
	private String auditResult;

	// 驳回原因:价格原因；品牌原因；存货原因；其他原因等
	private String turnDownReason;

	// 驳回描述
	private String turnDownNote;

	public String getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
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
}
