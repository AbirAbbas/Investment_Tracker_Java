/*
 * Name : Abir Abbas
 * Date : 2017 - 10 - 16
 * Student ID : 0955448
 * Email : abbasa@mail.uoguelph.ca
 */
package guiAssignment3;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Abir Abbas
 */
public class Portfolio {

//global declaration of scanner so it doesn't have to be declared several times!    
    private static DecimalFormat df2 = new DecimalFormat(".##"); //format for 2 decimal places
    private static String fileName = "";
    private static ArrayList<Investment> stockList = new ArrayList<Investment>();
    private static HashMap<String, ArrayList<Integer>> hashList = new HashMap<String, ArrayList<Integer>>(10);

    /**
     * Constructor to create the Portfolio Object
     *
     * @param fileName : the fileName that is to be opened and check if it
     * already exists, if it does not then it will create the file when closing
     * the program
     *
     */
    public Portfolio(String fileName) throws PortfolioException {

        File openFile = null;
        String fileInput = "";
        this.fileName = fileName;
        String splitInput[];
        String type = "";
        String symbol = "";
        String name = "";
        int quantity = 0;
        double price = 0;
        double bookValue = 0;

        boolean fileExists = true;

        openFile = new File(fileName);

        Scanner StreamObject = null;

        try {
            StreamObject = new Scanner(new FileInputStream(openFile));
            System.out.println("File was opened.");
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found!, file with the provided name will be created when the investments are saved!");
            fileExists = false;
        }

        if (fileExists == true) {

            while (StreamObject.hasNextLine()) {
                fileInput = StreamObject.nextLine();

                if (fileInput.contains("type = ")) {
                    splitInput = fileInput.split("\"");
                    type = splitInput[1];
                } else if (fileInput.contains("symbol = ")) {
                    splitInput = fileInput.split("\"");
                    symbol = splitInput[1];
                } else if (fileInput.contains("name = ")) {
                    splitInput = fileInput.split("\"");
                    name = splitInput[1];
                } else if (fileInput.contains("quantity = ")) {
                    splitInput = fileInput.split("\"");
                    if (isNumber(splitInput[1])) {
                        quantity = Integer.parseInt(splitInput[1]);
                    }
                } else if (fileInput.contains("price = ")) {
                    splitInput = fileInput.split("\"");
                    if (isNumber(splitInput[1])) {
                        price = Double.parseDouble(splitInput[1]);
                    }
                } else if (fileInput.contains("bookValue = ")) {
                    splitInput = fileInput.split("\"");
                    if (isNumber(splitInput[1])) {
                        bookValue = Double.parseDouble(splitInput[1]);
                    }
                } else if (fileInput.isEmpty()) { //create array instance here
                    if (type.equals("stock")) {
                        stockList.add(new Stocks(symbol, name, quantity, price, bookValue));
                    } else if (type.equals("mutualfund")) {
                        stockList.add(new MutualFunds(symbol, name, quantity, price, bookValue));
                    }
                }
            }

            StreamObject.close();
        }
        //Hashes current List
        hashFunction();

    }

    /**
     *
     * @param gainText : the pointer to the textArea so that it may be edited
     * freely!
     * @param textBox : the pointer to the textBox so that it may be edited
     * freely!
     */
    public static void showGains(JTextArea gainText, JTextField textBox) {
        double sum = 0;
        for (int i = 0; i < stockList.size(); i++) {
            gainText.append("Gain from " + stockList.get(i).getSymbol() + " is = $" + df2.format(stockList.get(i).getGain()) + "\n");
            sum += stockList.get(i).getGain();
        }

        textBox.setText(df2.format(sum) + "");
    }

    /**
     * No params, simply writes everything inside the stockList into the
     * specified file in the begining of the program
     */
    public static void writeFile() {
        PrintWriter outputStream = null; // declared outside of try catch so that it can be accessed outside of try catch

        try {
            // TODO code application logic here
            outputStream = new PrintWriter(new FileOutputStream(fileName)); // opens file to write/overwrite
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening the file test.txt.");
            System.exit(0);
        }

        for (int z = 0; z < stockList.size(); z++) { //Save file is complete
            outputStream.println("type = \"" + stockList.get(z).getType() + "\"");
            outputStream.println("symbol = \"" + stockList.get(z).getSymbol() + "\"");
            outputStream.println("name = \"" + stockList.get(z).getName() + "\"");
            outputStream.println("quantity = \"" + stockList.get(z).getQuantity() + "\"");
            outputStream.println("price = \"" + stockList.get(z).getPrice() + "\"");
            outputStream.println("bookValue = \"" + stockList.get(z).getbookValue() + "\"");
            outputStream.println("");
        }

        outputStream.close();
    }

    /**
     * Hashes the entire stockList and puts all indexes into the hashMap Integer
     * array
     *
     *
     */
    public static void hashFunction() {

        int temp = 0;

        for (int i = 0; i < stockList.size(); i++) {
            String[] splitter = stockList.get(i).getName().split(" ");

            for (int a = 0; a < splitter.length; a++) {
                splitter[a] = splitter[a].toLowerCase();
            }

            for (int x = 0; x < splitter.length; x++) {
                ArrayList<Integer> searchIndex = new ArrayList<Integer>();
                for (int j = 0; j < stockList.size(); j++) {
                    if (stockList.get(j).getName().toLowerCase().contains(splitter[x].toLowerCase())) {
                        if (!searchIndex.contains(j)) {
                            searchIndex.add(j);
                        }
                    }
                }
                hashList.put(splitter[x], searchIndex);
            }

        }
    }

    /**
     * The buy function that adds an object of Investment into the arrayList and
     * hashMap!
     *
     * @param buyText : pointer to the textArea to output messages
     * @param userChoice : the type of investment user is currently buying
     * @param strQuantity : the quantity of the investment user is currently
     * buying
     * @param strPrice : the price of the investment user is currently buying
     * @param symbol : the symbol of the investment user currently is buying
     * @param name : the name of the investment user currently is buying
     * @throws PortfolioException custom exception
     */
    public static void Buy(JTextArea buyText, int userChoice, String strQuantity, String strPrice, String symbol, String name) throws PortfolioException {

        boolean trackRepeat = false;

        if (symbol.equals("")) {
            throw new PortfolioException("Symbol cannot be left empty!");
        } else if (name.equals("")) {
            throw new PortfolioException("Name cannot be left empty!");
        } else if (!isInteger(strQuantity)) {
            throw new PortfolioException("Invalid quantity, Must be a whole number!");
        } else if (!isNumber(strPrice)) {
            throw new PortfolioException("Invalid Price, Must be a number!");
        } else if (isInteger(strQuantity) && Integer.parseInt(strQuantity) <= 0) {
            throw new PortfolioException("Invalid quantity, Must be greater than 0!");
        } else if (isNumber(strPrice) && Double.parseDouble(strPrice) <= 0) {
            throw new PortfolioException("Invalid Price, Must be greater than 0!");
        } else {
            double price = Double.parseDouble(strPrice);
            int quantity = Integer.parseInt(strQuantity);
            if (stockList.size() > 0) {
                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol)) {
                        stockList.get(i).addShares(quantity, price);
                        trackRepeat = true;
                        break;
                    }
                    trackRepeat = false;
                }
            } else {
                if (userChoice == 0) {
                    stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
                    trackRepeat = true;
                    buyText.append("Stock was successfully added!\n");
                } else {
                    stockList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
                    trackRepeat = true;
                    buyText.append("Mutual fund was successfully added!\n");
                }

                return;
            }

            if (trackRepeat == false) {
                if (userChoice == 0) {
                    stockList.add(new Stocks(symbol, name, quantity, price, (quantity * price) + 9.99));
                    buyText.append("Stock was successfully added!\n");
                } else {
                    stockList.add(new MutualFunds(symbol, name, quantity, price, (quantity * price)));
                    buyText.append("Mutual fund was successfully added!\n");
                }
            } else {
                buyText.append("Investment already exists!\n");

            }
            hashFunction();
        }
    }

    /**
     * Finds the symbol user inputed, and offers to sell, and has some
     * limitations when selling or when symbol not found
     *
     * @param sellText : pointer to the textArea for messages to be outputed
     * @param strQuantity : quantity of investment the user is going to sell
     * @param strPrice : price of investments the user is going to sell
     * @param symbol : symbol of the investment user is going to sell
     * @throws PortfolioException custom exceptions
     */
    public static void Sell(JTextArea sellText, String strQuantity, String strPrice, String symbol) throws PortfolioException {

        if (symbol.equals("")) {
            throw new PortfolioException("Symbol cannot be left empty!");
        } else if (!isInteger(strQuantity)) {
            throw new PortfolioException("Invalid quantity, Must be a whole number!");
        } else if (!isNumber(strPrice)) {
            throw new PortfolioException("Invalid Price, Must be a number!");
        } else if (isInteger(strQuantity) && Integer.parseInt(strQuantity) <= 0) {
            throw new PortfolioException("Invalid quantity, Must be greater than 0!");
        } else if (isNumber(strPrice) && Double.parseDouble(strPrice) <= 0) {
            throw new PortfolioException("Invalid Price, Must be greater than 0!");
        } else {

            double soldGain = 0;
            double soldPrice = 0;

            int quantity = Integer.parseInt(strQuantity);
            double price = Double.parseDouble(strPrice);

            boolean trackRepeat = false;

            if (stockList.size() > 0) {
                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol)) {
                        //System.out.println("Item has been found in Stock List:");
                        sellText.append(stockList.get(i) + "\n");

                        if (quantity == stockList.get(i).getQuantity()) {
                            //put this into the subclasses, because mutualFund is -45!
                            sellText.append("The item was sold at : " + df2.format((quantity * price) - stockList.get(i).getFee()) + "\n");
                            stockList.remove(i);

                        } else if (quantity < stockList.get(i).getQuantity()) {
                            soldGain -= (stockList.get(i).getbookValue() / stockList.get(i).getQuantity()) * quantity;
                            soldPrice = stockList.get(i).sellShares(quantity, price);
                            soldGain += soldPrice;
                            sellText.append("The item was sold at : " + df2.format(soldPrice) + "\n");
                            sellText.append("The gain from sale is : " + df2.format(soldGain) + "\n");
                            stockList.get(i).addGain(soldGain);

                        } else {
                            sellText.append("You cannot sell more than what you own!\n");

                        }

                        trackRepeat = true;
                        break;
                    }
                }
            } else {
                sellText.append("There is no elements in the list!\n");
                trackRepeat = true;
            }

            if (trackRepeat == false) {
                sellText.append("No Symbol was found, please double check the spelling/name\n");
            }

            hashFunction();
        }

    }

    /**
     * Updates the indexed stock and calculates/updates tradeGains
     *
     * @param updateText : pointer to the textArea to output messages
     * @param index : index of which stock is going to updated
     * @param strPrice : New price of stock
     * @throws PortfolioException custom exceptions
     */
    public static void Update(JTextArea updateText, int index, String strPrice) throws PortfolioException {
        if (!isNumber(strPrice)) {
            throw new PortfolioException("Invalid price, must be a number!");
        } else if (isNumber(strPrice) && Integer.parseInt(strPrice) <= 0) {
            throw new PortfolioException("Invalid price, must be greater than 0!");
        } else {
            int price = Integer.parseInt(strPrice);
            stockList.get(index).updatePrice(price);
            updateText.append("Price has been upadted!\n");
        }
    }

    /**
     *
     * @param index index of the current investment
     * @return returns the symbol of current investment
     */
    public static String getSymbol(int index) {
        return stockList.get(index).getSymbol();
    }

    /**
     *
     * @param index index of current investment
     * @return returns name of current Investment
     */
    public static String getName(int index) {
        return stockList.get(index).getName();
    }

    /**
     *
     * @param index index of current Investment
     * @return returns price of current investment
     */
    public static double getPrice(int index) {
        return stockList.get(index).getPrice();
    }

    /**
     *
     * @return returns size of the stockList
     */
    public static int getSize() {

        return stockList.size();
    }

    /**
     * Searches using 3 different option which can be used interchangebly or
     * together Search by symbol = Matches symbol and returns list Search by
     * Keywords = Overrides symbol and matches based on key words without case
     * being sensitive, and also matches based on price Search by price =
     * depending on the range user enters, it will search based on price ranges
     * between the 2 values
     *
     * @param searchText : pointer to textArea to output all matches found
     * @param strKeyWord : key words for names to search for in the list
     * @param strSymbol : symbol to search for in the list
     * @param strLowPrice : lower limit of price range
     * @param strHighPrice : upper limit of price range
     * @throws PortfolioException
     *
     * Algorithm for using hash maps :
     *
     * Goes through the hashmap list and retrieves the indexes of the key words
     * Checks if any key words intersect with one another A second array is
     * created containing only the intersections The second array is used to
     * grab the indexes and output information based on the users filters!
     *
     */
    public static void Search(JTextArea searchText, String strKeyWord, String strSymbol, String strLowPrice, String strHighPrice) throws PortfolioException {

        if (!isNumber(strLowPrice) && !strLowPrice.equals("")) {
            throw new PortfolioException("Invalid lower price, Must be a number!");
        } else if (!isNumber(strHighPrice) && !strHighPrice.equals("")) {
            throw new PortfolioException("Invalid higher price, Must be a number!");
        } else if (isNumber(strLowPrice) && Integer.parseInt(strLowPrice) < 0) {
            throw new PortfolioException("Invalid lower price, Must be greater than 0!");
        } else if (isNumber(strHighPrice) && Integer.parseInt(strHighPrice) < 0) {
            throw new PortfolioException("Invalid Price, Must be greater than 0!");
        } else {

            String lowPrice = strLowPrice;
            String highPrice = strHighPrice;
            String keyWord = strKeyWord;
            String symbol = strSymbol;

            String priceRange = "";

            if (!lowPrice.equals("") && highPrice.equals("")) {
                priceRange = lowPrice + "-";
            } else if (lowPrice.equals("") && !highPrice.equals("")) {
                priceRange = "-" + highPrice;
            } else if (lowPrice.equals("") && highPrice.equals("")) {
                priceRange = "";
            } else {
                priceRange = lowPrice + "-" + highPrice;
            }
            String delimeters = "[ ]";
            ArrayList<Integer> allIndex = new ArrayList<Integer>();
            ArrayList<Integer> intersectIndex = new ArrayList<Integer>();
            String[] searchWords;

            if (!symbol.isEmpty() && keyWord.isEmpty()) {

                for (int i = 0; i < stockList.size(); i++) {
                    if (stockList.get(i).equals(symbol) && checkPrice(priceRange, i)) {
                        searchText.append(stockList.get(i) + "\n");
                    }
                }

            } else if (symbol.isEmpty() && !keyWord.isEmpty()) {
                searchWords = keyWord.split(delimeters);
                //For Stock List
                for (int i = 0; i < searchWords.length; i++) {
                    if (hashList.containsKey(searchWords[i])) {
                        for (int x = 0; x < hashList.get(searchWords[i]).size(); x++) {

                            if (allIndex.contains(hashList.get(searchWords[i]).get(x))) {
                                intersectIndex.add(hashList.get(searchWords[i]).get(x));
                            } else {
                                allIndex.add(hashList.get(searchWords[i]).get(x));
                            }

                        }
                    }
                }
                if (searchWords.length <= 1) {
                    for (int i = 0; i < allIndex.size(); i++) {
                        if (checkPrice(priceRange, allIndex.get(i))) {
                            searchText.append(stockList.get(allIndex.get(i)) + "\n");
                        }
                    }
                } else {
                    for (int i = 0; i < intersectIndex.size(); i++) {
                        if (checkPrice(priceRange, intersectIndex.get(i))) {
                            searchText.append(stockList.get(intersectIndex.get(i)) + "\n");
                        }
                    }
                }

            } else if (symbol.isEmpty() && keyWord.isEmpty() && priceRange.isEmpty()) {

                for (int i = 0; i < stockList.size(); i++) {
                    searchText.append(stockList.get(i) + "\n");
                }
            } else if (!symbol.isEmpty() && !keyWord.isEmpty()) {
                searchWords = keyWord.split(delimeters);
                for (int i = 0; i < searchWords.length; i++) {
                    if (hashList.containsKey(searchWords[i])) {
                        for (int x = 0; x < hashList.get(searchWords[i]).size(); x++) {
                            if (allIndex.contains(hashList.get(searchWords[i]).get(x))) {
                                intersectIndex.add(hashList.get(searchWords[i]).get(x));
                            } else {
                                allIndex.add(hashList.get(searchWords[i]).get(x));
                            }
                        }
                    }
                }
                if (searchWords.length <= 1) {
                    for (int i = 0; i < allIndex.size(); i++) {
                        if (checkPrice(priceRange, allIndex.get(i))) {
                            if (stockList.get(allIndex.get(i)).equals(symbol)) {
                                searchText.append(stockList.get(allIndex.get(i)) + "\n");
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < intersectIndex.size(); i++) {
                        if (checkPrice(priceRange, intersectIndex.get(i))) {
                            if (stockList.get(intersectIndex.get(i)).equals(symbol)) {
                                searchText.append(stockList.get(intersectIndex.get(i)) + "\n");
                            }
                        }
                    }
                }
            } else if (symbol.isEmpty() && keyWord.isEmpty() && !priceRange.isEmpty()) {
                for (int i = 0; i < stockList.size(); i++) {
                    if (checkPrice(priceRange, i)) {
                        searchText.append(stockList.get(i) + "\n");
                    }
                }
            }
        }
    }

    /**
     * Checks if the string is a number or not, returns true if it is, returns
     * false if it gives an error
     *
     * @param test : the string to be tested whether it is a string or a
     * integer!
     * @return returns whether it is a number or not
     *
     */
    public static boolean isNumber(String test) {
        try {
            double number = Double.parseDouble(test);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param test
     * @return
     */
    public static boolean isInteger(String test) {
        try {
            int number = Integer.parseInt(test);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Checks the format of the price range, ie : -15 means 0 to 15, 15- means
     * 15 and Higher, 15-20 means 15 to 20, etc returns the true if it matches,
     * rerturns false if its not in between price range
     *
     * @param priceRange contains the the string entered by the user for price
     * ranges
     * @param index contains the index of the stock/mutualfund currently being
     * checked for price
     * @return whether it falls in range of the price range
     *
     */
    public static boolean checkPrice(String priceRange, int index) {
        double price = 0;
        String[] priceList;

        if (priceRange.isEmpty()) {
            return true;
        } else if (priceRange.substring(0, 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(1, priceRange.length()));
            //System.out.println("The price range is from 0 to " + price);

            if (stockList.get(index).getPrice() <= price) {
                return true;
            } else {
                return false;
            }
        } else if (priceRange.substring(priceRange.length() - 1).equals("-")) {
            price = Double.parseDouble(priceRange.substring(0, priceRange.length() - 1));
            //System.out.println("The price range is from " + price + " and higher");

            if (stockList.get(index).getPrice() >= price) {
                return true;
            } else {
                return false;
            }
        } else if (isNumber(priceRange)) {
            price = Double.parseDouble(priceRange);
            //System.out.println("Price is exactly " + price);

            if (stockList.get(index).getPrice() == price) {
                return true;
            } else {
                return false;
            }
        } else {
            priceList = priceRange.split("-");
            //System.out.println("Price Range is from " + priceList[0] + " to " + priceList[1]);

            if (stockList.get(index).getPrice() >= Double.parseDouble(priceList[0]) && stockList.get(index).getPrice() <= Double.parseDouble(priceList[1])) {
                return true;
            } else {
                return false;
            }
        }
    }

}
