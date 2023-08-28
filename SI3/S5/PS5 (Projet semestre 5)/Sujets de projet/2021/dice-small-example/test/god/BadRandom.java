package god;

import java.util.Random;

public class BadRandom extends Random {

    int badValueAlwaysReturned;

    public BadRandom(int always) {
        badValueAlwaysReturned = always;
    }

    public int nextInt(int seed) {
        return badValueAlwaysReturned;
    }

}
