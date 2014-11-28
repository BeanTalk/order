package com.saituo.order.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AddressForm {

	private Long addressId;

	@NotEmpty(message = "收货地址不能为空")
	@Size(max = 200, min = 5, message = "最小长度5个字符, 最大长度200个字符")
	// 收货地址
	private String receiptAddress;

	@NotEmpty(message = "收货人不能为空")
	@Size(max = 64, message = "最大长度64个字符")
	// 收货人
	private String receiptPerson;

	@NotEmpty(message = "联系电话不能为空")
	@Size(min = 8, max = 16, message = "最小长度8个字符, 最大长度16个字符")
	// 联系电话
	private String contactPhone;

	@Size(max = 512, message = "最大长度512个字符")
	private String invoiceCaput;

	@Size(max = 512, message = "最大长度512个字符")
	private String invoiceRequire;

	@Size(max = 512, message = "最大长度512个字符")
	private String otherRequire;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
