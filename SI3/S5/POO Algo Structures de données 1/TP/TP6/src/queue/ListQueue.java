package queue;

/**
 * A class for list-based queue
 */
public class ListQueue<T> implements QueueInterface<T> {
	
	private ListNode head;
	private ListNode tail;
	private int size;
	/**
	 * Build an empty queue
	 * Complexity: THETA(1)
	 */
	public ListQueue() {
		head = null;
		tail = null;
		size = 0;
	}
		
	/**
	 * Return the number of elements
	 * currently in the queue
	 * Complexity: THETA(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return the next value to be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: THETA(1)
	 */
	public T peek() throws EmptyQueueException {
		return head.getValue();
	}
	
	/**
	 * Enqueue x in the queue
	 * Complexity: THETA(1)
	 */
	public void enqueue(T x) {
		ListNode newNode = new ListNode(x);
		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: THETA(1)
	 */
	public T dequeue() throws EmptyQueueException {
		T value = head.getValue();
		head = head.getNext();
		size--;
		return value;
	}
	
	/**
	 * Return a string representation of the queue
	 * in the form of "<- A B C <-" where A is the
	 * front and C the tail of the queue
	 * Complexity: THETA(n) where n is the number
	 * of items currently in the queue
	 */	
	public String toString() {
		String result = "<- ";
		ListNode current = head;
		while (current != null) {
			result += current.getValue() + " ";
			current = current.getNext();
		}
		result += "<-";
		return result;
	}
	
	//////////////////////////////////////////////
	
	/**
	 * A private class for list node
	 */
	private class ListNode {
		T data;
		ListNode next;
		
		ListNode(T data) {
			this.data = data;
			this.next = null;
		}
		
		public T getValue() {
			return data;
		}
		
		public void setNext(ListNode newNode) {
			next = newNode;
		}
		
		public ListNode getNext() {
			return next;
		}
	}
}
