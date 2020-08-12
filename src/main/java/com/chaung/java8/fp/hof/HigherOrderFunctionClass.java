package com.chaung.java8.fp.hof;


public class HigherOrderFunctionClass {

    public <T> IFactory<T>  createFactory(IProducer<T> producer, IConfigurator<T> configurator) {
        return () -> {
            T instance = producer.produce();
            configurator.configure(instance);
            return instance;
        };
    }

}
