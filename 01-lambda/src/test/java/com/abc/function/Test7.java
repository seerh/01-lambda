package com.abc.function;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test7 {

    @Test
    public void test01() {
        BinaryOperator<Integer> bo = (x, y) -> x * y;
        System.out.println(bo.apply(3, 5));  //15
    }

    @Test
    public void test02() {
        BinaryOperator<Integer> bo = (x, y) -> x * y;
        Function<Integer, String> up = n -> "结果为 " + n;
        // 将(3,5)应用于bo上,再将bo的运算结果作为up的参数进行运算
        System.out.println(bo.andThen(up).apply(3, 5));  //结果为 15
    }

    @Test
    public void test03() {
        Student student3 = new Student("张三", 23);
        Student student4 = new Student("李四", 24);

        StudentComparator studentComparator = new StudentComparator();
        Student minStu = BinaryOperator.minBy(studentComparator).apply(student3, student4);
        Student maxStu = BinaryOperator.maxBy(studentComparator).apply(student3, student4);

        System.out.println(minStu);  //student3
        System.out.println(maxStu);  //student4
    }

}
