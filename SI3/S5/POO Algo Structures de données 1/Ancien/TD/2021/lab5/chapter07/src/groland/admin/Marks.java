package groland.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores marks for students.
 *
 * @author Peter Sander
 * @author Florian Latapie
 */
@SuppressWarnings("serial")  // don't ask
class Marks {
    private final Map<String, int[]> marks;

    /**
     * Dependency injection in constructor.
     *
     * @param marks Student marks.
     */
    Marks(Map<String, int[]> marks) {
        this.marks = marks;
    }

    /**
     * @param student A student.
     * @return The student's marks.
     */
    int[] getMarks(String student) {
        if (marks.containsKey(student)) {
            return marks.get(student).clone();//code to develop
        } else {
            return new int[]{};
        }
    }

    /**
     * @return The set of all student names.
     */
    Set<String> getStudents() {
        return marks.keySet();
        /*code to develop
        you might want to look at the documentation for HashMap
        look for (and understand)a method which returns a Set*/
    }
}