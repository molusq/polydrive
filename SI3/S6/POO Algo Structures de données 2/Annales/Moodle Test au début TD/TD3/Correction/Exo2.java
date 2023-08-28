
/**
 * A class for simple binary nodes
 */

public class BinaryTree<T> {

	private  T data;
	private BinaryTree<T> leftBT;
	private BinaryTree<T> rightBT;



	//////////////// constructors
	/**
	 * Build a binary node which is
	 * a leaf holding the value 'getData'
	 */
	public BinaryTree(T data) {
		this(data,null,null);
	}

	/**
	 * Build a binary node holding the value 'getData' with
	 * 'leftBT' as the leftBT subtree and 'rightBT' as the rightBT subtree
	 */
	public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
		this.data = data;
		this.leftBT = left;
		this.rightBT = right;
	}
	
	//////////////// accessors
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinaryTree<T> left() {
		return leftBT;
	}
	
	public BinaryTree<T> right() {
		return rightBT;
	}

	public void setLeftBT(BinaryTree<T> node) {
		leftBT = node;

	}
	public void setRightBT(BinaryTree<T> node) {
		rightBT = node;

	}

	//////////////// utility methods
	
	public boolean isLeaf() {
		return leftBT == null && rightBT == null;
	}

	/**
	 * @param data
	 * @return true if the tree contains the data
	 */
	public boolean contains(T data) {
		return getData().equals(data)
				|| (leftBT != null && leftBT.contains(data))
				|| (rightBT != null && rightBT.contains(data));
	}


	/**
	 * a Tree is full if all the nodes have 0 or 2 children
	 * @return true if the tree is full
	 */
	public boolean isFull() {
		if (isLeaf()) {
			return true;
		} else if (leftBT == null || rightBT == null) { //the both can't be null
			return false;
		} else {
			return leftBT.isFull() && rightBT.isFull();
		}
	}

	private static final int NOT_PERFECT = -1;

	/**
	 * a Tree is perfect if all the nodes have 0 or 2 children
	 * and all the leaves are at the same level
	 * @return true if the tree is perfect
	 */
	public boolean isPerfect() {
		return (this.perfectLevel() != NOT_PERFECT) ;
	}

	private int perfectLevel() {
		if (isLeaf()) {
			return 0;
		} else
			if (leftBT == null || rightBT == null) { //the both can't be null
			return NOT_PERFECT;
		} else {
			int leftLevel = leftBT.perfectLevel();
			int rightLevel = rightBT.perfectLevel();
			if (leftLevel == NOT_PERFECT || rightLevel == NOT_PERFECT) {
				return NOT_PERFECT;
			} else if (leftLevel == rightLevel) {
				return leftLevel + 1;
			} else {
				return NOT_PERFECT;
			}
		}
	}

	//////////////// toString ////////////////

	public String toString(){
		String result;
		//Apply
		String str = this.data + "\n";
		result = str + traversePreOrderToString(this.left(),"|", makeString('_',str.length()-1) );
		result  += traversePreOrderToString(this.right(),"", "|"+makeString('_',str.length()-1) );

		return result;
	}

	private String traversePreOrderToString(final BinaryTree<T> root, String height, String branch) {
		String str;
		String strBranch = height + branch;
		if (root == null) {
			return strBranch + "\n";
		}
		int addedChar = root.getData().toString().length();
		str = strBranch + " " + root.getData() + "\n";
		if (root.isLeaf())
			return str ;
		String newHeight = height + makeString(' ',branch.length()+1 )+"|";
		String newBranch = makeString('_',addedChar);
		str+= traversePreOrderToString(root.left(),newHeight,newBranch);
		str += newHeight + "\n";
		//pour le 2e fils, on ne veut plus descendre la branche du parent
		newHeight = height + makeString(' ',branch.length()+1 );
		str += traversePreOrderToString(root.right(),newHeight,"|"+newBranch);
		return str;
	}
	protected String makeString(char c, int k) {
		return  String.valueOf(c).repeat(k);
	}


}

