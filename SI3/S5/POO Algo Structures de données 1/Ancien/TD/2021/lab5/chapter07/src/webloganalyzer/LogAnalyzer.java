package webloganalyzer;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
class LogAnalyzer {
    // Where to calculate the hourly access counts.
    private final int[] dayCounts;
    private final int[] hourCounts;
    // Use a LogfileReader to access the data.
    private final LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    LogAnalyzer() {
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[31];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    void analyzeHourlyData() {
        while (reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    void printHourlyCounts() {
        System.out.println("Hr: Count");
        for (int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    private void printData() {
        reader.printData();
    }

    int[] busiestHour(){
        int max = -1, iDeMax = -1;
        for(int i = 0 ; i < hourCounts.length ; i++){
            if (max < hourCounts[i]){
                max = hourCounts[i];
                iDeMax = i;
            }
        }
        return new int[]{iDeMax, max};
    }

    int[] quietestHour(){
        int min = Integer.MAX_VALUE, iDeMin = hourCounts.length;
        for(int i = 0 ; i < hourCounts.length ; i++){
            if (min > hourCounts[i]){
                min = hourCounts[i];
                iDeMin = i;
            }
        }
        return new int[]{iDeMin, min};
    }

    int[] busiestTwoHours(){
        int max = -1, iDeMax = -1;
        for(int i = 0 ; i < hourCounts.length-1 ; i++){
            if (max < hourCounts[i]+hourCounts[i+1]){
                max = hourCounts[i]+hourCounts[i+1];
                iDeMax = i;
            }
        }
        return new int[]{iDeMax, max};
    }

    void analyzeDailyData() {
        while (reader.hasNext()) {
            LogEntry entry = reader.next();

            int day = entry.getDay();
            dayCounts[day-1]++;
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    void printDailyCounts() {
        System.out.println("Day: Count");
        for (int day = 0; day < dayCounts.length; day++) {
            System.out.println(day+1 + ": " + dayCounts[day]);
        }
    }

}
