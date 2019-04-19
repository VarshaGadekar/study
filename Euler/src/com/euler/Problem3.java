package com.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * https://projecteuler.net/problem=3
 * 
 * @author VGadekar
 *
 *         -------------------------- 
 *         Largest prime factor
 * 
 * 
 *         The prime factors of 13195 are 5, 7, 13 and 29.
 * 
 *         What is the largest prime factor of the number 600851475143 ?
 *         ----------------------------
 *
 */
public class Problem3 {
	public static void main(String[] args) {
		long no2 = 600851475143L;
		System.out.println(primeFactors(no2));
	}
	
	static Long primeFactors(long number) {
		List<Long> factors = new ArrayList<Long>();
		for (int i = 2; i < number/2; i++) {
			if (number % i == 0) {
				factors.add(Long.valueOf(i));
//				System.out.println("i = " + i);
				number = number/i;
//				System.out.println("number = " + number);
			}
		}
		if (number > 1) {
			factors.add(number);
		}
//		System.out.println(number);
//		System.out.println("factors are :: ");
//		for (long f: factors) {
//			System.out.println(f);
//		}
		return factors.get(factors.size() - 1);
	}
}
