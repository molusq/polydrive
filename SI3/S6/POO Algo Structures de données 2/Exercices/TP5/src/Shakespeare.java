import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shakespeare {
	static final int[] scrabbleENScore = {
			// a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
			1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
	};
	private static final int[] scrabbleENDistrib = {
			// a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
			9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
	};
	private static ArrayList<String> authorizedWords;
	
	public static void loadDictionary() throws IOException {
		String fileName = "data/shakespeare.txt";
		
		try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
			authorizedWords = stream.map(String::trim).collect(Collectors.toCollection(ArrayList::new));
		}
	}
	
	public static Map<Integer, ArrayList<String>> getWordsByScore() {
		return authorizedWords.stream().collect(Collectors.groupingBy(EnglishScrabbleUtil::computeScoreOfWord, Collectors.toCollection(ArrayList::new)));
	}
	
	public static Map<Integer, ArrayList<String>> getWordsByScoreParallel() {
		return authorizedWords.parallelStream().collect(Collectors.groupingBy(EnglishScrabbleUtil::computeScoreOfWord, Collectors.toCollection(ArrayList::new)));
	}
	
	public static Map<Integer, Integer> getWordsCountByScore() {
		Map<Integer, ArrayList<String>> map = getWordsByScore();
		Map<Integer, Integer> countMap = new HashMap<>();
		for(Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
			countMap.put(entry.getKey(), entry.getValue().size());
		}
		return countMap;
	}
	
	public static Map<Integer, Integer> getWordsCountByScoreParallel() {
		Map<Integer, ArrayList<String>> map = getWordsByScoreParallel();
		Map<Integer, Integer> countMap = new HashMap<>();
		for(Map.Entry<Integer, ArrayList<String>> entry : map.entrySet()) {
			countMap.put(entry.getKey(), entry.getValue().size());
		}
		return countMap;
	}
	
	public static Map<String, Integer> getWordsWithHigherScoreThan(int score) {
		return authorizedWords.stream().filter(word -> EnglishScrabbleUtil.computeScoreOfWord(word) > score).collect(Collectors.toMap(word -> word, EnglishScrabbleUtil::computeScoreOfWord));
	}
	
	public List<String> exo4_7A() {
		// Return list of words that where EnglishScrabbleUtil.isWordAvailable() is false
		return authorizedWords.stream().filter(word -> !EnglishScrabbleUtil.isWordAvailable(word)).collect(Collectors.toList());
	}
	
	public List<String> exo4_7B() {
		// Return list of words that which aren't allows
		return authorizedWords.stream().filter(word -> !EnglishScrabbleUtil.isWordAllowed(word)).collect(Collectors.toList());
	}
	
	public List<String> exo4_7C() {
		// Return list of words  which are allowed and available
		return authorizedWords.stream().filter(word -> EnglishScrabbleUtil.isWordAllowed(word) && EnglishScrabbleUtil.isWordAvailable(word)).collect(Collectors.toList());
	}
	
	public int exo4_7D() {
		return exo4_7B().size() + exo4_7A().size();
	}
	
	public static int getNumberOfRequiredJokersToPlay(String word) {
		var scrabbleENDistribCopy = scrabbleENDistrib.clone();
		return word.chars().map(letter -> {
			int index = letter - 'a';
			if(scrabbleENDistribCopy[index] > 0) {
				scrabbleENDistribCopy[index]--;
				return 0;
			}
			return 1;
		}).sum();
	}
	
	public static List<Character> missingLetters(String word) {
		List<Character> missingLetters = new ArrayList<>();
		var scrabbleENDistribCopy = scrabbleENDistrib.clone();
		word.chars().forEach(letter -> {
			int index = letter - 'a';
			if(scrabbleENDistribCopy[index] > 0) {
				scrabbleENDistribCopy[index]--;
			} else {
				missingLetters.add((char) letter);
			}
		});
		return missingLetters;
	}
	
	public static int computeScoreOfWord(String word) {
		if (EnglishScrabbleUtil.isWordAllowed(word) && EnglishScrabbleUtil.isWordAvailable(word))
			return word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();
		else if (getNumberOfRequiredJokersToPlay(word) <= 2) {
			// Jokers count as 0
			int scoreOfMissingLetters = 0;
			for(Character letter : missingLetters(word)) {
				scoreOfMissingLetters += scrabbleENScore[letter - 'a'];
			}
			
			return word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum() - scoreOfMissingLetters;
		}
		return 0;
	}
	
	public List<String> exo5_2(String word) {
		int score = computeScoreOfWord(word);
		List<String> words = new ArrayList<>();
		for(String word2 : authorizedWords)
			if(EnglishScrabbleUtil.isWordAllowed(word2) && EnglishScrabbleUtil.isWordAvailable(word2) && computeScoreOfWord(word2) > score)
				words.add(word2);
		return words;
	}
	
	public static void main(String[] args) throws IOException {
		Shakespeare.loadDictionary();
		EnglishScrabbleUtil.loadDictionary();
		
		// 1 time
		long start = System.currentTimeMillis();
		getWordsByScore();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		getWordsByScoreParallel();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		
		// 100 times
		start = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
			getWordsByScore();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
			getWordsByScoreParallel();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		
		System.out.println("Score of word: " + computeScoreOfWord("buzzards"));
	}
}
