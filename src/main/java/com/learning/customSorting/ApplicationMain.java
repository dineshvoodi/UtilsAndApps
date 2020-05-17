package com.learning.customSorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationMain {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Dinesh", "Voodi", "India", 27));
        list.add(new Person("Ramesh", "Khan", "Singapore", 22));
        list.add(new Person("Suresh", "Rao", "Japan", 33));
        list.add(new Person("Mahesh", "Reddy", "India", 54));
        list.add(new Person("Dhanush", "Kanth", "USA", 32));
        list.add(new Person("Mohesh", "Var", "China", 21));
        list.add(new Person("Manohar", "Varma", "India", 27));

        String[] level = {"country", "age", "firstname"};

        System.out.println(list);

        Collections.sort(list, new MyComparator(level));

        System.out.println(list);

    }
}
