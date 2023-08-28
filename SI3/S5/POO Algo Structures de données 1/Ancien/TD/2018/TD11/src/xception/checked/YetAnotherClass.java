package xception.checked;

public class YetAnotherClass {
    private SomeOtherClass soc = new SomeOtherClass();
    
    public double yetAnotherMethod(int i) throws Exception{
        return Math.sqrt(soc.someOtherMethod(i));
    }
}
