package admin;

import admin.sploit.Sploit;

public class Main {
    public static void main(String[] args) {
        Marks marks = new Marks();
        Consulter consulter = new Consulter(marks);
        // administration consults studentj marks
        marks.getStudents().forEach(s -> consulter.displayMarks(s));

        // Wilma introduces exploit
        new Sploit().haxMyMarks(marks.getMarks("Wilma"));
        // administration consults studentj marks again
        marks.getStudents().forEach(s -> consulter.displayMarks(s));
    }
}