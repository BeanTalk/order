package com.saituo.order.entity.user;

public class Audit {

	private String userOrderId;

	private String productOrderId;

	private String auditStatus;

	private String turnDownReason;

	private String turnDownNote;

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
