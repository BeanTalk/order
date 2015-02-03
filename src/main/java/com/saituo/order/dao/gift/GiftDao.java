package com.saituo.order.dao.gift;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.gift.Gift;

public interface GiftDao {

	
	/**
	 * <p>
	 * Description:分页查询数据
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return UserRecord
	 * @throws
	 */
	public int count(@Param(value = "gift")Gift gift,
			@Param(value = "filter") Map<String, Object> filter);
	
	/**
	 * <p>
	 * Description:查询数据
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return  List<UserRecord> 
	 * @throws
	 */
	public List<Gift> queryList(@Param(value = "gift") Gift gift,
			@Param(value = "filter") Map<String, Object> filter);
	
	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param Gift
	 * @return void 
	 * @throws 
	*/
	public void update (Gift gift);
}
