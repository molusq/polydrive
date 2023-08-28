package ads.lab4;

import ads.util.TestClass;

import java.util.*;
/**
 * A class for interactive testing of ranked binary search trees
 */
public class TestRankedBST extends TestClass<TestRankedBST> {
	
	private RankedBST<Integer> bst;
	private Scanner input;
	
	public TestRankedBST() {
		bst = new RankedBST<Integer>();
		input = new Scanner(System.in);
	}
	
	public void isEmpty() {
		if ( bst.isEmpty() )
			System.out.println("the tree is empty");
		else
			System.out.println("the tree is not empty");
	}
	
	public void makeEmpty() {
		bst.makeEmpty();
	}
	
	public void contains() {
		System.out.print("enter the integer you're looking for: ");
		int x = input.nextInt();
		if ( bst.contains(x) )
			System.out.println(x + " is in the tree");
		else
			System.out.println(x + " is not in the tree");
	}
	
	public void insert() {
		System.out.print("enter the integer you want to insert: ");
		int x = input.nextInt();
		bst.insert(x);
	}
	
	public void remove() {
		System.out.print("enter the integer you want to remove: ");
		int x = input.nextInt();
		bst.remove(x);
	}
	
	public void rank() {
		System.out.print("enter the integer you want the rank of: ");
		int x = input.nextInt();
		System.out.print(bst.rank(x));
	}
	
	public void element() {
		System.out.print("enter the rank you want the element of: ");
		int x = input.nextInt();
		System.out.print(bst.element(x));		
	}

    public void showTree() {
    	bst.display();
    }
    
    public static void main(String[] args) {
    	TestRankedBST test = new TestRankedBST();
        test.tester();
    }
}
