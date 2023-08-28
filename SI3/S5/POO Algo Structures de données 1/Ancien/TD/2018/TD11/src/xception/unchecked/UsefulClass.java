package xception.unchecked;

public class UsefulClass {
    private int i = 0;
    
    /**
     * This method only works for values i >= 0.
     * Return value indicates whether the argument receives an acceptable
     * value for i. An unacceptable value of i should not be used in any application.
     * You have been warned!
     * @param i Must be >= 0.
     * @return true if i >= 0; false otherwise.
     */
    public boolean usefulMethod(int i) throws MyUsefulException {
        // yes, this code is awkward, but that's (sort of) intentional
        this.i = i;
        if (i < 0) {
            throw new MyUsefulException("Hey! i = " + i +", should be >=0");
        } else {
            return true;
        }
    }
    
    /**
     * Getter method for i.
     * @return Value of i.
     */
    public int getI() {
        return i;
    }
}
