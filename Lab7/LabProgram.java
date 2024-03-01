/**
 * LabProgram.java
 * CIS 22C, Lab 6
 */
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private Stack<String> s1;
    private Stack<String> s2;
    private Stack<String> nullStack = null;
    private Stack<String> emptyStack;
    private Stack<String> s4;
    private Queue<String> q1;
    private Queue<Integer> q2;
    private Queue<Double> q3;
    private Queue<String> emptyQueue;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        System.out.print("Calling testIsSortedStack()\n");
        if (lab.testIsSortedStack() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testLinearSearchStack()\n");
        if (lab.testLinearSearchStack() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testBinarySearchStack()\n");
        if (lab.testBinarySearchStack() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testReverseStack()\n");
        if (lab.testReverseStack() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testIsSortedQueue()\n");
        if (lab.testIsSortedQueue() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testLinearSearchQueue()\n");
        if (lab.testLinearSearchQueue() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testBinarySearchQueue()\n");
        if (lab.testBinarySearchQueue() == 0) {
            System.out.println("-No errors found");
        }
        System.out.print("Calling testReverseQueue()\n");
        if (lab.testReverseQueue() == 0) {
            System.out.println("-No errors found");
        }
    }

    private void msg(String message) {
        System.out.println(message);
    }

    private void setUp() {
        s1 = new Stack<>();
        for(int i = 'A'; i < 'M'; i++) {
            s1.push("" + (char)i);
        }
        s2 = new Stack<>();
        for(int i = 'L'; i >= 'A'; i--) {
            s2.push("" + (char)i);
        }
        emptyStack = new Stack<>();
        q1 = new Queue<>();
        for(int i = 'A'; i < 'M'; i++) {
            q1.enqueue("" + (char) i);
        }
        emptyQueue = new Queue<>();
        q3 = new Queue<>(new Double[]{1.1, 2.2, 3.3, 4.4});
    }

    private int testIsSortedStack() {
        int cntErr = 0;
        setUp();
        //assertTrue(empty.isSorted());
        if (!emptyStack.isSorted()) {
            msg("-Stack{" + emptyStack.toString().trim()
                + "}.isSorted() must return true.");
            cntErr++;
        }
        //assertFalse(s1.isSorted());
        if (s1.isSorted()) {
            msg("-Stack{" + s1.toString().trim()
                + "}.isSorted() must return false.");
            cntErr++;
        }
        //assertTrue(s2.isSorted());
        if (!s2.isSorted()) {
            msg("-Stack{" + s2.toString().trim()
                + "}.isSorted() must return true.");
            cntErr++;
        }
        Stack<Integer> mixed = new Stack<>(new Integer[] { 1, 2, 4, 3 });
        //assertFalse(mixed.isSorted());
        if (mixed.isSorted()) {
            msg("-Stack{" + mixed.toString().trim()
                + "}.isSorted() must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testLinearSearchStack() {
        int cntErr = 0;
        setUp();
        //assertEquals(true, s1.linearSearch("D"));
        if (!s1.linearSearch("A")) {
            msg("-Stack{" + s1.toString().trim()
                + "}.linearSearch(\"A\") must return true.");
            cntErr++;
        }

        //assertEquals(false, s2.linearSearch("Z"));
        if (s2.linearSearch("Z")) {
            msg("-Stack{" + s2.toString().trim()
                + "}.linearSearch(\"Z\") must return false.");
            cntErr++;
        }
        //assertEquals(true, s2.linearSearch("A"));
        if (!s2.linearSearch("G")) {
            msg("-Stack{" + s2.toString().trim()
                + "}.linearSearch(\"G\") must return true.");
            cntErr++;
        }

        Stack<Integer> mixed = new Stack<>(new Integer[] { 1, 2, 4, 3 });
        //assertEquals(true, mixed.linearSearch(4));
        if (!mixed.linearSearch(4)) {
            msg("-Stack{" + mixed.toString().trim()
                + "}.linearSearch(4) must return true.");
            cntErr++;
        }
        //assertEquals(false, empty.linearSearch(5));
        //System.out.print("emptyStack:" + emptyStack);
        if (emptyStack.linearSearch("a")) {
            msg("-Stack{" + emptyStack.toString().trim()
                + "}.linearSearch(5) must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testBinarySearchStack() {
        int cntErr = 0;
        setUp();
        //assertThrows(NoSuchElementException.class, ()->{s1.binarySearch("A")});
        try {
            s1.binarySearch("A");
            msg("-Must throw IllegalStateException calling binarySearch() on unsorted stack.");
            cntErr++;
        } catch(IllegalStateException e) {
            // thrown as expected
        }
        //assertFalse(s1.binarySearch("D"));
        if (!s2.binarySearch("D")) {
            msg("-Stack{" + s2.toString().trim()
                + "}.binarySearch(\"A\") must return true.");
            cntErr++;
        }
        Stack<String> s3 = new Stack<>(new String[] { "B", "C", "D" });
        //assertFalse(s3.binarySearch("A"));
        if (s3.binarySearch("A")) {
            msg("-Stack{" + s3.toString().trim()
                + "}.binarySearch(\"A\") must return false.");
            cntErr++;
        }
        //assertTrue(s3.binarySearch("B"));
        if (!s3.binarySearch("B")) {
            msg("-Stack{" + s3.toString().trim()
                + "}.binarySearch(\"B\") must return true.");
            cntErr++;
        }
        //assertTrue(s3.binarySearch("C"));
        if (!s3.binarySearch("C")) {
            msg("-Stack{" + s3.toString().trim()
                + "}.binarySearch(\"C\") must return true.");
            cntErr++;
        }
        //assertTrue(s3.binarySearch("D"));
        if (!s3.binarySearch("D")) {
            msg("-Stack{" + s3.toString().trim()
                + "}.binarySearch(\"D\") must return true.");
            cntErr++;
        }
        //assertFalse(s3.binarySearch("E"));
        if (s3.binarySearch("E")) {
            msg("-Stack{" + s3.toString().trim()
                + "}.binarySearch(\"E\") must return false.");
            cntErr++;
        }
        //assertFalse(emptyStack.binarySearch("D"));
        if (emptyStack.binarySearch("D")) {
            msg("-Stack{" + emptyStack.toString().trim()
                + "}.binarySearch(\"D\") must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testReverseStack() {
        int cntErr = 0;
        setUp();
        Stack<String> two = new Stack<>();
        two.push("C");
        two.push("B");
        two.push("A");
        //assertEquals("C B A \n", two.reverseStack());
        if (!two.reverseStack().equals("C B A \n")) {
            msg("-Stack{" + two.toString().trim()
                + "}.reverseStack().equals(\"C B A \\n\"}) must return true.");
            cntErr++;
        }
        Stack<String> one = new Stack<>();
        one.push("A");
        one.push("B");
        one.push("C");
        //assertEquals("A B C \n", one.reverseStack());
        if (!one.reverseStack().equals("A B C \n")) {
            msg("-Stack{" + one.toString().trim()
                + "}.reverseStack().equals(\"A B C \\n\"}) must return true.");
            cntErr++;
        }
        //assertEquals("\n", emptyStack.reverseStack());
        if (!emptyStack.reverseStack().equals("\n")) {
            msg("-Stack{" + emptyStack.toString().trim()
                + "}.reverseStack().equals(\"\\n\"}) must return true.");
            cntErr++;
        }
        return cntErr;
    }

    private int testIsSortedQueue() {
        int cntErr = 0;
        setUp();
        //assertTrue(empty.isSorted());
        if (!emptyQueue.isSorted()) {
            msg("-Queue{" + emptyQueue.toString().trim()
                + "}.isSorted() must return true.");
            cntErr++;
        }
        //assertTrue(q1.isSorted());
        if (!q1.isSorted()) {
            msg("-Queue{" + q1.toString().trim()
                + "}.isSorted() must return true.");
            cntErr++;
        }
        Queue<Integer> mixed = new Queue<>(new Integer[] { 1, 2, 4, 3 });
        //assertFalse(mixed.isSorted());
        if (mixed.isSorted()) {
            msg("-Queue{" + mixed.toString().trim()
                + "}.isSorted() must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testLinearSearchQueue() {
        int cntErr = 0;
        setUp();
        //assertEquals(true, q1.linearSearch("D"));
        if (!q1.linearSearch("A")) {
            msg("-Queue{" + q1.toString().trim()
                + "}.linearSearch(\"A\") must return true.");
            cntErr++;
        }

        //assertEquals(false, q1.linearSearch("Z"));
        if (q1.linearSearch("Z")) {
            msg("-Queue{" + q1.toString().trim()
                + "}.linearSearch(\"Z\") must return false.");
            cntErr++;
        }
        //assertEquals(true, q1.linearSearch("A"));
        if (!q1.linearSearch("A")) {
            msg("-Queue{" + q1.toString().trim()
                + "}.linearSearch(\"A\") must return true.");
            cntErr++;
        }

        Queue<Integer> mixed = new Queue<>(new Integer[] { 1, 2, 4, 3 });
        //assertEquals(true, mixed.linearSearch(4));
        if (!mixed.linearSearch(4)) {
            msg("-Queue{" + mixed.toString().trim()
                + "}.linearSearch(4) must return true.");
            cntErr++;
        }
        //assertEquals(false, empty.linearSearch(5));
        //System.out.print("emptyQueue:" + emptyQueue);
        if (emptyQueue.linearSearch("a")) {
            msg("-Queue{" + emptyQueue.toString().trim()
                + "}.linearSearch(5) must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testBinarySearchQueue() {
        int cntErr = 0;
        setUp();
        Queue<Integer> mixed = new Queue<>(new Integer[] { 1, 2, 4, 3 });
        //assertThrows(NoSuchElementException.class, ()->{mixed.binarySearch("A")});
        try {
            mixed.binarySearch(4);
            msg("-Must throw IllegalStateException calling binarySearch() on unsorted queue.");
            cntErr++;
        } catch(IllegalStateException e) {
            // thrown as expected
        }
        //assertFalse(q1.binarySearch("D"));
        if (!q1.binarySearch("D")) {
            msg("-Queue{" + q1.toString().trim()
                + "}.binarySearch(\"A\") must return true.");
            cntErr++;
        }
        Queue<String> q4 = new Queue<>(new String[] { "B", "C", "D" });
        //assertFalse(q4.binarySearch("A"));
        if (q4.binarySearch("A")) {
            msg("-Queue{" + q4.toString().trim()
                + "}.binarySearch(\"A\") must return false.");
            cntErr++;
        }
        //assertTrue(q4.binarySearch("B"));
        if (!q4.binarySearch("B")) {
            msg("-Queue{" + q4.toString().trim()
                + "}.binarySearch(\"B\") must return true.");
            cntErr++;
        }
        //assertTrue(q4.binarySearch("C"));
        if (!q4.binarySearch("C")) {
            msg("-Queue{" + q4.toString().trim()
                + "}.binarySearch(\"C\") must return true.");
            cntErr++;
        }
        //assertTrue(q4.binarySearch("D"));
        if (!q4.binarySearch("D")) {
            msg("-Queue{" + q4.toString().trim()
                + "}.binarySearch(\"D\") must return true.");
            cntErr++;
        }
        //assertFalse(q4.binarySearch("E"));
        if (q4.binarySearch("E")) {
            msg("-Queue{" + q4.toString().trim()
                + "}.binarySearch(\"E\") must return false.");
            cntErr++;
        }
        //assertFalse(emptyQueue.binarySearch("D"));
        if (emptyQueue.binarySearch("D")) {
            msg("-Queue{" + emptyQueue.toString().trim()
                + "}.binarySearch(\"D\") must return false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testReverseQueue() {
        int cntErr = 0;
        setUp();
        Queue<String> two = new Queue<>();
        two.enqueue("C");
        two.enqueue("B");
        two.enqueue("A");
        //assertEquals("A B C \n", two.reverseQueue());
        if (!two.reverseQueue().equals("A B C \n")) {
            msg("-Queue{" + two.toString().trim()
                + "}.reverseQueue().equals(\"A B C \\n\"}) must return true.");
            cntErr++;
        }
        Queue<String> one = new Queue<>();
        one.enqueue("A");
        one.enqueue("B");
        one.enqueue("C");
        //assertEquals("C B A \n", one.reverseQueue());
        if (!one.reverseQueue().equals("C B A \n")) {
            msg("-Queue{" + one.toString().trim()
                + "}.reverseQueue().equals(\"C B A \\n\"}) must return true.");
            cntErr++;
        }
        //assertEquals("\n", emptyQueue.reverseQueue());
        if (!emptyQueue.reverseQueue().equals("\n")) {
            msg("-Queue{" + emptyQueue.toString().trim()
                + "}.reverseQueue().equals(\"\\n\"}) must return true.");
            cntErr++;
        }
        return cntErr;
    }
}
