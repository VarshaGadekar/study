package com.euler;

import java.util.ArrayList;
import java.util.List;

/**
https://projecteuler.net/problem=10

Summation of primes
Problem 10 
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.


 * @author VGadekar
 *
 */
public class Problem10 {
	int MAX = 2000000;
	private List<Long> primes = new ArrayList<Long>();
	private int limit = 10000;

	public static void main(String[] args) {

		new Problem10().process();
	}

	private void process() {
		long sum = 0;
		for (int i = 0; i < limit; i++) {
			if (isPrime(i)) {
				sum = sum + i;
				primes.add(new Long(i));
			}
		}
		boolean flag = false;
		while (true) {
			List<Long> tempList = new ArrayList<Long>();
			for (int i = limit; i < limit + 1000; i++) {
				if (i >= MAX) {
					flag = true;
					break;
				}
				if (isPrime(i)) {

					sum = sum + i;
					tempList.add(new Long(i));
				}
			}
			if (flag) {
				break;
			}
			primes.addAll(tempList);
			limit = limit + 1000;
		}
		
		System.out.println(sum);
	
	}

	private boolean isPrime(long n) {
		// Corner cases
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;

		// This is checked so that we can skip
		// middle five numbers in below loop
		if (n % 2 == 0 || n % 3 == 0)
			return false;

		for (int i = 5; i * i <= n; i = i + 1) {
			// System.out.println("i=" + i);
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}

		return true;
	}

}
