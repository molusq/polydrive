
/**
 * A class for the heap sort algorithm.
 */
public class HeapSort {
	
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	/**
	 * Sort the array in place using the heapsort algorithm
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */	
	public static <T extends Comparable<T>> void sort(T[] array) {
		// Build the heap
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			heapify(array, i, array.length);
		}
		
		// Sort the array
		for (int i = array.length - 1; i >= 1; i--) {
			swap(array, 0, i);
			heapify(array, 0, i);
		}
	}
	
	private static <T extends Comparable<T>> void heapify(T[] array, int i, int length) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int largest = i;
		if (left < length && array[left].compareTo(array[largest]) > 0) {
			largest = left;
		}
		if (right < length && array[right].compareTo(array[largest]) > 0) {
			largest = right;
		}
		if (largest != i) {
			swap(array, i, largest);
			heapify(array, largest, length);
		}
	}
}
