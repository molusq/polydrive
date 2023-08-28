public class LogItemImpl<V> implements LogItem<V> {
    private V value;

    public LogItemImpl(String action,V value) {
        this.value = value;
        this.action = action;
    }

    private String action;
    
    @Override
    public String getAction() {
        return action;
    }

    @Override
    public V getValue() {
        return value;
    }
    
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogItemImpl<?> logItem = (LogItemImpl<?>) o;
        return Objects.equals(value, logItem.value) && Objects.equals(action, logItem.action);
    }
    
        @Override
    public int hashCode() {
        return Objects.hash(value, action);
    }
    //Pas demandé et pas utilisé
    @Override
    public String toString() {
        return "Log{" +
                "value=" + value +
                ", action='" + action + '\'' +
                '}';
    }
    
    
}
