package com.saituo.order.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.order.Agent;

public interface AgentDao {

	public List<Agent> getAgentListByIdAndDelFlag(@Param(value = "filter") Map<String, ?> filter);

}
