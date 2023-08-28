package ads.lab4;

import ads.util.TestClass;
import java.util.*;

/**
 * A class for interactive testing of binary search trees
 */
public class TestBinarySearchTree extends TestClass<TestBinarySearchTree> {
	
	private BinarySearchTree<Integer> bst;
	private Scanner input;
	
	public TestBinarySearchTree() {
		bst = new BinarySearchTree<Integer>();
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
	
	public void findMin() {
		try {
			System.out.println(bst.findMin());
		}
		catch ( EmptyTreeException ue ) {
			System.out.println("oops! the tree is empty!");
		}
	}
	
	public void findMax() {
		try {
			System.out.println(bst.findMax());
		}
		catch ( EmptyTreeException ue ) {
			System.out.println("oops! the tree is empty!");
		}
	}
	
	public void remove() {
		System.out.print("enter the integer you want to remove: ");
		int x = input.nextInt();
		bst.remove(x);
	}
	
	public void removeLessThan() {
		System.out.print("you want to remove all values less than: ");
		int x = input.nextInt();
		bst.removeLessThan(x);
	}
	
	public void removeGreaterThan() {
		System.out.print("you want to remove all values greater than: ");
		int x = input.nextInt();
		bst.removeGreaterThan(x);
	}
	
	public void toSortedList() {
		System.out.println(bst.toSortedList());
	}
	
    private static List<Integer> read(String inputString) {
    	List<Integer> list = new LinkedList<Integer>();
    	Scanner input = new Scanner(inputString);
    	while ( input.hasNextInt() )
    		list.add(input.nextInt());
    	input.close();
    	return list;
    }
    
    public void binarySearchTree() {
    	System.out.print("the sorted increasing list of integer: ");
    	String s = input.nextLine();
    	bst = new BinarySearchTree<Integer>(read(s));
    }
    
    public void iterateTree() {
    	for ( Integer n : bst )
    		System.out.print(n + " ");
    	System.out.println();
    }

    public void showTree() {
    	bst.display();
    }
    
    public static void main(String[] args) {
    	TestBinarySearchTree test = new TestBinarySearchTree();
        test.tester();
    }
}
