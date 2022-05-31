package com.itjing.treemenu;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lijing
 * @date 2022年05月31日 20:30
 * @description 树形菜单工具类
 */
public class TreeMenuUtil {

    /**
     * 生成菜单树
     *
     * @param menuNodes
     * @return
     */
    private static List<MenuNode> listWithTree(List<MenuNode> menuNodes) {
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
     *
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

    public static void main(String[] args) {
        MenuNode node1 = new MenuNode("1", "1", "江苏省", "");
        MenuNode node2 = new MenuNode("2", "3", "连云港市", "1");
        MenuNode node3 = new MenuNode("3", "1", "灌云县", "2");
        MenuNode node4 = new MenuNode("4", "2", "苏州市", "1");
        MenuNode node5 = new MenuNode("5", "4", "无锡市", "1");
        MenuNode node6 = new MenuNode("6", "5", "盐城市", "1");
        MenuNode node7 = new MenuNode("7", "6", "淮安市", "1");
        List<MenuNode> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        List<MenuNode> menu = listWithTree(list);
        System.out.println(JSON.toJSONString(menu));
    }
}
