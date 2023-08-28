package techsupport.analysis;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Keep a record of how many times each word was
 * entered by users.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
class WordCounter {
    // Associate each word with a count.
    private final HashMap<String, Integer> counts;

    /**
     * Create a WordCounter
     */
    WordCounter() {
        counts = new HashMap<>();
    }
    
    /**
     * Update the usage count of all words in input.
     * @param input A set of words entered by the user.
     */
    void addWords(HashSet<String> input) {
        for (String word : input) {
            int counter = counts.getOrDefault(word, 0);
            counts.put(word, counter + 1);
        }
    }
}
