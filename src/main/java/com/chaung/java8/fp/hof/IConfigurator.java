package com.chaung.java8.fp.hof;

@FunctionalInterface
public interface IConfigurator<T> {

    void configure(T t);
}
