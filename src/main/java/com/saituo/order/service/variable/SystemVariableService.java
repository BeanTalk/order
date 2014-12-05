package com.saituo.order.service.variable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.saituo.order.dao.account.AreaDao;
import com.saituo.order.dao.account.GroupDao;
import com.saituo.order.dao.account.UserDao;

/**
 * 系统静态变量Id与名称对应关系
 *
 * @author maurice
 */
public class SystemVariableService {

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private UserDao userDao;

	private Map<String, String> areaIdAndNameMap = Maps.newHashMap();
	private Map<String, Map<String, String>> userIdAndNameByAreaMap = Maps.newHashMap();
	private Map<String, Map<String, String>> groupIdAndNameMap = Maps.newHashMap();
	private Map<String, Map<String, String>> userIdAndNameByOfficeMap = Maps.newHashMap();

	public void init() {
		areaIdAndNameMap = getAreaData();
		groupIdAndNameMap = getGroupData();
		userIdAndNameByOfficeMap = getUserDataByOfficeId();
		userIdAndNameByAreaMap = getAllUserData();
	}
	/**
	 * 通过AreaId，获取AreaName
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, String> getAreaIdAndNameCache() {
		return areaIdAndNameMap;
	}

	/**
	 * 通过AreaId 获取所在本地市的所有用户信息
	 * 
	 * @param areaId
	 * @return
	 */
	public Map<String, String> getAllofUserIdAndNameByCache(String areaId) {
		return userIdAndNameByAreaMap.get(areaId);
	}

	/**
	 * 通过所属地市获取组信息
	 * 
	 * @param areaId
	 * @return
	 */
	public Map<String, String> getGroupIdAndNameCache(String areaId) {
		return groupIdAndNameMap.get(areaId);
	}

	/**
	 * 获取该组织下的所有的人员
	 * 
	 * @param officeId
	 * @return
	 */
	public Map<String, String> getUserIdAndNameCache(String officeId) {
		return userIdAndNameByOfficeMap.get(officeId);
	}

	private Map<String, String> getAreaData() {
		List<Map<String, Object>> areaList = areaDao.getAllArea();
		Map<String, String> idAndNameMap = Maps.newHashMap();
		for (Map<String, Object> mapData : areaList) {
			idAndNameMap.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
		}
		return idAndNameMap;
	}

	private Map<String, Map<String, String>> getGroupData() {
		List<Map<String, Object>> groupList = groupDao.get(null);
		Map<String, Map<String, String>> idAndNameMap = Maps.newHashMap();
		for (Map<String, Object> mapData : groupList) {
			String areaId = String.valueOf(mapData.get("areaId"));
			Map<String, String> mapInternalData = idAndNameMap.get(areaId);
			if (mapInternalData == null) {
				mapInternalData = Maps.newHashMap();
			}
			mapInternalData.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			idAndNameMap.put(areaId, mapInternalData);
		}
		return idAndNameMap;
	}

	private Map<String, Map<String, String>> getUserDataByOfficeId() {
		List<Map<String, Object>> userList = userDao.findAllofUserByOfficeId(null);
		Map<String, Map<String, String>> idAndNameMap = Maps.newHashMap();
		for (Map<String, Object> mapData : userList) {
			String officeId = String.valueOf(mapData.get("officeId"));
			Map<String, String> mapInternalData = idAndNameMap.get(officeId);
			if (mapInternalData == null) {
				mapInternalData = Maps.newHashMap();
			}
			mapInternalData.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			idAndNameMap.put(officeId, mapInternalData);
		}
		return idAndNameMap;
	}

	private Map<String, Map<String, String>> getAllUserData() {
		List<Map<String, Object>> userList = userDao.findAllofUserByAreaId(null);
		Map<String, Map<String, String>> idAndNameMap = Maps.newHashMap();
		for (Map<String, Object> mapData : userList) {
			String areaId = String.valueOf(mapData.get("areaId"));
			Map<String, String> mapInternalData = idAndNameMap.get(areaId);
			if (mapInternalData == null) {
				mapInternalData = Maps.newHashMap();
			}
			mapInternalData.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			idAndNameMap.put(areaId, mapInternalData);
		}
		return idAndNameMap;
	}
}
