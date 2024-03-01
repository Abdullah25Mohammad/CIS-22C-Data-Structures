/**
 * LabProgram.java
 * CIS 22C, Lab 8.1
 */
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private Stack<String> s1;
    private Stack<String> s2;
    private Stack<String> emptyStack;
    private Stack<String> nullStack = null;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        msg("Calling testStack()");
        if (lab.testStack() == 0) {
            msg("-No errors found");
        }
        msg("Calling testStackTArray()");
        if (lab.testStackTArray() == 0) {
            msg("-No errors found");
        }
        msg("Calling testStackStackOfT()");
        if (lab.testStackStackOfT() == 0) {
            msg("-No errors found");
        }
        msg("Calling testPeek()");
        if (lab.testPeek() == 0) {
            msg("-No errors found");
        }
        msg("Calling testGetSize()");
        if (lab.testGetSize() == 0) {
            msg("-No errors found");
        }
        msg("Calling testIsEmpty()");
        if (lab.testIsEmpty() == 0) {
            msg("-No errors found");
        }
        msg("Calling testPush()");
        if (lab.testPush() == 0) {
            msg("-No errors found");
        }
        msg("Calling testPop()");
        if (lab.testPop() == 0) {
            msg("-No errors found");
        }
        msg("Calling testToString()");
        if (lab.testToString() == 0) {
            msg("-No errors found");
        }
        msg("Calling testEqualsObject()");
        if (lab.testEqualsObject() == 0) {
            msg("-No errors found");
        }
        msg("Calling testReverseStack()");
        if (lab.testReverseStack() == 0) {
            msg("-No errors found");
        }
    }

    private static void msg(String message) {
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
    }

    private int testStack() {
        int cntErr = 0;
        setUp();
        //assertThrows(NoSuchElementException.class, ()->{emptyStack.peek();});
        try {
            emptyStack.peek();
            msg("-Must throw NoSuchElementException calling peek() on empty stack.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        //assertTrue(emptyStack.isEmpty());
        if (!emptyStack.isEmpty()) {
            msg("-isEmpty() must return true on empty stack.");
            cntErr++;
        }
        return cntErr;
    }

    private int testStackTArray() {
        int cntErr = 0;
        setUp();
        String[] array = null;
        Stack<String> stack = new Stack<>(array);
        //assertTrue(stack.isEmpty());
        if (!stack.isEmpty()) {
            msg("-isEmpty() must return true on empty stack.");
            cntErr++;
        }
        //assertEquals("A", s2.peek());
        s2 = new Stack<>(new String[]
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"});
        String str = s2.peek();
        if (!str.equals("A")) {
            msg("-peek() must return item at top of stack, not: " + str);
            cntErr++;
        }
        //assertEquals(12, s2.getSize());
        int size = s2.getSize();
        if (size != 12) {
            msg("-getSize() must return 12 for 12 item stack, not: " + size);
            cntErr++;
        }
        return cntErr;
    }

    private int testStackStackOfT() {
        int cntErr = 0;
        setUp();
        Stack<String> copy = new Stack<>(s1);
        //assertEquals(s1.peek(), copy.peek());
        if (!s1.peek().equals(copy.peek())) {
            msg("-peek() must return same item on copied stack as original stack.");
        }
        s1.pop();
        //assertNotEquals(s1.peek(), copy.peek());
        if (s1.peek().equals(copy.peek())) {
            msg("-After pop(), peek() must NOT return same first item on copied stack as original stack.");
            cntErr++;
        }
        copy = new Stack<>(nullStack);
        //assertTrue(copy.isEmpty());
        if (!copy.isEmpty()) {
            msg("-isEmpty() must return true on copy of nullStack.");
            cntErr++;
        }
        copy = new Stack<>(emptyStack);
        //assertTrue(copy.isEmpty());
        if (!copy.isEmpty()) {
            msg("-isEmpty() must return true on copy of emptyStack.");
            cntErr++;
        }
        return cntErr;
    }

    private int testPeek() {
        int cntErr = 0;
        setUp();
        //assertThrows(NoSuchElementException.class, () -> {emptyStack.peek();});
        try {
            emptyStack.peek();
            msg("-Must throw NoSuchElementException calling peek() on empty stack.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        //assertEquals("A", s2.peek());
        String str = s2.peek();
        if (!str.equals("A")) {
            msg("-peek() must return item at top of stack, not: " + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testGetSize() {
        int cntErr = 0;
        setUp();
        //assertEquals(0, emptyStack.getSize());
        int size = emptyStack.getSize();
        if (size != 0) {
            msg("-getSize() must return 0 for empty stack, not: " + size);
            cntErr++;
        }
        //assertEquals(12, s2.getSize());
        size = s2.getSize();
        if (size != 12) {
            msg("-getSize() must return 12 for 12 item stack, not: " + size);
            cntErr++;
        }
        return cntErr;
    }

    private int testIsEmpty() {
        int cntErr = 0;
        setUp();
        //assertTrue(emptyStack.isEmpty());
        if (!emptyStack.isEmpty()) {
            msg("-isEmpty() must return true for empty stack.");
            cntErr++;
        }
        //assertFalse(s2.isEmpty());
        if (s2.isEmpty()) {
            msg("-isEmpty() must return false for non-empty stack.");
            cntErr++;
        }
        return cntErr;
    }

    private int testPush() {
        int cntErr = 0;
        setUp();
        //assertEquals(12, s1.getSize());
        int size = s1.getSize();
        if (size != 12) {
            msg("-getSize() must return 12 for 12 item stack, not: " + size);
            cntErr++;
        }
        //assertEquals("L", s1.peek());
        String str = s1.peek();
        if (!str.equals("L")) {
            msg("-peek() must return item at top of stack, not: " + str);
            cntErr++;
        }
        //assertEquals(12, s2.getSize());
        size = s2.getSize();
        if (size != 12) {
            msg("-getSize() must return 12 for 12 item stack, not: " + size);
            cntErr++;
        }
        //assertEquals("A", s2.peek());
        str = s2.peek();
        if (!str.equals("A")) {
            msg("-peek() must return item at top of stack, not: " + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testPop() {
        int cntErr = 0;
        setUp();
        //assertThrows(NoSuchElementException.class, () -> {emptyStack.pop();});
        try {
            emptyStack.pop();
            msg("-Must throw NoSuchElementException calling pop() on empty stack.");
            cntErr++;
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        s2.pop();
        //assertEquals("B", s2.peek());
        String str = s2.peek();
        if (!str.equals("B")) {
            msg("-peek() must return item at top of stack, not: " + str);
            cntErr++;
        }
        emptyStack.push("A");
        emptyStack.pop();
        //assertTrue(emptyStack.isEmpty());
        if (!emptyStack.isEmpty()) {
            msg("-isEmpty() must return true for empty stack.");
            cntErr++;
        }
        return cntErr;
    }

    private int testToString() {
        int cntErr = 0;
        setUp();
        //assertEquals("A B C D E F G H I J K L \n", s2.toString());
        String str = s2.toString();
        if (!str.equals("A B C D E F G H I J K L \n")) {
            msg("-s2.toString() must return items in order followed by spaces and end in \\n, not: "
                + str.trim());
            cntErr++;
        }
        //assertEquals("\n", emptyStack.toString());
        str = emptyStack.toString();
        if (!str.equals("\n")) {
            msg("-s2.toString() must return \\n, not: " + str.trim());
            cntErr++;
        }
        return cntErr;
    }

    private int testEqualsObject() {
        int cntErr = 0;
        Stack<String> one = new Stack<>();
        Stack<String> two = new Stack<>();
        String a = new String("A");
        String b = new String("B");
        String c = new String("C");
        one.push(a);
        two.push(a);
        one.push(b);
        two.push(b);
        one.push(c);
        //assertFalse(one.equals(two));
        if (one.equals(two)) {
            msg("-Stack{" + one.toString().trim() + "}.equals(Stack{"
                + two.toString().trim() + "}) must return false.");
            cntErr++;
        }
        two.push(c);
        //assertTrue(one.equals(two));
        if (!one.equals(two)) {
            msg("-Stack{" + one.toString().trim() + "}.equals(Stack{"
                + two.toString().trim() + "}) must return true.");
            cntErr++;
        }
        //assertFalse(one.equals(nullStack));
        if (one.equals(nullStack)) {
            msg("-Stack{" + one.toString().trim() + "}.equals(null) must return false.");
            cntErr++;
        }
        //assertFalse(one.equals(new Stack<String>()));
        if (one.equals(emptyStack)) {
            msg("-Stack{" + one.toString().trim() + "}.equals(Stack{"
                + emptyStack.toString().trim() + "}) must return false.");
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
        //assertEquals("C B A \n", two.reverseStack());;
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
}
