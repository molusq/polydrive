class UndoableStack<T> extends ArrayStack<T> implements UndoInterface {
    private static final String PUSH_ACTION = "push";
    private static final String POP_ACTION = "pop";

    LogItemStack<T> actions ;

    // Build an undoable stack
    public UndoableStack() {
        super();
        actions = new LogItemStack<>();
    }


    // Push the value x onto the stack.
    @Override
    public void push(T x) {
        super.push(x);
        actions.push(new LogItemImpl<T>(PUSH_ACTION, x));
    }

    // Pop the stack and return the value popped
    @Override
    public T pop() throws EmptyStackException {
        T value = super.pop();
        actions.push(new LogItemImpl<T>(POP_ACTION, value));
        return value;
    }

    // undo le dernier effectif push ou pop
    public boolean undo() {
        try {
            if ((actions.isEmpty())) {
                return false;
            }
            LogItem<T> action;
            action = actions.pop();
            if (action.getAction().equals(PUSH_ACTION)) {
                super.pop();
            } else {
                super.push(action.getValue());
            }
        } catch (EmptyStackException e) {
            assert false : "unexpected exception in undo ";
        }
        return true;
    }

    public LogItem<T> lastAction() throws EmptyStackException {
        return actions.peek();
    }

}