/**
 * CustomerInterface.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Applied Lab 4
 */
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInterface {
    public static void main(String[] args) throws IOException {
        final int NUM_MUTUAL_FUNDS = 7;
        final int NUM_CUSTOMERS = 100;
        HashTable<MutualFund> funds = new HashTable<>(NUM_MUTUAL_FUNDS * 2);
        HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);

        DecimalFormat df = new DecimalFormat("###,##0.00");

        String first, last, email, password, mutualName, ticker;
        double cash, sharePrice, numShares, fee;


        File file1 = new File("mutual_funds.txt");
        File file2 = new File("customers.txt");


        // Creating Customers
        
        try {
            Scanner fileScanner = new Scanner(file1);
            Scanner fileScanner2 = new Scanner(file2);
            while (fileScanner.hasNextLine()) {
                mutualName = fileScanner.nextLine();
                ticker = fileScanner.nextLine();
                sharePrice = Double.parseDouble(fileScanner.nextLine());
                fee = Double.parseDouble(fileScanner.nextLine());
                
                MutualFund tempFund = new MutualFund(mutualName, ticker, sharePrice, fee);         

                funds.add(tempFund);
            }
            fileScanner.close();
            
            while(fileScanner2.hasNextLine()) {
                first = fileScanner2.next();
                last = fileScanner2.next();
                fileScanner2.nextLine(); // Clear the buffer
                email = fileScanner2.nextLine();
                password = fileScanner2.nextLine();
                cash = Double.parseDouble(fileScanner2.nextLine());
                
                int numMFA = Integer.parseInt(fileScanner2.nextLine());

                /// -------------------------

                ArrayList<MutualFundAccount> tempAccounts = new ArrayList<MutualFundAccount>();

                for(int i = 0; i < numMFA; i++) {
                    mutualName = fileScanner2.nextLine();
                    numShares = Double.parseDouble(fileScanner2.nextLine());

                    MutualFund tempFund = funds.get(new MutualFund(mutualName));
                    MutualFundAccount tempMFA = new MutualFundAccount(tempFund, numShares);

                    tempAccounts.add(tempMFA);
                }
                
                Customer tempCustomer = new Customer(first, last, email, password, cash, tempAccounts);

                customers.add(tempCustomer);

            }
            fileScanner2.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
        
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        System.out.print("Please enter your email address: ");
        String userEmail = input.next();
        System.out.print("Please enter your password: ");
        String userPassword = input.next();
        input.nextLine(); // Clear the buffer
        System.out.println();

        Customer currentCustomer;

        // check if customer exists
        if (customers.find(new Customer(userEmail, userPassword)) == -1) {
            System.out.println("We don't have your account on file...\n");
            System.out.println("Let's create an account for you!");
            System.out.print("Enter your first name: ");
            String userFirstName = input.next();
            System.out.print("Enter your last name: ");
            String userLastName = input.next();
            System.out.println();

            input.nextLine(); // Clear the buffer

            System.out.println("Welcome, " + userFirstName + " " + userLastName + "!\n\n");

            currentCustomer = new Customer(userFirstName, userLastName, userEmail, userPassword);
            customers.add(currentCustomer);
        } else {
            // logging in
            currentCustomer = customers.get(new Customer(userEmail, userPassword));
            System.out.println("Welcome, " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName() + "!\n\n");
        }
        

        while (!exit) {

            System.out.println("Please select from the following options:\n");
            System.out.println("A. Purchase a Fund");
            System.out.println("B. Sell a Fund");
            System.out.println("C. Add Cash");
            System.out.println("D. Display Your Current Funds");
            System.out.println("X. Exit\n");

            System.out.print("Enter your choice: ");
            char actionChoice = input.next().charAt(0);
            
            System.out.println();

            ValueComparator valueCMP = new ValueComparator();
            NameComparator nameCMP = new NameComparator();
            MutualFund tempFund;

            switch (actionChoice) {
                case 'A':
                    System.out.println("Please select from the options below:\n");
                    // List all funds

                    System.out.print(funds.toString());

                    System.out.println();
                    
                    
                    System.out.print("Enter the ticker of the fund to purchase: ");
                    String choiceTicker1 = input.next();
                    System.out.println();
                    
                    // Get fund
                    tempFund = funds.get(new MutualFund(choiceTicker1));
            
                    
                    System.out.print("Enter the number of shares to purchase: ");
                    numShares = Integer.parseInt(input.next());
                    System.out.println();                    
            
                    // Adding Fund
                    boolean transactionCompleted = currentCustomer.addFund(numShares, tempFund);
                    
                    if(transactionCompleted) {
                        DecimalFormat df2 = new DecimalFormat("#.0");

                        System.out.println("You successfully added shares of the following fund:\n");
                        System.out.println(tempFund.toString() + "\n");
                        System.out.println("Number of shares added: " + df2.format(numShares));
                    } else {
                        System.out.println("You don't have enough cash to purchase that fund.");
                        System.out.println("Please add cash to make a purchase\n\n");
                    }

                    break;

                case 'B':
                    if (!currentCustomer.hasOpenAccounts()) {
                        System.out.println("You don't have any funds to sell at this time.\n");
                        break;
                    }

                    // List all owned funds
                    System.out.println("You own the following mutual funds:\n");
                    currentCustomer.printAccountsByName();
                    input.nextLine();

                    System.out.print("Enter the name of the fund to sell: ");
                    String choiceFund = input.nextLine();

                    // check if user has the fund
                    if (currentCustomer.getAccountByName(choiceFund) == null) {
                        System.out.println("Sorry you don't own an account by that name.\n");
                        System.out.println();
                        break;
                    }

                                
                    
                    System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
                    String numSharesStr = input.next();

                    if (numSharesStr.equals("all")) {
                        numShares = currentCustomer.getAccountByName(choiceFund).getNumShares();
                    } else {
                        numShares = Double.parseDouble(numSharesStr);
                    }

                    System.out.println();

                    // Selling Fund
                    currentCustomer.sellShares(choiceFund, numShares);
                    
                    // Print customer's funds after selling
                    System.out.println("You own the following funds:\n");
                    currentCustomer.printAccountsByName();
                    System.out.println("Your current cash balance is $" + df.format(currentCustomer.getCash()) + "\n");

                    break;

                case 'C':
                    System.out.println("Your current cash balance is $" + df.format(currentCustomer.getCash()) + "\n");
                    System.out.print("Enter the amount of cash to add: $");
                    double cashToAdd = Double.parseDouble(input.next());
                    System.out.println();

                    currentCustomer.updateCash(cashToAdd);

                    System.out.println("Your current cash balance is $" + df.format(currentCustomer.getCash()) + "\n");

                    break;

                case 'D':
                    if (!currentCustomer.hasOpenAccounts()) {
                        System.out.println("You don't have any funds to display at this time.\n");
                        break;
                    }
                    
                    System.out.println("View Your Mutual Funds By:\n");
                    System.out.println("1. Name");
                    System.out.println("2. Value\n");

                    System.out.print("Enter your choice (1 or 2): ");
                    char displayChoice = input.next().charAt(0);

                    if (displayChoice == '1') {
                        currentCustomer.printAccountsByName();
                        System.out.println();
                    } else if (displayChoice == '2') {
                        currentCustomer.printAccountsByValue();
                        System.out.println();
                    } else {
                        System.out.println("\n\nInvalid Choice!\n");
                    }

                    break;

                case 'X':
                    System.out.println("Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid menu option. Please enter A-D or X to exit.\n");
                    break;
            }
        }
    
        input.close();
    }
}