package com.saituo.order.dao.user;

import org.apache.ibatis.annotations.Select;

import com.saituo.order.entity.user.CustomerBean;

public interface CustomerBeanDao {

	@Select("select beans_num from st_cust_beans where cust_id = #{userId}")
	public int getBean(CustomerBean customerBean);

	public int insert(CustomerBean customerBean);

}
