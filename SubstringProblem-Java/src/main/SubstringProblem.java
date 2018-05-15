package main;

import java.util.Scanner;

public class SubstringProblem {
	public static void main(String args[]) {
		String string, sub;
		int i, c, length;

		Scanner in = new Scanner(System.in);
		System.out.println("Enter a string:");
		string = in.nextLine();

		length = string.length();
		
		System.out.print("Valid Strings: ");
		for (c = 0; c < length; c++) {
			for (i = 1; i <= length - c; i++) {
				sub = string.substring(c, c + i);
				if(sub.length() - countUnique(sub) == 1) {
					System.out.print(sub+" ");
				}
			}
		}
		
	}
	
	//counts the unique chars:
	public static int countUnique(String input) {
	    boolean[] exists = new boolean[Character.MAX_VALUE];
	    for (int i = 0; i < input.length(); i++) {
	        exists[input.charAt(i)] = true;
	    }

	    int count = 0;
	    for (int i = 0; i < exists.length; i++) {
	        if (exists[i] == true){
	            count++;
	        }
	    }

	    return count;
	}
}
