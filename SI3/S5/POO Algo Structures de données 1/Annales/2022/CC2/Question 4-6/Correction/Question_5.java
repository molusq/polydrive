/**
 * A class to model a simple email client. The client is run by a particular
 * user, and sends and retrieves mail via a particular server.
 *
 * @author David J. Barnes and Michael Kolling
 * @author MBF
 */

 public class MailClient implements MailClientInterface {
    //The server used for sending and receiving.
    MailServer server = new MailServer();
    //The user  running this client.
    String user;

    /**
     * Create a mail client run by user and dependent on the the given server.
     */
    MailClient(MailServer server, String user) {
        this.server=server;
        this.user=user;
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
        if(server.getNextMailItem(user)!=null){
            return server.getNextMailItem(user).toString();
        }
        return "No new mail.";
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
        MailItem mt = new MailItem(this.user,to,message);
        return(server.post(mt));
    }

    /**
     * Send the given message to the given recipient via the attached mail
     * server.
     *
     * @param to      The intended recipient.
     * @param subject      The subject
     * @param message The text of the message to be sent.
     * @return true if the item was sent; false otherwise.
     */
    boolean sendMailItem(String to, String subject, String message) {
        MailItem mi = new MailItem(user,to,message,subject);
        return server.post(mi);
    }


    //It is better to work on the tests than on the hand!  These codes are only there to help understand.
    public static void main(String[] args) {
        // ---------------
        String JOHN = "John";
        String PAUL = "Paul";
        String MSG = "Yo! Ni hao! Tervist!";
        MailServer server = new MailServer();
        MailClient johnMailClient = new MailClient(server, JOHN);
        MailClient paulMailClient = new MailClient(server, PAUL);
        johnMailClient.sendMailItem(PAUL, MSG);
        johnMailClient.sendMailItem(PAUL, MSG);
        System.out.println(paulMailClient.nextMailItemToString());
        System.out.println(paulMailClient.nextMailItemToString());
        System.out.println(paulMailClient.nextMailItemToString());
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
/**
 * A class to model a simple mail item. The item has sender
 * and recipient addresses and a message string.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author MBF
 * @version 2016.02.29
 */
class MailItem {
    // The sender of the item.
    private final String from;
    // The intended recipient.
    private final String to;
    // The text of the message.
    private final String message;

    private String subject;

    /**
     * Create a mail item from sender to the given recipient,
     * containing the given message.
     * 
     * @param from
     *            The sender of this item.
     * @param to
     *            The intended recipient of this item.
     * @param message
     *            The text of the message to be sent.
     */
    MailItem(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public MailItem(String from, String to, String message, String subject) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }


    /**
     * @return The sender of this message.
     */
    String getFrom() {
        return from;
    }

    /**
     * @return The intended recipient of this message.
     */
    String getTo() {
        return to;
    }

    /**
     * @return The text of the message.
     */
    String getMessage() {
        return message;
    }

    /**
     * Print this mail message to the text terminal.
     */
   /* void print() {
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
    }
    */

    @Override
    public String toString() {
        return "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'';
    }
}
