package com.chaung.java8.stream.parallelstream;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamMain {

    private static final String DIR = System.getProperty("user.dir") + "/files/";

    public static void main(String[] args) {

        // TODO BaseStream.parallel()
        System.out.println("Normal .....");
        IntStream intStream = IntStream.rangeClosed(1, 10);
        intStream.forEach(i -> System.out.print(i + " "));
        System.out.println("\nParallel ......");
        IntStream intStream1 = IntStream.rangeClosed(1, 10);
        intStream1.parallel().forEach(i -> System.out.print(i + " "));

        // TODO Collection.parallelStream()
        List<String> alpha = getData();
        System.out.println("\nNormal .....");
        alpha.stream().forEach(a -> System.out.print(a + " "));
        System.out.println("\nParallel ......");
        alpha.parallelStream().forEach(a -> System.out.print(a + " "));
        System.out.println();

        // TODO Is Stream running in parallel mode?
        IntStream intStream21 = IntStream.rangeClosed(1, 10);
        System.out.println(intStream21.isParallel());
        IntStream intStream22 = IntStream.rangeClosed(1, 10).parallel();
        System.out.println(intStream22.isParallel());

        IntStream intStream31 = IntStream.rangeClosed(1, 10);
        intStream31.forEach(x -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", value: " + x);
        });
        System.out.println("\nParallel ......");
        IntStream intStream32 = IntStream.rangeClosed(1, 10).parallel();
        intStream32.forEach(x -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", value: " + x);
        });

        // TODO Java 8 streams to print all prime numbers up to 1 million
        long start = System.currentTimeMillis();
        ZonedDateTime startTime = ZonedDateTime.now();
        long normalCount = Stream.iterate(0, n -> n+1)
            .limit(100_000)
            .filter(ParallelStreamMain::isPrime)
            //.peek(x -> System.out.format("%s\t", x))
            .count();
        long end = System.currentTimeMillis();
        ZonedDateTime endTime = ZonedDateTime.now();
        System.out.println();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println("\nTotal: " + normalCount + ". " +
            "Took: " + (end - start) + "milis. " +
            "Took: " + Duration.between(startTime, endTime).getSeconds());

        start = System.currentTimeMillis();
        startTime = ZonedDateTime.now();
        long parallelCount = Stream.iterate(0, n -> n+1)
            .limit(100_000)
            .parallel()
            .filter(ParallelStreamMain::isPrime)
            // .peek(x -> System.out.format("%s\t", x))
            .count();
        end = System.currentTimeMillis();
        endTime = ZonedDateTime.now();
        System.out.println();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println("\nTotal: " + parallelCount + ". " +
            "Took: " + (end - start) + "milis. " +
            "Took: " + Duration.between(startTime, endTime).getSeconds());

        // TODO Parallel streams to increase the performance of a time-consuming save file tasks
        System.out.println(DIR);
        List<Employee> employeeList = generateEmployee(10);
        employeeList.parallelStream().forEach(ParallelStreamMain::save);
    }

    private static void save(Employee input) {
        try (
            FileOutputStream fos = new FileOutputStream(new File(DIR + input.getName() + ".txt"));
            ObjectOutputStream obs = new ObjectOutputStream(fos)
        ) {
            obs.writeObject(input.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> generateEmployee(int numOfEmployee) {
        return Stream.iterate(0, n -> n + 1)
            .limit(numOfEmployee)
            .map(x -> new Employee(
                generateRandomName(5),
                generateRandomAge(15, 100),
                generateRandomSalary(30_000, 100_000))
            ).collect(Collectors.toList());
    }

    private static String generateRandomName(int length) {
        return new Random()
            .ints(length, 97, 122) // 97 = a , 122 = z
            .mapToObj(c -> String.valueOf((char)c))
            .collect(Collectors.joining());
    }

    private static int generateRandomAge(int min, int max) {
        return new Random()
            .ints(1, min, max)
            .findFirst()
            .getAsInt();
    }

    private static BigDecimal generateRandomSalary(long min, long max) {
        return new BigDecimal(new Random()
            .doubles(1, min, max)
            .findFirst()
            .getAsDouble()).setScale(2, RoundingMode.HALF_UP);
    }

    private static List<String> getData() {
        List<String> alpha = new ArrayList<>();

        int n = 97;  // 97 = a , 122 = z
        while (n <= 122) {
            char c = (char) n;
            alpha.add(String.valueOf(c));
            n++;
        }

        return alpha;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

}
