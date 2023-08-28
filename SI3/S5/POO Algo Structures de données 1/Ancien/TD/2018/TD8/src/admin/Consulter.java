package admin;

import java.util.Arrays;

class Consulter {
    private final Marks marks;

    Consulter(Marks marks) {
        this.marks = marks;
    }

    void displayMarks(String student) {
        System.out.print(student + ": ");
        for (int m : marks.getMarks(student)) {
            System.out.print(m + " ");
        }
        System.out.println();
    }
}