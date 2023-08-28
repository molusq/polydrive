package ads.lab1;

/**
 * A class for practicing recursion
 */
public class Recursion {
	
	///// printing binary words

    /**
     * Print out all binary words of length k
     * Complexity: 
     */
    public static void binary(int k) {
        binary("",k);
    }

    /**
     * Print out all binary words of length d
     * prefixed by s
	 * Complexity: 
     */
    private static void binary(String s, int d) {

    }
    
    ///// printing A-B words
    
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * Complexity: 
     */
    public static void words(int x, int y) {
        words(x,y,"");
    }
 
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * prefixed by s
     * Complexity: 
     */
    private static void words(int x, int y, String s) {

    }

    ///// printing all permutations of { 0, 1, 2, ..., n - 1 }
    
    /**
     * Print out all permutations of
     * (1, 2, 3, ..., n)
     * Complexity:
     */
    public static void permutations(int n) {
        int[] p = new int[n];
        for( int i = 0; i < p.length; i++ )
            p[i] = i+1;
        permutations(p,0);
    }

    /**
     * Print out the array p for all
     * permutations of (i, ..., n).
     * This method must NOT change the array p
     * (i.e. after the call, p is the same as 
     * before the call).
     * Complexity: 
     */
    private static void permutations(int[] p, int i) {

    }

    /**
     * Prints out the content of the
     * array in the form of a permutation
     */
    private static void print(int[] p) {
        System.out.print('(');
        System.out.print(p[0]);
        for( int i = 1; i < p.length; i++ )
            System.out.print("," + p[i]);
        System.out.print(") ");
    }
    
    ///// checks if a given value can be found by summing up items in an array
    
    /**
     * Check if N can be found by adding
     * some values taken inside A
     * Complexity: 
     */
    public static boolean sum(int[] A, int N) {
        return false;
    }

    /**
     * Check if N can be found by adding
     * some values taken inside A[0..k-1]
     * Complexity: 
     */
    private static boolean sum(int[] A, int N, int k) {
        return false;
    }
}
