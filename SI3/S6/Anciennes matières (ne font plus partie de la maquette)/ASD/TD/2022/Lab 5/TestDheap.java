package ads.lab5;

import ads.util.TestClass;
import java.util.Scanner;

/**
 * A class for interactive testing of d-heap
 */
public class TestDheap extends TestClass<TestDheap> {
	
	private Dheap<Integer> heap;
	private Scanner input;
	
	public TestDheap() {
		heap = new Dheap<Integer>(128,3);
		input = new Scanner(System.in);
	}
	
	public void size() {
		System.out.println(heap.size());
	}
	
	public void isEmpty() {
		if ( heap.isEmpty() )
			System.out.println("heap is empty");
		else
			System.out.println("heap is not empty");
	}
	
	public void extreme() throws EmptyHeapException {
		System.out.println(heap.extreme());
	}
	
	public void deleteExtreme() throws EmptyHeapException {
		Integer x = heap.deleteExtreme();
		System.out.println(x);
	}
	
	public void add() throws FullHeapException {
		System.out.print("Enter an integer: ");
		int x = input.nextInt();
		heap.add(x);
	}
	
	public void newHeap() {
		System.out.print("Enter the value of d for a new d-heap: ");
		int x = input.nextInt();
		heap = new Dheap<Integer>(128,x);
	}

    public static void main(String[] args) {
    	TestDheap test = new TestDheap();
        test.tester();
    }
}