package com.chaung.java8.fp.hof;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MethodReferencesMain {

    private static final List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        StringOperations op1 = (s) -> System.out.println(s);
        StringOperations op2 = System.out::println;
        op1.accept("udemy");
        op2.accept("chau");

        StringOperationsV2 operationsV2 = (s) -> Objects.isNull(s);
        StringOperationsV2 operationsV22 = Objects::isNull;
        System.out.println(operationsV2.accept(null));
        System.out.println(operationsV22.accept("null"));

        StringOperationsV3 operationsV3 = String::toUpperCase;
        System.out.println(operationsV3.accept("chau"));

        // StringOperationsV4 operationsV4 = (s1, s2) -> s1.concat(s2);
        StringOperationsV4 operationsV4 = String::concat;
        System.out.println(operationsV4.accept("ab", "cd"));

        StringOperations operationAdding = list::add;
        operationAdding.accept("udemy");
        System.out.println(list);


    }
}
