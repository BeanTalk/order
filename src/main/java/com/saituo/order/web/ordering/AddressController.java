package com.saituo.order.web.ordering;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.entity.user.Address;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.web.form.AddressForm;

@Controller("account/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Address> getAddressList() {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		Address address = new Address();
		address.setUserId(userId);
		return addressService.queryList(address);
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String save(@Valid @ModelAttribute AddressForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "account/address/edit";
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
		addressService.insert(address);
		model.addAttribute("success", true);
		return "account/address/list";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Address address = new Address();
			address.setAddressId(id);
			model.addAttribute("entity", addressService.query(address));
		}
		return "account/address/edit";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam(required = true) Long id, Model model) {
		if (id != null) {
			Address address = new Address();
			address.setAddressId(id);
			addressService.delete(address);
		}
		return "account/address/list";
	}
}
