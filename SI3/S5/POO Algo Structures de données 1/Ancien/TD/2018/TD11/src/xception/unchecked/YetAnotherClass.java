package xception.unchecked;

public class YetAnotherClass {
    private SomeOtherClass soc = new SomeOtherClass();
    
    public double yetAnotherMethod(int i) {
        return Math.sqrt(soc.someOtherMethod(i));
    }
}
