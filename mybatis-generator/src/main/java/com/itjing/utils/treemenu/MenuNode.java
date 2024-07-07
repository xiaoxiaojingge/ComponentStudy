package com.itjing.utils.treemenu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lijing
 * @date 2021年12月21日 14:50
 * @description 菜单节点
 */
@Getter
@Setter
@ToString
public class MenuNode implements Comparable<MenuNode> {

	private String id;

	private String menuCode;

	private String menuName;

	private String pid;

	private List<MenuNode> children;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuNode) {
			MenuNode node = (MenuNode) obj;
			return this.id.equals(node.getId());
		}
		else {
			return super.equals(obj);
		}
	}

	@Override
	public int compareTo(MenuNode o) {
		return this.getMenuCode().compareTo(o.getMenuCode());
	}

}