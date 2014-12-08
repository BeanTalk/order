package com.saituo.order.commons.utils;

import com.saituo.order.service.variable.SystemVariableService;

/**
 * Cache工具类
 */
public class CacheUtils {

	private static SystemVariableService systemVariableService = (SystemVariableService) SpringContextHolder
			.getBean("systemVariableService");

	/**
	 * AreaIdAndName
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static String getAreaIdAndNameData(String areaId) {
		return systemVariableService.getAreaIdAndNameData(areaId);
	}

	/**
	 * 通过areaId 和 userId 获取userName
	 * 
	 * @param areaId
	 * @param userId
	 * @return
	 */
	public static String getUserNameByAreaIdData(String areaId, String userId) {
		return systemVariableService.getUserByAreaIdData(areaId, userId);
	}

	/**
	 * 通过 officeId 和 userId 获取userName
	 * 
	 * @param officeId
	 * @param userId
	 * @return
	 */
	public static String getUserNameByOfficeIdData(String officeId, String userId) {
		return systemVariableService.getUserByOfficeIdData(officeId, userId);
	}

}
