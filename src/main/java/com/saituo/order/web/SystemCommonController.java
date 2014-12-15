package com.saituo.order.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.mailer.RetryPasswordMailService;
import com.saituo.order.commons.utils.CaptchaUtils;
import com.saituo.order.commons.utils.Encodes;
import com.saituo.order.service.account.AccountService;
import com.saituo.order.service.account.CaptchaAuthenticationFilter;
import com.saituo.order.web.form.RetryPasswordForm;

/**
 * 系统公共Controller
 */
@Controller
public class SystemCommonController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RetryPasswordMailService retryPasswordMailService;

	/**
	 * 登录方法签名
	 *
	 * @return 响应页面为:WEB-INF/page/login.html
	 */
	@RequestMapping("login")
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.isAuthenticated()) {
			return "redirect:/logout";
		}
		return "login";
	}

	/**
	 * 跳转到密码找回页面
	 * 
	 * @param filter
	 * @param model
	 * @return
	 */
	@RequestMapping("forgetpassword")
	public String forgetpasswd() {
		return "/account/user/forgetpassword";
	}

	/**
	 * 密码找回 - 发送邮件
	 * <p>
	 * UUID与登录名拼串后生成MD5加密字符串,并与loginName的base64加密，发送到用户的邮件
	 * <p>
	 * 通过Gmail的邮件进行发送
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sendpassword", method = RequestMethod.POST)
	public String retrypasswd(@Valid @ModelAttribute RetryPasswordForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "/account/user/forgetpassword";
		}

		String loginname = form.getLoginname();
		String email = form.getEmail();

		// 通过loginname与email查询不到用户时，返回
		if (!accountService.isExistsUsers(loginname, email)) {
			model.addAttribute("errorinfo", "请使用注册的邮箱!");
			return "/account/user/forgetpassword";
		} else {
			// 发送邮件服务
			retryPasswordMailService.sendEmailForRetryPassword(loginname, email);
		}

		model.addAttribute("successinfo", "已经发送到您的 " + email + " 邮件中，请登录邮件按照提示进行操作!");
		return "success";
	}

	@RequestMapping(value = "requestresetpassword", method = RequestMethod.GET)
	public String requestResetPassword(@RequestParam Map<String, String> entity, Model model) {

		String pin = entity.get("pin");
		String ul = entity.get("ul");

		if (StringUtils.isEmpty(pin) || StringUtils.isEmpty(ul)) {
			model.addAttribute("errorinfo", "您请求的服务无效");
			return "error";
		}

		String loginname = new String(Encodes.decodeBase64(ul));
		if (!accountService.isValidPinByLoginName(loginname, pin)) {
			model.addAttribute("errorinfo", "您请求的服务非法");
			return "error";
		}

		model.addAttribute("loginname", loginname);
		return "/account/user/resetpassword";
	}

	/**
	 * 重设密码服务
	 * 
	 * @param entity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "resetpassword", method = RequestMethod.POST)
	public String resetpassword(@RequestParam Map<String, String> entity, Model model) {

		String loginname = entity.get("loginname");
		String password = entity.get("password");

		accountService.resetPassword(loginname, password);
		return "success";
	}

	/**
	 * 生成验证码方法签名
	 *
	 * @throws IOException
	 *
	 * @return 验证码图片的 byte 数组
	 */
	@RequestMapping("get-captcha")
	public ResponseEntity<byte[]> getCaptcha(HttpSession session) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = CaptchaUtils.getCaptcha(70, 32, 4, outputStream).toLowerCase();

		session.setAttribute(CaptchaAuthenticationFilter.DEFAULT_CAPTCHA_PARAM, captcha);
		byte[] bs = outputStream.toByteArray();
		outputStream.close();
		return new ResponseEntity<byte[]>(bs, headers, HttpStatus.OK);
	}

	/**
	 * 根据用户的权限与角色跳转到不同的页面
	 * 
	 * @param entity
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void redirectToPageByRole(Model model) {
		Integer userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);
		Integer areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), Integer.class);
		Integer groupId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getGroupId(),
				Integer.class);
		System.out.println("userId:" + userId);
	}

}
