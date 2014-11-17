package com.saituo.order.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saituo.order.entity.user.CustomerBean;

public interface CustomerBeanDao {

	@Select("select beans_num from st_cust_beans where cust_id = #{userId}")
	public int getBean(CustomerBean customerBean);

	public int insert(CustomerBean customerBean);

	public List<CustomerBean> getCustomerBean(String userId);

	// 返回更新行数
	public int modifiedCustomerBean(CustomerBean customerBean);

	// 返回更新行数
	public int removedCustomerBean(String usedId);

}
