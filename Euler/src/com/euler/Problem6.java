package com.euler;
/**
 * https://projecteuler.net/problem=6
 * @author VGadekar
 *
 * Sum square difference
-----------------------------------------------------------
The sum of the squares of the first ten natural numbers is,

1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,

(1 + 2 + ... + 10)^2 = 55^2 = 3025
Hence the difference between the sum of the squares of the 
first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

Find the difference between the sum of the squares of 
the first one hundred natural numbers and the square of the sum.
-------------------------------------------------------------------------
 *
 */
public class Problem6 {
	public static void main(String[] args) {
		new Problem6().process(100);
	}

	// sum of first n number is n (n+1)/2

	private void process(int n) {

		long sumOfnNumbers = n * (n + 1) / 2;
		long squareOfSum = sumOfnNumbers * sumOfnNumbers;
		System.out.println(squareOfSum);
		long sumOfSquares = 0l;
		for (int i = 1 ; i <=100;i++) {
			sumOfSquares = sumOfSquares + (i * i);
		}
		System.out.println(sumOfSquares);
		System.out.println(squareOfSum - sumOfSquares);
	}
}
