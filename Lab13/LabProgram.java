/**
 * LabProgram.java
 * CIS 22C, Lab 13
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private final int SIZE = 10;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testHashTableInt()");
        if (lab.testHashTableInt() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetNumElements()");
        if (lab.testGetNumElements() == 0) {
            msg("-No errors found");
        }
        msg("Calling testAdd()");
        if (lab.testAdd() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetLoadFactor()");
        if (lab.testGetLoadFactor() == 0) {
            msg("-No errors found");
        }
        msg("Calling testBucketToString()");
        if (lab.testBucketToString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testFind()");
        if (lab.testFind() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGet()");
        if (lab.testGet() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testHashTableInt() {
        int cntErr = 0;
        int numElems = 0;
        double load = 0.0;
        HashTable<Student> ht = new HashTable<>(SIZE);
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-HashTable.getNumElements() must return 0 not " + numElems
                + " when HashTable has no elements.");
            cntErr++;
        }
        ht.add(new Student("Gus", 44));
        //assertEquals(1, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 1) {
            msg("-HashTable.getNumElements() must return 1 not " + numElems
                + " for HashTable with 1 element.");
            cntErr++;
        }
        //assertEquals(.1, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.1) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.1 not " + load
                + " for HashTable size 10 with 1 element.");
            cntErr++;
        }
        //assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(-1);});
        try {
            ht = new HashTable<>(-1);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetNumElements() {
        int cntErr = 0;
        int numElems = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(0, empty.getNumElements());
        numElems = empty.getNumElements();
        if (numElems != 0) {
            msg("-HashTable.getNumElements() must return 0 not " + numElems
                + " for HashTable with no elements.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
            new Student("Andrea", 66), new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(4, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != students.length) {
            msg("-HashTable.getNumElements() must return " + students.length
            + " not " + numElems + " for HashTable with " + students.length
            + " elements.");
            cntErr++;
        }
        return cntErr;
    }

    private int testAdd() {
        int cntErr = 0;
        int numElems = 0;
        int bucket = 0;
        String str = "";
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student gus = new Student("Gus", 44);
        bucket = gus.hashCode() % SIZE;
        ht.add(gus);
        //assertEquals(1, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 1) {
            msg("-HashTable.getNumElements() must return 1 not " + numElems
                + " for HashTable with 1 element.");
            cntErr++;
        }
        //assertTrue(ht.contains(new Student("Gus", 44))); // N/A use following
        //assertEquals("Gus: 44 \n", ht.bucketToString(bucket));
        str =  ht.bucketToString(bucket);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                + ") must return \"" + gus + " \\n\""
                + " after adding Student(" + gus + ")");
            cntErr++;
        }
        Student tay = new Student("Tay", 45);
        bucket = tay.hashCode() % SIZE;
        ht.add(tay);
        //assertEquals(2, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 2) {
            msg("-HashTable.getNumElements() must return 2 not " + numElems
                + " for HashTable with 2 elements.");
            cntErr++;
        }
        //assertEquals("Tay: 45 \n", ht.bucketToString(bucket));
        str =  ht.bucketToString(bucket);
        if (!str.equals("Tay: 45 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                + ") must return \"" + tay + " \\n\""
                + " after adding Student(" + tay + ")");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.add(noStu);});
        try {
            ht.add(noStu);
            msg("-Must throw NullPointerException when the object to add"
                + " is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetLoadFactor() {
        int cntErr = 0;
        double load = 0.0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(0.0, empty.getLoadFactor());
        load = empty.getLoadFactor();
        if (Math.abs(load - 0.0) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.0 not " + load
                + " for HashTable size 10 with no elements.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44),
            new Student("Tanya", 55), new Student("Andrea", 66),
            new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(0.4, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.4) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.4 not " + load
                + " for HashTable size 10 with 4 elements.");
            cntErr++;
        }
        //ht.delete(new Student("Gus", 44));
        //assertEquals(0.3, ht.getLoadFactor());
        // no delete yet so use following
        ht.add(new Student("Joey", 21));
        //assertEquals(0.5, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.5) >= 0.001) {
            msg("-HashTable.getLoadFactor() must return 0.5 not " + load
                + " for HashTable size 10 with 5 elements.");
            cntErr++;
        }
        return cntErr;
    }

    private int testBucketToString() {
        int cntErr = 0;
        int bucket = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals("\n", empty.bucketToString(0));
        str =  empty.bucketToString(0);
        if (!str.equals("\n")) {
            msg("-HashTable.bucketToString(0) must return \"\\n\""
                + " for empty HashTable size " + SIZE + ".");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44),
            new Student("Tanya", 55), new Student("Andrea", 66),
            new Student("Tay", 45)};
        for (int idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals("Tanya: 55 Tay: 45 \n", ht.bucketToString(5));
        bucket = students[1].hashCode() % SIZE;
        str =  ht.bucketToString(bucket);
        if (!str.equals("Tanya: 55 Tay: 45 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                + ") must return \"Tanya: 55 Tay: 45 \\n\""
                + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertEquals("Gus: 44 \n", ht.bucketToString(4));
        bucket = students[0].hashCode() % SIZE;
        str =  ht.bucketToString(bucket);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(" + bucket
                + ") must return \"Gus: 44 \\n\""
                + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertEquals("\n", ht.bucketToString(0));
        bucket = 0;
        str =  ht.bucketToString(bucket);
        if (!str.equals("\n")) {
            msg("-HashTable.bucketToString(" + bucket
                + ") must return \"\\n\""
                + " for HashTable size " + SIZE + ".");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(-1);});
        try {
            str =  ht.bucketToString(-1);
            msg("-HashTable.bucketToString(-1) must throw "
                + "IndexOutOfBoundsException.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.bucketToString(10);});
        try {
            str =  ht.bucketToString(10);
            msg("-HashTable.bucketToString(10) must throw "
                + "IndexOutOfBoundsException for HashTable size " + SIZE + ".");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testFind() {
        int cntErr = 0, idx = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(-1, empty.find(new Student("Gus", 44)));
        idx = empty.find(new Student("Gus", 44));
        if (idx != -1) {
            msg("-find(new Student(\"Gus\", 44)) must return -1 not " + idx
                + " for an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
            new Student("Andrea", 66), new Student("Tay", 45)};
        for (idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals(4, ht.find(new Student("Gus", 44)));
        idx = ht.find(new Student("Gus", 44));
        if (idx != 4) {
            msg("-find(new Student(\"Gus\", 44)) must return 4 not " + idx
                + " when bucket 4 contains: "
                + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertEquals(-1, ht.find(new Student("Maria", 77)));
        idx = ht.find(new Student("Maria", 77));
        if (idx != -1) {
            msg("-find(new Student(\"Maria\", 77)) must return -1 not " + idx
                + " when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.find(noStu);});
        try {
            ht.find(noStu);
            msg("-find(element) must throw NullPointerException when"
                + " element to find is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGet() {
        int cntErr = 0, idx = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals(null, empty.find(new Student("Gus", 44)));
        Student stu = empty.get(new Student("Gus", 44));
        if (stu != null) {
            msg("-get(new Student(\"Gus\", 44)) must return null not " + stu
                + " for an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(SIZE);
        Student[] students = new Student[] {new Student("Gus", 44), new Student("Tanya", 55),
            new Student("Andrea", 66), new Student("Tay", 45)};
        for (idx = 0; idx < students.length; idx++) {
            ht.add(students[idx]);
        }
        //assertEquals("Gus: 44", ht.get(new Student("Gus", 44)));
        stu = ht.get(new Student("Gus", 44));
        if (!stu.toString().equals("Gus: 44")) {
            msg("-get(new Student(\"Gus\", 44)).toString() must return "
                + "\"Gus: 44\" not " + stu + " when bucket 4 contains: "
                + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertEquals(null, ht.find(new Student("Maria", 77)));
        stu = ht.get(new Student("Maria", 77));
        if (stu != null) {
            msg("-get(new Student(\"Maria\", 77)) must return null not " + stu
                + " when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.get(noStu);});
        try {
            ht.get(noStu);
            msg("-get(element) must throw NullPointerException when"
                + " element to get is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }
}
