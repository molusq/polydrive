package mailsystem;

/**
 * A class to model a simple email client. The client is run by a particular
 * user, and sends and retrieves mail via a particular server.
 *
 * @author David J. Barnes and Michael Kolling
 * @author Florian Latapie
 */
class MailClient {
    private final MailServer server;
    private final String user;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    MailClient(MailServer server, String user){
        this.server = server;
        this.user = user;
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    MailItem getNextMailItem(){
        return server.getNextMailItem(user);
    }

    /**
     * Print the next mail item (if any) for this user to the text terminal.
     */
    void printNextMailItem(){
        MailItem mailItem = getNextMailItem();
        if (mailItem != null){
           mailItem.print();
        } else {
            System.out.println("No new mail.");
        }
    }

    /**
     * Send the given message to the given recipient via the attached mail
     * server.
     *
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     * @return true if the item was sent; false otherwise.
     */
    boolean sendMailItem(String to, String subject, String message){
        MailItem mi = new MailItem(user, to, subject, message);
        return server.post(mi);
    }
}