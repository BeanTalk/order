package com.saituo.order.service.variable;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.ShowType;
import com.saituo.order.commons.utils.StringUtils;
import com.saituo.order.dao.account.AreaDao;
import com.saituo.order.dao.account.GroupDao;
import com.saituo.order.dao.account.UserDao;
import com.saituo.order.dao.order.SupplyDao;
import com.saituo.order.entity.order.Supply;
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
	private SupplyDao supplyDao;

	@Autowired
	private OrderCacheService orderCacheService;

	private static final String UNDEFINE = "未知姓名";

	private static final String AREA_TO_OFFICE_ID_NAME_CACHE = "officeToUserIdAndNameCache";
	private static final String AREA_TO_USER_ID_NAME_CACHE = "areaToUserIdAndNameCache";

	public void init() {
		initUserByOfficeIdData();
		// initUserByAreaIdData();
	}

	public Map<String, String> getAreaIdAndName() {
		Map<String, String> mapData = Maps.newHashMap();
		List<Map<String, String>> list = areaDao.getAreaIdAndName();
		for (Map<String, String> mapDataTemp : list) {
			mapData.put(String.valueOf(mapDataTemp.get("id")), mapDataTemp.get("name"));
		}
		return mapData;
	}

	public Map<String, String> getSupplyIdAndName() {
		Map<String, String> mapData = Maps.newHashMap();
		Map<String, Object> filter = Maps.newHashMap();
		List<Supply> supplyList = supplyDao.getSupplyListByIdAndDelFlag(filter);
		for (Supply supply : supplyList) {
			mapData.put(supply.getSupplierId(), supply.getSupplierName());
		}
		return mapData;
	}

	public Map<String, String> getUserIdAndName() {
		Map<String, String> mapData = Maps.newHashMap();
		List<Map<String, String>> list = userDao.getAllofUser();
		for (Map<String, String> mapDataTemp : list) {
			mapData.put(String.valueOf(mapDataTemp.get("id")), mapDataTemp.get("name"));
		}
		return mapData;
	}

	public String getAreaIdAndNameData(String areaId) {
		return (String) areaDao.getAreaNameById(areaId);
	}

	public Map<String, Object> getGroupNameByAreaIdAndGroupIdDataToShow(Integer areaId) {

		List<Map<String, Object>> groupList = groupDao.get(areaId, ShowType.SHOW.getValue());
		Map<String, Object> officeIdAndNameMap = Maps.newHashMap();
		for (Map<String, Object> mapData : groupList) {
			officeIdAndNameMap.put(String.valueOf(mapData.get("id")), mapData.get("name"));
		}
		return officeIdAndNameMap;
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

	public Map<String, String> getUserMapsDataByOfficeId(String officeId) {
		Map<String, String> mapData = (Map<String, String>) orderCacheService.get(AREA_TO_OFFICE_ID_NAME_CACHE,
				officeId);
		return mapData;
	}

	public String getUserByOfficeIdData(String officeId, String userId) {
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
	 * 
	 *         private void initUserByAreaIdData() {
	 * 
	 *         Map<String, String> mapCond = Maps.newConcurrentMap();
	 *         List<Map<String, Object>> userList =
	 *         userDao.findAllofUserByAreaId(mapCond);
	 * 
	 *         Map<String, Map<String, String>> mapRes = Maps.newHashMap(); for
	 *         (Map<String, Object> mapData : userList) { String areaId =
	 *         String.valueOf(mapData.get("areaId")); Map<String, String>
	 *         mapInternalData = mapRes.get(areaId); if (mapInternalData ==
	 *         null) { mapInternalData = Maps.newHashMap(); }
	 *         mapInternalData.put(String.valueOf(mapData.get("id")),
	 *         String.valueOf(mapData.get("name"))); mapRes.put(areaId,
	 *         mapInternalData); }
	 * 
	 *         for (Entry<String, Map<String, String>> entry :
	 *         mapRes.entrySet()) {
	 *         orderCacheService.put(AREA_TO_USER_ID_NAME_CACHE, entry.getKey(),
	 *         entry.getValue()); } }
	 */

	public String getUserByAreaIdData(String areaId, String userId) {

		// Map<String, Object> mapData = (Map<String, Object>)
		// orderCacheService.get(AREA_TO_USER_ID_NAME_CACHE,
		// String.valueOf(areaId));
		//
		// if (mapData != null &&
		// StringUtils.isNotEmpty(String.valueOf(mapData.get(userId)))) {
		// return String.valueOf(mapData.get(userId));
		// }

		Map<String, String> mapTemp = Maps.newHashMap();
		mapTemp.put("areaId", String.valueOf(areaId));
		mapTemp.put("userId", String.valueOf(userId));

		List<Map<String, Object>> list = userDao.findAllofUserByAreaId(mapTemp);
		if (list.size() > 0) {
			orderCacheService.put(AREA_TO_USER_ID_NAME_CACHE, String.valueOf(areaId), list.get(0));
			return String.valueOf(list.get(0).get("name"));
		}
		return UNDEFINE;
	}
}
