/**
 * LabProgram.java
 * CIS 22C, Lab 14
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private final int SIZE = 10;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testHashTableTArrayInt()");
        if (lab.testHashTableTArrayInt() == 0) {
            msg("-No errors found");
        }
        msg("Calling testCountBucket()");
        if (lab.testCountBucket() == 0) {
            msg("-No errors found");
        }
        msg("Calling testContains()");
        if (lab.testContains() == 0) {
            msg("-No errors found");
        }
        msg("Calling testDelete()");
        if (lab.testDelete() == 0) {
            msg("-No errors found");
        }
        msg("Calling testClear()");
        if (lab.testClear() == 0) {
            msg("-No errors found");
        }
        msg("Calling testRowToString()");
        if (lab.testRowToString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testToString()");
        if (lab.testToString() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testHashTableTArrayInt() {
        int cntErr = 0, numElems = 0;
        double load = 0;
        Student[] array = null;
        HashTable<Student> ht = new HashTable<>(array, SIZE);
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-getNumElements() must return 0 not " + numElems
                + " when constructor called with null array.");
            cntErr++;
        }
        array = new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66)};
        ht = new HashTable<>(array, 10);
        //assertEquals(3, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != array.length) {
            msg("-getNumElements() must return " + array.length
                + " not " + numElems + " when constructor called with "
                + array.length + " element array.");
            cntErr++;
        }
        //assertEquals(0.3, ht.getLoadFactor());
        load = ht.getLoadFactor();
        if (Math.abs(load - 0.3) >= 0.001) {
            msg("-getLoadFactor() must return 0.3 not " + load
                + " for HashTable size 10 with " + + array.length + " elements.");
            cntErr++;
        }
        //assertEquals(4, ht.find(new Student("Gus", 44)));
        Student stu = new Student("Gus", 44);
        int bucket = ht.find(stu);
        if (bucket != 4) {
            msg("-find() must return bucket 4 not " + bucket + " for student("
                + stu + ").");
            cntErr++;
        }
        //assertEquals("Gus: 44 \n", ht.bucketToString(4));
        String str =  ht.bucketToString(4);
        if (!str.equals("Gus: 44 \n")) {
            msg("-HashTable.bucketToString(4) must return \"Gus: 44 \\n\"");
            cntErr++;
        }
        String[] array2 = {"a", "b", "c"};
        //assertThrows(IllegalArgumentException.class, ()-> {new HashTable<String>(array2, -1);});
        try {
            HashTable<String> sht = new HashTable<String>(array2, -1);
            msg("-Must throw IllegalArgumentException when size is <= 0.");
            cntErr++;
        } catch(IllegalArgumentException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testCountBucket() {
        int cntErr = 0, cnt = 0;
        HashTable<Student> ht = new HashTable<>(new Student[] {
            new Student("Gus", 44), new Student("Tanya", 55),
            new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertEquals(0, ht.countBucket(0));
        cnt = ht.countBucket(0);
        if (cnt != 0) {
            msg("-countBucket(0) must return 0 not " + cnt
                + " for bucket: {" + ht.bucketToString(0).trim() + "}");
            cntErr++;
        }
        //assertEquals(1, ht.countBucket(4));
        cnt = ht.countBucket(4);
        if (cnt != 1) {
            msg("-countBucket(4) must return 1 not " + cnt
                + " for bucket: {" + ht.bucketToString(4).trim() + "}");
            cntErr++;
        }
        //assertEquals(2, ht.countBucket(5));
        cnt = ht.countBucket(5);
        if (cnt != 2) {
            msg("-countBucket(5) must return 2 not " + cnt
                + " for bucket: {" + ht.bucketToString(5).trim() + "}");
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(-1);});
        try {
            cnt =  ht.countBucket(-1);
            msg("-countBucket(-1) must throw IndexOutOfBoundsException when"
                + " index is outside bounds of the table.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{ht.countBucket(10);});
        try {
            cnt =  ht.countBucket(10);
            msg("-countBucket(10) must throw IndexOutOfBoundsException when"
                + " index is outside bounds of the table.");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testContains() {
        int cntErr = 0;
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertFalse(empty.contains(new Student("Gus", 44)));
        if (empty.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false for "
                + "an empty HashTable.");
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertTrue(ht.contains(new Student("Gus", 44)));
        if (!ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return true when"
                + " bucket 4 contains: " + ht.bucketToString(4).trim());
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Maria", 77)));
        if (ht.contains(new Student("Maria", 77))) {
            msg("-contains(new Student(\"Maria\", 77)) must return false "
            + "when HashTable does NOT include Student(\"Maria\", 77).");
            cntErr++;
        }
        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.contains(noStu);});
        try {
            ht.contains(noStu);
            msg("-contains(element) must throw NullPointerException when"
                + " element to find is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testDelete() {
        int cntErr = 0, cnt = 0;
        String prev = "";
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        //assertTrue(ht.delete(new Student("Gus", 44)));
        prev = ht.bucketToString(4).trim(); // before deletion
        if (!ht.delete(new Student("Gus", 44))) {
            msg("-delete(new Student(\"Gus\", 44)) must return true when"
                + " bucket 4 contains: " + prev);
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Gus", 44)));
        if (ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false when"
                + " element was previously deleted.");
            cntErr++;
        }
        //assertEquals(0, ht.countBucket(4));
        cnt = ht.countBucket(4);
        if (cnt != 0) {
            msg("-countBucket(4) must return 0 not " + cnt
                + " for previously deleted bucket contents.");
            cntErr++;
        }
        //assertTrue(ht.delete(new Student("Tay", 45)));
        prev = ht.bucketToString(5).trim(); // before deletion
        if (!ht.delete(new Student("Tay", 45))) {
            msg("-delete(new Student(\"Tay\", 45)) must return true when"
                + " bucket 5 contains: " + prev);
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Tay", 45)));
        if (ht.contains(new Student("Tay", 45))) {
            msg("-contains(new Student(\"Tay\", 45)) must return false when"
                + " element was previously deleted.");
            cntErr++;
        }
        //assertEquals(1, ht.countBucket(5));
        cnt = ht.countBucket(5);
        if (cnt != 1) {
            msg("-countBucket(5) must return 1 not " + cnt
                + " when bucket 5 is: " + ht.bucketToString(5).trim());
            cntErr++;
        }
        //assertFalse(ht.delete(new Student("Tay", 45)));
        if (ht.delete(new Student("Tay", 45))) {
            msg("-delete(new Student(\"Tay\", 45)) must return false when"
                + " element was previously deleted.");
            cntErr++;
        }
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertFalse(empty.contains(new Student("Tay", 45)));
        if (empty.contains(new Student("Tay", 45))) {
            msg("-contains(new Student(\"Tay\", 45)) must return false when"
                + " HashTable is empty.");
            cntErr++;
        }

        Student noStu = null;
        //assertThrows(NullPointerException.class, ()->{ht.delete(noStu);});
        try {
            ht.delete(noStu);
            msg("-contains(element) must throw NullPointerException when"
                + " element to delete is null.");
            cntErr++;
        } catch(NullPointerException e) {
            // thrown as expected
        }
        return cntErr;
    }

    //@Test
    private int testClear() {
        int cntErr = 0, cnt = 0, numElems = 0;
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        ht.clear();
        for(int i = 0; i < SIZE; i++) {
            //assertEquals(0, ht.countBucket(i));
            cnt = ht.countBucket(i);
            if (cnt != 0) {
                msg("-countBucket(" + i + ") must return 0 not " + cnt
                    + " after calling clear().");
                cntErr++;
            }
        }
        //assertEquals(0, ht.getNumElements());
        numElems = ht.getNumElements();
        if (numElems != 0) {
            msg("-getNumElements() must return 0 not " + numElems
                + " after calling clear().");
            cntErr++;
        }
        //assertFalse(ht.contains(new Student("Gus", 44)));
        if (ht.contains(new Student("Gus", 44))) {
            msg("-contains(new Student(\"Gus\", 44)) must return false"
                + " after calling clear().");
            cntErr++;
        }
        return cntErr;
    }

    private int testRowToString() {
        int cntErr = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        /*assertEquals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n",
                empty.rowToString());
        */
        str =  empty.rowToString();
        if (!str.equals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n")) {

            msg("-rowToString() must return:\nBucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: empty\n" +
                "Bucket 5: empty\n" +
                "Bucket 6: empty\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\nNOT:\n" + str);
            cntErr++;
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        /*assertEquals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: Gus: 44\n" +
                "Bucket 5: Tanya: 55\n" +
                "Bucket 6: Andrea: 66\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n",
                ht.rowToString());
         */
        str =  ht.rowToString();
        if (!str.equals("Bucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: Gus: 44\n" +
                "Bucket 5: Tanya: 55\n" +
                "Bucket 6: Andrea: 66\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\n")) {

            msg("-rowToString() must return:\nBucket 0: empty\n" +
                "Bucket 1: empty\n" +
                "Bucket 2: empty\n" +
                "Bucket 3: empty\n" +
                "Bucket 4: Gus: 44\n" +
                "Bucket 5: Tanya: 55\n" +
                "Bucket 6: Andrea: 66\n" +
                "Bucket 7: empty\n" +
                "Bucket 8: empty\n" +
                "Bucket 9: empty\nNOT:\n" + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testToString() {
        int cntErr = 0;
        String str = "";
        HashTable<Student> empty = new HashTable<>(SIZE);
        //assertEquals("\n", empty.toString());
        str =  empty.toString();
        if (!str.equals("\n")) {
            msg("-toString() must return \\n for an empty HashTable.");
        }
        HashTable<Student> ht = new HashTable<>(new Student[] {new Student("Gus", 44),
                new Student("Tanya", 55), new Student("Andrea", 66), new Student("Tay", 45)}, SIZE);
        /*assertEquals("Gus: 44 \n" +
                "Tanya: 55 Tay: 45 \n" +
                "Andrea: 66 \n\n", ht.toString());
        */
        str =  ht.toString();
        if (!str.equals("Gus: 44 \n" +
                "Tanya: 55 Tay: 45 \n" +
                "Andrea: 66 \n\n")) {
            msg("-toString() must return\nGus: 44 \n" +
                "Tanya: 55 Tay: 45 \n" +
                "Andrea: 66 \\n\\n\nNOT\n" + str);
        }
        return cntErr;
    }
}
