package betterticketmachine;

class Main {

    public static void main(String... args) {
        int paid = 30;
        TicketMachine machine = new TicketMachine(25);
        System.out.println("Ticket price = " + machine.getPrice() + " cents\n");
        System.out.println("Before inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.insertMoney(paid);
        System.out.println("After inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.printTicket();
        System.out.println("After printing ticket balance = " + machine.getBalance() + " cents");
        System.out.println("Refunding = " + machine.refundBalance() + " cents");
        System.out.println("After refunding balance = " + machine.getBalance() + " cents");

        System.out.println("\n\nThat's better...\n");
        paid = 20;
        System.out.println("Before inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.insertMoney(paid);
        System.out.println("After inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.printTicket();
        System.out.println("After printing ticket balance = " + machine.getBalance() + " cents");
        System.out.println("Refunding = " + machine.refundBalance() + " cents");
        System.out.println("After refunding balance = " + machine.getBalance() + " cents");
    }
}
