package test3.mailsystem;
/**
 * A class to model a simple email client. The client is run by a particular
 * user, and sends and retrieves mail via a particular server.
 *
 * @author David J. Barnes and Michael Kolling
 * @author MBF
 */

public class MailClient implements MailClientInterface {
    //The server variable used for sending and receiving.
    final MailServer server;

    //The user variable running this client.
    private final String user;

    /**
     * Create a mail client run by user and dependent on the the given server.
     */
    MailClient(MailServer server, String user) {
        this.server = server;
        this.user = user;
    }

    /**
     * Return the next mail item (if any) for this user presents on the linked server
     * else return null;
     */
    @Override
    public MailItem getNextMailItem() {
        return server.getNextMailItem(user);
    }

    /**
     * Returns the next mail item (if any) for this user as a string
     */
    @Override
    public String nextMailItemToString() {
        MailItem item = getNextMailItem();
        if (item == null) {
            return ("No new mail.");
        } else {
            return (item.toString());
        }
    }

    /**
     * Send the given message to the given recipient via the attached mail
     * server.
     *
     * @param to      The intended recipient.
     * @param message The text of the message to be sent.
     * @return true if the item was sent; false otherwise.
     */
    @Override
    public boolean sendMailItem(String to, String message) {
        return server.post(new MailItem(user, to, message));
    }


    



    /**
     *
     * @return the name of the user of the mail client
     */
    @Override
    public String getUser() {
        return user;
    }

}