package webloganalyzer;

/**
 * @author Florian Latapie
 */
public class MonMain {
    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();
        la.analyzeHourlyData();
        la.printHourlyCounts();

        /*LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeHourlyData();
        int[] busiest = analyzer.busiestHour();
        System.out.println(busiest[0] + " is busiest hour with " + busiest[1] + " counts");*/

        /*LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeHourlyData();
        int[] quietest = analyzer.quietestHour();
        System.out.println(quietest[0] + " is quietest hour with " + quietest[1] + " counts");*/

        /*LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeHourlyData();
        int[] busiest = analyzer.busiestTwoHours();
        System.out.println(busiest[0] + " starts busiest two hours with " + busiest[1] + " total counts");*/

        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyzeDailyData();
        analyzer.printDailyCounts();
    }
}
