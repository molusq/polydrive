package mailsystem;

class Main{
	public static void main(String[] args) {
String FRED = "Fred";
String WILMA = "Wilma";
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