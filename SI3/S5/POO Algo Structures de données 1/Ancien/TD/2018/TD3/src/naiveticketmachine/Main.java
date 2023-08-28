package naiveticketmachine;

class Main {

    public static void main(String... args) {
        int paid = 30;
        TicketMachine machine = new TicketMachine(100);
        System.out.println(machine.getBalance());

        //System.out.println(machine.getPrice());
        machine.insertMoney(20);
        System.out.println(machine.getBalance());

        machine.printTicket();
        System.out.println(machine.getBalance());

        TicketMachine machine_2 = new TicketMachine(50);
        System.out.println(machine_2.getPrice());
        machine_2.insertMoney(20);
        System.out.println(machine_2.getBalance());
        machine_2.printTicket();
        System.out.println(machine_2.getBalance());
        
        /*
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
        */
    }
}
