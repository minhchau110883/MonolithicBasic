package com.chaung.java8.stream.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream<String[]>		-> flatMap ->	Stream<String>
 * Stream<Set<String>>	-> flatMap ->	Stream<String>
 * Stream<List<String>>	-> flatMap ->	Stream<String>
 * Stream<List<Object>>	-> flatMap ->	Stream<Object>
 *
 *      { {1,2}, {3,4}, {5,6} } -> flatMap -> {1,2,3,4,5,6}
 *
 *      { {'a','b'}, {'c','d'}, {'e','f'} } -> flatMap -> {'a','b','c','d','e','f'}
 */
public class FlatMapMain {
    public static void main(String[] args) {

        // TODO Stream + String[] + flatMap
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream<String[]> temp = Arrays.stream(data);
        List<String> stringStream = temp.flatMap(x -> Arrays.stream(x)).collect(Collectors.toList());
        stringStream.forEach(System.out::println);

        // TODO Stream + Set + flatMap
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        // List<Object> -> Set<String>
        System.out.println(list.stream()
            .map(x -> x.getBook()).collect(Collectors.toList()));

        // List<Object> -> Set<String> -> List<String>
        List<String> bookList = list.stream()
            .map(student -> student.getBook())
            .flatMap(bookSet -> bookSet.stream())
            .distinct()
            .collect(Collectors.toList());
        System.out.println(bookList);

        // TODO Stream + Primitive + flatMapToInt
        int[] intArray = {1, 2, 3, 4, 5, 6};
        Stream<int[]> streamArray = Stream.of(intArray);
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));
        intStream.forEach(System.out::println);
    }
}
