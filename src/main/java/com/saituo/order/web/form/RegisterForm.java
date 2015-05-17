package com.saituo.order.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {

	@NotEmpty(message = "邮件不能为空")
	@Email
	private String email;

	@Size(max = 64, message = "最大长度64个字符")
	private String name;

	@NotEmpty(message = "登录名不能为空")
	@Size(max = 64, message = "最大长度64个字符")
	private String loginname_reg;

	@NotEmpty(message = "密码不能为空")
	@Size(min = 6, max = 16, message = "最小长度8个字符, 最大长度16个字符")
	private String password_reg;

	@Size(min = 6, max = 16, message = "最小长度8个字符, 最大长度16个字符")
	@NotEmpty(message = "密码不能为空")
	private String confirm_password_reg;

	@Size(max = 11, message = "最大长度11个字符")
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginname_reg() {
		return loginname_reg;
	}

	public void setLoginname_reg(String loginname_reg) {
		this.loginname_reg = loginname_reg;
	}

	public String getPassword_reg() {
		return password_reg;
	}

	public void setPassword_reg(String password_reg) {
		this.password_reg = password_reg;
	}

	public String getConfirm_password_reg() {
		return confirm_password_reg;
	}

	public void setConfirm_password_reg(String confirm_password_reg) {
		this.confirm_password_reg = confirm_password_reg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
