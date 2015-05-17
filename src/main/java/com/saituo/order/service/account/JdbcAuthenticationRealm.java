package com.saituo.order.service.account;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.ResourceType;
import com.saituo.order.commons.enumeration.entity.UserCatagory;

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
		Map<String, Object> currentUser = accountService.getUserByUsernameOrEmail(username);

		if (MapUtils.isEmpty(currentUser)) {
			throw new IncorrectCredentialsException();
		}

		if (currentUser == null) {
			return null;
		}

		Integer userId = VariableUtils.typeCast(currentUser.get("id"));
		List<Map<String, Object>> authorizationInfo = accountService.getUserMenus(userId);
		List<Map<String, Object>> menuList = accountService.mergeMenus(authorizationInfo, ResourceType.SECURITY);

		SessionVariable model = new SessionVariable(currentUser);
		model.setMenusList(menuList);
		model.setAuthorizationInfo(authorizationInfo);

		List<String> roleList = accountService.getUserRoles(userId);
		model.setRoleList(roleList);

		int userCatagory = VariableUtils.typeCast(currentUser.get("userCatagory"), Integer.class).intValue();
		if (userCatagory == UserCatagory.INTERNAL.getValue().intValue()) {
			model.setIsInternalUser(true);
		} else {
			model.setIsInternalUser(false);
		}
		Map<String, Object> groupAndareaData = accountService.getUserGroup(userId);
		if (groupAndareaData.get("areaId") != null) {
			model.setAreaId((Integer) groupAndareaData.get("areaId"));
		}
		if (groupAndareaData.get("groupId") != null) {
			model.setGroupId((Integer) groupAndareaData.get("groupId"));
		}

		return new SimpleAuthenticationInfo(model, currentUser.get("password"), getName());
	}
}
