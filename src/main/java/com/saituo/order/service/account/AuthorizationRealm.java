package com.saituo.order.service.account;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.ResourceType;
import com.saituo.order.commons.enumeration.entity.UserCatagory;
import com.saituo.order.service.ServiceException;

/**
 * 公用授权抽象类，用于 apache shrio 在执行授权操作时，通过该类去统一对本系统的功能进行授权。
 */
public abstract class AuthorizationRealm extends AuthorizingRealm {

	@Autowired
	private AccountService accountService;

	private List<String> defaultPermission = Lists.newArrayList();

	/**
	 * 设置默认的 permission
	 * 
	 * @param defaultPermissionString
	 *            permission 如果存在多个值，使用逗号","分割
	 */
	public void setDefaultPermissionString(String defaultPermissionString) {
		String[] perms = StringUtils.split(defaultPermissionString, ",");
		CollectionUtils.addAll(defaultPermission, perms);
	}

	/**
	 * 设置默认的 permission
	 * 
	 * @param defaultPermission
	 *            permission 集合
	 */
	public void setDefaultPermission(List<String> defaultPermission) {
		this.defaultPermission = defaultPermission;
	}

	/**
	 * 
	 * 当用户进行访问链接时的授权方法
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		SessionVariable sv = principals.oneByType(SessionVariable.class);

		if (sv == null) {
			throw new ServiceException("session 变量对象不存在");
		}

		Map<String, Object> currentUser = sv.getUser();
		String userId = VariableUtils.typeCast(currentUser.get("id"));

		// 加载用户资源信息
		List<Map<String, Object>> authorizationInfo = accountService.getUserMenus(userId);
		List<Map<String, Object>> menuList = accountService.mergeMenus(authorizationInfo, ResourceType.SECURITY);
		List<String> roleList = accountService.getUserRoles(userId);

		// 添加用户拥有的permission
		addPermissions(info, authorizationInfo);
		// 添加用户拥有的role
		info.addRoles(roleList);

		sv.setAuthorizationInfo(authorizationInfo);
		sv.setMenusList(menuList);
		sv.setRoleList(roleList);

		int userCatagory = VariableUtils.typeCast(currentUser.get("userCatagory"), Integer.class).intValue();
		if (userCatagory == UserCatagory.INTERNAL.getValue().intValue()) {
			sv.setIsInternalUser(true);
		} else {
			sv.setIsInternalUser(false);
		}

		Map<String, Object> groupAndareaData = accountService.getUserGroup(userId);
		sv.setAreaId((String) groupAndareaData.get("areaId"));
		sv.setGroupId((String) groupAndareaData.get("groupId"));

		SecurityUtils.getSubject().getSession().setAttribute(SessionVariable.DEFAULT_SESSION_KEY, sv);
		return info;
	}
	/**
	 * 通过资源集合，将集合中的 permission 字段内容解析后添加到 SimpleAuthorizationInfo 授权信息中
	 * 
	 * @param info
	 *            SimpleAuthorizationInfo
	 * @param authorizationInfo
	 *            资源集合
	 */
	private void addPermissions(SimpleAuthorizationInfo info, List<Map<String, Object>> authorizationInfo) {

		List<String> permissions = Lists.newArrayList();

		// 添加默认的permissions到permissions
		if (CollectionUtils.isNotEmpty(defaultPermission)) {
			CollectionUtils.addAll(permissions, defaultPermission.iterator());
		}

		// 解析当前用户资源中的permissions
		for (Map<String, Object> m : authorizationInfo) {
			Object permission = m.get("permission");

			if (permission == null || StringUtils.isEmpty(permission.toString())) {
				continue;
			}

			for (String permis : StringUtils.split(permission.toString(), ",")) {
				permissions.add(permis);
			}
		}
		// 将当前用户拥有的permissions设置到SimpleAuthorizationInfo中
		info.addStringPermissions(permissions);
	}
}
