package webloganalyzer;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
class LogAnalyzer {
    // Where to calculate the hourly access counts.
    private final int[] hourCounts;
    private final int[] dayCounts;
    // Use a LogfileReader to access the data.
    private final LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    LogAnalyzer() { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[32];
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
    void printData() {
        reader.printData();
    }

    int[] busiestHour(){
        int[] busiest = new int[2];
        busiest[0] = 0;
        busiest[1] = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            if(hourCounts[hour] > busiest[1]){
                busiest[0] = hour;
                busiest[1] = hourCounts[hour];
            }
        }
        return busiest;
    }

    int[] quietestHour(){
        int[] quietest = new int[2];
        quietest[0] = 0;
        quietest[1] = hourCounts[0];
        for(int hour = 0; hour < hourCounts.length; hour++){
            if(hourCounts[hour] < quietest[1]){
                quietest[0] = hour;
                quietest[1] = hourCounts[hour];
            }
        }
        return quietest;
    }

    int[] busiestTwoHours(){
        int[] busiest = new int[2];
        int hourC = 0;
        busiest[0] = 0;
        busiest[1] = hourCounts[0];
        for(int hour = 0; hour < hourCounts.length - 1; hour++){
            hourC = hourCounts[hour] + hourCounts[hour + 1];
            if(hourC > busiest[1]){
                busiest[0] = hour;
                busiest[1] = hourC;
            }

        }
        
        return busiest;
    }

    void analyzeDailyData(){
        while (reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }

    void printDailyCounts(){
        System.out.println("Day: Count");
        for (int day = 1; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
}
