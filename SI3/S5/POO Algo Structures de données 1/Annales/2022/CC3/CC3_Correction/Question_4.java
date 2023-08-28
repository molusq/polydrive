    //sort the logItem on their action
    public List<LogItem> sortByAction(List<LogItem> logs){
        Comparator<LogItem> byAction= Comparator.comparing(LogItem::getAction);
        Collections.sort(logs, byAction);
        return logs;
    }