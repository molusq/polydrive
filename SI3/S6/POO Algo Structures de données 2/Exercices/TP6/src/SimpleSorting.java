
/**
 * A class for simple sorting methods
 * We use the Comparable interface to compare elements
 * It's a utility class, so we don't need to instantiate it
 * @author Marc Gaetano
 * @author Mireille blay
 */
public class SimpleSorting {

	/**
	 * Sort the array in place using the selection sort algorithm
	 */
	public static <T extends Comparable<T>> void selection(T[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i;
			T minValue = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(minValue) < 0) {
					minIndex = j;
					minValue = array[j];
				}
			}
			swap(array, i, minIndex);
		}
	}
	
	/**
	 * Sort the array in place using the insertion sort algorithm
	 */
	public static <T extends Comparable<T>> void insertion(T[] array) {
		for (int i = 1; i < array.length; i++) {
			T value = array[i];
			int j = i - 1;
			while (j >= 0 && array[j].compareTo(value) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = value;
		}
	}
	
	/**
	 * Swap array[i] and array[j]
	 */
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
