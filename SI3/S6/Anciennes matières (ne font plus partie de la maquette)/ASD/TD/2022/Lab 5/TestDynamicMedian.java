package ads.lab5;

import ads.util.TestClass;
import java.util.Scanner;

/**
 * A class for interactive testing of dynamic median finding
 */
public class TestDynamicMedian extends TestClass<TestDynamicMedian> {

	private DynamicMedian<Integer> median;
	private Scanner input;
	
	public TestDynamicMedian() {
		median = new DynamicMedian<Integer>(128);
		input = new Scanner(System.in);
	}
	
	public void add() throws EmptyHeapException, FullHeapException {
		System.out.print("Enter an integer: ");
		int x = input.nextInt();
		median.add(x);
	}
	
	public void findMedian() throws EmptyHeapException {
		System.out.println(median.findMedian());
	}
	
	public void deleteMedian() throws EmptyHeapException, FullHeapException {
		System.out.println(median.deleteMedian());
	}
	
    public static void main(String[] args) {
    	TestDynamicMedian test = new TestDynamicMedian();
        test.tester();
    }
}
