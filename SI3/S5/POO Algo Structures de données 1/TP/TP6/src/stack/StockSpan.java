package stack;

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

        for ( int i = 0; i < prices.length; i++ ) {
            span[i] = 1;
            for ( int j = i-1; j >= 0 && prices[j] <= prices[i]; j-- )
                span[i]++;
        }
        
        return span;
    }

    /**
     * Compute and return the span of stocks
     * whose prices are stored in 'prices'
     * Complexity: O(n) where n = prices.length
     */
    public static int[] smart(int[] prices) throws EmptyStackException {
        int[] span = new int[prices.length];
    
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(0);
        span[0] = 1;
    
        for(int i = 1; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] <= prices[i])
                stack.pop();
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
    
        return span;
    }
}