package com.saituo.order.web.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.PortraitSize;
import com.saituo.order.commons.enumeration.entity.State;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.service.ServiceException;
import com.saituo.order.service.account.AccountService;

/**
 * 用户控制器
 */
@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order/user")
public class UserController {

	@Autowired
	private AccountService accountService;

	/**
	 * 用户列表
	 * 
	 * @param pageRequest
	 *            分页请求实体
	 * @param filter
	 *            查询条件
	 * @param model
	 *            spring mvc 的 Model 接口，主要是将 http servlet request 的属性返回到页面中
	 * 
	 * @return 响应页面:WEB-INF/page/account/user/list.html
	 */
	@RequestMapping("list")
	public Page<Map<String, Object>> list(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		model.addAttribute("states", VariableUtils.getVariables(State.class, State.DELETE.getValue()));
		return accountService.findUsers(pageRequest, filter);
	}

	/**
	 * 保存用户
	 * 
	 * @param entity
	 *            用户实体 Map
	 * @param groupIds
	 *            关联的组主键 ID 集合
	 * @param redirectAttributes
	 *            spring mvc 重定向属性
	 * 
	 * @return 响应页面:WEB-INF/page/account/user/list.html
	 */
	@RequestMapping("save")
	public String insert(@RequestParam Map<String, Object> entity, RedirectAttributes redirectAttributes) {

		accountService.saveUser(entity);
		redirectAttributes.addFlashAttribute("success", "保存成功");

		return "redirect:/account/user/list";
	}

	/**
	 * 判断登录帐号是否唯一
	 * 
	 * @param username
	 *            用户登录帐号
	 * 
	 * @return true 表示唯一，否则 false
	 */
	@ResponseBody
	@RequestMapping("is-username-unique")
	public boolean isUsernameUnique(String username) {
		return accountService.isUsernameUnique(username);
	}

	/**
	 * 编辑用户，响应页面 WEB-INF/page/account/user/edit.html
	 * 
	 * @param id
	 *            主键id
	 * @param model
	 *            spring mvc 的 Model 接口，主要是将 http servlet request 的属性返回到页面中
	 * 
	 */
	@RequestMapping("edit")
	public void edit(@RequestParam(required = false) String id, Model model) {

		model.addAttribute("states", VariableUtils.getVariables(State.class, State.DELETE.getValue()));
		model.addAttribute("groups", accountService.findGroups(new HashMap<String, Object>()));

		model.addAttribute("entity", Maps.newHashMap());
		model.addAttribute("hasGroups", Lists.newArrayList());

		if (id != null) {
			model.addAttribute("entity", accountService.getUser(id));
		}

	}

	/**
	 * 获取当前用户头像
	 * 
	 * @throws java.io.IOException
	 * 
	 * @return 用户头像的 byte 数组
	 * 
	 */
	@RequestMapping("get-portrait")
	public ResponseEntity<byte[]> getCurrentUserPortrait(@RequestParam(required = false) String name)
			throws IOException {
		String temp = name;
		if (StringUtils.isEmpty(temp)) {
			temp = PortraitSize.MIDDLE.getName();
		}

		PortraitSize size = PortraitSize.getPortraitSize(temp);
		return new ResponseEntity<byte[]>(accountService.getCurrentUserPortrait(size), HttpStatus.OK);
	}

	/**
	 * 修改用户头像
	 * 
	 * @param request
	 *            HttpServletRequest http servlet request 对象，用于获取 FaustCplus
	 *            上穿上来的头像
	 * 
	 * @throws IOException
	 * 
	 * @return 上传成功返回 json: {status:"success"}，否则抛出异常。
	 */
	@ResponseBody
	@RequestMapping("update-portrait")
	public Map<String, Object> updatePortrait(HttpServletRequest request) throws IOException {
		Map<String, Object> user = SessionVariable.getCurrentSessionVariable().getUser();
		accountService.updateUserPortrait(user, request.getInputStream());

		Map<String, Object> result = Maps.newHashMap();
		// 设置状态值，让 FaustCplus 再次触发 jsfunc 的 js 函数
		result.put("state", Boolean.TRUE);
		result.put("message", "修改头像成功");

		return result;
	}

	/**
	 * 当前用户修改密码
	 * 
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * 
	 * @return 响应页面:系统首页
	 */
	@ResponseBody
	@RequestMapping("update-password")
	public String updatePassword(String oldPassword, String newPassword) {

		Map<String, Object> user = SessionVariable.getCurrentSessionVariable().getUser();
		String message;
		try {
			accountService.updateUserPassword(user, oldPassword, newPassword);
			message = "修改密码成功";
		} catch (ServiceException e) {
			message = e.getMessage();
		}
		return message;

	}

	/**
	 * 修改用户信息
	 * 
	 * @param entity
	 *            用户实体 Map
	 * 
	 * @throws IOException
	 * 
	 * @return 修改后的用户实体 json
	 */
	@ResponseBody
	@RequestMapping("update-profile")
	public Map<String, Object> updateProfile(@RequestParam Map<String, Object> entity) throws IOException {
		Map<String, Object> user = SessionVariable.getCurrentSessionVariable().getUser();
		user.putAll(entity);
		accountService.saveUser(user);
		SessionVariable.getCurrentSessionVariable().setUser(user);
		entity.put("message", "修改个人信息成功过");
		return entity;
	}
}
