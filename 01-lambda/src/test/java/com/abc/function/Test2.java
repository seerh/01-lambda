package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class Test2 {

    @Test
    public void test01() {
        Consumer<String> consumer = str -> System.out.println("Hello, " + str);
        consumer.accept("Tom");
    }

    @Test
    public void test02() {
        Consumer<Integer> consumer1 = n -> System.out.println(n * 2);
        Consumer<Integer> consumer2 = n -> System.out.println(n * n);

        consumer1.andThen(consumer2).accept(5);
    }

}
