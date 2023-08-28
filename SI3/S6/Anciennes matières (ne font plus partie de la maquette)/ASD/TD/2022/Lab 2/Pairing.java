package ads.lab2;

import java.io.*;
import java.util.*;

/**
 * A class to find pairs (x,y) of integers inside an increasing
 * sequence matching y = x + n for a given n.
 */
public class Pairing {
	
	/**
	 * Display all the pairs (x,y), x and y  in the input, such that y = x + n
	 * The input contains an entirely increasing (strict) sequence of integers
	 * Running time complexity: THETA(S) where S is the size of the input
	 * Extra memory usage: O(n)
	 */
	public static void showPairs(int n, Scanner input) throws EmptyQueueException {

	}
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws FileNotFoundException, EmptyQueueException {
		// put the right path here
		String filepath = "/your/path/here/big-file.txt";
		Scanner input = new Scanner(new File(filepath));
		showPairs(1273,input);
		input.close();
	}

}
