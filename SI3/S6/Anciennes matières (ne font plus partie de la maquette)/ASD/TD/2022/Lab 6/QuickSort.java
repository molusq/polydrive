package ads.lab6;

/**
 * A class for the quicksort algorithm
 */
public class QuickSort {
	
	private static final int CUTOFF = 10;
	
	/**
	 * Sort the array in place using the quicksort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		sort(array,0,array.length-1);
	}

	/**
	 * Sort the portion array[lo,hi] in place using the quicksort algorithm
	 */	
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, int lo, int hi) {

	}

	/**
	 * Partition the portion array[lo,hi] and return the index of the pivot
	 */
	private static <AnyType extends Comparable<AnyType>> int partition(AnyType[] array, int lo, int hi) {

	}

	/**
	 * Return the index of the median of { array[lo], array[mid], array[hi] }
	 */
	private static <AnyType extends Comparable<AnyType>> int median(AnyType[] array, int lo, int mid, int hi) {

	}
	
	/**
	 * Sort array[lo, hi] in place using the insertion sort algorithm
	 */
	private static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array, int lo, int hi) {

	}
	
	/**
	 * Swap array[i] and array[j]
	 */
	private static <AnyType> void swap(AnyType[] array, int i, int j) {
		AnyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
