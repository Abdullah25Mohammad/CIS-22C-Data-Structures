/**
 * LabProgram.java
 * CIS 22C, Lab 9
 */
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * This comparator will sort String objects using compareTo().
 */
class StrComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        msg("Calling testBST()");
        if (lab.testBST() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetRoot()");
        if (lab.testGetRoot() == 0) {
            msg("-No errors found");
        }
        msg("Calling testIsEmpty()");
        if (lab.testIsEmpty() == 0) {
            msg("-No errors found");
        }
        msg("Calling testInsert()");
        if (lab.testInsert() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testBST() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertTrue(bst.isEmpty());
        if (!bst.isEmpty()) {
            msg("-isEmpty() must return true on empty BST.");
            cntErr++;
        }
        //assertEquals(0, bst.getSize());
        if (bst.getSize() != 0) {
            msg("-getSize() must return 0 on empty BST.");
            cntErr++;
        }
        //assertThrows(NoSuchElementException.class, ()->{bst.getRoot();});
        try {
            bst.getRoot();
            msg("-Must throw NoSuchElementException calling getRoot() on empty BST.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        return cntErr;
    }

    int testGetRoot() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertThrows(NoSuchElementException.class, ()->{bst.getRoot();});
        try {
            bst.getRoot();
            msg("-Must throw NoSuchElementException calling getRoot() on empty BST.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        bst.insert("F", strCmp);
        //assertEquals("F", bst.getRoot());
        if (!bst.getRoot().equals("F")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("C", strCmp);
        bst.insert("P", strCmp);
        bst.insert("Q", strCmp);
        bst.insert("Z", strCmp);
        bst.insert("L", strCmp);
        //assertEquals("F", bst.getRoot());
        if (!bst.getRoot().equals("F")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //bst.remove("F", strCmp);
        //assertEquals("L", bst.getRoot());
        return cntErr;
    }

    int testIsEmpty() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        //assertTrue(bst.isEmpty());
        if (!bst.isEmpty()) {
            msg("-isEmpty() must return true on empty BST.");
            cntErr++;
        }
        bst.insert("C", strCmp);
        //assertFalse(bst.isEmpty());
        if (bst.isEmpty()) {
            msg("-isEmpty() must return false on non-empty BST.");
            cntErr++;
        }
        bst.insert("A", strCmp);
        bst.insert("B", strCmp);
        bst.insert("D", strCmp);
        //assertFalse(bst.isEmpty());
        if (bst.isEmpty()) {
            msg("-isEmpty() must return false on non-empty BST.");
            cntErr++;
        }
        return cntErr;
    }

    int testInsert() {
        int cntErr = 0;
        BST<String> bst = new BST<>();
        bst.insert("A", strCmp);
        bst.insert("B", strCmp);
        bst.insert("C", strCmp);
        bst.insert("D", strCmp);
        bst.insert("E", strCmp);
        //assertEquals("A", bst.getRoot());
        if (!bst.getRoot().equals("A")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //assertEquals("A B C D E \n", bst.levelOrderString());
        String str = bst.levelOrderString();
        if (!str.equals("A B C D E \n")) {
            msg("-levelOrderString() must return A B C D E \\n not "
                + str.trim() + " \\n");
            cntErr++;
        }
        // More subtle ordering of strings
        bst = new BST<>();
        bst.insert("ME", strCmp);
        bst.insert("MAY", strCmp);
        bst.insert("MAH", strCmp);
        bst.insert("MO", strCmp);
        bst.insert("MOO", strCmp);
        //assertEquals("ME", bst.getRoot());
        if (!bst.getRoot().equals("ME")) {
            msg("-getRoot() must return first element inserted.");
            cntErr++;
        }
        //assertEquals("ME MAY MO MAH MOO \n", bst.levelOrderString());
        str = bst.levelOrderString();
        if (!str.equals("ME MAY MO MAH MOO \n")) {
            msg("-levelOrderString() must return ME MAY MO MAH MOO \\n not "
                + str.trim() + " \\n");
            cntErr++;
        }
        return cntErr;
    }
}
