/**
 * LabProgram.java
 * CIS 22C, Lab 11
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * This comparator will sort String objects using compareTo().
 */
class StrComparator implements Comparator<String> {
    public int compare(String one, String two) {
        return one.compareTo(two);
    }
}

public class LabProgram {
    private StrComparator strCmp = new StrComparator();

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();

        msg("Calling testPreOrderString()");
        if (lab.testPreOrderString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testInOrderString()");
        if (lab.testInOrderString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testPostOrderString()");
        if (lab.testPostOrderString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testBSTBSTOfT()");
        if (lab.testBSTBSTOfT() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
        System.out.println(message);
    }

    private int testPreOrderString() {
        int cntErr = 0;
        String str = "";
        BST<String> empty = new BST<>();
        // assertEquals("\n", empty.preOrderString());
        str = empty.preOrderString();
        if (!str.equals("\n")) {
            msg("-BST.preOrderString() must return \"\\n\" for empty BST");
            cntErr++;
        }

        BST<String> states = new BST<>();
        states.insert("HI", strCmp);
        states.insert("MN", strCmp);
        states.insert("CA", strCmp);
        states.insert("IA", strCmp);
        states.insert("MI", strCmp);
        states.insert("AK", strCmp);

        // assertEquals("HI CA AK MN IA MI \n", states.preOrderString());
        str = states.preOrderString();
        if (!str.endsWith("\n")) {
            msg("-BST.preOrderString() must end with \\n.");
            cntErr++;
        } else if (!str.equals("HI CA AK MN IA MI \n")) {
            msg("-BST.preOrderString() must return \"HI CA AK MN IA MI \\n\" not \""
                    + str.trim() + " \\n\"");
            cntErr++;
        }
        return cntErr;
    }

    // @Test
    private int testInOrderString() {
        int cntErr = 0;
        String str = "";
        BST<String> empty = new BST<>();
        // assertEquals("\n", empty.inOrderString());
        str = empty.inOrderString();
        if (!str.equals("\n")) {
            msg("-BST.preOrderString() must return \"\\n\" for empty BST");
            cntErr++;
        }

        BST<String> states = new BST<>();
        states.insert("HI", strCmp);
        states.insert("MN", strCmp);
        states.insert("CA", strCmp);
        states.insert("IA", strCmp);
        states.insert("MI", strCmp);
        states.insert("AK", strCmp);

        // assertEquals("AK CA HI IA MI MN \n", states.inOrderString());
        str = states.inOrderString();
        // System.out.println(str);
        if (!str.endsWith("\n")) {
            msg("-BST.inOrderString() must end with \\n.");
            cntErr++;
        } else if (!str.equals("AK CA HI IA MI MN \n")) {
            msg("-BST.inOrderString() must return \"AK CA HI IA MI MN \\n\" not \""
                    + str.trim() + " \\n\"");
            cntErr++;
        }
        return cntErr;
    }

    // @Test
    private int testPostOrderString() {
        int cntErr = 0;
        String str = "";
        BST<String> empty = new BST<>();
        // assertEquals("\n", empty.postOrderString());
        str = empty.inOrderString();
        if (!str.equals("\n")) {
            msg("-BST.postOrderString() must return \"\\n\" for empty BST");
            cntErr++;
        }

        BST<String> states = new BST<>();
        states.insert("HI", strCmp);
        states.insert("MN", strCmp);
        states.insert("CA", strCmp);
        states.insert("IA", strCmp);
        states.insert("MI", strCmp);
        states.insert("AK", strCmp);

        // assertEquals("AK CA MI IA MN HI \n", states.postOrderString());
        str = states.postOrderString();
        // System.out.println(str);
        if (!str.endsWith("\n")) {
            msg("-BST.postOrderString() must end with \\n.");
            cntErr++;
        } else if (!str.equals("AK CA MI IA MN HI \n")) {
            msg("-BST.postOrderString() must return \"AK CA MI IA MN HI \\n\" not \""
                    + str.trim() + " \\n\"");
            cntErr++;
        }
        return cntErr;
    }

    private int testBSTBSTOfT() {
        int cntErr = 0;
        String str = "";
        BST<String> nullBst = null;
        BST<String> b = new BST<>(nullBst, strCmp);
        // assertTrue(b.isEmpty());
        if (!b.isEmpty()) {
            msg("-isEmpty() must return true on null BST argument.");
            cntErr++;
        }

        BST<String> bst4 = new BST<>();
        bst4.insert("5", strCmp);
        bst4.insert("1", strCmp);
        bst4.insert("2", strCmp);
        bst4.insert("4", strCmp);
        bst4.insert("6", strCmp);
        bst4.insert("8", strCmp);
        BST<String> bst = new BST<>(bst4, strCmp);
        // assertEquals("5", bst.getRoot());
        str = bst.getRoot();
        if (!str.equals("5")) {
            msg("-BST.getRoot() must return \"5\" not \"" + str
                    + "\" for BST {" + bst4.levelOrderString().trim()
                    + "} (level order)");
            cntErr++;
        }
        // assertEquals(3, bst.getHeight());
        int height = bst.getHeight();
        if (height != 3) {
            msg("-BST.getHeight() must return 3 not " + height
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        bst.insert("3", strCmp);
        // assertEquals(4, bst.getHeight());
        height = bst.getHeight();
        if (height != 4) {
            msg("-BST.getHeight() must return 4 not " + height
                    + " for BST {" + bst.levelOrderString().trim() + "}");
            cntErr++;
        }
        // assertEquals(3, bst4.getHeight());
        height = bst4.getHeight();
        if (height != 3) {
            msg("-BST.getHeight() must return 3 not " + height
                    + " for BST {" + bst4.levelOrderString().trim() + "}");
            cntErr++;
        }
        return cntErr;
    }
}
