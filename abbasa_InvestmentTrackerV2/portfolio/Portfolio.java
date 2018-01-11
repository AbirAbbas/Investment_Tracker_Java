/*
 * Name : Abir Abbas
 * Date : 2017 - 10 - 16
 * Student ID : 0955448
 * Email : abbasa@mail.uoguelph.ca
 */
package portfolio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Spliterator;

/**
 *
 * @author Abir Abbas
 */
public class Portfolio {

//global declaration of scanner so it doesn't have to be declared several times!    
public static Scanner s = new Scanner(System.in); 
private static DecimalFormat df2 = new DecimalFormat(".##"); //format for 2 decimal places
    /**
     * @param args the command line arguments
     * calls and executes menu functions 
     */
    public static void main(String[] args) {
        
        String userInput = "";
        double tradeGain = 0;
        File openFile = null;
        String fileInput = "";
        ArrayList<Investment> stockList = new ArrayList<Investment>();
        HashMap<String, ArrayList<Integer>> hashList = new HashMap<String, ArrayList<Integer>>(10);
                
        String splitInput[];
        String type = "";
        String symbol = "";
        String name= "";
        int quantity = 0;
        double price = 0;
        double bookValue = 0;
        int index = 0;
        
        boolean fileExists = true;
        
            //If user did not specify a file, program will 
            if (args.length >= 1) openFile = new File(args[0]);
            
            else {
                System.out.println("No file name was entered, program will exit, please enter the fileName when running the program!");
                System.exit(0);
            }
            
            Scanner StreamObject = null;
             
            try {
                StreamObject = new Scanner(new FileInputStream(openFile));
                System.out.println("File was opened.");
            } catch (FileNotFoundException ex) {
                 System.out.println("File was not found!, fill will be created when the investments are saved!");
                 fileExists = false;
            }
            
            if (fileExists == true) {
                
                while (StreamObject.hasNextLine()) {
                    fileInput = StreamObject.nextLine();
                
                    if (fileInput.contains("type = ")) {
                        splitInput = fileInput.split("\"");
                        type = splitInput[1];
                    }
                    else if (fileInput.contains("symbol = ")) {
                        splitInput = fileInput.split("\"");
                        symbol = splitInput[1];
                    }
                    else if (fileInput.contains("name = ")) {
                        splitInput = fileInput.split("\"");
                        name = splitInput[1];
                    }
                    else if (fileInput.contains("quantity = ")) {
                        splitInput = fileInput.split("\"");
                        if (isNumber(splitInput[1])) quantity = Integer.parseInt(splitInput[1]);
                    }
                    else if (fileInput.contains("price = ")) {
                        splitInput = fileInput.split("\"");
                        if (isNumber(splitInput[1])) price = Double.parseDouble(splitInput[1]);
                    }
                    else if (fileInput.contains("bookValue = ")) {
                        splitInput = fileInput.split("\"");
                        if (isNumber(splitInput[1])) bookValue = Double.parseDouble(splitInput[1]);
                    }
                    else if (fileInput.isEmpty()) { //create array instance here
                        if (type.equals("stock")) {
                            stockList.add(new Stocks(symbol, name, quantity, price, bookValue));
                        }
                    
                        else if (type.equals("mutualfund")) { 
                            stockList.add(new MutualFunds(symbol, name, quantity, price, bookValue));
                        }
                    }
                }
            
                StreamObject.close();
            }
        
        //ArrayList<MutualFunds> mutualList = new ArrayList<MutualFunds>();
        
        while (!userInput.toLowerCase().equals("quit") && !userInput.toLowerCase().equals("q") && !userInput.equals("6")) {
            hashFunction(stockList, hashList);
            //Resets All Values
   
            Menu();
            
            userInput = readStr();
            //Takes user input, and organizes options accordingly
            if (userInput.equals("1")) {
                clear();
                Buy(stockList, hashList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("2")) {
                clear();
                tradeGain += Sell(stockList, hashList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("3")) {
                clear();
                Update(stockList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("4")) {
                clear();
                System.out.println("Total Gains from sales : " + df2.format(tradeGain));
                System.out.println("Total Gains overall : " + df2.format(totalGain(stockList, hashList)));
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.equals("5")) {
                clear();
                Search(stockList, hashList);
                System.out.println("Press enter to continue...");
                s.nextLine();
            }
            else if (userInput.toLowerCase().equals("quit") || userInput.toLowerCase().equals("q") || userInput.equals("6")) {
                
                PrintWriter outputStream = null; // declared outside of try catch so that it can be accessed outside of try catch
                
                try {
                // TODO code application logic here
                outputStream = new PrintWriter(new FileOutputStream(args[0])); // opens file to write/overwrite
                } catch (FileNotFoundException ex) {
                    System.out.println("Error opening the file test.txt.");
                    System.exit(0);
                }
                
                for (int z = 0; z < stockList.size(); z++) { //Save file is complete
                    outputStream.println("type = \"" + stockList.get(z).getType() + "\"");
                    outputStream.println("symbol = \"" + stockList.get(z).getSymbol()+ "\"");
                    outputStream.println("name = \"" + stockList.get(z).getName()+ "\"");
                    outputStream.println("quantity = \"" + stockList.get(z).getQuantity()+ "\"");
                    outputStream.println("price = \"" + stockList.get(z).getPrice()+ "\"");
                    outputStream.println("bookValue = \"" + stockList.get(z).getbookValue()+ "\"");
                    outputStream.println("");
                }
                
                outputStream.close();
                
                System.out.println("Good Bye!");
                break;
            }
            else {
                System.out.println("Invalid Input.");
                System.out.println("Press any key to continue...");
                readStr();
            }
            
            
            //for (int i = 0;  i < stockList.size(); i++) System.out.println(stockList.get(i));
            clear();
        }

        //stockList.remove(0);
        

    }
    
    
    /* Read User Int
    @params none
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
    @param none
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
    @param none
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
    @param none
    */
    public static void Menu() {
        System.out.println("1. Buy investment.");
        System.out.println("2. Sell investment");
        System.out.println("3. Update Data.");
        System.out.println("4. View total gains.");
        System.out.println("5. Search for an investment.");
        System.out.println("6. Exit program. (quit, or q)");
    }
    
    
    /*
    * @param stockList ,pointer to original stock list that will be used to create the HashMap with all iterations
    * @param hashList , the original HashMap that will be used to store the newly created HashSet
    */
    public static void hashFunction(ArrayList<Investment> stockList, HashMap<String, ArrayList<Integer>> hashList) {

        int temp = 0;
       
        for (int i = 0; i < stockList.size(); i ++) {
            String [] splitter = stockList.get(i).getName().split(" ");
            
            for (int a = 0 ; a < splitter.length; a++) {
                splitter[a] = splitter[a].toLowerCase();
            }
            
            for (int x = 0; x < splitter.length; x ++) {
            ArrayList<Integer> searchIndex = new ArrayList<Integer>();  
                for (int j = 0; j < stockList.size(); j ++) {
                    if (stockList.get(j).getName().toLowerCase().contains(splitter[x].toLowerCase())) {
                        if (!searchIndex.contains(j)) searchIndex.add(j);
                    }
                }
                hashList.put(splitter[x], searchIndex);
            }
            
        }
    }
    
    /*
    @param hashList, contains original Hashmap list that will be printed out if needed!
    */
    public static void printHash(HashMap<String, ArrayList<Integer>> hashList) {
        String s = hashList.keySet().toString().substring(1, hashList.keySet().toString().length() - 1);
        String[] splitter = s.split(", ");
        for (int i = 0; i < splitter.length; i ++) {
            System.out.println(splitter[i]+ " = " +hashList.get(splitter[i]));
        }
    }
    
    /* Iterates through list, checks if symbol already exists, if it does, then it increments that list, if not then it adds a new instance
    @param stockList, hashList, are needed to add the new stock/mutualfund entered by the user
    */
    public static void Buy(ArrayList<Investment> stockList, HashMap<String, ArrayList<Integer>> hashList) {
        int userChoice = -1;
        int quantity = -1;
        double price = -1;
        String symbol = "";
        String name = "";
        boolean trackRepeat = false;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        
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
        
        
        if (stockList.size() > 0) { 
            for (int i = 0; i < stockList.size(); i++) {
                if (stockList.get(i).equals(symbol)){
                    stockList.get(i).addShares(quantity, price);
                    trackRepeat=true;
                    break;
                }
                trackRepeat = false;
            }
        }
        else {
            if (userChoice == 1) {
                stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
                trackRepeat = true;
            }
            else {
                stockList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
                trackRepeat = true;
            }
        }
        
        if (trackRepeat == false) 
        {
            if (userChoice == 1) {
                stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
            }
            else {
                stockList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
            }
        }
    }
    
    
    /* Iterates through the list, finds the symbol, if it matches, offers user input for quantity and price, and sells the stock!   
    @param stockList and hashList, are needed to remove the instance of stock/mutual fund from boht lists!
    */
    public static double Sell(ArrayList<Investment> stockList, HashMap<String, ArrayList<Integer>> hashList) {
        

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
                        //put this into the subclasses, because mutualFund is -45!
                        tradeGain += (quantity * price) - stockList.get(i).getbookValue() - stockList.get(i).getFee(); 
                        System.out.println("The item was sold at : " + df2.format((quantity * price) - stockList.get(i).getFee()));
                        stockList.remove(i);
                        
                    }
                    else if (quantity < stockList.get(i).getQuantity()) {
                        tradeGain-= (stockList.get(i).getbookValue() / stockList.get(i).getQuantity()) * quantity;
                        soldPrice = stockList.get(i).sellShares(quantity, price);
                        tradeGain+=soldPrice;
                        System.out.println("The item was sold at : " + df2.format(soldPrice));  
                    }
                    else {
                        System.out.println("You cannot sell more than what you own!");
                    }
                    
                    trackRepeat = true;
                    break;
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
    @param, stockList is needed to update all prices in the list!
    */
    public static void Update(ArrayList<Investment> stockList) {
        double price = -1;
        
        for (int i = 0; i < stockList.size(); i++) {
            System.out.println(stockList.get(i));
            System.out.println("Please enter the updated price for this item : ");
                    
            while (price < 0) {
                price = readDbl();
            }
            stockList.get(i).updatePrice(price);
            price = -1;
        }
    }
    
    /* Searches using 3 different option which can be used interchangebly or together
    Search by symbol = Matches symbol and returns list
    Search by Keywords = Overrides symbol and matches based on key words without case being sensitive, and also matches based on price
    Search by price = depending on the range user enters, it will search based on price ranges between the 2 values
    
    @param stockList, needed for sequential search by symbol/price range only
    @param hashList, needed to do a hashSearch by keywords
    
    Algorithm for using hash maps :
    
        Goes through the hashmap list and retrieves the indexes of the key words
        Checks if any key words intersect with one another
        A second array is created containing only the intersections
        The second array is used to grab the indexes and output information based on the users filters!
    */
    public static void Search(ArrayList<Investment> stockList, HashMap<String, ArrayList<Integer>> hashList) {
        String keyWord = "";
        String symbol = "";
        String priceRange = "";
        String delimeters = "[ ]";
        ArrayList<Integer> allIndex = new ArrayList<Integer>();
        ArrayList<Integer> intersectIndex = new ArrayList<Integer>();
        String[] searchWords;
        String[] returnWords;
        String[] splitWords;
        int searchCounter = 0;
        
        System.out.println("Please enter a symbol to search (leave blank if you do not know the exact symbol): ");
        symbol = readStr().toLowerCase();
        System.out.println("Please enter a keyword(s) to search (No special characters allowed, only delimit using spaces) : ");
        keyWord = readStr().toLowerCase();
        System.out.println("Please enter a price range (- indicates range, Example : 15-20, or -15, or 15-) : ");
        priceRange = readStr();
        
        if (!symbol.isEmpty() && keyWord.isEmpty()) {
           
                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol) && checkPrice(stockList, priceRange, i, hashList)) {
                        System.out.println(stockList.get(i));
                    }
                }
            
        }
        else if (symbol.isEmpty() && !keyWord.isEmpty()){
            searchWords = keyWord.split(delimeters);
            //For Stock List
            for (int i = 0; i < searchWords.length; i ++) {
                if (hashList.containsKey(searchWords[i])) {
                    for (int x = 0; x < hashList.get(searchWords[i]).size(); x++) {
                        
                        if (allIndex.contains(hashList.get(searchWords[i]).get(x))) {
                            intersectIndex.add(hashList.get(searchWords[i]).get(x));
                        }
                        else {
                            allIndex.add(hashList.get(searchWords[i]).get(x));
                        }
                        
                    }
                }
            }
            if (searchWords.length <= 1) {
                for (int i = 0; i < allIndex.size(); i ++) {
                    if (checkPrice(stockList, priceRange, allIndex.get(i), hashList)) System.out.println(stockList.get(allIndex.get(i)));
                }
            }
            else {
                for (int i = 0; i < intersectIndex.size(); i ++) {
                    if (checkPrice(stockList, priceRange, intersectIndex.get(i), hashList)) System.out.println(stockList.get(intersectIndex.get(i)));
                }
            }
            
            
            
            
            //Old version of searching with keys
            /*
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
                if (searchCounter >= searchWords.length && checkPrice(stockList, priceRange, i, hashList)) System.out.println("Found :" +stockList.get(i));
            }
            */
            
        }
        
        else if (symbol.isEmpty() && keyWord.isEmpty() && priceRange.isEmpty()) {
            
            for (int i = 0; i < stockList.size(); i++) {
                System.out.println(stockList.get(i));
            }
        }
        else if (!symbol.isEmpty() && !keyWord.isEmpty()) {
            searchWords = keyWord.split(delimeters);
            for (int i = 0; i < searchWords.length; i ++) {
                if (hashList.containsKey(searchWords[i])) {
                    for (int x = 0; x < hashList.get(searchWords[i]).size(); x++) {
                        if (allIndex.contains(hashList.get(searchWords[i]).get(x))) {
                            intersectIndex.add(hashList.get(searchWords[i]).get(x));
                        }
                        else {
                            allIndex.add(hashList.get(searchWords[i]).get(x));
                        }
                    }
                }
            }
            if (searchWords.length <= 1) {
                for (int i = 0; i < allIndex.size(); i ++) {
                    if (checkPrice(stockList, priceRange, allIndex.get(i), hashList)) {
                        if (stockList.get(allIndex.get(i)).equals(symbol)) {
                            System.out.println(stockList.get(allIndex.get(i)));
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < intersectIndex.size(); i ++) {
                    if (checkPrice(stockList, priceRange, intersectIndex.get(i), hashList)) {
                        if (stockList.get(intersectIndex.get(i)).equals(symbol)) {
                            System.out.println(stockList.get(intersectIndex.get(i)));
                        }
                    }
                }
            }
        }
        
        else if (symbol.isEmpty() && keyWord.isEmpty() && !priceRange.isEmpty()) {
            for (int i = 0; i < stockList.size(); i++) {
                if (checkPrice(stockList, priceRange, i, hashList)) System.out.println(stockList.get(i));
            }
        }
    }
    
    /* Checks if the string is a number or not, returns true if it is, returns false if it gives an error
    @param test : the string to be tested whether it is a string or a integer!
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
    @param stockList needed to checkPrices
    @param priceRange contains the the string entered by the user for price ranges
    @param index contains the index of the stock/mutualfund currently being checked for price
    */
    public static boolean checkPrice(ArrayList<Investment> stockList, String priceRange, int index, HashMap<String, ArrayList<Integer>> hashList) {
        double price = 0;
        String[] priceList;

        if (priceRange.isEmpty()) {
            return true;
        }
        else if (priceRange.substring(0, 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(1, priceRange.length()));
            //System.out.println("The price range is from 0 to " + price);
            
            if (stockList.get(index).getPrice() <= price) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (priceRange.substring(priceRange.length() - 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(0, priceRange.length()-1));
            //System.out.println("The price range is from " + price + " and higher");
            
            if (stockList.get(index).getPrice() >= price) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (isNumber(priceRange)) {
            price = Double.parseDouble(priceRange);
            //System.out.println("Price is exactly " + price);
            
            if (stockList.get(index).getPrice() == price) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            priceList = priceRange.split("-");
            //System.out.println("Price Range is from " + priceList[0] + " to " + priceList[1]);
            
            if (stockList.get(index).getPrice() >= Double.parseDouble(priceList[0]) && stockList.get(index).getPrice() <= Double.parseDouble(priceList[1])) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    /* Iterates through list and calculates total Gains
    @param stockList needed to get all gains 
    */
    
    public static double totalGain(ArrayList<Investment> stockList, HashMap<String,ArrayList<Integer>> hashList) {
        double gains = 0;
        
        for (int i = 0; i < stockList.size(); i++) {
            gains += stockList.get(i).getGain();
        }
        return gains;
    }
    
    /* simple clear console function
    @param none
    */
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }
    
}


