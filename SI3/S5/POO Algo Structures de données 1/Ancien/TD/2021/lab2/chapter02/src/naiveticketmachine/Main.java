package naiveticketmachine;

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

        System.out.println("\n\nAnd as if that's not bad enough...\n");
        paid = 20;
        System.out.println("Before inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.insertMoney(paid);
        System.out.println("After inserting " + paid + " cents balance = " + machine.getBalance() + " cents");
        machine.printTicket();
        System.out.println("After printing ticket balance = " + machine.getBalance() + " cents");


        TicketMachine machine2 = new TicketMachine(25);
        System.out.println(machine2.getPrice());
        machine2.insertMoney(30);
        System.out.println(machine2.getBalance());
        machine2.printTicket();
        System.out.println(machine2.getBalance());

    }
}
