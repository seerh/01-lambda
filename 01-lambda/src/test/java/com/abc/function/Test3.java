package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test3 {

    @Test
    public void test01() {
        Supplier<String> supplier = () -> "Lambda";
        System.out.println(supplier.get());
    }

}
