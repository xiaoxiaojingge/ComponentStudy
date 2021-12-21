package com.itjing.controller;


import com.itjing.response.RestResult;
import com.itjing.response.RestResultUtils;
import com.itjing.utils.treemenu.MenuNode;
import com.itjing.utils.treemenu.TreeMenuUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijing
 * @date 2021年12月21日 14:47
 * @description 测试树形菜单
 */
@RestController
@RequestMapping("/tree")
public class TreeMenuController {

    static List<MenuNode> menuNodes = new ArrayList<MenuNode>();

    static {
        MenuNode node1 = new MenuNode();
        node1.setId("1");
        node1.setMenuCode("00001");
        node1.setMenuName("浦发银行");
        node1.setPid("");


        MenuNode node2 = new MenuNode();
        node2.setId("11");
        node2.setMenuCode("00022");
        node2.setMenuName("南京分行");
        node2.setPid("1");

        MenuNode node3 = new MenuNode();
        node3.setId("12");
        node3.setMenuCode("00021");
        node3.setMenuName("上海分行");
        node3.setPid("1");

        MenuNode node4 = new MenuNode();
        node4.setId("112");
        node4.setMenuCode("00112");
        node4.setMenuName("莫愁湖支行");
        node4.setPid("11");

        MenuNode node5 = new MenuNode();
        node5.setId("111");
        node5.setMenuCode("00111");
        node5.setMenuName("建邺支行");
        node5.setPid("11");

        menuNodes.add(node1);
        menuNodes.add(node2);
        menuNodes.add(node3);
        menuNodes.add(node4);
        menuNodes.add(node5);
    }

    /**
     * 获取菜单树
     * @return
     */
    @GetMapping("/menu")
    public RestResult<?>  getTreeMenu(){
        List<MenuNode> menuTree = TreeMenuUtil.getMenuTree(menuNodes);
        return RestResultUtils.success(menuTree);
    }
}
