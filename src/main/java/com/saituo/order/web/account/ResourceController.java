package com.saituo.order.web.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.ResourceType;
import com.saituo.order.service.account.AccountService;

/**
 * 资源控制器
 */
@Controller
@RequestMapping("/account/resource")
public class ResourceController {

	@Autowired
	private AccountService accountService;

	/**
	 * 资源列表
	 * 
	 * @return 响应页面:WEB-INF/page/account/resource/list.html
	 */
	@RequestMapping("list")
	public List<Map<String, Object>> list() {
		return accountService.mergeResources(accountService.getMenus());
	}

	/**
	 * 保存资源
	 * 
	 * @param entity
	 *            资源实体 Map
	 * @param redirectAttributes
	 *            spring mvc 重定向属性
	 * 
	 * @return 响应页面:WEB-INF/page/account/resource/list.html
	 */
	@RequestMapping("save")
	public String save(@RequestParam Map<String, Object> entity, RedirectAttributes redirectAttributes) {

		accountService.saveResource(entity);
		redirectAttributes.addFlashAttribute("success", "保存成功");

		return "redirect:/account/resource/list";
	}

	/**
	 * 判断名称帐号是否唯一
	 * 
	 * @param name
	 *            名称
	 * 
	 * @return true 表示唯一，否则 false
	 */
	@ResponseBody
	@RequestMapping("is-name-unique")
	public boolean isNameUnique(String name) {
		return !accountService.resourceNameExists(name);
	}

	/**
	 * 删除资源
	 * 
	 * @param ids
	 *            资源主键 ID 集合
	 * @param redirectAttributes
	 *            spring mvc 重定向属性
	 * 
	 * @return 响应页面:WEB-INF/page/account/resource/list.html
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam List<Long> ids, RedirectAttributes redirectAttributes) {
		accountService.deleteResources(ids);
		redirectAttributes.addFlashAttribute("success", "删除" + ids.size() + "条信息成功");
		return "redirect:/account/resource/list";
	}

	/**
	 * 编辑资源，响应页面:WEB-INF/page/account/resource/edit.html
	 * 
	 * @param id
	 *            主键id
	 * @param model
	 *            spring mvc 的 Model 接口，主要是将 http servlet request 的属性返回到页面中
	 * 
	 */
	@RequestMapping("edit")
	public void edit(@RequestParam(required = false) Long id, Model model) {

		model.addAttribute("resourceTypes", VariableUtils.getVariables(ResourceType.class));

		if (id != null) {
			model.addAttribute("entity", accountService.getMenu(id));
			model.addAttribute("resources", accountService.getMenus(id));
		} else {
			model.addAttribute("entity", new HashMap<String, Object>());
			model.addAttribute("resources", accountService.getMenus());
		}

	}
}
