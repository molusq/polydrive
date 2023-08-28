
package god;

import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class DiceTest {

	Dice theDice;

	@Test
	public void rollReturnsAValue() {
		theDice = new Dice(new Random());
		for (int i = 0; i < 100; i++) {
			int result = theDice.roll();
			assertTrue(result >= 1);
			assertTrue(result <= 6);
		}
	}

	@Test(expected = RuntimeException.class)
	public void identifyBadValuesGreaterThanNumberOfFaces() {
		Random tooMuch = new BadRandom(7);
		theDice = new Dice(tooMuch);
		theDice.roll();
	}
	
    @Test(expected = RuntimeException.class)
    public void identifyBadValuesLesserThanOne() {
        Random notEnough = new BadRandom(-2);
        theDice = new Dice(notEnough);
        theDice.roll();
    }
	

}
