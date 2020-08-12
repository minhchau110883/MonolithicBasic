package com.chaung.java8.fp.hof;

@FunctionalInterface
public interface MyFunctionalInterface1 {

    void run();

    default void doIt() {
        System.out.println("Doing it");
    }

    static void doItStatically() {
        System.out.println("doing it statically");
    }

}
