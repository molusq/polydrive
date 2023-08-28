package pkg1;

/**
 * @author Florian Latapie
 */
class Sub extends Souper {
    void sub() {
        System.out.println("Sub#sub");
    }

    @Override
    void souperAndSub() {
        System.out.println("Sub#souperAndSub");
        super.souperAndSub();
    }
}
