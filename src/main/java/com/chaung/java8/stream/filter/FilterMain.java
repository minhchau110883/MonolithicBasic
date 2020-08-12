package com.chaung.java8.stream.filter;

import com.chaung.java8.fp.hof.StringOperations;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

/**
 * https://mkyong.com/java8/java-8-streams-filter-examples/
 */
public class FilterMain {
    public static void main(String[] args) {
        // TODO Filter and collect
        // Before Java8
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = getFilterOutput(lines, "mkyong");
        for (String temp : result) {
            System.out.println(temp);    //output : spring, node
        }
        System.out.println(lines);

        // From Java8
        List<String> resultJ8 =
            lines.stream()  // convert list to stream
                .filter(line -> !"mkyong".equals(line)) // we don't like mekong
                .collect(Collectors.toList()); // collect the data and convert stream to a list
        resultJ8.forEach(System.out::println);

        // TODO filter, findAny and orElse
        List<Person> persons = Arrays.asList(
            new Person("mkyong", 30),
            new Person("jack", 20),
            new Person("lawrence", 40)
        );
        Person aPerson = persons.stream()
            .filter(person -> "jack".equals(person.getName()))
            .findAny().orElse(null);
        System.out.println(aPerson);

        Person anotherPerson = persons.stream()
            .filter(person -> "chau".equals(person.getName()))
            .findAny().orElse(null);
        System.out.println(anotherPerson);

        // Multiple condition
        Person aPerson2 = persons.stream()
            .filter(person -> {
                if ( "jack".equals(person.getName()) && 20 == person.getAge()) {
                    return true;
                } else {
                    return false;
                }
            })
            .findAny()
            .orElse(null);
        System.out.println(aPerson2);

        // TODO filer and map
        // filter(): Used for filtering data
        // map(): Transforms the received data from one form to another
        String aName = persons.stream()
            .filter(person -> "jack".equals(person.getName()))
            .map(Person::getName)
            .findAny()
            .orElse("");
        System.out.println("Name: " + aName);

        List<String> personToNames = persons.stream()
            .map(person -> person.getName() + "_" + person.getAge())
            .collect(Collectors.toList());
        personToNames.forEach(System.out::println);



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Bangkok"));
        Instant now = Instant.now();
        System.out.println(formatter.format(now));

        // 2020-07-23T00:01:00.415+07:00
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); //.withZone(ZoneId.of("Asia/Bangkok"));
        ZonedDateTime current = ZonedDateTime.now();
        System.out.println(formatter1.format(current));


        System.out.println(ISO_OFFSET_DATE_TIME.format(current));

        System.out.println(ISO_OFFSET_DATE_TIME.toString());

        System.out.println(ZonedDateTime.now().toString());

    }

    // Before Java8
    private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (!"mkyong".equals(line)) { // we dont like mkyong
                result.add(line);
            }
        }
        return result;
    }
}
