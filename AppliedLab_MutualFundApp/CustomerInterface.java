/**
 * CustomerInterface.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Applied Lab 3
 */
import java.io.*;
import java.util.Scanner;

public class CustomerInterface {
    public static BST<MutualFundAccount> accountValue = new BST<>();
    public static BST<MutualFundAccount> accountName = new BST<>();
    public static LinkedList<MutualFund> funds = new LinkedList<>();
    
    public static void main(String[] args) {

        String mutualName, ticker;
        double sharePrice;
        // double numShares;
        

        File file = new File("mutual_funds.txt");
        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                mutualName = input.nextLine();
                ticker = input.nextLine();
                sharePrice = Double.parseDouble(input.nextLine());

                MutualFund tempFund = new MutualFund(mutualName, ticker, sharePrice);

                funds.addLast(tempFund);
                // numShares++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
        char choice = ' ';
        while(choice != 'X') {
            choice = menu(funds);
        }
    }
    
    public static char menu(LinkedList<MutualFund> funds) {
        System.out.println("Please select from the following options:\n");
        System.out.println("A. Purchase a Fund");
        System.out.println("B. Sell a Fund");
        System.out.println("C. Display Your Current Funds");
        System.out.println("X. Exit\n");

        System.out.print("Enter your choice: ");
        
        Scanner input = new Scanner(System.in);
        char choice = input.nextLine().charAt(0);
        
        System.out.println();
        
        if (choice == 'A') {
            purchaseFund();
        }
        if (choice == 'B') {
            sellFund();
        }
        if (choice == 'C') {
            
        }
        if (choice == 'X') {
            
        }
        
        input.close();

        return choice;
    }

    public static MutualFundAccount displayListOfFunds() {

        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter your choice (1-7): ");
        int choice = input.nextInt();
        System.out.println();
        
        // Get fund
        funds.advanceIteratorToIndex(choice - 1);

        
        System.out.print("Enter the number of shares to purchase: ");
        int numShares = input.nextInt();
        System.out.println();
        

        System.out.println();
        
        MutualFundAccount tempFund = new MutualFundAccount(funds.getIterator(), numShares);
        


        input.close();

        return tempFund;
    }

    public static void purchaseFund() {
        
        System.out.println("Please select from the options below:\n");
        // List all funds
        System.out.print(funds.numberedListString());
        
        MutualFundAccount tempFund = displayListOfFunds();

        // Insert into BSTs
        ValueComparator valueCMP = new ValueComparator();
        NameComparator nameCMP = new NameComparator();
        
        accountValue.insert(tempFund, valueCMP);
        accountName.insert(tempFund, nameCMP);
    }
        
    public static void sellFund() {
        // Display funds
        System.out.println(accountName.inOrderString());


        MutualFundAccount tempFund = displayListOfFunds();        

        // Remove from BSTs
        ValueComparator valueCMP = new ValueComparator();
        NameComparator nameCMP = new NameComparator();
        
        accountValue.remove(tempFund, valueCMP);
        accountName.remove(tempFund, nameCMP);
    }

    public static void displayFunds() {
        // View Your Mutual Funds By:

        // 1. Name
        // 2. Value

        // Enter your choice (1 or 2): 1
        System.out.println("View your mutual funds by:\n");

        System.out.println("1. Name");
        System.out.println("2. Value\n");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your choice (1 or 2): ");
        int choice = input.nextInt();

        if(choice != 1 || choice != 2) {
            
        }
        
        System.out.println();

        if(choice == 1) {
            System.out.println(accountName.inOrderString());
        } else if (choice == 2) {
            System.out.println(accountValue.inOrderString());
        }

        input.close();
    }
}