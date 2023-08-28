package addressbook.v1t;

import java.util.Arrays;

/**
 * Provide a textual interface to an AddressBook. Different commands provide
 * access to the data in the address book.
 * 
 * One to search the address book.
 * 
 * One to allow a set of contact details to be entered.
 * 
 * One to show all the entries in the book.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookTextInterface {
    // The address book to be viewed and manipulated.
    private AddressBook book;
    // A parser for handling user commands.
    private Parser parser;

    /**
     * Constructor for objects of class AddressBookTextInterface
     * 
     * @param book
     *            The address book to be manipulated.
     */
    public AddressBookTextInterface(AddressBook book) {
        this.book = book;
        parser = new Parser();
    }

    /**
     * Read a series of commands from the user to interact with the address
     * book. Stop when the user types 'quit'.
     */
    public void run() {
        System.out.println("Address Book.");
        System.out.println("Type 'help' for a list of commands.");

        String command;
        do {
            command = parser.getCommand();
            switch (command) {
                case "add":
                    try{
                        add();
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    } catch(DuplicateKeyException e){
                        System.out.println(e.toString());
                    }
                    break;
                case "list":
                    list();
                    break;
                case "get":
                    try{
                        book.get();
                    } catch(NoMatchingDetailsException e){
                        System.out.println(e.toString());
                    }
                    break;
                case "remove":
                    try{
                        book.remove();
                    } catch(NoMatchingDetailsException e){
                        System.out.println(e.toString());
                    }
                    break;
                case "search":
                    find();
                    break;
                case "help":
                    help();
                    break;
                default:
                    break;
            }
        } while (!(command.equals("quit")));

        System.out.println("Goodbye.");
    }

    /**
     * Add a new entry.
     */
    private void add() {
        System.out.print("Name: ");
        String name = parser.readLine();
        System.out.print("Phone: ");
        String phone = parser.readLine();
        System.out.print("Address: ");
        String address = parser.readLine();
        book.addDetails("".equals(name) && "".equals(phone)
                ? null : new ContactDetails(name, phone, address));
    }

    /**
     * Find entries matching a key prefix.
     */
    private void find() {
        System.out.println("Type a prefix of the key to be found.");
        String prefix = parser.readLine();
        ContactDetails[] results = book.search(prefix);
        Arrays.stream(results)
                .forEach(r -> System.out.println(r + "\n=====\n"));
    }

    /**
     * List the available commands.
     */
    private void help() {
        parser.showCommands();
    }

    /**
     * List the address book's contents.
     */
    private void list() {
        System.out.println(book.listDetails());
    }
}
