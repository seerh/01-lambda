package com.abc.lambda;

import org.junit.jupiter.api.Test;

@FunctionalInterface
interface Some4 {

    String doSome(String a, int b);

    default void doOther(String a, int b) {
        System.out.println("执行默认方法doOther() -" + a + b);
    }

}

public class Test4 {

    @Test
    public void test01() {
        Some4 some4 = (a, b) -> a + b;
        System.out.println(some4.doSome("Hello, ", 2021));
        some4.doOther("Hello, ", 2022);
    }

}
