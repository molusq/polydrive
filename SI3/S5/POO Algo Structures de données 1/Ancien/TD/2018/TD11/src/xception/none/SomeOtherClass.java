package xception.none;

public class SomeOtherClass {
    private UsefulClass uc = new UsefulClass();
    
    public int someOtherMethod(int i) {
        boolean borked = uc.usefulMethod(i);
        // ignore returned diagnostic value, because we can...
        return uc.getI();
    }
}
