package org.itjing.stream;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lijing
 * @date 2021年12月10日 11:27
 * @description
 */
public class StreamDemo03 {
    public static void main(String[] args) {
        // 注意：Stream流一旦调用终止方法，就不可以再操作。

        // 获取Stream流中数据的总数
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        long count = stream.count();
        System.out.println("count = " + count);
        // 获取Stream流中的最大值
        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Optional<Integer> max = stream.max(Integer::compareTo);
        System.out.println("max = " + max.get());
        // 获取Stream流中的最小值
        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Optional<Integer> min = stream.min(Integer::compareTo);
        System.out.println("min = " + min.get());

        System.out.println("-----------------------------");
        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Optional<Integer> maxOptional = stream.collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("max = " + maxOptional.get());

        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Optional<Integer> minOptional = stream.collect(Collectors.minBy(Integer::compareTo));
        System.out.println("min = " + minOptional.get());

        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        count = stream.collect(Collectors.counting());
        System.out.println("count = " + count);

        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Double avg = stream.collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("avg = " + avg);

        stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Integer sum = stream.collect(Collectors.summingInt(Integer::intValue));
        System.out.println("sum = " + sum);
    }
}
