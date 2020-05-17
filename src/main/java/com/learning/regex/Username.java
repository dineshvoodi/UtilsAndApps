package com.learning.regex;

import java.util.Scanner;

public class Username {

    private static final Scanner scan = new Scanner(System.in);
    public static final String regularExpression = "^[a-zA-Z]{1}[a-zA-Z0-9_]{7,29}";
    public static void main(String[] args) {
        int n = Integer.parseInt(scan.nextLine());
        while (n-- != 0) {
            String userName = scan.nextLine();

            if (userName.matches(regularExpression)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }
    }

}
