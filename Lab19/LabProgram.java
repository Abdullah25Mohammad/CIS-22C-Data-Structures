/**
 * LabProgram.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Lab 19
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/*
 * This comparator will compare Integer objects using compare().
 */
class IntComparator implements Comparator<Integer> {
    // Write compare method here
    public int compare(Integer one, Integer two) {
        return one.compareTo(two);
    }
}

/*
 * This comparator will compare String objects using compare().
 */
class StrComparator implements Comparator<String> {
    // Write compare method here
    public int compare(String one, String two) {
        return one.compareTo(two);
    }
}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();
    private IntComparator intCmp = new IntComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testInsert()");
        if (lab.testInsert() == 0) {
            msg("-No errors found");
        }

        msg("Calling testRemove()");
        if (lab.testRemove() == 0) {
            msg("-No errors found");
        }

        msg("Calling testSort()");
        if (lab.testSort() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testInsert() {
        int cntErr = 0, max = 0;
        String str, maxStr;
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        hi.insert(2);
        //assertEquals(2, hi.getMax());
        max = hi.getMax();
        if (max != 2) {
            msg("-With heap: 1 then insert(2), getMax() must return 2 not "
                + max);
            cntErr++;
        }
        hi.insert(3);
        //assertEquals(3, hi.getMax());
        max = hi.getMax();
        if (max != 3) {
            msg("-With heap: 2, 1 then insert(3), getMax() must return 3 not "
                + max);
            cntErr++;
        }
        //assertEquals("3, 1, 2", hi.toString());
        str = hi.toString();
        if (!str.equals("3, 1, 2")) {
            msg("-With heap: 1 and inserting: 2, 3, toString() must return "
                + "3, 1, 2 not " + str);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        hs.insert("p");
        //assertEquals("p", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("p")) {
            msg("-With heap b, a, C then insert(\"p\"), getMax() must return "
                + "\"p\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("r");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap p, b, C, a then insert(\"r\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("i");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap r, p, C, a, b then insert(\"i\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        hs.insert("o");
        //assertEquals("r", hs.getMax());
        maxStr = hs.getMax();
        if (!maxStr.equals("r")) {
            msg("-With heap r, p, C, a, b then insert(\"o\"), getMax() must "
                + "return \"r\" not \"" + maxStr + "\".");
            cntErr++;
        }
        str = hs.toString();
        //assertEquals("r, p, o, a, b, C, i", hs.toString());
        if (!str.equals("r, p, o, a, b, C, i")) {
            msg("-Starting with: a, b, C and inserting: p, r, i, o, toString()"
                + " must\n return r, p, o, a, b, C, i\n"
                + String.format("%" + 4 + "s", " ") + "not " + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testRemove() {
        int cntErr = 0;
        String str = "";
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(1, 2, 3));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        hi.remove(1);
        //assertEquals("2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("2, 1")) {
            msg("-With heap: 3, 2, 1 then calling remove(1), toString()"
                + " must return 2, 1 not " + str);
            cntErr++;
        }
        hi.remove(2);
        //assertEquals("2, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("2")) {
            msg("-With heap: 2, 1 then calling remove(2), toString()"
                + " must return 2 not " + str);
            cntErr++;
        }
        hi.remove(1);
        //assertEquals("", hi.toString());
        str = hi.toString();
        if (!str.equals("")) {
            msg("-With heap: 2 then calling remove(1), toString()"
                + " must return \"\" (empty string) not \"" + str + "\"");
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("a", "b", "C"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        hs.remove(3);
        //assertEquals("b, a", hs.toString());
        str = hs.toString();
        if (!str.equals("b, a")) {
            msg("-With heap: b, a, C then calling remove(3), toString()"
                + " must return b, a not " + str);
            cntErr++;
        }
        hs.remove(1);
        //assertEquals("b, a", hs.toString());
        str = hs.toString();
        if (!str.equals("a")) {
            msg("-With heap: b, a then calling remove(1), toString()"
                + " must return a not " + str);
            cntErr++;
        }
        hs.remove(1);
        //assertEquals("", hs.toString());
        str = hs.toString();
        if (!str.equals("")) {
            msg("-With heap: a then calling remove(1), toString()"
                + " must return \"\" (empty string) not \"" + str + "\"");
            cntErr++;
        }
        return cntErr;
    }

    private int testSort() {
        int cntErr = 0;
        String str = "";
        ArrayList<Integer> iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 8, 3, 2, 6, 5));
        Heap<Integer> hi = new Heap<>(iLst, intCmp);
        ArrayList<Integer> sorted = hi.sort();
        //assertEquals("[2, 3, 4, 5, 6, 7, 8]", sorted.toString());
        if (!sorted.toString().equals("[2, 3, 4, 5, 6, 7, 8]")) {
            msg("-Starting with the heap: 8, 7, 6, 3, 2, 4, 5 then "
                + "sort()\n  must return ArrayList [2, 3, 4, 5, 6, 7, 8]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        str = hi.toString();
        //assertEquals("8, 7, 6, 3, 2, 4, 5", hi.toString());
        str = hi.toString();
        if (!str.equals("8, 7, 6, 3, 2, 4, 5")) {
            msg("-Starting with the heap: 8, 7, 6, 3, 2, 4, 5 then after"
                + " sort()\n  toString() must return 8, 7, 6, 3, 2, 4, 5\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        iLst = new ArrayList<>(
            java.util.Arrays.asList(4, 7, 7, 7, 5, 0, 2, 3, 5, 1));
        hi = new Heap<>(iLst, intCmp);
        sorted = hi.sort();
        //assertEquals("[0, 1, 2, 3, 4, 5, 5, 7, 7, 7]", sorted.toString());
        if (!sorted.toString().equals("[0, 1, 2, 3, 4, 5, 5, 7, 7, 7]")) {
            msg("-Starting with the heap: 7, 7, 7, 5, 5, 0, 2, 3, 4, 1 then "
                + "sort()\n  must return ArrayList [0, 1, 2, 3, 4, 5, 5, 7, 7, 7]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        str = hi.toString();
        //assertEquals("7, 7, 7, 5, 5, 0, 2, 3, 4, 1", hi.toString());
        str = hi.toString();
        if (!str.equals("7, 7, 7, 5, 5, 0, 2, 3, 4, 1")) {
            msg("-Starting with the heap: 7, 7, 7, 5, 5, 0, 2, 3, 4, 1 then after"
                + " sort()\n  toString() must return 7, 7, 7, 5, 5, 0, 2, 3, 4, 1\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        ArrayList<String> sLst = new ArrayList<>(
            java.util.Arrays.asList("P", "r", "i", "o", "r", "i", "t", "y"));
        Heap<String> hs = new Heap<>(sLst, strCmp);
        ArrayList<String> sortStr = hs.sort();
        if (!sortStr.toString().equals("[P, i, i, o, r, r, t, y]")) {
            msg("-Starting with the heap: y, r, t, o, r, i, i, P then "
                + "sort()\n  must return ArrayList [P, i, i, o, r, r, t, y]\n"
                + String.format("%" + 20 + "s", " ") + "not " + sorted);
            cntErr++;
        }
        //assertEquals("y, r, t, o, r, i, i, P", hs.toString());
        str = hs.toString();
        if (!str.equals("y, r, t, o, r, i, i, P")) {
            msg("-Starting with the heap: y, r, t, o, r, i, i, P then after"
                + " sort()\n  toString() must return y, r, t, o, r, i, i, P\n"
                + String.format("%" + 21 + "s", " ") + "not " + str);
            cntErr++;
        }
        return cntErr;
    }
}
