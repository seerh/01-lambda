package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class Test4 {

    @Test
    public void test01() {
        Function<Integer, String> function = n -> "我爱你, " + n;
        System.out.println(function.apply(2021));
    }

    @Test
    public void test02() {
        Function<Integer, Integer> function1 = x -> x * 2;
        Function<Integer, Integer> function2 = x -> x * x;

        // 先将5作为function1的参数,计算结果为10,
        // 再将function1的计算结果10,作为function2的参数再计算
        System.out.println(function1.andThen(function2).apply(5));  //100

        // 先将5作为function2的参数,计算结果为25,
        // 再将function2的计算结果25,作为function1的参数再计算
        System.out.println(function1.compose(function2).apply(5));  //50
    }

    @Test
    public void test03() {
        System.out.println(Function.identity().apply(5));  //5
        System.out.println(Function.identity().apply(3 * 8));  //24
    }

}
