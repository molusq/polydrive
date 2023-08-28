package labclasses;

class Main {
    private void demoStudent() {
        Student student = new Student("Fred Flintstone", "flint001");
        System.out.println(student.getLoginName() + ": " + student);
        student.enrollInClass(new LabClass("Advanced navel-gazing", 24));
        student.enrollInClass(new LabClass("Astrophysics for dummies", 4));
        student.print();
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Student demo");
        main.demoStudent();
        System.out.println("\nLabClass demo");
        main.demoLabclass();
    }

    private void demoLabclass() {
        LabClass labClass = new LabClass("Astrophysics for dummies", 2);
        System.out.println(labClass);
        // labClass.enrollStudent(new Student("Barney Rubble", "rubb999"));
        // Student br = new Student("Barney Rubble", "rubb999");
        labClass.enrollStudent(new Student("Barney Rubble", "rubb999"));
        labClass.printList();
        labClass.enrollStudent(new Student("Brian May", "may123"));
        labClass.printList();
        labClass.enrollStudent(new Student("Steven Hawking", "hawk11"));
        labClass.printList();
    }
}
