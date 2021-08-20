package com.abc.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test2 {

    // 使用数组创建流
    @Test
    public void test01() {
        int[] nums = {1, 2, 3};
        System.out.println(IntStream.of(nums).sum());
        System.out.println(Arrays.stream(nums).sum());
    }

    // 使用集合创建流
    @Test
    public void test02() {
        List<String> names = new ArrayList<>();
        Stream<String> listStream = names.stream();

        Set<String> cities = new HashSet<>();
        Stream<String> setStream = cities.stream();
    }

    // 创建数字流
    @Test
    public void test03() {
        // 创建一个包含1,2,3的Stream
        IntStream stream = IntStream.of(1, 2, 3);

        // 创建一个包含[1,5)范围的Stream
        IntStream range1 = IntStream.range(1, 5);
        // 创建一个包含[1,5]范围的Stream
        IntStream range2 = IntStream.rangeClosed(1, 5);

        System.out.println(range1.sum());
        System.out.println(range2.sum());

        // new Random().ints()创建一个无限流
        // limit(5)限制流中元素个数为5个
        IntStream ints = new Random().ints().limit(5);
        System.out.println(" ===== " + new Random().ints(5).sum());

        new Random().ints(5).peek(System.out::println).sum();
    }

    // 自定义流
    @Test
    public void test04() {
        System.out.println(Stream.generate(() -> Math.random()).limit(5).peek(System.out::println).count());
    }

}
