package com.itjing.utils.treemenu;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lijing
 * @date 2021年12月21日 14:05
 * @description 树形菜单工具类
 */
public class TreeMenuUtil {

	/**
	 * 缓存
	 */
	private static LRUCache<String, List<MenuNode>> menuCache = new LRUCache();

	/**
	 * 清除菜单缓存
	 */
	public static void clearMenuCache() {
		menuCache.clear();
	}

	/**
	 * 获取菜单树，并将其放入缓存中
	 * @param nodeList
	 * @return
	 */
	public static List<MenuNode> getMenuTree(List<MenuNode> nodeList) {
		if (nodeList != null && nodeList.size() != 0) {
			// 如果缓存中有，证明没有添加新的菜单，直接从缓存中获取
			if (menuCache.containsKey("treeMenu")) {
				return menuCache.get("treeMenu");
			}
			else {
				// 否则重新生成菜单树，并添加到缓存中
				List<MenuNode> result = listWithTree(nodeList);
				menuCache.put("treeMenu", result);
				return result;
			}
		}
		else {
			return nodeList;
		}
	}

	/**
	 * 生成菜单树
	 * @param menuNodes
	 * @return
	 */
	private static List<MenuNode> listWithTree(List<MenuNode> menuNodes) {
		// 进入了本方法，证明有新菜单加入，需要重新生成菜单树，需要清空缓存
		TreeMenuUtil.clearMenuCache();
		// 组装成父子的树形结构
		List<MenuNode> level1Menus = menuNodes.stream()
			// 找到一级菜单
			.filter(node -> StringUtils.isEmpty(node.getPid()))
			.map(node -> {
				// 从所有菜单中找menu的子菜单
				node.setChildren(getChildrens(node, menuNodes));
				return node;
			})
			// 按照menuCode排序并处理为空情况，nullsLast是值将空值放最后，nullsFirst同理
			.sorted(Comparator.comparing(MenuNode::getMenuCode, Comparator.nullsLast(String::compareTo)))
			.collect(Collectors.toList());
		return level1Menus;
	}

	/**
	 * 递归查找所有菜单的子菜单
	 * @param root
	 * @param all
	 * @return
	 */
	public static List<MenuNode> getChildrens(MenuNode root, List<MenuNode> all) {
		List<MenuNode> childrens = all.stream()
			.filter(node -> Objects.equals(node.getPid(), root.getId()))
			.map(node -> {
				// 找到子菜单
				node.setChildren(getChildrens(node, all));
				return node;

			})
			// 排序，可以自行定义排序规则
			// 按照menuCode排序并处理为空情况，nullsLast是值将空值放最后，nullsFirst同理
			.sorted(Comparator.comparing(MenuNode::getMenuCode, Comparator.nullsLast(String::compareTo)))
			.collect(Collectors.toList());
		return childrens;
	}

}