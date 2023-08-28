package webloganalyzer;

class Main{
	public static void main(String[] args) {
		LogAnalyzer analyzer = new LogAnalyzer();
		analyzer.analyzeDailyData();
		analyzer.printDailyCounts();
		
	}
}