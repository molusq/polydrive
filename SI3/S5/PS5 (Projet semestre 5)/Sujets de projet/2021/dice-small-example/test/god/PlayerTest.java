package god;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class PlayerTest {

    Player p;
    
    @Test
    public void lastValueNotInitialized() {
        p = new Player("John Doe", new Dice(new Random()));
        assertEquals(-1, p.getLastValue());
    }

    @Test
    public void lastValueInitialized() {
        int trickedValue = 4;
        p = new Player("John Doe", new Dice(new BadRandom(trickedValue)));
        p.play();
        assertEquals(trickedValue+1,p.getLastValue());
    }
    

    
}