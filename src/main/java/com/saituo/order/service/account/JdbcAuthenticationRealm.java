package com.saituo.order.service.account;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.utils.Encodes;

/**
 * 认证（登录）类，用于 apache shiro 在执行认证（登录）时，通过该类对登录信息认证（登录）是否通过。
 */
public class JdbcAuthenticationRealm extends AuthorizationRealm {

	@Autowired
	private AccountService accountService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		Map<String, Object> user = accountService.getUserByUsernameOrEmail(username);

		if (MapUtils.isEmpty(user)) {
			throw new IncorrectCredentialsException();
		}

		if (user == null) {
			return null;
		}

		SessionVariable model = new SessionVariable(user);
		
		byte[] salt = Encodes.decodeHex(String.valueOf(user.get("password")).substring(0, 16));
		return new SimpleAuthenticationInfo(model, String.valueOf(user.get("password")).substring(16),
				ByteSource.Util.bytes(salt), getName());
	}

}