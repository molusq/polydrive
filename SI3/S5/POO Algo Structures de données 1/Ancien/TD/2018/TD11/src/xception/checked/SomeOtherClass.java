package xception.checked;

public class SomeOtherClass {
    private UsefulClass uc = new UsefulClass();
    
    public int someOtherMethod(int i) throws Exception{
        boolean borked = uc.usefulMethod(i);
        // ignore returned diagnostic value, because we can...
        return uc.getI();
    }
}
