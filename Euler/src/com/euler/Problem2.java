package com.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://projecteuler.net/problem=2
 * 
 * Each new term in the Fibonacci sequence is generated by adding the previous
 * two terms. By starting with 1 and 2, the first 10 terms will be:
 * 
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * 
 * By considering the terms in the Fibonacci sequence whose values do not exceed
 * four million, find the sum of the even-valued terms.
 * 
 * @author VGadekar
 *
 */

public class Problem2 {
	private int MAX = 4000000;

	public static void main(String[] args) {
		new Problem2().start();
	}

	private void start() {
		List<Integer> fibonacci = new ArrayList<Integer>();
		List<Integer> evenFib = new ArrayList<Integer>();
		fibonacci.add(1);
		fibonacci.add(1);
		int term = 3;
		int value = 0;
		int[] matrix =new int[] {1, 1, 1, 0};
		while (value < MAX) {
			value = calculateNextFib(matrix,term);
			System.out.println("value = " + value);
			fibonacci.add(value);
			if (value % 2 == 0) {
				evenFib.add(value);
			}
			term++;
		}
		Integer[] allEvenFib = new Integer[evenFib.size()];
		allEvenFib = evenFib.toArray(allEvenFib);
		 System.out.println(Arrays.stream(allEvenFib).mapToInt(Integer::intValue).sum());
	}

	/**
	 * The matrix is defined in row packed order.
	 * 
	 * if you multiply the matrix [1, 0, 0, 1] by any matrix anster is same. if is
	 * like multiply the number by 1.
	 * 
	 * |1 0| 
	 * |1 1| 
	 *   *
	 * |1 1|
	 * |0 1|
	 *  =
     * |1 0|
     * |1 0|
	 * 
	 * 
	 * to find the nth number lets assume following matrix 21 is 7th term 
	 * |21 13|
	 * |13 8|
	 * 
	 * 
	 * 
	 * This can be defined as 7th power of following matix |1 1| |1 0|
	 *
	 *
	 * lets do square of 
	 * |1 1| 
	 * |1 0|
	 *   * 
	 * |1 1| 
	 * |1 0|
	 *   = 
	 * |2 1|
	 * |1 1|

	 * 
	 * 
	 * |1 0|
	 * |1 1| 
	 *  
	 *  here as you notice 2 is second term
	 * 
	 * so the format is
	 * 
	 * |f(n+1)   f(n)| 
	 * |f(n)   f(n-1)|
	 * 
	 *
	 * if it starts with 1 [<compute 2st term>, 1, 1, 0]
	 * 
	 * 
	 * @param term
	 * @return
	 */
	private int calculateNextFib(int[] matrix, int term) {
		int[] result = { 1, 0, 0, 1 };
		while (term != 0) { // Exponentiation by squaring
			if (term % 2 != 0)
				result = multiplyMatrices(result, matrix);
			term /= 2;
			matrix = multiplyMatrices(matrix, matrix);
		}
		return result[1];
	}

	private int[] multiplyMatrices(int[] a, int[] b) {
		int[] answer = { a[0] * b[0] + a[1] * b[2], a[0] * b[1] + a[1] * b[3], a[2] * b[0] + a[3] * b[2],
				a[2] * b[1] + a[3] * b[3] };
		return answer;

	}

}