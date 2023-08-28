package admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
class Marks {
    private static final String BARNEY = "Barney";
    private static final String FRED = "Fred";
    private static final String WILMA = "Wilma";

    // this is voodoo, but it correctly intializes marks
    private final Map<String, int[]> marks = new HashMap<String, int[]>(){{
        put(BARNEY, new int[]{12, 8});
        put(FRED, new int[]{7, 9});
        put(WILMA, new int[]{15, 13});
    }};

    int[] getMarks(String student) {
        //return marks.get(student);
        int[] myMarks = marks.get(student);
        return Arrays.copyOf(myMarks, myMarks.length);
    }

    Set<String> getStudents() {
        return marks.keySet();
    }
}