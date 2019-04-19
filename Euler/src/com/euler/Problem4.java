package com.euler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
https://projecteuler.net/problem=4

A palindromic number reads the same both ways. 
The largest palindrome made 
from the product of two 2-digit numbers is 
9009 = 91 × 99.

	91
  *	99
  -------
	819
+  8190
----------
   9009

-------------------------------------------------------------------------
	991     |      910                      
*	999     |    * 990  
   -------  |   --------
    8919    |         0
+  89190    |   + 81990
+ 891900    |  + 819900
----------  | ------------
  980009    |    901890
    
Find the largest palindrome made from the product of 
two 3-digit numbers.

 * @author VGadekar
 *
 */
public class Problem4 {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("euler-problem-4.txt", "UTF-8");

		Problem4 p4 = new Problem4();
		List<Integer> list = new ArrayList<Integer>();
		p4.findPalindrome(writer, list, 999);

		Collections.sort(list);
		System.out.println(list.get(list.size() - 1));
		writer.close();
	}

	private void findPalindrome(PrintWriter writer, List<Integer> list, int max) {
		int i = max;
		int j = max;
		int limit = max - 99;
		writer.println("i = " + i + " j = " + j + " limit = " + limit);
		int initJ = j;
		int product = 0;
		while (true) {
			product = i * j;
			if (isPalindrome(product)) {
				writer.println("i = " + i + " j = " + j + " product = " + product);
				list.add(product);
			}
			j--;

			if (j < limit) {
				if (list.isEmpty()) {
					i--;
					j = initJ;
					
					if (i < limit) {
						limit = limit - 100;
						writer.println("i = " + i + " j = " + j + " limit = " + limit);
						if (limit == 0) {
							break;
						}
					}
				} else {
					// we have something in the list
					break;
				}
			}
		}
	}

	private boolean isPalindrome(int number) {
		String n = Integer.toString(number);
		int index = n.length() / 2;
		int len = n.length() - 1;

		for (int i = 0; i < index;) {
			if (n.charAt(len) == n.charAt(i)) {
				len--;
				i++;
			} else {
				return false;
			}
		}
		return true;
	}

}
