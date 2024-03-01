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
            Scanner FileScanner = new Scanner(file);

            while (FileScanner.hasNextLine()) {
                mutualName = FileScanner.nextLine();
                ticker = FileScanner.nextLine();
                sharePrice = Double.parseDouble(FileScanner.nextLine());

                MutualFund tempFund = new MutualFund(mutualName, ticker, sharePrice);

                funds.addLast(tempFund);
                // numShares++;
            }
            FileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
        
        Scanner input = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Please select from the following options:\n");
            System.out.println("A. Purchase a Fund");
            System.out.println("B. Sell a Fund");
            System.out.println("C. Display Your Current Funds");
            System.out.println("X. Exit\n");

            System.out.print("Enter your choice: ");
            char actionChoice = input.next().charAt(0);
            System.out.println();


            ValueComparator valueCMP = new ValueComparator();
            NameComparator nameCMP = new NameComparator();
            MutualFundAccount tempFund;
            
            switch (actionChoice) {
                case 'A':
            
                    System.out.println("Please select from the options below:\n");
                    // List all funds
                    System.out.print(funds.numberedListString());
                    

                    System.out.print("Enter your choice: (1-7): ");
                    int choice = Integer.parseInt(String.valueOf(input.next().charAt(0)));

                    System.out.println();
                    
                    // Get fund
                    funds.advanceIteratorToIndex(choice - 1);
            
                    
                    System.out.print("Enter the number of shares to purchase: ");
                    int numShares = Integer.parseInt(input.next());
                    System.out.println();
                    
                    tempFund = new MutualFundAccount(funds.getIterator(), numShares);
                    
            
                    
                    // Add to account (if it exists, update shares, else add new account)
                    if (accountName.search(tempFund, nameCMP) != null) {
                        MutualFundAccount found = accountName.search(tempFund, nameCMP);
                        found.updateShares(found.getNumShares() + numShares);
                    } else {
                        accountName.insert(tempFund, nameCMP);
                        accountValue.insert(tempFund, valueCMP);
                    }

                    
                    break;

                case 'B':
                    if (accountName.isEmpty()) {
                        System.out.println("You don't have any funds to sell at this time.\n");
                        break;
                    }

                    System.out.println("You own the following mutual funds:");


                    System.out.println(accountName.inOrderString());

                    input.nextLine();
                    System.out.print("Enter the name of the fund to sell: ");
                    String fundName = input.nextLine();
                    System.out.println();

                    System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
                    String sharesToSell = input.next();

                    MutualFundAccount temp = new MutualFundAccount(new MutualFund(fundName, "", 0), 0);
                    MutualFundAccount found = accountName.search(temp, nameCMP);

                    if (sharesToSell.equals("all")) {
                        accountValue.remove(found, valueCMP);
                        accountName.remove(found, nameCMP);
                    } else {
                        int shares = Integer.parseInt(sharesToSell);
                        found.updateShares(found.getNumShares() - shares);
                    }

                    // Reorder the binary search trees (to account for the change in value)
                    BST<MutualFundAccount> tempName = new BST<>(accountName, nameCMP);
                    BST<MutualFundAccount> tempValue = new BST<>(accountValue, valueCMP);

                    accountName = tempName;
                    accountValue = tempValue;


                    break;

                case 'C':
                    if (accountName.isEmpty() || accountValue.isEmpty()) {
                        System.out.println("You don't have any funds to display at this time.\n");
                        break;
                    }
                    System.out.println("View Your Mutual Funds By:\n");
                    System.out.println("1. Name");
                    System.out.println("2. Value\n");
                    System.out.print("Enter your choice (1 or 2): ");
                    char viewChoice = input.next().charAt(0);
                    // System.out.println("'" + viewChoice + "'");
                    System.out.println();

                    switch (viewChoice) {
                        case '1':
                            System.out.println(accountName.inOrderString());
                            break;
                        case '2':
                            System.out.println(accountValue.inOrderString());
                            break;
                        default:
                            System.out.println("Invalid Choice!\n");
                            break;
                    }


                    break;

                case 'X':
                    input.close();
                    exit = true;
                    System.out.println("Goodbye!");

                    break;

                default:
                    System.out.println("Invalid menu option. Please enter A-C or X to exit.\n");
                    break;
            }
        }
        
    }
    
}