package com.saituo.order.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.user.UserRecord;

/**
 * 客户记录编码表维护
 */
public interface UserRecordDao {
	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param UserOrder
	 * @return void
	 * @throws
	 */
	public void insert(UserRecord userRecord);
	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return UserRecord
	 * @throws
	 */
	public int count(@Param(value = "userRecord") UserRecord userRecord,
			@Param(value = "filter") Map<String, Object> filter);
	
	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return  List<UserRecord> 
	 * @throws
	 */
	public List<UserRecord> queryList(@Param(value = "userRecord") UserRecord userRecord,
			@Param(value = "filter") Map<String, Object> filter);
}
