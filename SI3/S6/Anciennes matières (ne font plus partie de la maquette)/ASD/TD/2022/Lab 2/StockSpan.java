package ads.lab2;

import java.util.Arrays;

/**
 * A class to provide two implementations
 * of the stock span algorithm
 */
public class StockSpan {
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		int[] prices = {100,80,60,70,60,75,85,120};
		
		System.out.println(Arrays.toString(naive(prices)));
		System.out.println(Arrays.toString(smart(prices)));
	}
	// expected output
	//
	// [1, 1, 1, 2, 1, 4, 6, 8]
	// [1, 1, 1, 2, 1, 4, 6, 8]
	
	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n²) where n = prices.length
	 */
	public static int[] naive(int[] prices) {
		int[] span = new int[prices.length];

		return span;
	}

	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n) where n = prices.length
	 */
	public static int[] smart(int[] prices) throws EmptyStackException {
		int[] span = new int[prices.length];

		return span;
	}
}
