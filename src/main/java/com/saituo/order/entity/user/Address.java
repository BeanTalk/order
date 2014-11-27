package com.saituo.order.entity.user;

public class Address {
	// 地址编码
	private Long addressId;
	// 客户编码
	private String userId;
	// 收货地址
	private String receiptAddress;
	// 收货人
	private String receiptPerson;
	// 联系电话
	private String contactPhone;
	// 发票抬头
	private String invoiceCaput;
	// 发票要求
	private String invoiceRequire;
	// 发票要求
	private String otherRequire;

	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getReceiptPerson() {
		return receiptPerson;
	}
	public void setReceiptPerson(String receiptPerson) {
		this.receiptPerson = receiptPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getInvoiceCaput() {
		return invoiceCaput;
	}
	public void setInvoiceCaput(String invoiceCaput) {
		this.invoiceCaput = invoiceCaput;
	}
	public String getInvoiceRequire() {
		return invoiceRequire;
	}
	public void setInvoiceRequire(String invoiceRequire) {
		this.invoiceRequire = invoiceRequire;
	}
	public String getOtherRequire() {
		return otherRequire;
	}
	public void setOtherRequire(String otherRequire) {
		this.otherRequire = otherRequire;
	}

}
