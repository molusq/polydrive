    //return the sum of the LogItem Values
    public int sum( List<LogItem<Integer>> logs){
        int sum = 0;
        for (LogItem<Integer> l : logs){
            sum += l.getValue();
        }
        return sum;
    }