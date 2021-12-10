package org.itjing.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lijing
 * @date 2021年12月09日 16:59
 * @description
 */
public class StreamDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        list.stream()
                .filter(name -> name.startsWith("张"))
                .filter((name) -> name.length() == 3)
                .forEach(System.out::println);
    }
}
