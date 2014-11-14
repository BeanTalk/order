package com.saituo.order.service.core;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.ResourceType;

@Service
public class CoreService {

	/**
	 * 合并资源，要获取资源的父类通过 "parent" key 来获取，如果不存在 "parent" key 表示该资源 Map 为根节点，
	 * 要获取资源的子类通过 "children" key 来获取
	 * 
	 * @param resources
	 *            要合并的资源实体 Map 集合
	 * 
	 * @return 合并好的树形资源实体 Map 集合
	 */
	public List<Map<String, Object>> mergeResources(List<Map<String, Object>> resources) {
		return mergeResources(resources, null);
	}

	/**
	 * 合并资源，要获取资源的父类通过 "parent" key 来获取，如果不存在 "parent" key 表示该资源 Map 为根节点，
	 * 要获取资源的子类通过 "children" key 来获取
	 * 
	 * @param resources
	 *            要合并的资源实体 Map 集合
	 * @param ignoreType
	 *            要忽略资源类型
	 * 
	 * @return 合并好的树形资源实体 Map 集合
	 */
	public List<Map<String, Object>> mergeResources(List<Map<String, Object>> resources, ResourceType ignoreType) {

		List<Map<String, Object>> result = Lists.newArrayList();
		for (Map<String, Object> entity : resources) {

			Long parentId = VariableUtils.typeCast(entity.get("parent_id"));
			Integer type = VariableUtils.typeCast(entity.get("type"));

			if (parentId == null && (ignoreType == null || !ignoreType.getValue().equals(type))) {
				recursionRessource(entity, resources, ignoreType);
				result.add(entity);
			}

		}

		return result;
	}

	/**
	 * 递归并合并资源到指定的父类
	 * 
	 * @param parent
	 *            父类
	 * @param resources
	 *            资源实体 Map 集合
	 * @param ignoreType
	 *            要忽略不合并的资源类型
	 */
	private void recursionRessource(Map<String, Object> parent, List<Map<String, Object>> resources,
			ResourceType ignoreType) {

		parent.put("children", Lists.newArrayList());

		for (Map<String, Object> entity : resources) {

			String parentId = VariableUtils.typeCast(entity.get("parent_id"));

			if (StringUtils.isEmpty(parentId)) {
				continue;
			}
			Integer type = VariableUtils.typeCast(entity.get("type"));
			String id = VariableUtils.typeCast(parent.get("id"));

			if ((ignoreType == null || !ignoreType.getValue().equals(type)) && StringUtils.equals(parentId, id)) {
				recursionRessource(entity, resources, ignoreType);
				List<Map<String, Object>> children = VariableUtils.typeCast(parent.get("children"));
				entity.put("parent", parent);
				children.add(entity);
			}
		}
	}

}
