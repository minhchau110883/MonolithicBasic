package com.chaung.java8.stream.map;

import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Map: let you convert an object to something else
 */
public class MapMain {

    public static void main(String[] args) {

        // TODO List of string to Uppercase
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> toUppercase = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(alpha);
        System.out.println(toUppercase);

        List<Integer> num = Arrays.asList(1,2,3,4,5);
        List<Integer> toSquare = num.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(toSquare);

        // TODO List of object to List of string
        List<Staff> staff = Arrays.asList(
            new Staff("mkyong", 30, new BigDecimal(10000)),
            new Staff("jack", 27, new BigDecimal(20000)),
            new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<String> staffNames = staff.stream()
            .map(Staff::getName)
            .collect(Collectors.toList());
        System.out.println(staffNames);

        // TODO List of objects -> List of other objects
        List<StaffPublic> staffPublics = staff.stream()
            .map(staff1 -> {
                StaffPublic staffPublic =  new StaffPublic(staff1.getName(), staff1.getAge());
                if ("mkyong".equals(staff1.getName())) {
                    staffPublic.setExtra("Belong to mkyong only");
                }
                return staffPublic;
            })
            .collect(Collectors.toList());
        System.out.println(staffPublics);

        // staff.add(new Staff("lawrenceDuplicate", 33, new BigDecimal(30000)));
        Map<Integer, StaffPublic> staffPublicMap = staff.stream()
            .collect(Collectors.toMap(Staff::getAge, staff1 -> new StaffPublic(staff1.getName(), staff1.getAge())));
        System.out.println(staffPublicMap);
    }
}
