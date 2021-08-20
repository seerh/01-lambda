package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test5 {

    @Test
    public void test01() {
        UnaryOperator<String> unaryOperator = n -> "我爱你, " + n;
        System.out.println(unaryOperator.apply("北京"));
    }

    @Test
    public void test02() {
        UnaryOperator<Integer> unaryOperator1 = x -> x * 2;
        UnaryOperator<Integer> unaryOperator2 = x -> x * x;

        // 先将5作为unaryOperator1的参数,计算结果为10,
        // 再将unaryOperator1的计算结果10,作为unaryOperator2的参数再计算
        System.out.println(unaryOperator1.andThen(unaryOperator2).apply(5));  //100

        // 先将5作为unaryOperator2的参数,计算结果为25,
        // 再将unaryOperator2的计算结果25,作为unaryOperator1的参数再计算
        System.out.println(unaryOperator1.compose(unaryOperator2).apply(5));  //50
    }

    @Test
    public void test03() {
        System.out.println(UnaryOperator.identity().apply(5));  //5
        System.out.println(UnaryOperator.identity().apply(3 * 8));  //24
    }

}
