package xception.checked;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception{
        Random rand = new Random();
        int i = rand.nextInt(3) - 1;
        double sqres = new YetAnotherClass().yetAnotherMethod(i);
        System.out.println("sqres = " + sqres);
        // if something weird happens then blows up
        //  and you have to admit that sqres != sqres -> true is a bit weird
        if (sqres != sqres) {
            throw new ArithmeticException("Panic! " + sqres + " != " + sqres);
        }
    }
}
