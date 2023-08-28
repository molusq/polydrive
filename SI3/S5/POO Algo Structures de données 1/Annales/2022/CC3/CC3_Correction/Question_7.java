public class ReversibleQueueImpl<T> extends EnhancedQueueImpl<T> {

    public EnhancedQueueInterface<T> reverse() throws EmptyQueueException {
        EnhancedQueueInterface<T> tmp = this.copy();

        ArrayStack<T> stackToReverse = new ArrayStack<>();
        while (!tmp.isEmpty()) {
            T value = tmp.dequeue();
            stackToReverse.push(value);
        }

        EnhancedQueueInterface<T> reversed = new EnhancedQueueImpl<>();
        while (!stackToReverse.isEmpty()) {
            try {
                reversed.enqueue(stackToReverse.pop());
            } catch (EmptyStackException e) {
                assert false : "unexpected situation";
            }
        }

        return reversed;
    }
}