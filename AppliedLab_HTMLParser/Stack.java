/**
 * Stack class - singly-linked list version
 * @author Abdullah Mohammad
 * CIS 22C, Lab 6
 */
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
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

    /**
     * Creates a String of the Stack in reverse order
     *
     * @return a Stack in reverse order
     */
    public String reverseStack() {
        return reverseStack(top) + "\n";
    }

    /**
     * Determines whether the values are sorted from
     * smallest to largest by calling its recursive helper.
     * @return whether the list is sorted
     */
    public boolean isSorted() {
        return isSorted(top);
    }

    /**
     * Uses the recursive linear search algorithm to locate an element.
     * @param element the value to search for
     * @return whether the element is present
     * Note that in the case length == 0 the element is considered not found
     */
    public boolean linearSearch(T element) {
        return linearSearch(top, element);
    }

    /**
     * Uses the recursive binarySearch algorithm to determine if a specific
     * value is available by calling the private helper method binarySearch.
     * @param value the value to search for
     * @return whether the element is present
     * @precondition isSorted()
     * @throws IllegalStateException when the precondition is violated.
     */
    public boolean binarySearch(T value) throws IllegalStateException {
        if (!isSorted()) {
            throw new IllegalStateException("binarySearch(): Stack is not sorted. Cannot binary search.");
        }
        return binarySearch(0, size - 1, value);
    }

    /** RECURSIVE HELPER METHODS */

    /**
     * Recursively (no loops) creates a String where the data is in reverse order.
     * @param n the current node
     * @return the Stack values as a String in reverse order.
     */
    private String reverseStack(Node n) {
        if (n == null) {
            return "";
        }
        return reverseStack(n.next) + n.data + " ";
    }

    /**
     * Helper method to isSorted recursively determines whether
     * data is sorted from smallest to largest.
     * @param node the current node
     * @return whether the data is sorted in ascending order
     */
    private boolean isSorted(Node node) {
        if (node == null) {
            return true;
        }

        if (node.next == null) {
            return true;
        } else if (isSorted(node.next)) {
            if (node.data.compareTo(node.next.data) <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for the specified value by implementing the recursive
     * linearSearch algorithm.
     * @param value the value to search for
     * @return whether the value exists or not.
     */
    private boolean linearSearch(Node node, T value) {
        if (node == null) {
            return false;
        } else if (node.data.equals(value)) {
            return true;
        } else {
            return linearSearch(node.next, value);
        }
    }

    /**
     * Helper method for private binarySearch.
     * Searches for the data stored at a Node in a given "midpoint".
     * @param node the current Node in the Queue
     * @param mid the integer location in the Queue
     * @return the data stored at the mid Node
     */
    private T getMid(Node node, int mid) {
        if (mid == 0) {
            return node.data;
        } else {
            return getMid(node.next, mid - 1);
        }
    }

    /**
     * Searches for the specified value by implementing the recursive
     * binarySearch algorithm.
     * @param low   the lowest bounds of the search
     * @param high  the highest bounds of the search
     * @param value the value to search for
     * @return whether the value exists or not.
     */
    private boolean binarySearch(int low, int high, T value) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            T midValue = getMid(top, mid);
            
            if (midValue.equals(value)) {
                return true;
            } else if (midValue.compareTo(value) > 0) {
                return binarySearch(low, mid - 1, value);
            } else {
                return binarySearch(mid + 1, high, value);
            }
        }
        
        return false;
    }
}
