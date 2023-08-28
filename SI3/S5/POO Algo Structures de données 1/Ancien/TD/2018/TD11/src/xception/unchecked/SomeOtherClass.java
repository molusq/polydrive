package xception.unchecked;

public class SomeOtherClass {
    private UsefulClass uc = new UsefulClass();
    
    public int someOtherMethod(int i) {
        try {
		    boolean borked = uc.usefulMethod(i);
		} catch (MyUsefulException e) {
		    // appropriate exception handler here...
		    //   one day...maybe...
		}
        return uc.getI();
    }
}
