package com.euler;

import java.util.Arrays;

/**
 * 
https://projecteuler.net/problem=30


Digit fifth powers
Problem 30 

Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

1634 = 1^4 + 6^4 + 3^4 + 4^4
8208 = 8^4 + 2^4 + 0^4 + 8^4
9474 = 9^4 + 4^4 + 7^4 + 4^4

As 1 = 1^4 is not a sum it is not included.

The sum of these numbers is 1634 + 8208 + 9474 = 19316.

Find the sum of all the numbers that can be written 
as the sum of fifth powers of their digits.





 * @author VGadekar
 *
 */
public class Problem30 {
	public static void main(String[] args) {
		new Problem30().process();
	}

	private void process() {
		double[] fourthPowers = new double[]{Math.pow(1, 4),
				Math.pow(2, 4),
				Math.pow(3, 4),
				Math.pow(4, 4),
				Math.pow(5, 4),
				Math.pow(6, 4),
				Math.pow(7, 4),
				Math.pow(8, 4),
				Math.pow(9, 4)};
			
			System.out.println(Arrays.toString(fourthPowers));
			System.out.println(Arrays.stream(fourthPowers).sum());
			double[] fifthPowers = new double[]{Math.pow(1, 5),
			Math.pow(2, 5),
			Math.pow(3, 5),
			Math.pow(4, 5),
			Math.pow(5, 5),
			Math.pow(6, 5),
			Math.pow(7, 5),
			Math.pow(8, 5),
			Math.pow(9, 5)};
		
		System.out.println(Arrays.toString(fifthPowers));
		System.out.println(Arrays.stream(fifthPowers).sum());
		/**
		 * 
		 * 
		 276 to 120825
		 */
		for (int number = 59049; number < 120825; number++) {
//			char[] charDigits = (Integer.toString(i)).toCharArray();
//			int totalDigits = charDigits.length;
//			int[] intDigits = new int [totalDigits];
//			int index = 0;
//			for (char c: charDigits) {
//				int d = Character.digit(c, 10);
//				intDigits[index] = d;
//				index++;
//			}
//			Arrays.sort(intDigits);
//			for (int d = totalDigits - 1 ; d >=0; d--) {
//			double powerVal = fifthPowers[d];
//			if (number > ) {
//				number = number - fifthPowers[intDigits[totalDigits - 1]];
//			}
//			}
			
			
			
		}
	
	}
}
