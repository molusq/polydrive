package ads.poo2.lab3.bst2.ORIGIN;

import java.util.*;

/**
 * A class for Binary Search Trees
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

    private T data;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    //return the value of the root of the tree
    public T getElement() {
        return data;
    }


    protected BinarySearchTree<T> getLeft() {
        return left;
    }

    protected void setLeft(BinarySearchTree<T> left) {
        this.left = left;
    }

    protected BinarySearchTree<T> getRight() {
        return right;
    }

    protected void setRight(BinarySearchTree<T> right) {
        this.right = right;
    }



    /**
     * Construct the tree.
     */
    public BinarySearchTree(T data, BinarySearchTree<T> left, BinarySearchTree<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinarySearchTree(T data) {
        this(data, null, null);
    }

    public BinarySearchTree() {
        this(null, null, null);
    }

    /////////////// isEmpty

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        if(this.data == null && this.left == null && this.right == null)
            return true;
        return false;
    }

    /////////////// makeEmpty  

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        this.data = null;
        this.left = null;
        this.right = null;
    }
    /////////////// contains

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(T x) {
        if (x==null ||isEmpty()){
            return false;
        }
        int compareResult = x.compareTo(this.getElement());
        if(compareResult ==0){
            return true;
        }if(compareResult < 0 && left !=null){
            return left.contains(x);
        }else if(compareResult >0 && right != null){
            return right.contains(x);
        }else{
            return false;
        }
    }

    //////////////// size ////////////////
    // The size of a BN is its number of
    // non-null nodes
    public int getSize() {

        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }
        if (left == null) {
            return 1+right.getSize();
        }
        else if (right == null) {
            return 1+left.getSize();
        }
        else {
            return 1+left.getSize()+right.getSize();
        }

    }

    public boolean isLeaf() {
        //to do
        return left == null && right == null;
    }

    /////////////// insert

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(T x) {
        if (x==null ||isEmpty()){
            this.data = x;
        }
        else{
            int compareResult = x.compareTo(this.getElement());
            if(compareResult < 0 ){
                if(left == null){
                    left = new BinarySearchTree<>();
                }
                left.insert(x);
            }else if(compareResult >0 ){
                if(right == null){
                    right = new BinarySearchTree<>();
                }
                right.insert(x);
            }
        }
    }

    public T findMin() {
        if(this.left == null){
            return this.getElement();
        }
        else{
            return left.findMin();
        }
    }

    /////////////// findMax

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item or null if empty.
     */
    public T findMax() {
        if(this.right == null){
            return this.getElement();
        }
        else{
            return right.findMin();
        }
    }

    /////////////// remove

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * In the test, we replace the removed element by the largest element of the left subtree
     * @param x the item to remove.
     */
    public void remove(T x) {
        int compareResult = x.compareTo(this.getElement());
        if(compareResult ==0){
            if (isLeaf()) {
                makeEmpty();
                return;
            }
        }else if(compareResult < 0 && left !=null){
            left.remove(x);
        }else if(compareResult >0 && right != null){
            right.remove(x);
        }
    }

    /////////////// removeLessThan



    /**ree all the elements
     * less than min
     * Remove from the t
     *
     * @param min the minimum value left in the tree
     */
    public void removeLessThan(T min) {
        root = removeLessThan( root, min );
    }
    
    private BinaryNode<AnyType> removeLessThan(BinaryNode<AnyType> t, AnyType min) {
        if ( t == null )
            return null;
        if ( t.element.compareTo(min) < 0 )
            return removeLessThan(t.right,min);
        t.left = removeLessThan(t.left,min);
        return t;
    }



    /////////////// removeGreaterThana

    /**
     * Remove from the tree all the elements
     * greater than max
     *
     * @param max the maximum value left in the tree
     */
    public void removeGreaterThan(T max) {

    }

    /////////////// toSortedList

    /**
     * Return a sorted list (increasing) of all
     * the elements of the tree
     *
     * @return the sorted list of all the elements of the tree
     */
    public List<T> toSortedList() {
        return null;
    }

    /////////////// sorted list to binary search tree

    /**
     * Build a binary search tree with all the
     * elements of the list
     *
     * @param list a sorted (increasing) list of elements
     */
    public BinarySearchTree(List<T> list) {
        makeTree(list, 0, list.size() - 1);
    }

    //Usefull method to build a binary search tree  from a sorted list
    //The list is divided in two parts, the first part is used to build
    //the left subtree, the second part is used to build the right subtree
    private BinarySearchTree<T> makeTree(List<T> list, int i, int j) {
        return null;
    }


    /////////////// iterator on binary search tree


    /**
     * Return an iterator over the elements of the tree.
     * The elements are enumerated in increasing order.
     */
    public Iterator<T> iterator() {
        return new BinarySearchTree<T>.BSTiterator(this);
    }


    /**
     * Inner class to build iterator over the elements of
     * a tree
     */
    private class BSTiterator implements Iterator<T> {

        // we must push some binary nodes on the stack

        Deque<BinarySearchTree<T>> stack;


        /**
         * Build an iterator over the binary node n.
         * The elements are enumerated in increasing order.
         */
        BSTiterator(BinarySearchTree<T> n) {
            stack = new ArrayDeque<>();
            // push all the left nodes on the stack
            //TODO
        }

        /**
         * Check if there are more elements in the
         * iterator
         */
        public boolean hasNext() {
            return false;
        }

        /**
         * Return and remove the next element from
         * the iterator
         */
        public T next() {
            return null;
        }

        /**
         * Unsupported operation
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    ////////////////////////////////////////////////////
    // Convenience method to print a tree
    ////////////////////////////////////////////////////


    public void display() {
        display(this, "", "");
    }
    private void display(BinarySearchTree<T> t, String r, String p) {
        if (t == null || t.isEmpty()) {
            System.out.println(r);
        } else {
            String rs = t.getElement().toString();
            System.out.println(r + rs);
            if (t.getLeft() != null || t.getRight() != null) {
                String rr = p + '|' + makeString('_', rs.length()) + ' ';
                display(t.getLeft(), rr, p + '|' + makeString(' ', rs.length() + 1));
                System.out.println(p + '|');
                display(t.getRight(), rr, p + makeString(' ', rs.length() + 2));
            }
        }
    }

    private String makeString(char c, int k) {
        return String.valueOf(c).repeat(Math.max(0, k));
    }


    ////////////////////////////////////////////////////
    // Convenience methods to build a list of integer from a string
    ////////////////////////////////////////////////////

    private static List<Integer> read(String inputString) {
        List<Integer> list = new LinkedList<>();
        Scanner input = new Scanner(inputString);
        while (input.hasNextInt())
            list.add(input.nextInt());
        input.close();
        return list;
    }

    /**
     * A short main for quick testing
     */
    public static void main(String[] args)  {
            List<Integer> list = read("50 30 70 20 40 80 60");
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            for (Integer n : list)
                bst.insert(n);
            bst.display();
            //From the lesson
            list = Arrays.asList(12, 17, 21, 19, 14, 13, 16, 9, 11, 10, 5, 8);
            System.out.println("---- From the lesson one by one");
            bst = new BinarySearchTree<>();
            //To control the position
            for (Integer n : list) {
                bst.insert(n);
            }
            System.out.println("Before insert 3");
            bst.display();
            bst.insert(3);
            System.out.println("After insert 3");
            bst.display();
            bst.remove(17);
            System.out.println("After remove 17");
            bst.display();
            System.out.println("---- From the lesson using initialisation with Arrays.asList");
            bst = new BinarySearchTree<>(list);
            bst.display();
            bst.insert(3);
            bst.display();
            bst.remove(17);
            bst.display();
    }
}
