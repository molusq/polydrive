package labclasses.work;

/**
 * The LabClass class represents an enrollment list for one lab class. It stores
 * the time, room and participants of the lab, as well as the instructor's name.
 * 
 * @author Michael Kolling and David Barnes
 * @author <a href="mailto:sander@unice.fr">Peter Sander</a>
 */
class LabClass {
    static final int MAX_STUDENTS = 24;
    static final int CREDITS = 30;
    private final String name;
    private String instructor;
    private String room;
    private String timeAndDay;
    private final Student[] students;
    int capacity;  // package visibility just for testing
    private int enrolled;

    LabClass(String name) {
        this(name, MAX_STUDENTS);
    }

    /**
     * Create a LabClass with a maximum number of enrolments. All other details
     * are set to default values.
     */
    LabClass(String name, int maxNumberOfStudents) {
        this.name = name;
        instructor = "unknown";
        room = "unknown";
        timeAndDay = "unknown";
        students = new Student[maxNumberOfStudents];
        capacity = maxNumberOfStudents;
    }

    String getName() {
        return name;
    }

    private boolean alreadyEnrolled(Student student) {
        for (int i = 0; i < enrolled; i++) {
            if (students[i] == student) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a student to this LabClass.
     */
    void enrollStudent(Student student) {
        if (enrolled == capacity) {
            System.out.println("The class is full, " + student.getName() + " cannot enroll. "
                    + enrolled + " enrolled; capacity " + capacity);
        } else if (!alreadyEnrolled(student)) {
            students[enrolled++] = student;
            student.enrollInClass(this);
        // } else {
        //     System.out.println(name + ": " + student.getName() + " already enrolled in " + name);
        }
    }

    /**
     * Return the number of students currently enrolled in this LabClass.
     */
    int numberOfStudents() {
        return enrolled;
    }

    /**
     * Set the room number for this LabClass.
     */
    void setRoom(String roomNumber) {
        room = roomNumber;
    }

    /**
     * Set the time for this LabClass. The parameter should define the day and
     * the time of day, such as "Friday, 10am".
     */
    void setTime(String timeAndDayString) {
        timeAndDay = timeAndDayString;
    }

    /**
     * Set the name of the instructor for this LabClass.
     */
    void setInstructor(String instructorName) {
        instructor = instructorName;
    }

    // @Override
    // String toString() {
    //     StringBuilder list = new StringBuilder("Lab class " + timeAndDay);
    //     list.append("\nInstructor: " + instructor + "   Room: " + room);
    //     list.append("\nClass list:");
    //     for (Student student : students) {
    //         list.append(student.toString());
    //     }
    //     list.append("Number of students: " + numberOfStudents());
    //     return list.toString();
    // }

    public String toString() {
        StringBuilder list = new StringBuilder(enrolled + " students enrolled in " + name + ":");
        for (int i = 0; i < enrolled; i++) {
            list.append("\n" + students[i].getName() + ", " + students[i].getStudentID());
        }
        return list.toString();
    }

    /**
     * Print out a class list with other LabClass details to the standard
     * terminal.
     */
    void printList() {
        System.out.println(this);
    }
}
