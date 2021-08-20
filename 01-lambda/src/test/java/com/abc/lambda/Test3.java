package com.abc.lambda;

import org.junit.jupiter.api.Test;

@FunctionalInterface
interface Some3 {

    //有参数有返回值
    String doSome(String a, int b);

}

public class Test3 {

    @Test
    public void test01() {
        Some3 some3 = new Some3() {
            @Override
            public String doSome(String a, int b) {
                return a + b;
            }
        };
        System.out.println(some3.doSome("hello, ", 2021));
    }

    @Test
    public void test02() {
        Some3 some3 = (a, b) -> a + b;
        System.out.println(some3.doSome("hello, ", 2021));
    }

}
