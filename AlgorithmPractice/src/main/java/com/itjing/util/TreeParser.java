package com.itjing.util;


import java.util.*;

public class TreeParser {
    /**
     * * 解析树形数据
     *
     * @param topId
     * @param entityList
     * @return
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        // 获取顶层元素集合
        Object parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null ||topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }


    public static <E extends TreeEntity<E>> List<E> getTreeOfEveryLevel(List<E> list){
        List<E> resultList=new ArrayList<>();
        List<Object> firstParentId=new ArrayList<>();

        Map<Object,List<E>> parentMap = new HashMap<Object,List<E>>();

        for (E vo:list) {
            Object parentId = vo.getParentId();
            if (Objects.isNull(parentId) || Objects.equals(parentId.toString(), vo.getId().toString()) || Objects.equals("0", String.valueOf(parentId))) {
                firstParentId.add(vo.getId());
            }
            if(parentMap.containsKey(parentId)){

                parentMap.get(parentId).add(vo);

            }else{

                List<E> _list = new ArrayList<>();
                        _list.add(vo);

                        parentMap.put(vo.getParentId(), _list);

            }
        }
        for (E vo:list) {
            if(firstParentId.contains(vo.getId())) {
                getTreeOfLevel(vo, parentMap);
                resultList.add(vo);
            }
        }
        return resultList;
    }

    public static  <E extends TreeEntity<E>> void getTreeOfLevel(E info,  Map<Object,List<E>> parentMap){
        List<E> childList=new ArrayList<>();

        if(parentMap.containsKey(info.getId())){

            childList = parentMap.get(info.getId());
        }

        for (E item:childList) {
            getTreeOfLevel(item,parentMap);
        }
        info.setChildList(childList);

    }

    /**
     * * 获取子数据集合
     *
     * @param id
     * @param entityList
     * @return
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(Object id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        Object parentId;
        // 子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        // 子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * * 拆解树形数据
     *
     * @param entityList
     * @return
     */
    public static <E extends TreeEntity<E>> List<E> treeToList(List<E> entityList) {
        List<E> list=new ArrayList<>();
        addTotalList(list,entityList);
        return list;
    }

    public static <E extends TreeEntity<E>> void addTotalList(List<E> totallist, List<E> entityList){
        for(E entity:entityList){
            List<E> childList=entity.getChildList();
            totallist.add(entity);
            if(childList!=null&&childList.size()>0){
                addTotalList(totallist,childList);
            }
        }
    }
}
