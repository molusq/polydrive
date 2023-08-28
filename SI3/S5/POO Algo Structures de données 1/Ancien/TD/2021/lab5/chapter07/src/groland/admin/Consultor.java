package groland.admin;

import java.util.Arrays;

/**
 * Allows consulting students' marks.
 * @author Peter Sander
 * @author Florian Latapie
 */
class Consultor {
    private final Marks marks;

    Consultor(Marks marks) {
        this.marks = marks;
    }

    /**
     * Displays a student's marks.
     * @param student A student.
     */
    void displayMarks(String student) {
        System.out.print(student + ": ");
        for (int m : marks.getMarks(student)) {
            System.out.print(m + " ");
        }
        System.out.println();
    }

    /**
     * Displays all students' marks.
     */
    void displayMarks() {
        marks.getStudents().forEach(
                s -> {
                    System.out.print(s + ": ");
                    Arrays.stream(marks.getMarks(s)).forEach(
                            m -> System.out.print(m + " ")
                    );
                    System.out.println();
                }
        );
    }
}