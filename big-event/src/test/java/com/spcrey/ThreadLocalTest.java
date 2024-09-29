package com.spcrey;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalTest() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        new Thread(()->{
            tl.set("My name is Xing");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "Xing").start();
        new Thread(()->{
            tl.set("My name is Ying");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "Ying").start();
    }
    
}
