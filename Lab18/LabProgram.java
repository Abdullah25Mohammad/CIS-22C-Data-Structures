/**
 * LabProgram.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Lab 18
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/*
 * This comparator will compare Integer objects using compare().
 */
class IntComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }
}

/*
* This comparator will compare String objects using compare().
 */
class StrComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();
    private IntComparator intCmp = new IntComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testHeap()");
        if (lab.testHeap() == 0) {
            msg("-No errors found");
        }

        msg("Calling testToString()");
        if (lab.testToString() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetLeft()");
        if (lab.testGetLeft() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetRight()");
        if (lab.testGetRight() == 0) {
            msg("-No errors found");
        }

        msg("Calling testBuildHeap()");
        if (lab.testBuildHeap() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetParent()");
        if (lab.testGetParent() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetMax()");
        if (lab.testGetMax() == 0) {
            msg("-No errors found");
        }

        msg("Calling testGetElement()");
        if (lab.testGetElement() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testHeap() {
        int cntErr = 0, size = 0;
        String str;
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals("8, 7, 6, 3, 2, 4, 5", hi.toString());
        str = hi.toString();
        if (!str.equals("8, 7, 6, 3, 2, 4, 5")) {
            msg("-toString() must return 8, 7, 6, 3, 2, 4, 5\n"
                + String.format("%" + 20 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 9 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        //assertEquals(7, hi.getHeapSize());
        size = hi.getHeapSize();
        if (size != 7) {
            msg("-getHeapSize() must return 7 not " + size
                + " when Heap constructed from list " + iLst);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals("b, a, C", hs.toString());
        str = hs.toString();
        //assertEquals("b, a, C", hs.toString());
        if (!str.equals("b, a, C")) {
            msg("-toString() must return b, a, C\n"
                + String.format("%" + 20 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 9 + "s", " ") + "from list "
                + sLst);
            cntErr++;
        }
        //assertEquals(3, hs.getHeapSize());
        size = hs.getHeapSize();
        if (size != 3) {
            msg("-getHeapSize() must return 3 not " + size
                + " when Heap constructed from list " + sLst);
            cntErr++;
        }
        return cntErr;
    }

    private int testToString() {
        final int ELEM_0 = 42;
        int cntErr = 0;
        String str;
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1, 1));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals("1, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("1, 1")) {
            msg("-toString() must return 1, 1\n"
                + String.format("%" + 20 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 9 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        iLst = new ArrayList<>(java.util.Arrays.asList(3, 2, 1));
        hi = new Heap<>(iLst, intCmp);
        //assertEquals("3, 2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("3, 2, 1")) {
            msg("-toString() must return 3, 2, 1\n"
                + String.format("%" + 20 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 9 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "a", "a"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals("a, a, a", hs.toString());
        str = hs.toString();
        if (!str.equals("a, a, a")) {
            msg("-toString() must return a, a, a\n"
                + String.format("%" + 20 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 9 + "s", " ") + "from list "
                + sLst);
            cntErr++;
        }
        return cntErr;
    }

    private int testGetLeft() {
        int cntErr = 0, idx = 0;
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("c", "b", "a"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals(2, hs.getLeft(1));
        idx = hs.getLeft(1);
        if (idx != 2) {
            msg("-getLeft(1) must return 2 not " + idx);
            cntErr++;
        }
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(9, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals(2, hi.getLeft(1));
        idx = hi.getLeft(1);
        if (idx != 2) {
            msg("-getLeft(1) must return 2 not " + idx);
            cntErr++;
        }
        //assertEquals(4, hi.getLeft(2));
        idx = hi.getLeft(2);
        if (idx != 4) {
            msg("-getLeft(2) must return 4 not " + idx);
            cntErr++;
        }
        //assertEquals(6, hs.getLeft(3));
        idx = hi.getLeft(3);
        if (idx != 6) {
            msg("-getLeft(3) must return 6 not " + idx);
            cntErr++;
        }
        //assertEquals(8, hs.getLeft(4));
        idx = hi.getLeft(4);
        if (idx != 8) {
            msg("-getLeft(4) must return 8 not " + idx);
            cntErr++;
        }
        //assertEquals(10, hs.getLeft(5));
        idx = hi.getLeft(5);
        if (idx != 10) {
            msg("-getLeft(5) must return 10 not " + idx);
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{hi.getLeft(0);});
        try {
            hi.getLeft(0);
            msg("-getLeft(0) must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class,
        //    ()->{hi.getLeft(hi.getHeapSize() + 1);});
        try {
            int size =  hi.getHeapSize() + 1;
            hi.getLeft(size);
            msg("-getLeft(" + size + ") must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetRight() {
        int cntErr = 0, idx = 0;
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("c", "b", "a"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals(3, hs.getRight(1));
        idx = hs.getRight(1);
        if (idx != 3) {
            msg("-getRight(1) must return 3 not " + idx);
            cntErr++;
        }
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(9, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals(3, hi.getRight(1));
        idx = hi.getRight(1);
        if (idx != 3) {
            msg("-getRight(1) must return 3 not " + idx);
            cntErr++;
        }
        //assertEquals(5, hi.getRight(2));
        idx = hi.getRight(2);
        if (idx != 5) {
            msg("-getRight(2) must return 5 not " + idx);
            cntErr++;
        }
        //assertEquals(6, hs.getRight(3));
        idx = hi.getRight(3);
        if (idx != 7) {
            msg("-getRight(3) must return 7 not " + idx);
            cntErr++;
        }
        //assertEquals(9, hs.getRight(4));
        idx = hi.getRight(4);
        if (idx != 9) {
            msg("-getRight(4) must return 9 not " + idx);
            cntErr++;
        }
        //assertEquals(11, hs.getRight(5));
        idx = hi.getRight(5);
        if (idx != 11) {
            msg("-getRight(5) must return 11 not " + idx);
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{hi.getRight(0);});
        try {
            hi.getRight(0);
            msg("-getRight(0) must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class,
        //    ()->{hi.getRight(hi.getHeapSize() + 1);});
        try {
            int size =  hi.getHeapSize() + 1;
            hi.getRight(size);
            msg("-getRight(" + size + ") must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testBuildHeap() {
        int cntErr = 0;
        String str = "";
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals("b, a, C", hs.toString());
        str = hs.toString();
        if (!str.equals("b, a, C")) {
            msg("-buildHeap(): toString() must return b, a, C\n"
                + String.format("%" + 33 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 26 + "s", " ") + "from list "
                + sLst);
            cntErr++;
        }
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1, 2, 3));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals("3, 2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("3, 2, 1")) {
            msg("-buildHeap(): toString() must return 3, 2, 1\n"
                + String.format("%" + 33 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 26 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 8, 3, 2, 6, 5));
        hi = new Heap<>(iLst, intCmp);
        //assertEquals("8, 7, 6, 3, 2, 4, 5", hi.toString());
        str = hi.toString();
        if (!str.equals("8, 7, 6, 3, 2, 4, 5")) {
            msg("-buildHeap(): toString() must return 8, 7, 6, 3, 2, 4, 5\n"
                + String.format("%" + 33 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 26 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 7, 7, 5, 0, 2, 3, 5, 1));
        hi = new Heap<>(iLst, intCmp);
        //assertEquals("7, 7, 7, 5, 5, 0, 2, 3, 4, 1", hi.toString());
        str = hi.toString();
        String answer = "7, 7, 7, 5, 5, 0, 2, 3, 4, 1";
        if (!str.equals(answer)) {
            msg("-buildHeap(): toString() must return " + answer + "\n"
                + String.format("%" + 33 + "s", " ") + "not " + str.trim()
                + "\n" + String.format("%" + 26 + "s", " ") + "from list "
                + iLst);
            cntErr++;
        }
        return cntErr;
    }

    private int testGetParent() {
        int cntErr = 0, idx = 0;
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("c", "b", "a"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals(1, hs.getParent(2));
        idx = hs.getParent(2);
        if (idx != 1) {
            msg("-getParent(2) must return 1 not " + idx);
            cntErr++;
        }
        //assertEquals(1, hs.getParent(3));
        idx = hs.getParent(3);
        if (idx != 1) {
            msg("-getParent(3) must return 1 not " + idx);
            cntErr++;
        }
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(9, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals(2, hi.getParent(4));
        idx = hi.getParent(4);
        if (idx != 2) {
            msg("-getParent(4) must return 2 not " + idx);
            cntErr++;
        }
        //assertEquals(2, hi.getParent(5));
        idx = hi.getParent(5);
        if (idx != 2) {
            msg("-getParent(5) must return 2 not " + idx);
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{hi.getParent(1);});
        try {
            hi.getParent(1);
            msg("-getParent(1) must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class,
        //    ()->{hi.getParent(hi.getHeapSize() + 1);});
        try {
            int size =  hi.getHeapSize() + 1;
            hi.getParent(size);
            msg("-getParent(" + size + ") must throw IndexOutOfBoundsException");
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testGetMax() {
        int cntErr = 0, max = 0;
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("b", "a", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals("b", hs.getMax());
        String str = hs.getMax();
        if (!str.equals("b")) {
            msg("-getMax() must return b not " + str + " for heap " + hs);
            cntErr++;
        }
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(3, 2, 1));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        //assertEquals("3", hi.getMax());
        max = hi.getMax();
        if (max != 3) {
            msg("-getMax() must return 3 not " + max + " for heap " + hi);
            cntErr++;
        }
        return cntErr;
    }

    private int testGetElement() {
        int cntErr = 0;
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("c", "b", "a"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        //assertEquals("c", hs.getElement(1));
        String str = hs.getElement(1);
        if (!str.equals("c")) {
            msg("-getElement(1) must return c not " + str + " for heap " + hs);
            cntErr++;
        }
        //assertEquals("b", hs.getElement(2));
        str = hs.getElement(2);
        if (!str.equals("b")) {
            msg("-getElement(2) must return b not " + str + " for heap " + hs);
            cntErr++;
        }
        //assertEquals("a", hs.getElement(3));
        str = hs.getElement(3);
        if (!str.equals("a")) {
            msg("-getElement(3) must return a not " + str + " for heap " + hs);
            cntErr++;
        }
        //assertThrows(IndexOutOfBoundsException.class, ()->{hi.getElement(0);});
        try {
            hs.getElement(0);
            msg("-getElement(0) must throw IndexOutOfBoundsException"
                + " for heap " + hs);
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        //assertThrows(IndexOutOfBoundsException.class,
        //    ()->{hi.getElement(hi.getHeapSize() + 1);});
        try {
            int size =  hs.getHeapSize() + 1;
            hs.getElement(size);
            msg("-getElement(" + size + ") must throw IndexOutOfBoundsException"
                + " for heap " + hs);
            cntErr++;
        } catch(IndexOutOfBoundsException e) {
            // thrown as expected
        }
        return cntErr;
    }
}