import java.util.Scanner;

public class LabProgram {
    public static void main(String[] args) {
        LabProgram lab = new LabProgram();
        Scanner scnr = new Scanner(System.in);

        // One list for each insertion method
        LinkedList<Double> list1 = new LinkedList<Double>();
        LinkedList<Double> list2 = new LinkedList<Double>();

        // Insert each number value and show contents after each insert
        while(scnr.hasNextDouble()) {
            double number = scnr.nextDouble();
            System.out.print("List1 after calling addFirst(" + number + "): ");
            list1.addFirst(number);
            System.out.print(list1); // Prints list using toString()
            System.out.print("List2 after calling addLast(" + number + "):  ");
            list2.addLast(number);
            System.out.print(list2); // Prints list using toString()
        }

        System.out.println("\nOther methods:\tList1\tList2");
        System.out.println("getFirst():\t" + list1.getFirst() + "\t" + list2.getFirst());
        System.out.println("getLast():\t" + list1.getLast() + "\t" + list2.getLast());
        System.out.println("getLength():\t" + list1.getLength() + "\t" + list2.getLength());
        System.out.println("isEmpty():\t" + list1.isEmpty() + "\t" + list2.isEmpty());

        // Remove each element and show contents after each removal
        System.out.println();
        while (list1.getLength() > 0) { // both lists are same length
            list1.removeFirst();
            System.out.print("List1 after calling removeFirst(): " + list1);
            list2.removeFirst();
            System.out.print("List2 after calling removeFirst(): " + list2);
            if (list1.getLength() > 0) {
                list1.removeLast();
                System.out.print("List1 after calling removeLast(): " + list1);
                list2.removeLast();
                System.out.print("List2 after calling removeLast(): " + list2);
            }
        }

        System.out.println("\nOther methods:\tList1\tList2");
        System.out.println("getLength():\t" + list1.getLength() + "\t" + list2.getLength());
        System.out.println("isEmpty():\t" + list1.isEmpty() + "\t" + list2.isEmpty());
    }
}
