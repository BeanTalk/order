package com.saituo.order.web.user;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.dao.user.UserGroupPointHisDao;
import com.saituo.order.entity.gift.Gift;
import com.saituo.order.entity.user.UserBeans;
import com.saituo.order.entity.user.UserGroupPointAccount;
import com.saituo.order.entity.user.UserGroupPointHis;
import com.saituo.order.entity.user.UserPeasHis;
import com.saituo.order.service.gift.GiftService;
import com.saituo.order.service.user.UserOrderService;
import com.saituo.order.service.variable.SystemVariableService;

@Controller
@RequiresAuthentication
@RequestMapping("order/mine")
public class PointCardController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private GiftService giftService;

	@Autowired
	private UserGroupPointHisDao userGroupPointHisDao;

	@RequiresPermissions("perms[order:mine:bean]")
	@RequestMapping(value = "beanlist", method = RequestMethod.GET)
	public void getUserDoudouList(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		filter.putAll(pageRequest.getMap());
		int count = giftService.getUserPeasHisCount(filter);
		List<UserPeasHis> peasList = giftService.getUserPeasHisList(filter);
		Page<UserPeasHis> page = new Page<UserPeasHis>(pageRequest, peasList, count);

		if (giftService.getUserBeans() != null) {
			model.addAttribute("beanNums", giftService.getUserBeans().getBeansNum());
		}
		model.addAttribute("page", page);
	}

	@RequiresPermissions("perms[order:mine:bean]")
	@RequestMapping(value = "giftList", method = RequestMethod.GET)
	public @ResponseBody Page<Gift> getGiftList(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		UserBeans userBeans = giftService.getUserBeans();
		Long beansNum = 0L;
		if (userBeans != null) {
			beansNum = userBeans.getBeansNum();
		}

		filter.put("beansNum", beansNum);
		filter.putAll(pageRequest.getMap());
		int count = giftService.getGiftCount(filter);
		List<Gift> giftList = giftService.getGiftList(filter);
		Page<Gift> page = new Page<Gift>(pageRequest, giftList, count);
		return page;
	}

	@RequiresPermissions("perms[order:mine:bean]")
	@RequestMapping(value = "exchange/gift", method = RequestMethod.POST)
	public String getExchangeGiftList(@RequestParam Map<String, Object> filter, Model model) {

		giftService.doExchange(filter);
		return "redirect:/order/mine/giftList";
	}

	@RequiresPermissions("perms[order:mine:point]")
	@RequestMapping(value = "pointlist", method = RequestMethod.GET)
	public void getPointList(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		filter.putAll(pageRequest.getMap());

		String groupId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getGroupId(), String.class);
		UserGroupPointHis userGroupPointHis = new UserGroupPointHis();
		userGroupPointHis.setGroupId(groupId);

		int count = userGroupPointHisDao.count(userGroupPointHis);
		UserGroupPointAccount userGroupPointAccount = userOrderService.queryGroupPointAccount(Integer.valueOf(groupId));
		List<UserGroupPointHis> userGroupPointHisList = userGroupPointHisDao.queryList(userGroupPointHis, filter);
		Page<UserGroupPointHis> page = new Page<UserGroupPointHis>(pageRequest, userGroupPointHisList, count);
		model.addAttribute("page", page);
		model.addAttribute("userGroupPointAccount", userGroupPointAccount);
	}

}
