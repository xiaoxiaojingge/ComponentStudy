package org.itjing.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lijing
 * @date 2021年12月09日 17:32
 * @description
 */
public class StreamDemo02 {
    public static void main(String[] args) {
        /*Stream<String> stream = Stream.of("Hello", "World");
        stream.map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(s -> {
                    System.out.print(s + " ");
                });*/

        // 阿里巴巴手册：【强制】在使用java.util.stream.Collectors类的toMap()方法转为Map集合时
        // 一定要使用含有参数类型为BinaryOperator,参数名为mergeFunction的方法
        // 否则当出现相同key值时会抛出 IllegalStateException：Duplicate key 异常。
        List<Pair> pairList = new ArrayList<>(3);
        pairList.add(new Pair("version", 12.10));
        pairList.add(new Pair("version", 12.19));
        pairList.add(new Pair("version", 6.28));

        // 注：常见实体类的get方法中，我们看到的是无参，有返回值
        // 但是在 Lamada 的使用上看，神奇的出现了实际上应该是传入了一个Pair对象，返回值为String.
        Function<Pair, String> function = Pair::getKey;

        // 一定要使用含有参数类型为BinaryOperator,参数名为mergeFunction的方法
        // 第三个参数就是为了在 key 出现重复时做出的解决措施
        // 这里 (v1, v2) -> v2 指，v1,v2 冲突了，使用 v2
        Map<String, Double> map = pairList.stream()
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));

        map.forEach((k, v) -> {
            System.out.println(k + "-->" + v);
        });
    }
}

class Pair {
    private String key;
    private Double value;

    public Pair() {
    }


    public Pair(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
