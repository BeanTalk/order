package com.saituo.order.web.form;

import org.hibernate.validator.constraints.NotEmpty;

//产品订单编号~审批结果~驳回原因～处理意见
public class ApproveForm {

	// 产品订单
	private String productOrderId;

	// 审批结果：1.通过 2不通过
	@NotEmpty(message = "审批结果不能为空")
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
