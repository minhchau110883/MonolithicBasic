package com.chaung.java8.fp.hof;

@FunctionalInterface
public interface IFactory<T> {
    T create();
}
