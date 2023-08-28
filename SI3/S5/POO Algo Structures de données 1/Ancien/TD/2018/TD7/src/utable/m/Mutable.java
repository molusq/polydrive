package utable.m;

public class Mutable {
    private int value;

    public Mutable(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}