package com.saituo.order.service.user;

import java.util.Map;

import com.saituo.order.dao.user.AuditHisDao;
import com.saituo.order.dao.user.ProductOrderDao;
import com.saituo.order.dao.user.ProductOrderHisDao;
import com.saituo.order.dao.user.UserOrderDao;
import com.saituo.order.entity.user.UserOrder;

/**
 * 客户订单业务逻辑
 */
@Service
@Transactional
public class UserOrderService {
	@Autowired
	private UserOrderDao userOrderDao;
	@Autowired
	private ProductOrderDao productOrderDao;
	@Autowired
	private AuditHisDao auditHisDao;
	@Autowired
	private ProductOrderHisDao productOrderHisDao;
	
	public Map<String, String> doCreateUserOrder() {
		UserOrder userOrder=new UserOrder();
		userOrder.setAcceptDate(acceptDate);
		userOrder.setAddressId(addressId);
		userOrder.setAreaId(areaId);
		userOrder.setStatusCd(statusCd);
		userOrder.setUserId(userId);
		userOrderDao.insert(userOrder);
			
	    }
}
