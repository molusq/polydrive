package techsupport.v2;

import java.util.Scanner;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is then chopped into words, and a set of words 
 * is provided.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.2 (2016.02.29)
 */
class InputReader {
    private final Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    InputReader() {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    String getInput() {
        System.out.print("> ");         // print prompt
        String inputLine = reader.nextLine();

        return inputLine;
    }
}

