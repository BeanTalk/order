package com.saituo.order.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.order.Supply;

public interface SupplyDao {

	public List<Supply> getSupplyListByIdAndDelFlag(@Param(value = "filter") Map<String, ?> filter);
}
