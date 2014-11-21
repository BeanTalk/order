package com.saituo.order.entity.user;

public class ProductOrder {

	//产品订单编码
	private Long  registerNumber;
	//客户订单编码
	private Long userOrderId;
	//受理地市
	private String areaId;
	//客户编码
	private String userId;
	//产品编码
	private Long  productId;
	//目录价
	private Double orderFee;
	//订购数量
	private Long  orderNum;
	//审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;
	private String auditCd;
	//状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
	private String statusCd;
	//状态:0.初始 1.未开具发票 2.已开具发票3.已送发票
	private String invoiceStatus;
	//创建时间
	private String acceptDate;
	//最后修改时间
	private String changeDate;
	//是否有效 1是有效，0无效
	private String ifValid;
	public Long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}
	public Long getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(Long userOrderId) {
		this.userOrderId = userOrderId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public String getAuditCd() {
		return auditCd;
	}
	public void setAuditCd(String auditCd) {
		this.auditCd = auditCd;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getIfValid() {
		return ifValid;
	}
	public void setIfValid(String ifValid) {
		this.ifValid = ifValid;
	}
	
	
	
}
