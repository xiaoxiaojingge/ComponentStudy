package org.itjing.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamInt {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // flatMap升维度
        List<int[]> pairs = numbers1.stream()
                .flatMap(
                        x -> numbers2.stream().map(y -> new int[]{x, y})
                )
                .collect(Collectors.toList());
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
    }
}