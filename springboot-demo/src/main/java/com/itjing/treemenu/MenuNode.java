package com.itjing.treemenu;

import lombok.*;

import java.util.List;

/**
 * @author lijing
 * @date 2022年05月31日 20:23
 * @description 菜单节点
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuNode implements Comparable<MenuNode> {

    private String id;
    private String menuCode;
    private String menuName;
    private String pid;
    private List<MenuNode> children;

    public MenuNode(String id, String menuCode, String menuName, String pid) {
        this.id = id;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.pid = pid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuNode) {
            MenuNode node = (MenuNode) obj;
            return this.id.equals(node.getId());
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int compareTo(MenuNode o) {
        return this.getMenuCode().compareTo(o.getMenuCode());
    }
}