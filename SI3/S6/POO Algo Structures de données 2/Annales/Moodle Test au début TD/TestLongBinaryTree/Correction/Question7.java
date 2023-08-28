package ads.poo2.exams.test3;

import java.util.*;

/**
 * A class for Binary Search Trees
 */

public class BST<T extends Comparable<? super T>> implements Iterable<T> {

    public static final String ELEMENT_NOT_FOUND = "Element not found";
    private T element;
    private BST<T> left;
    private BST<T> right;

    public T getElement() {
        return element;
    }

    public BST<T> getLeft() {
        return left;
    }

    public void setLeft(BST<T> left) {
        this.left = left;
    }

    public BST<T> getRight() {
        return right;
    }

    public void setRight(BST<T>right) {
        this.right = right;
    }
    
    /**
     *  It searches the shortest path between two given node labels.
     *  If one of the nodes is not in the tree, throw a NoSuchElementException exception with the message "Element not found".
     *  Tips : Find the common ancestor of two nodes, then traverse the tree from this common ancestor to the two nodes. Attention, the path to the departure node must be reversed.
     * @param node1 : label of the departure node
     * @param node2 : label of the arrival node
     * @return the shortest path between node1 and node2
     * @throws NoSuchElementException if one of the nodes is not in the tree
     */
    public List<T> shortestPath(T node1, T node2) {
        //Verifier que les 2 noeuds sont présents dans l'arbre (juste pour simplifier)
        if (this.find(node1) == null || this.find(node2) == null) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND);
        }
        //Si les 2 noeuds sont égaux, retourner une liste contenant le noeud
        if (node1 == node2) {
            List<T> list = new ArrayList<>();
            list.add(node1);
            return list;
        }
        BST<T> commonAncestor = this.findCommonAncestor(node1, node2);

        //Empiler les noeuds de l'ancetre commun au node1
        List<T> list = new ArrayList<>();
        Stack<T> stack = new Stack<>(); //Pile pour stocker les noeuds de la racine au node1
        fromRootToNode(commonAncestor, node1, stack);
        //Mettre dans la liste les noeuds de la racine au node1 en dépilant
        while (stack.size() != 1) {
            T x = stack.pop();
            list.add(x);
            if  (x == node2) {
                return list;
            }
        }
        fromRootToNode(commonAncestor, node2, list);
        return list;
    }


    /**
     * Ce code est une méthode qui recherche l'ancêtre commun d'un arbre binaire de recherche (Binary Search Tree - BST)
     * pour deux étiquettes de nœuds donnés.
     * La méthode prend deux nœuds "node1" et "node2" en entrée et renvoie l'ancêtre commun le plus bas des deux nœuds.
     *
     * @param node1 label of the depart node
     * @param node2 label of the target node
     * @return the common ancestor of the two nodes
     */
    public BST<T> findCommonAncestor(T node1, T node2) {
        if (! (this.contains(node1) && this.contains(node2))) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND);
        }
        if (this.element.equals(node1)|| this.element.equals(node2)) {
            return this;
        }
        if (node1 == node2) {
            return this.find(node1);
        }
        if ((this.left != null) && this.left.contains(node1) && this.left.contains(node2)) {
                return this.left.findCommonAncestor(node1, node2);
        }
        if (this.right!= null && this.right.contains(node1) && this.right.contains(node2)) {
                return this.right.findCommonAncestor(node1, node2);
        }
        return this;
    }

    private BST<T> find(T x) {
        if (x == null || isEmpty())
            return null;
        int compareResult = x.compareTo(this.element);

        if (compareResult == 0)
            return this;
        if (compareResult < 0 && left != null)
            return left.find(x);
        else if (compareResult > 0 && right != null)
            return right.find(x);
        else
            return null; // Not found
    }


    private Collection<T> fromRootToNode(BST<T> from, T node1, Collection<T> stack) throws NoSuchElementException {
        if (from == null) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND);
        }
        stack.add(from.element);
        if (from.element == node1) {
            return stack;
        }
        if (from.left != null && from.element.compareTo(node1) > 0) {
            return fromRootToNode(from.getLeft(), node1, stack);
        }
        if (from.right != null && from.element.compareTo(node1) < 0) {
            return fromRootToNode(from.getRight(), node1, stack);
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND);
    }

    /**
     * Construct the tree.
     */
    public BST(T data, BST<T> left, BST<T> right) {
        this.element = data;
        this.left = left;
        this.right = right;
    }

    public BST(T data) {
        this(data, null, null);
    }

    public BST() {
        this(null, null, null);
    }


    public boolean isEmpty() {
        return element == null;
    }

    public void makeEmpty() {
        element = null;
        left = null;
        right = null;
    }

    /////////////// getSize

    public int getSize() {
        if (isEmpty())
            return 0;
        if (isLeaf())
            return 1;
        if (left == null)
            return 1 + right.getSize();
        if (right == null)
            return 1 + left.getSize();
        return 1 + left.getSize() + right.getSize();
    }


    public boolean isLeaf() {
        return ((left == null || left.isEmpty()) &&
                (right == null || right.isEmpty()));
    }


    /////////////// contains

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */

    public boolean contains(T x) {
        if (x == null || isEmpty())
            return false;
        int compareResult = x.compareTo(this.element);

        if (compareResult == 0)
            return true;
        if (compareResult < 0 && left != null)
            return left.contains(x);
        else if (compareResult > 0 && right != null)
            return right.contains(x);
        else
            return false; // Not found
    }

    /////////////// insert

    public BST<T> createTree(T x) {
        return new BST<>(x);
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */

    public void insert(T x) {
        if (isEmpty()) {
            element = x;
            return;
        }
        int compareResult = x.compareTo(element);

        if (compareResult < 0) {
            if (left == null)
                left = createTree(x);
            else
                left.insert(x);
        } else if (compareResult > 0) {
            if (right == null)
                right = createTree(x);
            else
                right.insert(x);
        }
        // Duplicate; do nothing

    }

    /////////////// findMin

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public T findMin() {
        if (left == null || left.isEmpty())
            return element;
        return left.findMin();
    }

    /////////////// findMax

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public T findMax() {
        if (right == null || right.isEmpty())
            return element;
        return right.findMax();
    }


    /////////////// remove

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */

    public void remove(T x) {
        int compareResult = x.compareTo(element);
        if (compareResult == 0) {
            removeRoot();
        } else if (compareResult < 0 && left != null)
            left.remove(x);
        else if (compareResult > 0 && right != null)
            right.remove(x);
    }

    //If we want to remove the root of the tree
    //take care we now can have empty node in the tree !!
    private void removeRoot() {
        if (isLeaf()) {
            makeEmpty();
            return;
        }
        //the element has only one child
        if (left == null) {
            element = right.element;
            left = right.left;
            right = right.right;
            return;
        }
        //the element has only one child
        if (right == null) {
            element = left.element;
            right = left.right;
            left = left.left;
            return;
        }
        //the element has two children
        T max = left.findMax();//we can also use right.findMin()
        if (max == null) {
            assert false : "Error in removeRoot no max found";
            makeEmpty();
            return;
        }
        element = max;
        // Could be improved avoiding to search again : try with a findMaxAndRemove that returns the node and remove it
        left.remove(max);//or right.remove(min);
    }





    /////////////// sorted list to binary search tree

    /**
     * Build a binary search tree with all the
     * elements of the list
     *
     * @param list a sorted (increasing) list of elements
     */
    public BST(List<T> list) {
        if (list == null || list.isEmpty())
            return;
        BST<T> bst = makeTree(list, 0, list.size() - 1);
        this.element = bst.element;
        this.left = bst.left;
        this.right = bst.right;
    }

    private BST<T> makeTree(List<T> list, int i, int j) {
        if (i > j)
            return null;
        int m = (i + j) / 2;
        BST<T> t = new BST<>(list.get(m));
        t.left = makeTree(list, i, m - 1);
        t.right = makeTree(list, m + 1, j);
        return t;
    }

    /////////////// primeIterator on binary search tree

    /**
     * Return an primeIterator over the elements of the tree.
     * The elements are enumerated in increasing order.
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTiterator(this);
    }


    /**
     * Inner class to build primeIterator over the elements of
     * a tree
     */
    private class BSTiterator implements Iterator<T> {

        // we must push some binary nodes on the stack
        Deque<BST<T>> stack;

        /**
         * Build an primeIterator over the binary node n.
         * The elements are enumerated in increasing order.
         */
        BSTiterator(BST<T> n) {
            stack = new ArrayDeque<>();
            while (n != null && !n.isEmpty()) {
                stack.push(n);
                n = n.left;
            }
        }

        /**
         * Check if there are more elements in the
         * tree
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Return and remove the next element
         */
        public T next() {
            try {
                BST<T> bst = stack.pop();
                T value = bst.element;
                bst = bst.right;
                while (bst != null) {
                    stack.push(bst);
                    bst = bst.left;
                }
                return value;
            } catch (EmptyStackException e) {
                throw new NoSuchElementException("Empty Stack");
            }
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

    private void display(BST<T> t, String r, String p) {
        if (t == null || t.isEmpty()) {
            System.out.println(r);
        } else {
            String rs = t.element.toString();
            System.out.println(r + rs);
            if (t.left != null || t.right != null) {
                String rr = p + '|' + makeString('_', rs.length()) + ' ';
                display(t.left, rr, p + '|' + makeString(' ', rs.length() + 1));
                System.out.println(p + '|');
                display(t.right, rr, p + makeString(' ', rs.length() + 2));
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

}
