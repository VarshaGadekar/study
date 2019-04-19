package com.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://projecteuler.net/problem=5
 * 
 * 2520 is the smallest number that can be divided by each of the numbers from 1
 * to 10 without any remainder.
 * 
 * 
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 * 
 * @author VGadekar
 *
 */
public class Problem5 {
	private Map<Integer, Map<Integer, Integer>> numberFactors = new HashMap<Integer, Map<Integer, Integer>>();

	public static void main(String[] args) {
		new Problem5().process();
	}

	private void process() {

		for (int i = 1; i <= 20; i++) {
			System.out.println("get the prime factors of " + i);
			List<Integer> factors = primeFactors(i);

			System.out.println(" " + i + " -> ");
			System.out.println(factors);
			Map<Integer, Integer> factorOccurrence = new HashMap<Integer, Integer>();
			for (Integer f : factors) {
				if (factorOccurrence.get(f) == null) {
					factorOccurrence.put(f, 1);
				} else {
					int occurrence = factorOccurrence.get(f);
					factorOccurrence.put(f, occurrence + 1);
				}
			}

			numberFactors.put(i, factorOccurrence);
		}

		System.out.println(findMultiple(numberFactors));
	}

	private Long findMultiple(Map<Integer, Map<Integer, Integer>> numberFactors) {

		Map<Integer, Integer> factors = new HashMap<Integer, Integer>();
		for (int number : numberFactors.keySet()) {
			Map<Integer, Integer> numberFac = numberFactors.get(number);
			for (int f : numberFac.keySet()) {
				if (factors.containsKey(f)) {
					if (numberFac.get(f) > factors.get(f)) {
						factors.put(f, numberFac.get(f));
					}
				} else {
					factors.put(f, numberFac.get(f));
				}
			}
		}

		Long product = 1L;
		for (int f : factors.keySet()) {
			int occurrences = factors.get(f);
			for (int i = 0; i < occurrences; i++) {
				product = product * f;
			}
		}

		return product;

	}

	private List<Integer> primeFactors(Integer number) {
		List<Integer> factors = new ArrayList<Integer>();
		if (number == 1) {
			factors.add(number);
			return factors;
		}

		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				factors.add(Integer.valueOf(i));
				number = number / i;
			}
		}
		if (number > 1) {
			if (factors.isEmpty()) {
				factors.add(number);
			} else if (numberFactors.get(number) != null) {
				getAllFactors(number, factors);
			}
		}

		// check if all factors found are prime numbers
		Integer[] a = new Integer[factors.size()];
		factors.toArray(a);
		for (int n : a) {
			if (n == 1) {
				continue;
			}
			if (!isPrime(n)) {
				factors = getAllFactors(n, factors);
				factors.remove(new Integer(n));
			}
		}

		return factors;
	}

	private List<Integer> getAllFactors(Integer number, List<Integer> factors) {
		for (int n : numberFactors.get(number).keySet()) {
			int occ = numberFactors.get(number).get(n);
			int count = 0;
			while (count < occ) {
				factors.add(n);
				count++;
			}
		}
		return factors;
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

		for (int i = 5; i * i <= n; i = i + 6) {
			//System.out.println("i=" + i);
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}

		return true;
	}
}
