package stack;

/**
 * A class for stacks supporting the findMin operation in THETA(1)
 */
public class StackMin<T extends Comparable<T>> {
	// déclarions ajoutées après le TD
	ArrayStack<T> stack;
	ArrayStack<T> min;
	
	/**
	 * Build an empty stack
	 * Complexity: THETA(1)
	 */
	public StackMin() {
		stack = new ArrayStack<T>();
		min = new ArrayStack<T>();
	}
	
	/**
	 * Check if the stack is empty
	 * Complexity: THETA(1)
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Return the next value to be popped from the stack
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */
	public T peek() throws EmptyStackException {
		return stack.peek();
	}
	
	/**
	 * Push the value x onto the stack.
	 * If needed, the underlying array 
	 * will be enlarged by twice its size
	 * Complexity: THETA(1)
	 */
	public void push(T x) throws EmptyStackException {
		stack.push(x);
		if (min.isEmpty() || x.compareTo(min.peek()) <= 0)
			min.push(x);
	}
	
	/**
	 * Pop the stack and return the value popped
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */
	public T pop() throws EmptyStackException {
		T x = stack.pop();
		if (x.compareTo(min.peek()) == 0)
			min.pop();
		return x;
	}
	
	/**
	 * Return the minimum value currently in the stack
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */
	public T findMin() throws EmptyStackException {
		return min.peek();
	}
	
	public String toString() {
		return stack.toString();
	}
}