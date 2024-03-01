/**
 * Stack class - Array Version
 * @author Abdullah Mohammad
 * CIS 22C, Lab 8.1
 */
import java.util.NoSuchElementException;

public class Stack<T> implements LIFO<T> {
    private T[] stack;
    private int currSize;
    private final int SIZE = 10;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Stack class
     * with an initial length of 10 and
     * no elements
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        stack = (T[]) new Object[currSize];
        currSize = 0;
    }

   /**
     * Converts an array into a Stack in the same order
     * @param array the array to copy
     */
    @SuppressWarnings("unchecked")
    public Stack(T[] array) {
        if (array == null) {
            return;
        }
        currSize = array.length;
        stack = (T[]) new Object[currSize];
        for (int i = 0; i < currSize; i++) {
            stack[i] = array[currSize - i - 1];
        }
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     */
    @SuppressWarnings("unchecked")
    public Stack(Stack<T> original) {
        if (original == null) {
            return;
        }
        currSize = original.currSize;
        stack = (T[]) new Object[currSize];
        for (int i = 0; i < currSize; i++) {
            stack[i] = original.stack[i];
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the currSize of the Stack
     * @return the currSize from 0 to n
     */
    public int getSize() {
        return currSize;
    }

    /**
     * Returns the value stored at the top of the Stack
     * @return the value at the top of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
        if (currSize == 0) {
            throw new NoSuchElementException("peek(): Stack is empty. Cannot peek.");
        }
        return stack[currSize - 1];
    }

    /**
     * Determines whether a Stack is empty
     * @return whether the Stack is empty
     */
    public boolean isEmpty() {
        return (currSize == 0);
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the top of the Stack
     * @param data the new data to insert
     * @postcondition a new node at the end of the Stack
     */
    public void push(T data) {
        if (currSize == stack.length) {
            resize();
        }
        stack[currSize] = data;
        currSize++;
    }

    /**
     * Removes the top element of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the precondition
     * is violated
     * @postcondition the top element has been removed
     */
    public void pop() throws NoSuchElementException {
        if (currSize == 0) {
            throw new NoSuchElementException("pop(): Stack is empty. Cannot pop.");
        }
        stack[currSize - 1] = null;
        currSize--;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override public String toString() {
        String result = "";
        for (int i = currSize - 1; i >= 0; i--) {
            result += stack[i] + " ";
        }
        return result + "\n";
    }

    /**
     * Determines whether two obects are Stacks and
     * contain the same values in the same order
     * @param obj the Object to compare to this Stack
     * @return whether obj and this are equal
     */
    @Override public boolean equals(Object obj)  {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Stack)) {
            return false;
        }
        Stack<T> s = (Stack<T>) obj;
        if (currSize != s.currSize) {
            return false;
        }
        for (int i = 0; i < currSize; i++) {
            if (!stack[i].equals(s.stack[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a String of the Stack in reverse order by calling the
     * recursive helper method.
     * @return a Stack in reverse order
     */
    public String reverseStack() {
        return reverseStack(0);
    }

    /**PRIVATE HELPER METHODS*/

    /**
     * Recursively creates a String where the data is in reverse order.
     * @param index the current index
     * @return a String of this Stack in reverse order
     */
    private String reverseStack(int index) {
        if (index == currSize) {
            return "\n";
        }
        return stack[index] + " " + reverseStack(index + 1);
    }

    /**
     * Increases the current array
     * currSize by 10
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] temp = (T[]) new Object[currSize + SIZE];
        for (int i = 0; i < currSize; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
}
