package com.euler;

import java.util.ArrayList;
import java.util.List;

/**
https://projecteuler.net/problem=7

10001st prime

By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10001 st prime number?

 * @author VGadekar
 *
 */
public class Problem7 {
	int MAX = 10001;
	private List<Long> primes = new ArrayList<Long>();
	private int limit = 100;

	public static void main(String[] args) {

		new Problem7().process();
	}

	private void process() {
		for (int i = 0; i < limit; i++) {
			if (isPrime(i)) {
				primes.add(new Long(i));
			}
		}
		System.out.println(primes);
		int size = primes.size();
		while (size <= 10001) {
			List<Long> tempList = new ArrayList<Long>();
			for (int i = limit; i < limit + 1000; i++) {
				if (isPrime(i)) {
					for (long number : primes) {
						if (i % number == 0) {
							break;
						}
					}
					tempList.add(new Long(i));
				}
			}
			primes.addAll(tempList);
			System.out.println(primes.size());
			limit = limit + 1000;
			size = primes.size();
		}
		System.out.println(primes.get(MAX-1));
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
