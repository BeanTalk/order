package com.saituo.order.service.gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.gift.GiftDao;
import com.saituo.order.dao.user.UserBeansDao;
import com.saituo.order.dao.user.UserPeasHisDao;
import com.saituo.order.entity.gift.Gift;
import com.saituo.order.entity.user.UserBeans;
import com.saituo.order.entity.user.UserPeasHis;

@Service
@Transactional
public class GiftService {
	@Autowired
	private GiftDao giftDao;

	@Autowired
	private UserBeansDao userBeansDao;

	@Autowired
	private UserPeasHisDao userPeasHisDao;
	/**
	 * 获取客户豆豆信息
	 */
	public UserBeans getUserBeans() {
		UserBeans userBeans = new UserBeans();
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		userBeans.setUserId(userId);
		return userBeansDao.query(userBeans);
	}

	/**
	 * 查询礼品数量信息总数 分页
	 */
	public int getGiftCount(Map<String, Object> filter) {

		Gift gift = new Gift();
		gift.setAreaId(VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class));
		gift.setGiftStatus(1); // 礼品状态: 0,未开始兑换;1,开始兑换;2,兑换结束
		return giftDao.count(gift, filter);
	}

	/**
	 * 查询礼品信息列表
	 */
	public List<Gift> getGiftList(Map<String, Object> filter) {
		Gift gift = new Gift();
		// 当前地市
		gift.setAreaId(VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class));
		gift.setGiftStatus(1); // 礼品状态: 0,未开始兑换;1,开始兑换;2,兑换结束
		return giftDao.queryList(gift, filter);
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String> getGiftIdAndNameMap() {
		Gift gift = new Gift();
		Map<String, Object> filter = Maps.newHashMap();
		List<Gift> giftList = giftDao.queryList(gift, filter);
		Map<String, String> result = Maps.newHashMap();
		for (Gift giftTemp : giftList) {
			result.put(VariableUtils.typeCast(giftTemp.getId(), String.class), giftTemp.getGiftName());
		}
		return result;
	}

	/**
	 * 查询豆豆历史数量信息总数 分页
	 */
	public int getUserPeasHisCount(Map<String, Object> filter) {

		UserPeasHis userPeasHis = new UserPeasHis();
		if (SessionVariable.getCurrentSessionVariable().getIsInternalUser()) {
			Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
			userPeasHis.setAreaId(areaId);
			userPeasHis.setPeasType("2");
		} else {
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			userPeasHis.setUserId(userId);
		}
		return userPeasHisDao.count(userPeasHis, filter);
	}

	/**
	 * 查询豆豆历史信息列表
	 */
	public List<UserPeasHis> getUserPeasHisList(Map<String, Object> filter) {
		UserPeasHis userPeasHis = new UserPeasHis();
		if (SessionVariable.getCurrentSessionVariable().getIsInternalUser()) {
			Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
			userPeasHis.setAreaId(areaId);
			userPeasHis.setPeasType("2");
		} else {
			String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
					String.class);
			userPeasHis.setUserId(userId);
		}
		return userPeasHisDao.queryListbyUserId(userPeasHis, filter);
	}

	/**
	 * 兑换礼品
	 */
	public Map<String, String> doExchange(Map<String, Object> filter) {
		Map<String, String> returnMap = new HashMap<String, String>();

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class); //
		Long peasBalance = VariableUtils.typeCast(filter.get("peasBalance"), Long.class); // 本次使用豆豆数
		Long giftId = VariableUtils.typeCast(filter.get("giftId"), Long.class); // 兑换礼品ID
		Long beansNum = VariableUtils.typeCast(filter.get("beansNum"), Long.class); // 客户剩余豆豆数量
		int giftNum = VariableUtils.typeCast(filter.get("giftNum"), int.class); // 兑换礼品数量

		// 更新客户豆豆数量
		UserBeans userBeans = new UserBeans();
		userBeans.setUserId(userId);// 客户编号
		userBeans.setBeansNum(peasBalance);// 豆豆数量
		userBeansDao.updateReduction(userBeans);

		// 礼品表:减掉兑换了的礼品数量
		Gift gift = new Gift();
		gift.setId(giftId);// 兑换礼品ID
		gift.setUpdateBy(userId);// 更新者
		gift.setGiftNum(giftNum);
		giftDao.update(gift);

		// 记录客户豆豆历史表
		UserPeasHis userPeasHis = new UserPeasHis();
		userPeasHis.setAcceptPerson(userId);// 创建者
		userPeasHis.setGiftId(giftId);// 兑换礼品ID
		userPeasHis.setPeasBalance(peasBalance);// 本次使用或累计豆豆数
		userPeasHis.setPeasType("2");// 操作类型:1.累积积分2.使用积分
		userPeasHis.setIfExchange(0);
		// userPeasHis.setRegisterNumber(registerNumber);
		userPeasHis.setUserId(userId);// 客户编码
		userPeasHisDao.insert(userPeasHis);
		// 返回投诉编码
		returnMap.put("Id", VariableUtils.typeCast(userPeasHis.getPeasId(), String.class));
		return returnMap;
	}

	public void update(Map<String, Object> filter) {
		userPeasHisDao.update(filter);
	}

}
