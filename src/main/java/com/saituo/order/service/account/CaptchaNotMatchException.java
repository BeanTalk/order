package com.saituo.order.service.account;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码不匹配异常，用在
 * {@link CaptchaAuthenticationFilter#executeLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse)}
 * 里
 */
public class CaptchaNotMatchException extends AuthenticationException {

	private static final long serialVersionUID = -5793013000383800053L;

	/**
	 * 验证码不匹配异常，在登录时，执行校验验证码不匹配后抛出
	 */
	public CaptchaNotMatchException() {
		super();
	}

	/**
	 * 验证码不匹配异常，在登录时，执行校验验证码不匹配后抛出
	 *
	 * @param message
	 *            异常信息
	 */
	public CaptchaNotMatchException(String message) {
		super(message);
	}

	/**
	 * 验证码不匹配异常，在登录时，执行校验验证码不匹配后抛出
	 *
	 * @param cause
	 *            异常类
	 */
	public CaptchaNotMatchException(Throwable cause) {
		super(cause);
	}

	/**
	 * 验证码不匹配异常，在登录时，执行校验验证码不匹配后抛出
	 *
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常类
	 */
	public CaptchaNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
