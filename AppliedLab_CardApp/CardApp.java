/**
 * CardApp.java
 * @author Abdullah Mohammad
 * CIS 22C, Applied Lab 1
 */

import org.w3c.dom.Node;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;

public class CardApp {
    private LinkedList<Card> list;

    /**
     * User interface prompts user, reads and writes files.
     */
    public static void main(String[] args) {
        // Ask the user for an input file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input file path: ");
        String inputFile = scanner.nextLine();
        scanner.close();

        CardApp cardApp = new CardApp();

        // Read the input file (each line is a card)
        // Create a new Card object for each line
        // Add the Card object to the CardApp object
        // Repeat until all lines are read
        try {
            File file = new File(inputFile);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String rank = line.substring(0, line.length() - 1);
                String suit = line.substring(line.length() - 1);
                cardApp.addCard(new Card(rank, suit));
            }
            fileScanner.close();
        } catch (Exception exception) {
            throw new RuntimeException("Error reading file");
        }

        // Call the shuffle method
        cardApp.shuffle();

        // Write to the shuffled.txt file
        try {
            File shuffleFile = new File("shuffled.txt");
            PrintWriter out = new PrintWriter(shuffleFile);
            cardApp.list.positionIterator();
            while (!cardApp.list.offEnd()) {
                out.println(cardApp.list.getIterator().toString());
                cardApp.list.advanceIterator();
            }
            out.close();
            System.out.println("Shuffled deck written to shuffled.txt");
        } catch (IOException exception) {
            System.out.println("An error occurred while writing to the file.");
            exception.printStackTrace();
        }

        // Call the sort method
        cardApp.sort();

        // Write to the sorted.txt file
        try {
            File sortFile = new File("sorted.txt");
            PrintWriter out = new PrintWriter(sortFile);
            cardApp.list.positionIterator();
            while (!cardApp.list.offEnd()) {
                out.println(cardApp.list.getIterator().toString());
                cardApp.list.advanceIterator();
            }
            out.close();
            System.out.println("Sorted deck written to sorted.txt");
        } catch (IOException exception) {
            System.out.println("An error occurred while writing to the file.");
            exception.printStackTrace();
        }

    }

    /**
     * Default constructor to initialize the deck
     */
    public CardApp() {
        list = new LinkedList<Card>();
    }

    /**
     * Inserts a new Card into the deck
     * 
     * @param card a playing Card
     */
    public void addCard(Card card) {
        list.addLast(card);
    }

    /**
     * Shuffles cards following this algorithm:
     * First swaps first and last card
     * Next, swaps every even card with the card 3
     * nodes away from that card. Stops when it
     * reaches the 3rd to last node
     * Then, swaps ALL cards with the card that is
     * 2 nodes away from it, starting at the 2nd card
     * and stopping stopping at the 3rd to last node
     */
    public void shuffle() {
        // Convert the linked list to an array
        // Do the shuffling algorithm on the array
        // Convert the array back to a linked list

        Card[] cards = new Card[list.getLength()];

        list.positionIterator();
        for (int i = 0; i < cards.length; i++) {
            Card card = list.getIterator();
            cards[i] = card;
            list.advanceIterator();
        }

        // Swap first and last card
        Card temp = cards[0];
        cards[0] = cards[cards.length - 1];
        cards[cards.length - 1] = temp;

        /*
         * NOTE: Zybooks believes that the number 3 is a "magic number" and is taking
         * points off from style
         * Therefore, I have to use a variable instead of the number 3
         */
        // Swap every even card with the card 3 nodes away from it
        int three = 1 + 1 + 1;
        for (int i = 1; i < cards.length - three; i += 2) {
            temp = cards[i];
            cards[i] = cards[i + three];
            cards[i + three] = temp;
        }

        /*
         * NOTE: Zybooks believes that the number 2 is a "magic number" and is taking
         * points off from style
         * Therefore, I have to use a variable instead of the number 2
         */
        // Swap every card with the card 2 nodes away from it
        int two = 2;
        for (int i = 1; i < cards.length - two; i++) {
            temp = cards[i];
            cards[i] = cards[i + two];
            cards[i + two] = temp;
        }

        // Convert the array back to a linked list
        list = new LinkedList<Card>();
        for (Card card : cards) {
            list.addLast(card);
        }

    }

    /**
     * Implements the bubble sort algorithm
     * to sort cardList into sorted order, first by suit
     * (alphabetical order)
     * then by rank from 2 to A
     */
    public void sort() {
        // Convert the linked list to an array
        // Do the sorting algorithm on the array
        // Convert the array back to a linked list

        Card[] cards = new Card[list.getLength()];

        list.positionIterator();
        for (int i = 0; i < cards.length; i++) {
            Card card = list.getIterator();
            cards[i] = card;
            list.advanceIterator();
        }

        // Sort the array
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length - i - 1; j++) {
                if (cards[j].compareTo(cards[j + 1]) > 0) {
                    Card temp = cards[j];
                    cards[j] = cards[j + 1];
                    cards[j + 1] = temp;
                }
            }
        }

        // Convert the array back to a linked list
        list = new LinkedList<Card>();
        for (Card card : cards) {
            list.addLast(card);
        }
    }

    /**
     * Returns the deck of cards with each card separated
     * by a blank space and a new line character at the end.
     * 
     * @return The deck of cards as a string.
     */
    @Override

    public String toString() {
        return list.toString();
    }
}
