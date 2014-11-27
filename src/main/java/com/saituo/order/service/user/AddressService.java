package com.saituo.order.service.user;

import java.util.List;

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
	@Autowired
	private AddressDao addressDao;
	/**
	 * 新建地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
	public void insert(Address address) {
		addressDao.insert(address);
	}
	/**
	 * 更新地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
	public void update(Address address) {
		addressDao.update(address);
	}
	/**
	 * 删除地址信息
	 * 
	 * @param Address
	 *            地址实体
	 */
	public void delete(Long addressId) {
		Address address = new Address();
		address.setAddressId(addressId);
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
	public Address query(Long addressId) {
		Address address = new Address();
		address.setAddressId(addressId);
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
	public List<Address> queryList(String userId) {
		Address address = new Address();
		address.setUserId(userId);
		return addressDao.queryList(address);
	}

}
