package addressbook.v1t;

import java.util.Arrays;

/**
 * This class holds an enumeration of all command words known to the program.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class CommandWords {
    // a constant array that holds all valid command words
    private static final String validCommands[]
            = {"add", "get", "search", "list", "remove", "help", "quit",};

    /**
     * Constructor for CommandWords
     */
    public CommandWords() {
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param aString
     *            The string to be checked.
     * @return true if it is valid, false if it isn't.
     */
    public boolean isCommand(String aString) {
        if (aString != null) {
            return Arrays.stream(validCommands)
                    .filter(c -> c.equals(aString))
                    .findFirst()
                    .isPresent();
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() {
        Arrays.stream(validCommands).forEach(c -> System.out.print(c + " "));
        System.out.println();
    }
}
