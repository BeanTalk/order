package com.saituo.order.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.CustomerOrdering;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.cache.RedisCacheService;

@Service
public class BuyCardService {

	@Autowired
	private RedisCacheService redisCacheService;

	@Autowired
	private ProductDao productDao;

	/**
	 * 加入购物车
	 * 
	 * @param userId
	 * @param productId
	 */
	public void putProductIntoBag(String userId, String productId) {
		redisCacheService.putProductIntoBagAboutBuyCard(userId, productId, "1");
	}

	/**
	 * 删除购物车中的产品
	 * 
	 * @param userId
	 * @param productIds
	 */
	public void removeProductListFromBuyCard(String userId, String... productIds) {
		redisCacheService.removeProductListFromBuyCard(userId, productIds);
	}

	/**
	 * 统计购物车中的产品数量
	 * 
	 * @param userId
	 * @return
	 */
	public Long countProductInBagAboutBuyCard(String userId) {
		return redisCacheService.countProductInBagAboutBuyCard(userId);
	}

	/**
	 * 针对某一个用户的购物车中的数量
	 * 
	 * @param userId
	 * @return
	 */
	public Long getBuyProductCount(String userId) {
		return redisCacheService.countProductInBagAboutBuyCard(userId);
	}

	/**
	 * 是否已经添加到购物车中
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	public boolean isAddedIntoBuyCard(String userId, String productId) {
		return redisCacheService.isAddedIntoBuyCard(userId, productId);
	}

	/**
	 * 获取购物车中的产品列表
	 */

	public List<CustomerOrdering> getProductListFromBag(String userId) {

		List<Integer> productIds = new ArrayList<Integer>();
		Map<Object, Object> mapData = redisCacheService.getProductIdAndBuyNumMapFromCache(userId);

		for (Entry<Object, Object> entry : mapData.entrySet()) {
			Integer productId = Integer.valueOf(String.valueOf(entry.getKey()));
			productIds.add(productId);
		}

		List<Product> productList = productDao.getProductListByProductIds(productIds);
		List<CustomerOrdering> result = new ArrayList<CustomerOrdering>();
		try {
			for (Product product : productList) {
				CustomerOrdering customerOrdering = new CustomerOrdering();
				BeanUtils.copyProperties(customerOrdering, product);
				customerOrdering.setDiscountPrice(99);
				customerOrdering.setSubscriptCount(VariableUtils.typeCast(
						mapData.get(String.valueOf(product.getProductId())), Integer.class));
				result.add(customerOrdering);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
