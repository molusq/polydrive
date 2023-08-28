
import java.util.Arrays;
import java.util.Comparator;

/**
 * array class for binary heap implementation
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	
	private final T[] array; // to store the heap
	private int size;    // the number of elements in the heap
	
	// comparator to choose
	private Comparator<T> comparator = Comparable::compareTo;
	
	/**
	 * Build a heap based on an array.
	 * The elements are ordered according to the
	 * natural order on T.
	 * The heap is full
	 */
	@SafeVarargs
	public BinaryHeap(T... array) {
		this(array, Comparable::compareTo);
	}
	
	/**
	 * Return the array of the heap.
	 * This method is only for testing purposes.
	 * @return the array of the heap
	 */
	protected T[] getArray() {
		return array;
	}

	///////////// Constructors
	
	/**
	 * Build a heap of capacity n.
	 * The elements are ordered according to the
	 * natural order on T.
	 * The heap is empty.
	 * Complexity: THETA(1)
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n) {
		array = (T[]) new Comparable[n];
		size = 0;
	}
	
	/**
	 * Build a heap of capacity n.
	 * The elements are ordered according to comparator.
	 * The heap is empty.
	 * Complexity: THETA(1)
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n, Comparator<T> comparator) {
		this.array = (T[]) new Comparable[n];
		this.size = 0;
		this.comparator = comparator;
	}

	/**
	 * Build a heap based on array array.
	 * The elements are ordered according to comparator.
	 * The heap is full
	 */
	public BinaryHeap(T[] array, Comparator<T> comparator) {
		this.array = array;
		this.size = array.length;
		this.comparator = comparator;
		buildHeap();
	}
	
	///////////// Private methods
	
	/**
	 * Swap values in the array
	 * at indexes i and j.
	 * Complexity: THETA(1)
	 */
	private void swap(int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	/**
	 * Return the number of the left
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int left(int n) {
		return 2*n + 1;
	}
	
	/**
	 * Return the number of the right
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int right(int n) {
		return 2*(n + 1);
	}
	
	/**
	 * Return the number of the parent
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int parent(int n) {
		return (n - 1)/2;
	}
	
	public boolean isLeaf(T i) {
		return left((Integer) i) >= size && right((Integer) i) >= size;
	}
	
	public T inverseExtreme() {
		return array[0];
	}
	
	/**
	 * Percolate down the element at node number n
	 * Complexity: O(log(size))
	 */
	private void percolateDown(int n) {
		int g = left(n);
		int d = right(n);
		int k = n;
		
		if (g < size && comparator.compare(array[g], array[k]) > 0) {
			k = g;
		}
		if (d < size && comparator.compare(array[d], array[k]) > 0) {
			k = d;
		}
		if (k != n) {
			swap(n, k);
			percolateDown(k);
		}
	}
	
	/**
	 * Percolate up the element at node number n
	 * Complexity: O(log(size))
	 */
	private void percolateUp(int n) {
		T e = array[n];
		while (n > 0 && comparator.compare(e, array[parent(n)]) > 0) {
			array[n] = array[parent(n)];
			n = parent(n);
		}
		array[n] = e;
	}
	
	/**
	 * Arrange the elements in array such
	 * that it has the heap property.
	 * Complexity: O(size)
	 */
	private void buildHeap() {
		for (int i = parent(size - 1); i >= 0; i--) {
			percolateDown(i);
		}
	}
	
	///////////// Public methods
	
	/**
	 * Return the size of the heap
	 * (the number of elements in the heap).
	 * Complexity: THETA(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Check if the heap is empty.
	 * Complexity: THETA(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Return the extreme element.
	 * Complexity: THETA(1)
	 */
	public T extreme() throws EmptyHeapException {
		if (isEmpty()) {
			throw new EmptyHeapException();
		}
		return array[0];
	}
	
	/**
	 * Return and delete the extreme element.
	 * Ensure that the free space is at the end of the array and does not refer to any element.
	 * Complexity: O(log(size))
	 */
	public T deleteExtreme() throws EmptyHeapException {
		if (isEmpty()) {
			throw new EmptyHeapException();
		}
		T extreme = extreme();
		array[0] = array[size - 1];
		array[size - 1] = null;
		size--;
		percolateDown(0);
		return extreme;
	}
	
	/**
	 * Add a new element in the heap
	 * Complexity: O(log(size))
	 */
	public void add(T e) throws FullHeapException {
		if (size == array.length) {
			throw new FullHeapException();
		}
		array[size] = e;
		percolateUp(size);
		size++;
	}
	
	///////////// Part 3: deleting in the heap
	
	/**
	 * Delete the element e from the heap.
	 * Complexity: O(size)
	 */
	public void delete(T e) {
		int i = 0;
		while (i < size && !array[i].equals(e)) {
			i++;
		}
		if (i < size) {
			array[i] = array[size - 1];
			array[size - 1] = null;
			size--;
			percolateDown(i);
		}
	}
	
	/**
	 * Delete all the elements e from the heap.
	 * Complexity: O(size)
	 */
	public void deleteAll(T e) {
		int i = 0;
		while (i < size) {
			if (array[i].equals(e)) {
				array[i] = array[size - 1];
				array[size - 1] = null;
				size--;
			} else {
				i++;
			}
		}
		buildHeap();
	}

	public String byLevels() {
		StringBuilder bld = new StringBuilder();
		int level = 0;
		int nbNodes = 1;
		for (int i = 0; i < size; i++) {
			if (i == nbNodes) {
				bld.append( "\n");
				level++;
				nbNodes += Math.pow(2, level);
			}
			bld.append("(").append(i).append(")").append(array[i]).append(" ");
		}
		return bld.toString();
	}
	
	public String toStringByLevels() {
		return "BinaryHeap [array=" + byLevels() + ", size=" + size + ", comparator=" + comparator + "]";
	}
}
