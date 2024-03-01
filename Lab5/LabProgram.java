/**
 * LabProgram.java
 * CIS 22C, Lab 5
 */
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LabProgram {
    private Queue<String> q1;
    private Queue<Integer> q2;
    private Queue<Double> q3;

    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        System.out.println("Calling testQueue()");
        lab.testQueue();
        System.out.println("Calling testQueueTArray()");
        lab.testQueueTArray();
        System.out.println("Calling testQueueQueueOfT()");
        lab.testQueueQueueOfT();
        System.out.println("Calling testGetFront()");
        lab.testGetFront();
        System.out.println("Calling testGetSize()");
        lab.testGetSize();
        System.out.println("Calling testIsEmpty()");
        lab.testIsEmpty();
        System.out.println("Calling testEnqueue()");
        lab.testEnqueue();
        System.out.println("Calling testDequeue()");
        lab.testDequeue();
        System.out.println("Calling testToString()");
        lab.testToString();
        System.out.println("Calling testEqualsObject()");
        lab.testEqualsObject();
    }

    private void msg(String message) {
        System.out.println(message);
    }

    private void setUp() {
        q1 = new Queue<>();
        for(int i = 'A'; i < 'M'; i++) {
            q1.enqueue("" + (char) i);
        }
        q2 = new Queue<>();
        q3 = new Queue<>(new Double[]{1.1, 2.2, 3.3, 4.4});
    }

    private void testQueue() {
        setUp();
        //assertThrows(NoSuchElementException.class, ()->{q2.getFront();});
        try {
            q2.getFront();
            msg("Must throw NoSuchElementException calling getFront() on empty queue.");
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        //assertTrue(q2.isEmpty());
        if (!q2.isEmpty()) {
            msg("isEmpty() must return true on empty queue.");
        }
    }

    private void testQueueTArray() {
        setUp();
        //assertEquals(1.1, q3.getFront());
        if (q3.getFront() != 1.1) {
            msg("getFront() must return first queue element");
        }
        //assertEquals("1.1 2.2 3.3 4.4 \n", q3.toString());
        if (!q3.toString().equals("1.1 2.2 3.3 4.4 \n")) {
            msg("toString() must return items in order separated by spaces ending in newline");
        }
        //assertEquals(4, q3.getSize());
        String[] array = null;
        //assertEquals(0, new Queue<String>(array).getSize());
        if (new Queue<String>(array).getSize() != 0) {
            msg("getSize() must return 0 for empty queue");
        }
    }

    private void testQueueQueueOfT() {
        setUp();
        Queue<String> nullQ = null;
        Queue<String> copy = new Queue<>(nullQ);
        //assertEquals(0, copy.getSize());
        if (copy.getSize() != 0) {
            msg("getSize() must return 0 for copied null or empty queue");
        }
        copy = new Queue<>(q1);
        //assertEquals("A", copy.getFront());
        if (!copy.getFront().equals("A")) {
            msg("getFront() must return first element of copied queue");
        }
        //assertEquals(q1.toString(), copy.toString());
        if (!q1.toString().equals(copy.toString())) {
            msg("toString() of copied queue must be same as original queue");
        }
        //assertEquals(copy.getSize(), q1.getSize());
        if (copy.getSize() != q1.getSize()) {
            msg("Queue copy size and original size must be the same");
        }
        //checking for deep copy
        copy.enqueue("D");
        //assertNotEquals(copy.getSize(), q1.getSize());
        if (copy.getSize() == q1.getSize()) {
            msg("After copy.enqueue(\"D\"), copy and original size must differ");
        }
    }

    private void testGetFront() {
        setUp();
        //assertThrows(NoSuchElementException.class, ()->{q2.getFront();});
        try {
            q2.getFront();
            msg("Must throw NoSuchElementException calling getFront() on empty queue.");
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        //assertEquals("A", q1.getFront());
        if (!q1.getFront().equals("A")) {
            msg("getFront() must return first element of copied queue");
        }
        //assertEquals(1.1, q3.getFront());
        if (q3.getFront() != 1.1) {
            msg("getFront() must return first queue element");
        }
    }

    private void testGetSize() {
        setUp();
        //assertEquals(12, q1.getSize());
        if (q1.getSize() != 12) {
            msg("getSize() must return 12 for Queue {" + q1.toString().trim() + "}");
        }
        //assertEquals(0, q2.getSize());
        if (q2.getSize() != 0) {
            msg("getSize() must return 0 for Queue {" + q2.toString().trim() + "}");
        }
        //assertEquals(4, q3.getSize());
        if (q3.getSize() != 4) {
            msg("getSize() must return 0 for Queue {" + q3.toString().trim() + "}");
        }
    }

    private void testIsEmpty() {
        setUp();
        //assertTrue(q2.isEmpty());
        if (!q2.isEmpty()) {
            msg("isEmpty() must return true for Queue {" + q2.toString().trim() + "}");
        }
        //assertFalse(q1.isEmpty());
        if (q1.isEmpty()) {
            msg("isEmpty() must return false for Queue {" + q1.toString().trim() + "}");
        }
    }

    private void testEnqueue() {
        Queue<String> que = new Queue<>();
        //assertEquals(0, que.getSize());
        if (que.getSize() != 0) {
            msg("getSize() must return 0 for Queue {" + que.toString().trim() + "}");
        }
        que.enqueue("A");
        //assertEquals("A", que.getFront());
        if (!que.getFront().equals("A")) {
            msg("enqueue() must add elements to end of Queue");
        }
        //assertEquals(1, que.getSize());
        if (que.getSize() != 1) {
            msg("getSize() must return 1 for Queue {" + que.toString().trim() + "}");
        }
        que.enqueue("B");
        //assertEquals("B", que.getFront());
        if (!que.getFront().equals("A")) {
            msg("enqueue() must add elements to end of Queue");
        }
        //assertEquals(2, que.getSize());
        if (que.getSize() != 2) {
            msg("getSize() must return 2 for Queue {" + que.toString().trim() + "}");
        }
        //assertEquals("A B \n", que.toString());
        if (!que.toString().equals("A B \n")) {
            msg("enqueue() must add elements to end of Queue");
        }
    }

    private void testDequeue() {
        setUp();
        //assertThrows(NoSuchElementException.class, ()->{q2.dequeue();});
        try {
            q2.dequeue();
            msg("Must throw NoSuchElementException calling dequeue() on empty queue.");
        } catch(NoSuchElementException e) {
            // thrown as expected
        }
        q1.dequeue();
        //assertEquals("B", q1.getFront());
        if (!q1.getFront().equals("B")) {
            msg("dequeue() must remove elements at front of Queue");
        }
        q3.dequeue();
        q3.dequeue();
        q3.dequeue();
        q3.dequeue();
        //assertTrue(q3.isEmpty());
        if (!q3.isEmpty()) {
            msg("In 4 element Queue, calling dequeue() x4 must empty the Queue {" + q3.toString().trim() + "}");
        }
    }

    private void testToString() {
        setUp();
        //assertEquals("A B C D E F G H I J K L \n", q1.toString());
        if (!q1.toString().equals("A B C D E F G H I J K L \n")) {
            msg("q1.toString() must return items in order followed by spaces and end in a newline");
        }
        //assertEquals("1.1 2.2 3.3 4.4 \n", q3.toString());
        if (!q3.toString().equals("1.1 2.2 3.3 4.4 \n")) {
            msg("q3.toString() must return items in order followed by spaces and end in a newline");
        }
        //assertEquals("\n", q2.toString());
        if (!q2.toString().equals("\n")) {
            msg("q2.toString() must return no items and end in a newline");
        }
    }

    private void testEqualsObject() {
        setUp();
        //assertTrue(q1.equals(q1));
        if (!q1.equals(q1)) {
            msg("A Queue must equal itself");
        }
        //assertFalse(q1.equals(null));
        if (q1.equals(null)) {
            msg("A Queue must equal not equal null");
        }
        //assertFalse(q1.equals(new String("A B C D E F G H I J K L")));
        if (q1.equals(new String("A B C D E F G H I J K L"))) {
            msg("A Queue cannot equal a String");
        }
        for(int i = 0; i < 9; i++) {
            q1.dequeue();
        }
        Queue<String> test = new Queue<>();
        test.enqueue(new String("J"));
        test.enqueue(new String("K"));
        test.enqueue(new String("L"));
        //assertTrue(q1.equals(test));
        if (!q1.equals(test)) {
            msg("Queue {" + q1.toString().trim() + "} must equal "
                + "Queue {" + test.toString().trim() + "}");
            msg("--A Queue must equal a copy of itself");
        }
        test.dequeue();
        //assertFalse(q1.equals(test));
        if (q1.equals(test)) {
            msg("Queue {" + q1.toString().trim() + "} must not equal "
                + "Queue {" + test.toString().trim() + "}");
            msg("--A Queue must equal only a complete copy of itself");
        }
        Queue<Double> test2 = new Queue<>(new Double[]{1.1, 2.2, 3.2, 4.4});
        //assertFalse(test2.equals(q3));
        if (q3.equals(test2)) {
            msg("Queue {" + test2.toString().trim() + "} does not equal "
                + "Queue {" + q3.toString().trim() + "}");
            msg("--A Queue must equal only an exact copy of itself");
        }
    }
}
