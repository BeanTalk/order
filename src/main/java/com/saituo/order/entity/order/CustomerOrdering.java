package com.saituo.order.entity.order;

/**
 * 预定产品的
 * @author yangwunan
 *
 */
public class CustomerOrdering extends Product {

	// 订购价
	private double discountPrice;

	// 预定数
	private int subscriptCount;

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getSubscriptCount() {
		return subscriptCount;
	}

	public void setSubscriptCount(int subscriptCount) {
		this.subscriptCount = subscriptCount;
	}

}
