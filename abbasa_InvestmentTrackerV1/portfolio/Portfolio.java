/*
 * Name : Abir Abbas
 * Date : 2017 - 10 - 16
 * Student ID : 0955448
 * Email : abbasa@mail.uoguelph.ca
 */
package portfolio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Abir Abbas
 */
public class Portfolio {
    
public static Scanner s = new Scanner(System.in); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String userInput = "";
        double tradeGain = 0;    
        ArrayList<Stocks> stockList = new ArrayList<Stocks>();
        ArrayList<MutualFunds> mutualList = new ArrayList<MutualFunds>();
        
        while (!userInput.toLowerCase().equals("quit") && !userInput.toLowerCase().equals("q") && !userInput.equals("6")) {
            //Resets All Values
            
                
            Menu();
            
            userInput = readStr();
            //Takes user input, and organizes options accordingly
            if (userInput.equals("1")) {
                clear();
                Buy(stockList, mutualList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("2")) {
                clear();
                tradeGain += Sell(stockList, mutualList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("3")) {
                clear();
                Update(stockList, mutualList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("4")) {
                clear();
                System.out.println("Total Gains from sales : " + tradeGain);
                System.out.println("Total Gains overall : " + totalGain(stockList, mutualList));
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("5")) {
                clear();
                Search(stockList, mutualList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.toLowerCase().equals("quit") || userInput.toLowerCase().equals("q") || userInput.equals("6")) {
                System.out.println("Good Bye!");
                break;
            }
            
            
            //for (int i = 0;  i < stockList.size(); i++) System.out.println(stockList.get(i));
            clear();
        }

        //stockList.remove(0);
        

    }
    
    /* Read User Int
    */
    public static int readInt() {
        int userInput = -1;
        try {
            userInput = Integer.parseInt(s.nextLine());
        }
        catch (Exception e) {
            System.out.println("Invalid Entry (Only Integers)");
            System.out.println("Press enter to retry...");
            s.nextLine();
        }
        
        return userInput;
    }
    /* Read User String
    */
    public static String readStr() {
        String userInput = "";
        try {
            userInput = s.nextLine();
        }
        catch (Exception e) {
            System.out.println("Invalid Entry (Only Strings)");
            System.out.println("Press enter to retry...");
            s.nextLine();
        }
        return userInput;
    }
    /* Read User Double
    */
    public static double readDbl() {
        double userInput = -1;
        try {
            userInput = Double.parseDouble(s.nextLine());
        }
        catch (Exception e) {
            System.out.println("Invalid Entry (Only Doubles)");
            System.out.println("Press enter to retry...");
            s.nextLine();
        }
        return userInput;
    }
    /* Prints out Menu
    */
    public static void Menu() {
        System.out.println("1. Buy investment.");
        System.out.println("2. Sell investment");
        System.out.println("3. Update Data.");
        System.out.println("4. View total gains.");
        System.out.println("5. Search for an investment.");
        System.out.println("6. Exit program. (quit, or q)");
    }
    /* Iterates through list, checks if symbol already exists, if it does, then it increments that list, if not then it adds a new instance
    */
    public static void Buy(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList) {
        int userChoice = -1;
        int quantity = -1;
        double price = -1;
        String symbol = "";
        String name = "";
        boolean trackRepeat = false;
        
        while(userChoice != 1 && userChoice != 2) {
            System.out.println("1. Stocks\n2.Mutual Funds");
            userChoice = readInt();
        }
        while (symbol.equals("")) {
            System.out.println("Please enter the company symbol :");
            symbol = readStr();
        }
        while (name.equals("")) {
            System.out.println("Please enter the company name : ");
            name = readStr();
        }
        while (quantity < 0) {
            System.out.println("Please enter the quantity (Must be greater than 0) : ");
            quantity = readInt();
        }
        while (price < 0) {
            System.out.println("Please enter the price (Must be greater than 0) : ");
            price = readDbl();
        }
        
        if (userChoice == 1) {
            if (stockList.size() > 0) { 
                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol, name)){
                        stockList.get(i).addShares(quantity, price);
                        trackRepeat=true;
                        break;
                    }
                    trackRepeat = false;
                }
            }
            else {
                stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
                trackRepeat = true;
            }
            if (trackRepeat == false) stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
        
        }
        else if (userChoice == 2) {
            if (mutualList.size() > 0) { 
                for (int i = 0; i < mutualList.size(); i++) {
                    if (mutualList.get(i).equals(symbol, name)){
                        mutualList.get(i).addShares(quantity, price);
                        trackRepeat=true;
                        break;
                    }
                    trackRepeat = false;
                }
                        
            }
            else {
                mutualList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
                trackRepeat = true;
            }
            if (trackRepeat == false) mutualList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
        }
    }
    
    
    /* Iterates through the list, finds the symbol, if it matches, offers user input for quantity and price, and sells the stock!   
    */
    public static double Sell(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList) {
        

        int quantity = -1;
        double price = -1;
        double tradeGain = 0;
        double soldPrice = 0;
        String symbol = "";

        boolean trackRepeat = false;
        
        while (symbol.equals("")) {
            System.out.println("Please enter the symbol of the investment to sell : ");
            symbol = readStr();
        }
        if (stockList.size() > 0) {
            for (int i = 0; i < stockList.size(); i++) {
                if (stockList.get(i).equals(symbol)) {
                    //System.out.println("Item has been found in Stock List:");
                    System.out.println(stockList.get(i));
                    
                    while (quantity < 0) {
                        System.out.println("Please enter the quantity you want to sell : ");
                        quantity = readInt();
                    }
                    while (price < 0) {
                        System.out.println("Please enter the price of the sold goods : ");
                        price = readDbl();
                    }
                    
                    if (quantity == stockList.get(i).getQuantity()) {
                        tradeGain += (quantity * price) - stockList.get(i).getbookValue() - 9.99; 
                        System.out.println("The item was sold at : " + ((quantity * price) - 9.99));
                        stockList.remove(i);
                    }
                    else if (quantity < stockList.get(i).getQuantity()) {
                        tradeGain-= (stockList.get(i).getbookValue() / stockList.get(i).getQuantity()) * quantity;
                        soldPrice = stockList.get(i).sellShares(quantity, price);
                        tradeGain+=soldPrice;
                        System.out.println("The item was sold at : " + soldPrice);
                        
                    }
                    else {
                        System.out.println("You cannot sell more than what you own!");
                    }
                    
                    trackRepeat = true;
                    break;
                }
            }
            if (trackRepeat != true) {
                for (int i = 0; i < mutualList.size(); i++) {
                    if (mutualList.get(i).equals(symbol)) {
                        //System.out.println("Item has been found in Mutual List:");
                        System.out.println(mutualList.get(i));
                    
                        while (quantity < 0) {
                            System.out.println("Please enter the quantity you want to sell!");
                            quantity = readInt();
                        }
                        while (price < 0) {
                            System.out.println("Please enter the price of the sold goods!");
                            price = readDbl();
                        }
                        
                        if (quantity == mutualList.get(i).getQuantity()) {
                            tradeGain += (quantity * price) - mutualList.get(i).getbookValue() - 9.99; 
                            System.out.println("The item was sold at : " + ((quantity * price) - 9.99));
                            mutualList.remove(i);
                        }
                        else if (quantity < mutualList.get(i).getQuantity()) {
                            tradeGain-= (mutualList.get(i).getbookValue() / mutualList.get(i).getQuantity()) * quantity;
                            soldPrice = mutualList.get(i).sellShares(quantity, price);
                            tradeGain+=soldPrice;
                            System.out.println("The item was sold at : " + soldPrice);
                        
                        }
                        else {
                            System.out.println("You cannot sell more than what you own!");
                        }
                    
                        trackRepeat = true;
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("There is no elements in the list!");
        }
            
        if (trackRepeat == false) {
            System.out.println("No Symbol was found, please double check the spelling/name");
        }
        
        return tradeGain;
    }
    /* Iterates through the list and asks for user to update the price, user can enter same price to leave it the same!
    */
    public static void Update(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList) {
        double price = -1;
        
        for (int i = 0; i < stockList.size(); i++) {
            System.out.println(stockList.get(i));
            System.out.println("Please enter the updated price for this item : ");
                    
            while (price < 0) {
                price = readInt();
            }
            stockList.get(i).updatePrice(price);
            price = -1;
        }
        for (int i = 0; i < mutualList.size(); i++) {
            System.out.println(mutualList.get(i));
            System.out.println("Please enter the updated price for this item : ");
                   
            while (price < 0) {
                price = readInt();
            }
            mutualList.get(i).updatePrice(price);
            price = -1;
        }
    }
    
    /* Searches using 3 different option which can be used interchangebly or together
    Search by symbol = Matches symbol and returns list
    Search by Keywords = Overrides symbol and matches based on key words without case being sensitive, and also matches based on price
    Search by price = depending on the range user enters, it will search based on price ranges between the 2 values
    */
    public static void Search(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList) {
        String keyWord = "";
        String symbol = "";
        String priceRange = "";
        String delimeters = "[ ]";
        String[] searchWords;
        String[] returnWords;
        int searchCounter = 0;
        
        System.out.println("Please enter a symbol to search (leave blank if you do not know the exact symbol): ");
        symbol = readStr();
        System.out.println("Please enter a keyword(s) to search (No special characters allowed, only delimit using spaces) : ");
        keyWord = readStr();
        System.out.println("Please enter a price range (- indicates range, Example : 15-20, or -15, or 15-) : ");
        priceRange = readStr();
        
        if (!symbol.isEmpty() && keyWord.isEmpty()) {
            if (stockList.size() > 0) {
                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol) && checkPrice(stockList, mutualList, priceRange, i, 0)) {
                        System.out.println(stockList.get(i));
                    }
                }
            }
            if (mutualList.size() > 0) {
                for (int i = 0; i < mutualList.size(); i++) {
                    if (mutualList.get(i).equals(symbol) && checkPrice(stockList, mutualList, priceRange, i, 1)) {
                        System.out.println(mutualList.get(i));
                    }
                }
            }
        }
        else {
            searchWords = keyWord.split(delimeters);
            //For Stock List
            for (int i = 0; i < stockList.size(); i++) {
                searchCounter = 0;
                returnWords = stockList.get(i).getSymbol().split(delimeters);
                for (int j = 0; j < searchWords.length; j++) {
                
                    if (!searchWords[j].isEmpty()) {
                        for (int x = 0; x < returnWords.length; x++) {
                            if (searchWords[j].toLowerCase().equals(returnWords[x].toLowerCase())) searchCounter++;
                        //System.out.println(searchWords[j] + " = " + returnWords[x]);
                        }
                    }
                }
                if (searchCounter >= searchWords.length && checkPrice(stockList, mutualList, priceRange, i, 0)) System.out.println("Found :" +stockList.get(i));
            }
            //For Mutual List
            for (int i = 0; i < mutualList.size(); i++) {
                searchCounter = 0;
                returnWords = mutualList.get(i).getSymbol().split(delimeters);
                for (int j = 0; j < searchWords.length; j++) {
                
                    if (!searchWords[j].isEmpty()) {
                        for (int x = 0; x < returnWords.length; x++) {
                            if (searchWords[j].toLowerCase().equals(returnWords[x].toLowerCase())) searchCounter++;
                        //System.out.println(searchWords[j] + " = " + returnWords[x]);
                        }
                    }
                }
                if (searchCounter >= searchWords.length && checkPrice(stockList, mutualList, priceRange, i, 1)) System.out.println("Found :" +mutualList.get(i));
            }
        }
        
        if (symbol.isEmpty() && keyWord.isEmpty() && priceRange.isEmpty()) {
            
            for (int i = 0; i < stockList.size(); i++) {
                System.out.println(stockList.get(i));
            }
            for (int i = 0; i < mutualList.size(); i++) {
                System.out.println(mutualList.get(i));
            }
            
        }
        
        if (symbol.isEmpty() && keyWord.isEmpty() && !priceRange.isEmpty()) {
            for (int i = 0; i < stockList.size(); i++) {
                if (checkPrice(stockList, mutualList, priceRange, i, 0)) System.out.println(stockList.get(i));
            }
            for (int i = 0; i < mutualList.size(); i++) {
                if (checkPrice(stockList, mutualList, priceRange, i, 1)) System.out.println(mutualList.get(i));
            }
        }
    }
    
    /* Checks if the string is a number or not, returns true if it is, returns false if it gives an error
    */
    public static boolean isNumber(String test) {
       try {
           double number = Double.parseDouble(test);
       }
       catch (NumberFormatException nfe) {
           return false;
       }
       return true;
    }
    
    /* Checks the format of the price range, ie : -15 means 0 to 15, 15- means 15 and Higher, 15-20 means 15 to 20, etc
    returns the true if it matches, rerturns false if its not in between price range
    */
    public static boolean checkPrice(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList, String priceRange, int index, int type) {
        double price = 0;
        String[] priceList;

        if (priceRange.isEmpty()) {
            return true;
        }
        else if (priceRange.substring(0, 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(1, priceRange.length()));
            //System.out.println("The price range is from 0 to " + price);
            
            if (type == 0 && stockList.get(index).getPrice() <= price) {
                return true;
            }
            else if (type == 1 && mutualList.get(index).getPrice() <= price ) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (priceRange.substring(priceRange.length() - 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(0, priceRange.length()-1));
            //System.out.println("The price range is from " + price + " and higher");
            
            if (type == 0 && stockList.get(index).getPrice() >= price) {
                return true;
            }
            else if (type == 1 && mutualList.get(index).getPrice() >= price ) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (isNumber(priceRange)) {
            price = Double.parseDouble(priceRange);
            //System.out.println("Price is exactly " + price);
            
            if (type == 0 && stockList.get(index).getPrice() == price) {
                return true;
            }
            else if (type == 1 && mutualList.get(index).getPrice() == price ) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            priceList = priceRange.split("-");
            //System.out.println("Price Range is from " + priceList[0] + " to " + priceList[1]);
            
            if (type == 0 && stockList.get(index).getPrice() >= Double.parseDouble(priceList[0]) && stockList.get(index).getPrice() <= Double.parseDouble(priceList[1])) {
                return true;
            }
            else if (type == 1 && mutualList.get(index).getPrice() >= Double.parseDouble(priceList[0]) && mutualList.get(index).getPrice() <= Double.parseDouble(priceList[1])) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    /* Iterates through list and calculates total Gains
    */
    
    public static double totalGain(ArrayList<Stocks> stockList, ArrayList<MutualFunds> mutualList) {
        double gains = 0;
        
        for (int i = 0; i < stockList.size(); i++) {
            gains += stockList.get(i).getGain();
        }
        for (int i = 0; i < mutualList.size(); i++) {
            gains += mutualList.get(i).getGain();
        }
        return gains;
    }
    
    /* simple clear console function
    */
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }
    
}


