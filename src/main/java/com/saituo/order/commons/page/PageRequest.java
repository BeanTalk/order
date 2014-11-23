package com.saituo.order.commons.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 分页请求对象，用于在分页查询时，通过该对象得知要查询的页数，也每页大小。
 *
 * @author maurice
 */
public class PageRequest implements Serializable {

	private static final long serialVersionUID = -4042216993738779536L;

	/**
	 * 分页范围开始参数
	 */
	public static final String FIRST_RESULT = "first";

	/**
	 * 分页范围结束参数
	 */
	public static final String LAST_RESULT = "last";

	// 页号
	private int pageNumber = 1;
	// 每页大小
	private int pageSize = 10;

	/**
	 * 分页请求对象，用于在分页查询时，通过该对象得知要查询的页数。
	 */
	public PageRequest() {

	}

	/**
	 * 分页请求对象，用于在分页查询时，通过该对象得知要查询的页数。
	 *
	 * @param page
	 *            页号
	 * @param size
	 *            内容大小
	 */
	public PageRequest(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/**
	 * 获取每页的内容大小
	 *
	 * @return 内容数量
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取当前页号
	 *
	 * @return 页号
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置当前页号
	 *
	 * @param page
	 *            页号
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取每页的内容大小
	 *
	 * @param size
	 *            内容数量
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取指定第一个返回记录行的偏移量
	 * 
	 * indexNum
	 * 
	 * @return 偏移量
	 */
	// indexNum
	public int getOffset() {
		return (pageNumber - 1) * pageSize;
	}

	/**
	 * 获取分页范围的起始和结束的 Map 对象
	 *
	 * @return {"first":{@link #getOffset()}, "last": {@link #getPageSize()}
	 */
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FIRST_RESULT, getOffset());
		map.put(LAST_RESULT, getPageSize());
		return map;
	}
}
