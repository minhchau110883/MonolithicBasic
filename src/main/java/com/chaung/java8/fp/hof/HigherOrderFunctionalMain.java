package com.chaung.java8.fp.hof;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HigherOrderFunctionalMain {

    public static void main(String[] args) {

        // TODO Sorting Collections
        List<String> list = Arrays.asList("One", "Abc", "BCD");
        System.out.println("Original list: " + list);
        Collections.sort(list, (String a, String b) -> {
            return a.compareTo(b);
        });
        System.out.println("Sorted list: " + list);

        // TODO Sorting in Reverse Order
        Comparator<String> comparator = Comparator.naturalOrder();
        Comparator<String> comparatorReverse = comparator.reversed();
        Collections.sort(list, comparatorReverse);
        System.out.println("Sorted Reverse list: " + list);

        // TODO Test GrertingService
        GreetingService g = (n) -> n.toUpperCase();
        System.out.println(g.greet("chau"));
        testGreeting((n) -> n.toUpperCase() + n.toLowerCase() + n.length());
        testGreeting((a) -> a.replace("d", "D"));

        // TODO When Lambda is executed
        System.out.println("Starting execution");
        testGreeting( s -> {
            System.out.println("Inside lambda");
            return s.toUpperCase();
        });
        System.out.println("Ending execution");

        String s = getLambda().greet("data");
        System.out.println(s);
    }

    private static int getNumber() {
        System.out.println("Called getNumber");
        return 5;
    }

    private static void receivedNumber(int i) {
        System.out.println("Received number is :: " + i);
    }

    private static void testGreeting(GreetingService g) {
        System.out.println("Inside test method. Received greeting service");
        String returnedString = g.greet("udemy");
        System.out.println(returnedString);
    }

    private static GreetingService getLambda() {
        GreetingService g = (s) -> s.toUpperCase();
        return g;
    }
}
