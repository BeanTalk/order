package com.saituo.order.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RetryPasswordForm {

	@NotEmpty(message = "登录名不能为空")
	private String loginname;

	@Email(message = "输入Email的格式不正确")
	private String email;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "RetryPasswordForm [loginName=" + loginname + ", email=" + email + "]";
	}

}
