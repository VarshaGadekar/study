package com.euler;

import java.util.List;
import java.util.Scanner;

/**
 * 
 * Highly divisible triangular number
 * 
 * The sequence of triangle numbers is generated by adding the natural numbers.
 * So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first
 * ten terms would be:
 * 
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * 
 * Let us list the factors of the first seven triangle numbers:
 * 
 * 1: 1 3: 1,3 6: 1,2,3,6 10: 1,2,5,10 15: 1,3,5,15 21: 1,3,7,21 28:
 * 1,2,4,7,14,28
 * 
 * 
 * 
 * We can see that 28 is the first triangle number to have over five divisors.
 * 
 * What is the value of the first triangle number to have over five hundred
 * divisors?
 * 
 * 
 * @author VGadekar
 *
 *
 *
 *
9799
The 9799th triangle number is::48024900
finding the number of divisors for the triangle number...
Total number of divisors are::405


 */
public class Problem12 {

    public static void main(String[] args) {
        // 8400 - 192
        // Util.findDivisors(35280000) = 360
        // Util.findDivisors(352800000); - 486
        // 35284200
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            
            int ordinal = scanner.nextInt();
            long triangleNumber = Util.findNthTriangleNumber(ordinal);
            System.out.println("The " + ordinal + "th triangle number is::" + triangleNumber);
            // List<Long> l = Util.findDivisors(352800000);
            System.out.println("finding the number of divisors for the triangle number...");
            List<Long> l = Util.findDivisors(triangleNumber);
            System.out.println("Total number of divisors are::" + l.size());
            // System.out.println(Arrays.deepToString(l.toArray()));
        }
        scanner.close();
    }
}