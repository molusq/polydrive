package test3.mailsystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;





//Another implementations : extends ArrayList<String>
public class GroupImpl  implements Group {

    private String name;
    private List<String> members;

    public GroupImpl(){
        this("group");
    }
    public GroupImpl(String name) {
        this(name, new ArrayList<>());
    }
    private GroupImpl(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    @Override
    public String[] getMembers() {
        ArrayList<String> memberList = new ArrayList<>(members);
        return memberList.toArray(new String[0]);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addMember(String member) {
        members.add(member);
    }

}

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
              /*  System.out.println("From: " + item.getFrom());
                System.out.println("To: " + item.getTo());
                System.out.println("Message: " + item.getMessage());
               */
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


    // ADDED -- and only this one !!
    /**
     * Send the given message to the given recipient via the attached mail
     * server.
     *
     * @param to      The intended recipient.
     * @param message The text of the message to be sent.
     * @return true if the item was sent; false otherwise.
     */

  public boolean sendMailItem(Group to, String message) {
        boolean ok = true;
        for (String member : to.getMembers()) {
            ok &= server.post(new MailItem(user, member, message));
        }
        return ok;
    }

    /**
     * Send the given message to all the getMembers of the group via the attached mail
     * server.
     *
     * @param to      The group of recipients.
     * @param subject      The subject
     * @param message The text of the message to be sent.
     * @return true if the item was sent to all the recipients; false otherwise.
     */

   public boolean sendMailItem(Group to, String subject, String message) {
        boolean ok = true;
        for (String member : to.getMembers()) {
            ok &= server.post(new MailItemWithSubject(user, member, subject, message));
        }
        return ok;
    }

    //It is better to work on the tests than on the hand!  These codes are only there to help understand.
    public static void main(String[] args) {
        // ---------------


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

package test3.mailsystem;

public class MailItemWithSubject extends MailItem{
    private String subject = "";
    MailItemWithSubject(String from, String to, String subject, String message) {
        super(from,to,message);
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", subject='" + subject + '\'';
    }
}

