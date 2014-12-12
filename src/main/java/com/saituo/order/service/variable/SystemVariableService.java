package com.saituo.order.service.variable;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.utils.StringUtils;
import com.saituo.order.dao.account.AreaDao;
import com.saituo.order.dao.account.GroupDao;
import com.saituo.order.dao.account.UserDao;
import com.saituo.order.service.ehcache.OrderCacheService;

/**
 * 系统静态变量Id与名称对应关系
 *
 * @author maurice
 */
@SuppressWarnings("unchecked")
public class SystemVariableService {

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderCacheService orderCacheService;

	private static final String UNDEFINE = "未知姓名";

	private static final String AREA_ID_NAME_CACHE = "areaIdAndNameCache";
	private static final String AREA_TO_GROUP_ID_NAME_CACHE = "areaToGroupIdAndNameCache";
	private static final String AREA_TO_OFFICE_ID_NAME_CACHE = "officeToUserIdAndNameCache";
	private static final String AREA_TO_USER_ID_NAME_CACHE = "areaToUserIdAndNameCache";

	public void init() {
		initAreaIdAndNameData();
		initGroupByAreaIdData();
		initUserByOfficeIdData();
		initUserByAreaIdData();
	}

	/**
	 * AreaId and AreaName
	 * 
	 * @param id
	 * @return
	 */
	private void initAreaIdAndNameData() {
		List<Map<String, Object>> areaList = areaDao.getAllArea();
		for (Map<String, Object> mapData : areaList) {
			orderCacheService.put(AREA_ID_NAME_CACHE, String.valueOf(mapData.get("id")), (String) mapData.get("name"));
		}
	}

	public String getAreaIdAndNameData(String areaId) {
		return (String) orderCacheService.get(AREA_ID_NAME_CACHE, areaId);
	}

	/**
	 * 通过groupId 获取所在本地市信息
	 * 
	 * @param areaId
	 * @return
	 */
	private void initGroupByAreaIdData() {
		List<Map<String, Object>> groupList = groupDao.get(null);

		Map<String, Map<String, String>> mapRes = Maps.newConcurrentMap();
		for (Map<String, Object> mapData : groupList) {

			String areaId = String.valueOf(mapData.get("areaId"));
			Map<String, String> mapTemp = mapRes.get(areaId);
			if (mapTemp == null) {
				mapTemp = Maps.newHashMap();
			}
			mapTemp.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			mapRes.put(areaId, mapTemp);
		}

		for (Entry<String, Map<String, String>> entry : mapRes.entrySet()) {
			orderCacheService.put(AREA_TO_GROUP_ID_NAME_CACHE, entry.getKey(), entry.getValue());
		}
	}

	public Map<String, Object> getGroupByAreaIdData(Integer areaId) {
		Map<String, Object> mapData = (Map<String, Object>) orderCacheService.get(AREA_TO_GROUP_ID_NAME_CACHE,
				String.valueOf(areaId));
		return mapData;
	}

	/**
	 * 通过groupId 获取所在本地市的所有用户信息
	 * 
	 * @param areaId
	 * @return
	 */
	private void initUserByOfficeIdData() {
		Map<String, String> mapCond = Maps.newConcurrentMap();
		List<Map<String, Object>> userList = userDao.findAllofUserByOfficeId(mapCond);

		Map<String, Map<String, String>> mapRes = Maps.newHashMap();
		for (Map<String, Object> mapData : userList) {
			String officeId = String.valueOf(mapData.get("officeId"));
			Map<String, String> mapInternalData = mapRes.get(officeId);
			if (mapInternalData == null) {
				mapInternalData = Maps.newHashMap();
			}
			mapInternalData.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			mapRes.put(officeId, mapInternalData);
		}

		for (Entry<String, Map<String, String>> entry : mapRes.entrySet()) {
			orderCacheService.put(AREA_TO_OFFICE_ID_NAME_CACHE, entry.getKey(), entry.getValue());
		}
	}

	public String getUserByOfficeIdData(Integer officeId, Integer userId) {
		Map<String, Object> mapData = (Map<String, Object>) orderCacheService.get(AREA_TO_OFFICE_ID_NAME_CACHE,
				String.valueOf(officeId));

		if (mapData != null && StringUtils.isNotEmpty(VariableUtils.typeCast(mapData.get(userId), String.class))) {
			return String.valueOf(mapData.get(userId));
		}

		Map<String, String> mapTemp = Maps.newHashMap();
		mapTemp.put("officeId", String.valueOf(officeId));
		mapTemp.put("userId", String.valueOf(userId));

		List<Map<String, Object>> list = userDao.findAllofUserByOfficeId(mapTemp);
		if (list.size() > 0) {
			orderCacheService.put(AREA_TO_OFFICE_ID_NAME_CACHE, String.valueOf(officeId), list.get(0));
			return String.valueOf(list.get(0).get("name"));
		}
		return UNDEFINE;
	}

	/**
	 * 通过AreaId 获取所在本地市的所有用户信息
	 * 
	 * @param areaId
	 * @return
	 */
	private void initUserByAreaIdData() {

		Map<String, String> mapCond = Maps.newConcurrentMap();
		List<Map<String, Object>> userList = userDao.findAllofUserByAreaId(mapCond);

		Map<String, Map<String, String>> mapRes = Maps.newHashMap();
		for (Map<String, Object> mapData : userList) {
			String areaId = String.valueOf(mapData.get("areaId"));
			Map<String, String> mapInternalData = mapRes.get(areaId);
			if (mapInternalData == null) {
				mapInternalData = Maps.newHashMap();
			}
			mapInternalData.put(String.valueOf(mapData.get("id")), String.valueOf(mapData.get("name")));
			mapRes.put(areaId, mapInternalData);
		}

		for (Entry<String, Map<String, String>> entry : mapRes.entrySet()) {
			orderCacheService.put(AREA_TO_USER_ID_NAME_CACHE, entry.getKey(), entry.getValue());
		}
	}

	public String getUserByAreaIdData(Integer areaId, Integer userId) {

		Map<String, Object> mapData = (Map<String, Object>) orderCacheService.get(AREA_TO_USER_ID_NAME_CACHE,
				String.valueOf(areaId));

		if (mapData != null && StringUtils.isNotEmpty(VariableUtils.typeCast(mapData.get(userId), String.class))) {
			return String.valueOf(mapData.get(userId));
		}

		Map<String, String> mapTemp = Maps.newHashMap();
		mapTemp.put("areaId", String.valueOf(userId));
		mapTemp.put("userId", String.valueOf(areaId));

		List<Map<String, Object>> list = userDao.findAllofUserByAreaId(mapTemp);
		if (list.size() > 0) {
			orderCacheService.put(AREA_TO_USER_ID_NAME_CACHE, String.valueOf(areaId), list.get(0));
			return String.valueOf(list.get(0).get("name"));
		}
		return UNDEFINE;
	}
}
