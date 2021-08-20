package com.abc.stream;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test3 {

    // 静态方法
    public static void print(int i) {
        String name = Thread.currentThread().getName();
        System.out.println(i + " -- " + name);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 静态方法
    public static void printRed(int i) {
        String name = Thread.currentThread().getName();
        System.out.println(i + " -- " + name);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 串行处理
    @Test
    public void test01() {
        IntStream.range(1, 100)
                .peek(Test3::print)
                .count();
    }

    // 并行处理
    @Test
    public void test02() {
        IntStream.range(1, 100)
                .parallel()
                .peek(Test3::print)
                .count();
    }

    // 串并行执行效果为后者
    // 串并行混合处理
    @Test
    public void test03() {
        IntStream.range(1, 100)
                .parallel()             //并行处理
                .peek(Test3::print)
                .sequential()           //串行处理
                .peek(Test3::print)
                .count();
    }

    // 串并行执行效果为后者
    // 串并行混合处理
    @Test
    public void test04() {
        IntStream.range(1, 100)
                .sequential()           //串行处理
                .peek(Test3::print)
                .parallel()             //并行处理
                .peek(Test3::print)
                .count();
    }

    // 修改默认线程池中的线程数量
    @Test
    public void test05() {
        // 指定默认线程池中的数量为32,其中包含指定的31个和main线程
        String key = "java.util.concurrent.ForkJoinPool.common.parallelism";
        System.setProperty(key, "31");

        IntStream.range(1, 100)
                .parallel()             //并行处理
                .peek(Test3::print)
                .count();
    }

    // 使用自定义线程池
    @Test
    public void test06() {
        // 创建线程池,包含4个线程
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.submit(() -> IntStream.range(1, 100)
                                    .parallel()             //并行处理
                                    .peek(Test3::print)
                                    .count()
                    );

        // wait()、notify()、notifyAll()方法必须在同步方法或同步代码块中被调用,
        // 且哪个对象调用了这些方法,哪个对象就要充当同步锁
        synchronized (pool) {
            try {
                // main线程被阻塞在了这里
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
