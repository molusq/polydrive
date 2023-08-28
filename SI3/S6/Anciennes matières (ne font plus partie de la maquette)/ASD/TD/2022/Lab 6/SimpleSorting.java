package ads.lab6;

/**
 * A class for simple sorting methods
 */
public class SimpleSorting {

	/**
	 * Sort the array in place using the selection sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void selection(AnyType[] array) {

	}
	
	/**
	 * Sort the array in place using the insertion sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array) {

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
