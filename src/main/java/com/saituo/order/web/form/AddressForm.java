package com.saituo.order.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AddressForm {

	@NotEmpty(message = "区域不能为空")
	// 所在地区
	private String areaName;

	@NotEmpty(message = "收货地址不能为空")
	@Size(max = 200, min = 5)
	// 收货地址
	private String receiptAddress;

	@NotEmpty(message = "收货人不能为空")
	@Size(max = 64)
	// 收货人
	private String receiptPerson;

	@NotEmpty(message = "联系电话不能为空")
	@Size(min = 8, max = 16)
	// 联系电话
	private String contactPhone;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

}
