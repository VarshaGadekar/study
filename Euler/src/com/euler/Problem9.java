package com.euler;
/**
 * 

https://projecteuler.net/problem=9

Special Pythagorean triplet

A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.


 * @author VGadekar
 *
 */
public class Problem9 {
	public static void main(String[] args) {
		// 3 + 4 + 5 = 12
		// all numbers are less than 6 that is 12/2
		/**
		 * 2500
		 * 
		 * 300 400 500
		 * 
		 * ------ so the numbers are < 500.
		 * 
		 * ------
		 *
		 * 
		 * 
		 * 
		 */
		boolean found = false;
		int size = 1001;
		double[] squares = new double[size];
		int index = 0;
		for (int i = 1; i <= size - 1; i++) {
			squares[index] = i * i;
			index++;
		}
		for (int i = size; i >= 0; i--) {
			double c = squares[i - 1];
			// System.out.println("i=" + i);
			// System.out.println(" c = " + c);
			for (int j = 0; j < size - 1; j++) {
				// System.out.println("j=" + j);
				double b = squares[j];
				// System.out.println(" b = " + b);
				for (int k = size - 2; k >= 0; k--) {
					// System.out.println("k=" + k);
					if (k == j) {
						continue;
					}
					double a = squares[k];
					// System.out.println(" a = " + a);
					if (a + b == c) {

						// System.out.println(Math.sqrt(a) + " " + Math.sqrt(b) + " " + Math.sqrt(c));
						if (Math.sqrt(a) + Math.sqrt(b) + Math.sqrt(c) == 1000) {
							System.out.println(a + " " + b + " " + c);
							System.out.println("a=" + Math.sqrt(a) + " b=" + Math.sqrt(b) + " c=" + Math.sqrt(c));
							System.out.printf("%f", Math.sqrt(a) * Math.sqrt(b) * Math.sqrt(c));
							System.out.println("");
							System.out.println("found");
							found = true;
						}
					}
				}
				if (found) {
					break;
				}
			}
			if (found) {
				break;
			}
		}
	}

}
