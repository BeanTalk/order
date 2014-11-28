package com.saituo.order.web.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.entity.user.Address;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.web.form.AddressForm;

@Controller
@RequiresAuthentication
@RequestMapping("order/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	/**
	 * 获取使用地址列表 根据userId
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Address> getAddressList() {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		return addressService.queryList(userId);
	}

	/**
	 * 地址保存
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute AddressForm form, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			Map<String, String> mapData = Maps.newHashMap();
			mapData.put("receiptAddress", form.getReceiptAddress());
			mapData.put("receiptPerson", form.getReceiptPerson());
			mapData.put("contactPhone", form.getContactPhone());
			mapData.put("invoiceCaput", form.getInvoiceCaput());
			mapData.put("invoiceRequire", form.getInvoiceRequire());
			mapData.put("otherRequire", form.getOtherRequire());
			model.addAttribute("entity", mapData);
			return "order/address/edit";
		}

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		Address address = new Address();
		try {
			BeanUtils.copyProperties(address, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		address.setUserId(userId);
		
		if(form.getAddressId() == null){
			addressService.insert(address);
		}else{
			addressService.update(address);
		}
		return "redirect:/order/address/list";
	}

	/**
	 * 地址视图与修改
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long addressId, Model model) {
		model.addAttribute("entity", Maps.newHashMap());
		if (addressId != null) {
			model.addAttribute("entity", addressService.query(addressId));
		}
		return "order/address/edit";
	}

	/**
	 * 地址删除根据addressId
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam(required = true) Long addressId, Model model) {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		addressService.delete(addressId, userId);
		return "redirect:/order/address/list";
	}

}
