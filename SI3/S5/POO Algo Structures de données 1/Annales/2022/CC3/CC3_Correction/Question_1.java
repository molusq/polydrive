public class LogItemImplV0 implements LogItemV0 {
    private String value;

    public LogItemImplV0( String action,String value) {
        this.value = value;
        this.action = action;
    }

    private String action;
    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogItemImplV0 logItem = (LogItemImplV0) o;
        return Objects.equals(value, logItem.value) && Objects.equals(action, logItem.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, action);
    }
}
