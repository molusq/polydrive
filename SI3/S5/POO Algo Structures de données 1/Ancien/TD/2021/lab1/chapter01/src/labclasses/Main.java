package labclasses;

/**
 * @author Florian Latapie
 */
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("1", "A1");
        Student s2 = new Student("2", "A2");
        Student s3 = new Student("3", "A3");
        Student s4 = new Student("4", "A4");
        Student s5 = new Student("5", "A5");
        Student s6 = new Student("6", "A6");

        LabClass lab = new LabClass(20);

        System.out.println(s3.getName());

        lab.enrollStudent(s1);
        lab.enrollStudent(s2);

        System.out.println(lab.numberOfStudents());

        lab.printList();
    }
}
