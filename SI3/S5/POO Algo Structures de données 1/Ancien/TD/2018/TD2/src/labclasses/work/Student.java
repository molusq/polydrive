package labclasses.work;

import java.util.ArrayList;
import java.util.List;

import static labclasses.LabClass.CREDITS;


/**
 * The Student class represents a student in a student administration system. It
 * holds the student details relevant in our context.
 * 
 * @author Michael Kolling and David Barnes
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 */
class Student {
    // the student's full name
    private final String name;
    // the student ID
    private final String id;
    // the amount of credits for study taken so far
    private int credits;
    private final List<LabClass> labClasses = new ArrayList<>();

    /**
     * Create a new student with a given name and ID number.
     */
    Student(String fullName, String studentID) {
        name = fullName;
        id = studentID;
        credits = 0;
    }

    /**
     * Return the full name of this student.
     */
    String getName() {
        return name;
    }

    /**
     * Set a new name for this student.
     */
    // void changeName(String replacementName) {
    //     name = replacementName;
    // }

    /**
     * Return the student ID of this student.
     */
    String getStudentID() {
        return id;
    }

    /**
     * Add some credit points to the student's accumulated credits.
     */
    private void addCredits(int additionalPoints) {
        credits += additionalPoints;
    }

    /**
     * Return the number of credit points this student has accumulated.
     */
    int getCredits() {
        return credits;
    }

    /**
     * Enrolls this student in the given class, if not already enrolled.
     * This involves registering the class for this student and
     * registering this student in the class.
     * @param labClass In which to enroll the student.
     */
    void enrollInClass(LabClass labClass) {
        if (!labClasses.contains(labClass)) {
            labClasses.add(labClass);
            credits += CREDITS;
            labClass.enrollStudent(this);
        } else {
            System.out.println(name + ": " + labClass.getName() + " already contains " + name);
        }
    }

    /**
     * Return the login name of this student. The login name is a combination of
     * the first four characters of the student's name and the first three
     * characters of the student's ID number.
     */
    String getLoginName() {
        return name.substring(0, 4) + id.substring(0, 3);
    }

    public String toString() {
        StringBuilder str = new StringBuilder(name + ", student ID: " + id + ", credits: " + credits + "; enrolled in:");
        for (LabClass labClass : labClasses) {
            str.append("\n" + labClass.getName());
        }
        return str.toString();
    }

    /**
     * Print the student's name and ID number to the output terminal.
     */
    void print() {
        System.out.println(this);
    }
}
