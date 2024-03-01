import java.util.Scanner;

public class LabProgram {
    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        // Make and display list
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
           list.addLast(i);
        }
        System.out.print("Created list: " + list.toString()); // toString() has \n
        System.out.println("list.offEnd(): " + list.offEnd());
        System.out.println("list.positionIterator()");
        list.positionIterator();
        System.out.println("list.offEnd(): " + list.offEnd());
        System.out.println("list.getIterator(): " + list.getIterator());
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.getIterator(): " + list.getIterator());
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.getIterator(): " + list.getIterator());
        System.out.println("list.reverseIterator()");
        list.reverseIterator();
        System.out.println("list.getIterator(): " + list.getIterator());
        System.out.println("list.addIterator(42)");
        list.addIterator(42);
        System.out.println("list.getIterator(): " + list.getIterator());
        System.out.print("list.toString(): " + list.toString());
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.addIterator(99)");
        list.addIterator(99);
        System.out.print("list.toString(): " + list.toString());
        System.out.println("list.removeIterator()");
        list.removeIterator();
        System.out.print("list.toString(): " + list.toString());
        System.out.println("list.offEnd(): " + list.offEnd());
        System.out.println("list.positionIterator()");
        list.positionIterator();
        System.out.println("list.removeIterator()");
        list.removeIterator();
        System.out.println("list.offEnd(): " + list.offEnd());
        System.out.print("list.toString(): " + list.toString());
        System.out.println("list.positionIterator()");
        list.positionIterator();
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.advanceIterator()");
        list.advanceIterator();
        System.out.println("list.removeIterator()");
        list.removeIterator();
        System.out.print("list.toString(): " + list.toString());
    }
}
