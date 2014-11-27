package com.saituo.order.service.user;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> liurong-base

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.dao.user.AddressDao;
import com.saituo.order.entity.user.Address;

/**
 * 地址信息业务逻辑
 */
@Service
@Transactional
public class AddressService {
<<<<<<< HEAD

=======
>>>>>>> liurong-base
	@Autowired
	private AddressDao addressDao;
	/**
	 * 新建地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
<<<<<<< HEAD
	public void insert(Address address) {
=======
	public void insert(Map<String, Object> filter){
		//客户编码
		 String userId="123";// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"), String.class); 
		
		//收货地址
		String receiptAddress="";	
		if(filter.get("receiptAddress")!=null &&!filter.get("receiptAddress").equals("")){
			receiptAddress =(String)filter.get("receiptAddress");
		}
		//收货人
		 String receiptPerson="";
		if(filter.get("receiptPerson")!=null &&!filter.get("receiptPerson").equals("")){
				receiptPerson =(String)filter.get("receiptPerson");
			}
		//联系电话
		 String	contactPhone="";
		if(filter.get("contactPhone")!=null &&!filter.get("contactPhone").equals("")){
			contactPhone =(String)filter.get("contactPhone");
			}
		//发票抬头
		 String	invoiceCaput="";
		 if(filter.get("invoiceCaput")!=null &&!filter.get("invoiceCaput").equals("")){
			 invoiceCaput =(String)filter.get("invoiceCaput");
			}
		//发票要求
		 String	invoiceRequire="";
		 if(filter.get("invoiceRequire")!=null &&!filter.get("invoiceRequire").equals("")){
			 invoiceRequire =(String)filter.get("invoiceRequire");
			}
		 
		//发票要求
		 String otherRequire="";
		 if(filter.get("otherRequire")!=null &&!filter.get("otherRequire").equals("")){
			 otherRequire =(String)filter.get("otherRequire");
			}
		
		Address address =new Address();
		address.setContactPhone(contactPhone);
		address.setInvoiceCaput(invoiceCaput);
		address.setInvoiceRequire(invoiceRequire);
		address.setOtherRequire(otherRequire);
		address.setReceiptAddress(receiptAddress);
		address.setReceiptPerson(receiptPerson);
		address.setUserId(userId);
		
>>>>>>> liurong-base
		addressDao.insert(address);
	}
	/**
	 * 更新地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
<<<<<<< HEAD
	public void update(Address address) {
=======
	public void update(Map<String, Object> filter){
		//地址编码
		Long addressId=0l;
		 if(filter.get("addressId")!=null &&!filter.get("addressId").equals("")){
			 addressId =(Long)filter.get("addressId");
			}
		//收货地址
		String receiptAddress="";	
		if(filter.get("receiptAddress")!=null &&!filter.get("receiptAddress").equals("")){
			receiptAddress =(String)filter.get("receiptAddress");
		}
		//收货人
		 String receiptPerson="";
		if(filter.get("receiptPerson")!=null &&!filter.get("receiptPerson").equals("")){
				receiptPerson =(String)filter.get("receiptPerson");
			}
		//联系电话
		 String	contactPhone="";
		if(filter.get("contactPhone")!=null &&!filter.get("contactPhone").equals("")){
			contactPhone =(String)filter.get("contactPhone");
			}
		//发票抬头
		 String	invoiceCaput="";
		 if(filter.get("invoiceCaput")!=null &&!filter.get("invoiceCaput").equals("")){
			 invoiceCaput =(String)filter.get("invoiceCaput");
			}
		//发票要求
		 String	invoiceRequire="";
		 if(filter.get("invoiceRequire")!=null &&!filter.get("invoiceRequire").equals("")){
			 invoiceRequire =(String)filter.get("invoiceRequire");
			}
		 
		//发票要求
		 String otherRequire="";
		 if(filter.get("otherRequire")!=null &&!filter.get("otherRequire").equals("")){
			 otherRequire =(String)filter.get("otherRequire");
			}
		
		Address address =new Address();
		address.setContactPhone(contactPhone);
		address.setInvoiceCaput(invoiceCaput);
		address.setInvoiceRequire(invoiceRequire);
		address.setOtherRequire(otherRequire);
		address.setReceiptAddress(receiptAddress);
		address.setReceiptPerson(receiptPerson);
		address.setAddressId(addressId);
>>>>>>> liurong-base
		addressDao.update(address);
	}
	/**
	 * 删除地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
<<<<<<< HEAD
	public void delete(Address address) {
		addressDao.delete(address);
	}
	/**
	 * <p>
	 * Description: 查询数据:根据地址ID查询
	 * </p>
	 * 
	 * @Method: query
	 * @param Address
	 * @return Address
	 * @throws
	 */
	public Address query(Address address) {
		return addressDao.query(address);
	}
	/**
	 * <p>
	 * Description: 查询数据:根据客户ID查询地址信息集合
	 * </p>
	 * 
	 * @Method: query
	 * @param Address
	 * @return List<Address>
	 * @throws
	 */
	public List<Address> queryList(Address address) {
		return addressDao.queryList(address);
	}

=======
	public void delete(Map<String, Object> filter){
		//地址编码
		Long addressId=0l;
		if(filter.get("addressId")!=null &&!filter.get("addressId").equals("")){
			addressId =(Long)filter.get("addressId");
		}
		Address address =new Address();
		address.setAddressId(addressId);
		addressDao.delete(address);
	}
	/**
	 * <p>Description: 查询数据:根据地址ID查询</p>
	 * @Method: query
	 * @param Address
	 * @return Address 
	 * @throws 
	*/
	public Address query (Map<String, Object> filter){
		//地址编码
		Long addressId=0l;
		if(filter.get("addressId")!=null &&!filter.get("addressId").equals("")){
			addressId =(Long)filter.get("addressId");
		}
		Address address =new Address();
		address.setAddressId(addressId);
		return addressDao.query(address);
	}
	/**
	 * <p>Description: 查询数据:根据客户ID查询地址信息集合</p>
	 * @Method: query
	 * @param Address
	 * @return List<Address> 
	 * @throws 
	*/
	public List<Address> queryList (Map<String, Object> filter){
		//客户编码
		String userId="";
		if(filter.get("userId")!=null &&!filter.get("userId").equals("")){
			userId =(String)filter.get("userId");
		}
		Address address =new Address();
		address.setUserId(userId);
		return addressDao.queryList(address);
	}
	
>>>>>>> liurong-base
}
