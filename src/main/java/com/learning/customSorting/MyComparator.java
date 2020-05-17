package com.learning.customSorting;

import org.springframework.stereotype.Component;

import java.util.Comparator;

public class MyComparator implements Comparator<Person> {

    String[] level;

    public MyComparator(String[] level) {
        this.level = level;
    }

    @Override
    public int compare(Person o1, Person o2) {
        for (String type : level) {
            if (getValue(o1, type.toLowerCase()) instanceof String) {
                String s1 = (String) getValue(o1, type.toLowerCase());
                String s2 = (String) getValue(o2, type.toLowerCase());

                if (s1.compareTo(s2) == 0) {
                    continue;
                } else
                    return s1.compareTo(s2);

            } else {
                int s1 = (int) getValue(o1, type.toLowerCase());
                int s2 = (int) getValue(o2, type.toLowerCase());

                if (s1 - s2 == 0) {
                    continue;
                } else {
                    return s1 - s2;
                }
            }
        }
        return 0;
    }

    private Object getValue(Person person, String type) {
        switch (type) {

            case "firstname":
                return person.getFirstName();
            case "lastname":
                return person.getLastName();
            case "age":
                return person.getAge();
            case "country":
                return person.getCountry();
            default:
                return "";

        }

    }
}
