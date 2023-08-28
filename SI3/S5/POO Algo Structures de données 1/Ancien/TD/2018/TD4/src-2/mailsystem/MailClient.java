package mailsystem;

/**
 * A class to model a simple email client. The client is run by a particular
 * user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kolling
 * @author Florian Salord
 */
class MailClient {
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private final String nom;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    MailClient(MailServer server, String user){
        this.server = server;
        nom = user;

    }

    /**
     * Return the next mail item (if any) for this user.
     */
    MailItem getNextMailItem(){
        return server.getNextMailItem(nom);
    }

    /**
     * Print the next mail item (if any) for this user to the text terminal.
     */
    void printNextMailItem(){
        MailItem mail = server.getNextMailItem(nom);
        if(mail != null){
            mail.print();
        }
        else System.out.println("No new mail.");
    }

    /**
     * Send the given message to the given recipient via the attached mail
     * server.
     * 
     * @param to
     *            The intended recipient.
     * @param message
     *            The text of the message to be sent.
     * @return true if the item was sent; false otherwise.
     */
    boolean sendMailItem(String to, String subject, String message){
        MailItem mail = new MailItem(nom, to, subject, message);
        if(server.post(mail))return true;
            else return false;
    }
}