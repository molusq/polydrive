package ads.lab2;

/**
 * An interface for queue classes
 */
public interface QueueInterface<AnyType> {
	
	/**
	 * Check if the queue is empty
	 */
	public default boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Return the number of elements
	 * currently in the queue
	 */
	public int size();
	
	/**
	 * Return the next value to be dequeued
	 * If the queue is empty throws EmptyQueueException
	 */
	public AnyType peek() throws EmptyQueueException;
	
	/**
	 * Enqueue x in the queue
	 */
	public void enqueue(AnyType x);
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 */
	public AnyType dequeue() throws EmptyQueueException;
}
