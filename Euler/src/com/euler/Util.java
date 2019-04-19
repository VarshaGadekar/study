package com.euler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Util {
    private static List<Long> triangleNumbers;
    private static Map<Integer, Long> factorials;

    /**
     * find the factorial of the number
     * @param n
     * @return
     */
    public static long factorial(int n) {
        if (factorials == null) {
            factorials = new TreeMap<Integer, Long>();
        }
        

        if (factorials.get(n) == null) {
            int l = n - 1;
            while (factorials.get(l) == null && l > 0) {
                l--;
            }
            for (int i = l + 1; i <= n; i++) {
                Long factorial = 1L;
                if (factorials.get(i - 1) == null) {
                    factorial = (long)1 * i;
                } else {
                    factorial = factorials.get(i - 1) * i;
                }
                factorials.put(i, factorial);
            }
        } else {
            return factorials.get(n);
        }
        return factorials.get(n);
    }
    
    /**
     * 1. Start by inputting a number n 
     * 2. Let an int variable limit be sqrt(n) 
     * 3. Run a loop from i=1 to i=limit 
     *      3.1 if n is divisible by i 
     *          3.1.1 Print i 
     *          3.1.2 if i and n/i are unequal, print n/i also. 
     * 4. End.
     *
     * @param n
     * @return
     */
    public static List<Long> findDivisors(long n) {
        List<Long> factors = new ArrayList<Long>();
        int sqrt = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                factors.add((long)i);
                if (i != n / i) {
                    factors.add(n / i);
                }
            }
            
        }
        return factors;
    }

    /**
     * The sequence of triangle numbers is generated by adding the natural numbers.
     * So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28.
     * The first ten terms would be:
     * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
     * 
     * @param n
     * @return
     */
    public static long findNthTriangleNumber(long n) {
        if (triangleNumbers != null && triangleNumbers.get((int)n) != null) {
            return triangleNumbers.get((int)n);
        }
        if (triangleNumbers == null) {
            triangleNumbers = new ArrayList<Long>();
        }
        if (triangleNumbers.size() < n) {
            int lastIndex = triangleNumbers.size();
            for (int i = lastIndex + 1; i < n + 1; i++) {
                long prev = 0;
                if (i > 1) {
                    prev = triangleNumbers.get(i - 2);
                }
                triangleNumbers.add(i - 1, prev + i);
            }
        }
        return triangleNumbers.get((int)n - 1);
    }
}