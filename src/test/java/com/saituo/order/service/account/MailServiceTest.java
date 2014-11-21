package com.saituo.order.service.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.commons.mailer.JavaMailService;
import com.saituo.order.service.ServiceTestCaseSupport;

import freemarker.template.TemplateException;

public class MailServiceTest extends ServiceTestCaseSupport {

	@Autowired
	private JavaMailService javaMailService;

	@Test
	public void sendEmail() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("md5", "tttttt");
		try {
			String value = javaMailService.getTemplateString("operating-mail-template.ftl", model);

			System.out.println("value:" + value);
			// javaMailService.sendByTemplate("dywhappy@163.com", "wunyang",
			// "modified password for test",
			// "operating-mail-template.ftl", null, model);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
