package com.chaung.java8.fp.hof;

@FunctionalInterface
public interface IProducer<T> {

    T produce();
}
