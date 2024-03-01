/**
 * Stack class - Two Queue Version
 * @author Abdullah Mohammad
 * CIS 22C, Lab 8.2
 */
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for the Stack class
     */
    public Stack() {
        queue1 = new Queue<>();
        queue2 = new Queue<>();
    }

   /**
     * Converts an array into a Stack in the same order.
     * @param array the array to copy
     */
    public Stack(T[] array) {
        if (array == null) {
            queue1 = new Queue<>();
            queue2 = new Queue<>();
            return;
        }
        queue1 = new Queue<>(array);
        queue2 = new Queue<>();
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is an identical,
     * but distinct, copy of original
     */
    public Stack(Stack<T> original) {
        if (original == null) {
            queue1 = new Queue<>();
            queue2 = new Queue<>();
            return;
        }
        queue1 = new Queue<>(original.queue1);
        queue2 = new Queue<>(original.queue2);
    }

    /****ACCESSORS****/

    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    public int getSize() {
        return queue1.getSize() + queue2.getSize();
    }

    /**
     * Returns the value stored at the top of the Stack
     * @return the value at the top of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("peek(): Stack is empty. Cannot peek.");
        }
        return queue1.getFront();
    }

    /**
     * Determines whether a Stack is empty
     * @return whether the Stack is empty
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the top of the Stack
     * @param data the new data to insert
     * @postcondition a new node at the end of the Stack
     */
    public void push(T data) {
        queue2.enqueue(data);
        while (!queue1.isEmpty()) {
            queue2.enqueue(queue1.getFront());
            queue1.dequeue();
        }
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        
    }

    /**
     * Removes the top element of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the precondition
     * is violated
     * @postcondition the top element has been removed
     */
    public void pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("pop(): Stack is empty. Cannot pop.");
        }
        queue1.dequeue();
    }

    /**** ADDITONAL OPERATIONS ****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override
    public String toString() {
        return queue1.toString();
    }

    /**
     * Determines whether two objects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Stack)) {
            return false;
        } else {
            Stack<T> temp = (Stack<T>) obj;
            return queue1.equals(temp.queue1);
        }
    }

    /**
     * Creates a String of the Stack in reverse order.
     * @return a Stack in reverse order
     */
    public String reverseStack() {
        return queue1.reverseQueue();
    }
}
