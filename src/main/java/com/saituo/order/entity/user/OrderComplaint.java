package com.saituo.order.entity.user;

import com.saituo.order.entity.order.Product;

//投诉信息表
public class OrderComplaint {
	
	//序号
	private Long  id;
	// 产品订单编码
	private Long registerNumber;
	// 客户订单编码
	private Long userOrderId;
	// 受理地市
	private String areaId;
	// 客户编码
	private String userId;
	//投诉类型：1.产品质量问题、2.产品剂量问题、3.产品包装问题、4.产品保质期问题
	private String complaintType;
	//投诉内容
	private String  complaintNote;
	//状态：0.未完成、1.处理中、2.已完成、-1.已取消
	private String statusCd;
	//投诉时间
	private String  complaintDate;
   //	处理人编码
	private String handlePersonId;
   //处理意见
	private String handleNote;
   //处理结果：1.协商解决、2.换货、3.退货、4.投诉取消
	private String handleResult;
	//处理时间
	private String handleDate;
	
		// 产品编码
		private Long productId;
		// 目录价
		private Double orderFee;
		// 订购数量
		private Long orderNum;
		// 审批状态:0未处理 1.待审批;2.已驳回;3.审批通过;
		private String auditCd;
		// 状态:0未处理;1.已出单；2.已收货；3.已结款；-1.已取消
		private String productStatusCd;
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
		public String getProductStatusCd() {
			return productStatusCd;
		}
		public void setProductStatusCd(String productStatusCd) {
			this.productStatusCd = productStatusCd;
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
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		// 状态:0.初始 1.未开具发票 2.已开具发票3.已送发票
		private String invoiceStatus;
		// 创建时间
		private String acceptDate;
		// 最后修改时间
		private String changeDate;
		// 产品项
		private Product product;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getHandlePersonId() {
		return handlePersonId;
	}
	public void setHandlePersonId(String handlePersonId) {
		this.handlePersonId = handlePersonId;
	}
	public String getHandleNote() {
		return handleNote;
	}
	public void setHandleNote(String handleNote) {
		this.handleNote = handleNote;
	}
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public String getComplaintNote() {
		return complaintNote;
	}
	public void setComplaintNote(String complaintNote) {
		this.complaintNote = complaintNote;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
