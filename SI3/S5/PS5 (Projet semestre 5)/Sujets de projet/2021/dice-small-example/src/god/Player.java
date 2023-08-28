package god;

public class Player {

    private String name;
    private Dice dice;
    private int lastValue = -1;

    public Player(String name, Dice dice) {
        this.name = name;
        this.dice = dice;
    }

    public void play() {
        this.lastValue = dice.roll();
    }

    public int getLastValue() {
        return lastValue;
    }

}