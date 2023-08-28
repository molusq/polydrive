package ads.lab3;

import java.util.*;

/**
 * A class for simple binary nodes
 */
public class BinaryNode<AnyType> {
		
	private AnyType data;
	private BinaryNode<AnyType> left, right;

	//////////////// constructors
	
	/**
	 * Build a binary node which is
	 * a leaf holding the value 'data'
	 */
	public BinaryNode(AnyType data) {
		this(data,null,null);
	}

	/**
	 * Build a binary node holding the value 'data' with
	 * 'left' as the left sub-tree and 'right' as the right sub-tree
	 */
	public BinaryNode(AnyType data, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	//////////////// accessors
	
	public AnyType data() {
		return data;
	}
	
	public BinaryNode<AnyType> left() {
		return left;
	}
	
	public BinaryNode<AnyType> right() {
		return right;
	}
	
	//////////////// utility methods
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	//////////////// the example of the height: 
	//////////////// apply the same scheme to the other methods
	
	public int height() {
		return height(this);
	}
	
	private int height(BinaryNode<AnyType> t) {
		if ( t == null )
			return -1;
		return 1 + Math.max(height(t.left), height(t.right));
	}

	//////////////// methods you have to complete ////////////////
	
	//////////////// lowness ////////////////
	// The lowness of a BN is the length of a
	// shortest path from the root to a leaf
	
	public int lowness() {
		return lowness(this);
	}

	public int lowness(BinaryNode<AnyType> t) {
        return 0;
	}
	
	//////////////// size ////////////////
	// The size of a BN is its number of
	// non null nodes
	
	public int size() {
		return size(this);
	}
	
	private int size(BinaryNode<AnyType> t) {
		return 0;
	}
	
	//////////////// leaves ////////////////
	// The leaves method returns the number
	// of leaves of the BN
	
	public int leaves() {
		return leaves(this);
	}
	
	private int leaves(BinaryNode<AnyType> t) {
        return 0;
	}
	
	//////////////// isomorphic ////////////////
	// Two BN are isomorphic if they have exactly
	// the same structure, no matter the data they
	// store
	
	public boolean isomorphic(BinaryNode<AnyType> t) {
		return isomorphic(this,t);
	}
	
	private boolean isomorphic(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
        return false;
	}

	//////////////// balanced1 ////////////////
	// A BN is said balanced if for each node
	// (including the root node) the absolute
	// value of the difference between the height
	// of the left node and the height of the
	// right node is at most 1
	
	// First version: you are to use the height method
	
	public boolean balanced1() {
		return balanced1(this);
	}
	
	private boolean balanced1(BinaryNode<AnyType> t) {
        return false;
	}

	//////////////// balanced2 ////////////////
	// In this version, the complexity must be O(n)
	// where n is the size of the BN (the number of
	// non null nodes)
	
	public boolean balanced2() {
		return false;
	}
	
	private int balanced2(BinaryNode<AnyType> t) {
		return 0;
	}
	
	//////////////// shapely1 ////////////////
	// A BN is said to be shapely if its height
	// is less or equal to the double of its lowness

	// First version: you are to use the height and the lowness methods
	
	public boolean shapely1() {
		return shapely1(this);
	}
	
	private boolean shapely1(BinaryNode<AnyType> t) {
		return false;
	} 
	
	//////////////// shapely2 ////////////////
	// In this version, the complexity must be O(n)
	// where n is the size of the BN (the number of
	// non null nodes)
	
	private class Pair {
		int height;
		int lowness;
		
		Pair() {
			this(0,0);
		}
		
		Pair(int height, int lowness) {
			this.height = height;
			this.lowness = lowness;
		}
	}
	
	public boolean shapely2() {
		return false;
	}
	
	private Pair shapely2(BinaryNode<AnyType> t) {
		return null;
	}
	
	//////////////////////////
	
	/**
	 * Display the BN in (ascii) 2D
	 */
    public void display() {
    	display(this,"","");
    }

    private void display(BinaryNode<AnyType> t, String r, String p) {
        if ( t == null ) {
            System.out.println(r);
        }
        else {
            String rs = t.data.toString();
            System.out.println(r + rs);
            if ( t.left != null || t.right != null ) {
                String rr = p + '|' + makeString('_',rs.length()) + ' ';
                display(t.right,rr, p + '|' + makeString(' ',rs.length() + 1));
                System.out.println(p + '|');
                display(t.left,rr, p + makeString(' ',rs.length() + 2));
            }
        }
    }

    private String makeString(char c, int k) {
        String s = "";
        for ( int i = 0; i < k; i++ ) {
            s += c;
        }
        return s;
    }
    
    ////////////////////////////////////
    
    /**
     * Build a BN of strings from it's linear prefix representation
     * "root left right". We use the '$' sign to mark leaves and/or
     * null subtree:
     * - X$ means that X is a leaf
     * - $  is the empty tree
     */
    public static BinaryNode<String> read(String inputString) {
    	Scanner input = new Scanner(inputString);
    	return read(input);
    }
    
    private static BinaryNode<String> read(Scanner input) {
    	if ( ! input.hasNext() )
    		return null;
    	String s = input.next();
    	if ( s.equals("$") )
    		return null;
    	if ( s.endsWith("$") )
    		return new BinaryNode<String>(s.substring(0,s.length()-1));
    	return new BinaryNode<String>(s,read(input),read(input));
    }

    /**
     * A short main for quick testing the read and display methods
     */
	public static void main(String[] args) {
		BinaryNode<String> t = read("A B D X 1 10$ 20$ $ Y$ E V$ W$ C F$ G$");
		t.display();
	}
}
