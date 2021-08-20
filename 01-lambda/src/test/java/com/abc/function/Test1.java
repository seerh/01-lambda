package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Test1 {

    @Test
    public void test01() {
        Predicate<Integer> predicate = i -> i > 8;
        IntPredicate intPredicate = i -> i < 3;
        DoublePredicate doublePredicate = n -> n < 5;


        System.out.println(predicate.test(9)); //true
        System.out.println(predicate.test(7)); //false

        System.out.println(intPredicate.test(9)); //false
        System.out.println(intPredicate.test(2)); //true

        System.out.println(doublePredicate.test(2)); //true
        System.out.println(doublePredicate.test(6)); //false
    }

    @Test
    public void test02() {
        Predicate<Integer> gt8 = i -> i > 8;
        Predicate<Integer> lt3 = i -> i < 3;

        System.out.println(gt8.and(lt3).test(9)); //false
        System.out.println(gt8.or(lt3).test(9)); //true
        System.out.println(gt8.negate().test(9)); //false
    }

    @Test
    public void test03() {
        System.out.println(Predicate.isEqual("Hello").test("hello")); //false
        System.out.println(Predicate.isEqual("hello").test("hello")); //true
    }

}
