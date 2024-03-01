/**
 * Stack class - singly-linked list version
 * @author Abdullah Mohammad
 * CIS 22C, Lab 6
 */
import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Stack class
     * @postcondition a new Stack object with all fields
     * assigned default values
     */
    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Constructor for the Stack class
     * Converts an array into a Stack in the same order
     * @param an array of elements to copy
     * e.g. [1,2,3] becomes 1->2->3->null
     */
    public Stack(T[] array) {
        top = null;
        size = 0;

        if (array == null) {
            return;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            push(array[i]);
        }
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     * REQUIRED: THIS METHOD MUST BE IMPLEMENTED
     * IN O(N) TIME
     */
    public Stack(Stack<T> original) {
        top = null;
        size = 0;

        if (original == null) {
            return;
        }
        if (original.size == 0) {
            return;
        }

        Node temp1 = original.top;
        Node temp2 = new Node(temp1.data);
        top = temp2;

        while (temp1.next != null) {
            temp1 = temp1.next;
            temp2.next = new Node(temp1.data);
            temp2 = temp2.next;
            size++;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the value stored at the top of the Stack
     * @return the value at the top of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("peek(): Stack is empty. Cannot peek.");
        }
        return top.data;
    }

    /**
     * Determines whether a Stack is empty
     * @return whether the Stack is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the top of the Stack
     * @param data the new data to insert
     * @postcondition a new node at the end of the Stack
     */
    public void push(T data) {
        if (size == 0) {
            top = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.next = top;
            top = temp;
        }
        size++;
    }

    /**
     * Removes the top element of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the precondition
     * is violated
     * @postcondition the top element has been removed
     */
    public void pop() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("pop(): Stack is empty. Cannot pop.");
        }
        top = top.next;
        size--;       
    }


    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = top;
        while (temp != null) {
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this Stack are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true; 
        } else if (!(obj instanceof Stack)) {
            return false;
        } else if (this.size != ((Stack<T>)obj).size) {
            return false;
        }

        
        Node temp1 = this.top;
        Node temp2 = ((Stack<T>)obj).top;

        while (temp1 != null && temp2 != null) {
            if (!(temp1.data.equals(temp2.data))) {
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }
}

