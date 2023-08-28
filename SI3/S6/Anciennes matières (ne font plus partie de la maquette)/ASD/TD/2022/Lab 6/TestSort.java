package ads.lab6;

import java.util.Arrays;

import ads.util.TestClass;

/**
 * A class for interactive testing of sorting algorithms
 */
public class TestSort extends TestClass<TestSort> {
	
	// the size of the array
	private static final int SIZE = 256;
	private SlowInteger[] array;
	private SlowInteger[] unsorted;
	private SlowInteger[] sorted;
	
	/**
	 * Build a TestSort object to test various
	 * sorting methods
	 */
	public TestSort() {
		array = new SlowInteger[SIZE];
		unsorted = new SlowInteger[SIZE];
		sorted = new SlowInteger[SIZE];
		// set the slowness of the compareTo
		// method on SlowInteger objects
		SlowInteger.setSlowness(1);
		newArray();
	}
	
	public void heapsort() {
		long startTime = System.nanoTime();
		HeapSort.sort(array);
		long endTime = System.nanoTime();
		if ( Arrays.equals(array,sorted) )
			System.out.println("Sorting completed!");
		else
			System.out.println("Oops, your sort method is wrong!");
		showArrays();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void mergesort() {
		long startTime = System.nanoTime();
		MergeSort.sort(array);
		long endTime = System.nanoTime();
		if ( Arrays.equals(array,sorted) )
			System.out.println("Sorting completed!");
		else
			System.out.println("Oops, your sort method is wrong!");
		showArrays();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void quicksort() {
		long startTime = System.nanoTime();
		QuickSort.sort(array);
		long endTime = System.nanoTime();
		if ( Arrays.equals(array,sorted) )
			System.out.println("Sorting completed!");
		else
			System.out.println("Oops, your sort method is wrong!");
		showArrays();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void selectionsort() {
		long startTime = System.nanoTime();
		SimpleSorting.selection(array);
		long endTime = System.nanoTime();
		if ( Arrays.equals(array,sorted) )
			System.out.println("Sorting completed!");
		else
			System.out.println("Oops, your sort method is wrong!");
		showArrays();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void insertionsort() {
		long startTime = System.nanoTime();
		SimpleSorting.insertion(array);
		long endTime = System.nanoTime();
		if ( Arrays.equals(array,sorted) )
			System.out.println("Sorting completed!");
		else
			System.out.println("Oops, your sort method is wrong!");
		showArrays();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}

	public void showArrays() {
		System.out.println("original array:");
		System.out.println(Arrays.toString(unsorted));
		System.out.println("sorted array:");
		System.out.println(Arrays.toString(sorted));
		System.out.println("your sorted array:");
		System.out.println(Arrays.toString(array));		
	}
	
	public void newArray() {
		for ( int i = 0; i < array.length; i++ ) {
			array[i] = new SlowInteger(1 + (int) (Math.random() * SIZE));
			sorted[i] = unsorted[i] = array[i];
		}
		Arrays.sort(sorted);
	}
	
	private void restoreArray() {
		for ( int i = 0; i < array.length; i++ ) {
			array[i] = unsorted[i];
		}
	}
	
    public static void main(String[] args) {
    	TestSort test = new TestSort();
        test.tester();
    }
}
