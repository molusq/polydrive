package mailsystem;

/**
 * @author Florian Latapie
 */
public class MyMain {
    public static void main(String[] args) {
        /*String JOHN = "John";
        String PAUL = "Paul";
        String MSG = "Yo! Ni hao! Tervist!";
        MailServer server= new MailServer();
        MailClient from = new MailClient(server, JOHN);
        MailClient to = new MailClient(server, PAUL);
        from.sendMailItem(PAUL, MSG);
        MailItem item = to.getNextMailItem();
        System.out.println(item != null);
        System.out.println(MSG.equals(item.getMessage()));
        System.out.println(JOHN.equals(item.getFrom()));
        System.out.println(PAUL.equals(item.getTo()));*/

        /*String JOHN = "John";
        String PAUL = "Paul";
        String MSG = "Yo! Ni hao! Tervist!";
        MailServer server= new MailServer();
        MailClient from = new MailClient(server, JOHN);
        MailClient to = new MailClient(server, PAUL);
        from.sendMailItem(PAUL, MSG);
        from.sendMailItem(PAUL, MSG);
        to.printNextMailItem();
        to.printNextMailItem();
        to.printNextMailItem();*/

        String FRED = "Fred";
        //String WILMA = "Wilma";
        String MSG = "Did you remember to pick up the mammoth for supper? Better make it a large one if you can - we're having the whole tribe over!";
        MailServer server = new MailServer();
        MailClient from = new MailClient(server, "Wilma");
        MailClient to = new MailClient(server, "Fred");
        from.sendMailItem(FRED, "supper", MSG);
        System.out.println("Fred, you have " + server.howManyMailItems(FRED) + " mails...");
        to.printNextMailItem();
        to.printNextMailItem();
    }
}
