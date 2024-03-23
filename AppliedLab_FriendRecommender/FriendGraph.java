/**
 * FriendGraph.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Applied Lab 5
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FriendGraph {
    static private Graph friendGraph;
    static private ArrayList<Integer> ids = new ArrayList<Integer>();
    static private ArrayList<String> names = new ArrayList<String>();
    
    /**
     * Main method for the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Friend Recommender!\n");
        
        System.out.print("Enter the name of a file: ");
        String fileName = scanner.next();
        scanner.nextLine();
        int numPeople = readFile(fileName);
        while (numPeople == -1) {
            System.out.print("Enter the name of a file: ");
            fileName = scanner.next();
            scanner.nextLine();
            numPeople = readFile(fileName);
        }
        System.out.println("\n");


        System.out.println("Enter your user number below: ");
        for(int i = 0; i < numPeople; i++) {
            System.out.println(ids.get(i) + ". " + names.get(i));
        }
        System.out.println();
        System.out.print("Enter your choice: ");
        int userId = Integer.parseInt(scanner.next());
        // scanner.nextLine(); 
        System.out.println("\n");

        int negTwo = -1 - 1;
        int friendToAdd = negTwo;
        while(friendToAdd != -1) {
            printCurrentFriends(userId);
            boolean recommendationsExist = printRecommendedFriends(userId);
            if(!recommendationsExist) {
                break;
            }

            System.out.println("Enter the number of a friend to add or -1 to quit: ");
            System.out.print("Enter your choice: ");
            friendToAdd = Integer.parseInt(scanner.next());
            // scanner.nextLine();

            if(friendToAdd != -1) {
                friendGraph.addUndirectedEdge(userId, friendToAdd);
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    
    /**
     * Reads the file and creates a graph of friends.
     * @param fileName
     * @return the number of people in the file
     */
    public static int readFile(String fileName) {
        int numPeople = -1;
        
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            
            numPeople = Integer.parseInt(fileScanner.nextLine());
            int id;
            String name;

            friendGraph = new Graph(numPeople);
            
            for(int i = 0; i < numPeople; i++) {
                id = Integer.parseInt(fileScanner.nextLine());
                name = fileScanner.nextLine();
                names.add(name);
                ids.add(id);


                String friendLine = fileScanner.nextLine();
                friendLine = friendLine.substring(0, friendLine.length() - 1);
                String[] tempList = friendLine.split(", ");                

                for(int j = 0; j < tempList.length; j++) {
                    friendGraph.addDirectedEdge(id, Integer.parseInt(tempList[j]));
                }
            }
            
            fileScanner.close();
        } catch (IOException exception) {
            System.out.println("Invalid file name");
            return -1;
        }

        return numPeople;
    }

    /**
     * Prints the current friends of the user.
     * @param userId
     * @return true
     */
    public static boolean printCurrentFriends(int userId) {
        LinkedList<Integer> friendIds = friendGraph.getAdjacencyList(userId);
        friendIds.positionIterator();

        System.out.println("Here are your current friends:");
        for(int i = 0; i < friendIds.getLength(); i++) {
            int friendId = friendIds.getIterator();
            System.out.println(friendId + ". " + names.get(friendId - 1)); 
            friendIds.advanceIterator();
        }
        System.out.println();
        return true;
    }
        
    /**
     * Prints the recommended friends of the user.
     * @param userId
     * @return true if there are recommendations, false otherwise
     */
    public static boolean printRecommendedFriends(int userId) {
        System.out.println("Here are your recommended friends:");
        friendGraph.BFS(userId);

        int recommendedFriendsCount = 0;
        
        for(int i = 0; i < ids.size(); i++) {           
            if(friendGraph.getDistance(ids.get(i)) > 1) {
                System.out.println(ids.get(i) + ". " + names.get(i));  
                recommendedFriendsCount++;
            }
        }

        if(recommendedFriendsCount == 0) {
            System.out.println("Sorry! We don't have any recommendations for you at this time.");
            return false;
        }

        System.out.println();
        return true;
    }
}
