import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EnglishScrabbleUtilTest {
	
	@Test
	void test() throws IOException {
		
		EnglishScrabbleUtil.loadDictionary();
		
		assertEquals(0, EnglishScrabbleUtil.computeScoreOfWord(""));
		assertEquals(0, EnglishScrabbleUtil.computeScoreOfWord(" "));
		assertEquals(8, EnglishScrabbleUtil.computeScoreOfWord("Hello"));
		assertEquals(29, EnglishScrabbleUtil.computeScoreOfWord("Buzzards"));
		assertEquals(87, EnglishScrabbleUtil.computeScoreOfWord("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(EnglishScrabbleUtil.isWordAllowed("Hello"));
		assertTrue(EnglishScrabbleUtil.isWordAllowed("inspirit"));
		assertTrue(EnglishScrabbleUtil.isWordAllowed("Buzzards"));
		assertFalse(EnglishScrabbleUtil.isWordAllowed("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(EnglishScrabbleUtil.isWordAvailable("abcdefghijklmnopqrstuvwxyz"));
		assertTrue(EnglishScrabbleUtil.isWordAvailable("hello"));
		assertFalse(EnglishScrabbleUtil.isWordAvailable("Buzzards"));
	}
}
