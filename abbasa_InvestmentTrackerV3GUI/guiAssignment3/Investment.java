/*
 * Name : Abir Abbas
 * Date : 2017 - 10 - 16
 * Student ID : 0955448
 * Email : abbasa@mail.uoguelph.ca
 */
package guiAssignment3;

/**
 *
 * @author Abir Abbas
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/*
Investment super class, that has all similar properties of a stock and mutualfund
 */

/**
 *
 * @author Abir Abbas
 */

public abstract class Investment {

    private static DecimalFormat df2 = new DecimalFormat(".##"); //format for 2 decimal places
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue; //a four digit number.
    private double tradeGain = 0;

    /**
     * Initializing stocks calls the set function for stocks
     *
     * @param symbol contains the symbol of the stock/mutualfund
     * @param name contains the name of the stock/mutualfund
     * @param quantity contains the quantity of the stock/mutualfund
     * @param price contains the price of the stock/mutualfund
     * @param bookValue contains the bookValue of the stock/mutualfund
     * 
     *
     */
    public Investment(String symbol, String name, int quantity, double price, double bookValue) throws PortfolioException {
        if (symbol.equals("")) {
            throw new PortfolioException("Symbol cannot be left empty!");
        } else if (name.equals("")) {
            throw new PortfolioException("Name cannot be left empty!");
        } else if (quantity <= 0) {
            throw new PortfolioException("Invalid quantity, Must be gearter than 0!");
        } else if (price <= 0) {
            throw new PortfolioException("Invalid Price, Must be gearter than 0!");
        } else {
            setStocks(symbol, name, quantity, price, bookValue);
        }
    }

    /**
     * Sets the stocks Gets variable from user and inputs it into the stock
     * array element
     *
     * @param symbol, name, quantity, price, bookValue : used to set all the
     * variables in the current instance of the object investment!
     * @param name name that the user wants to set for the stocks
     * @param quantity quantity of the stocks user will set
     * @param price price of the stock user will set
     * @param bookValue bookValue of the stock user will set
     * 
     *
     */
    public void setStocks(String symbol, String name, int quantity, double price, double bookValue) throws PortfolioException {
        if (symbol.equals("")) {
            throw new PortfolioException("Symbol cannot be left empty!");
        } else if (name.equals("")) {
            throw new PortfolioException("Name cannot be left empty!");
        } else if (quantity <= 0) {
            throw new PortfolioException("Invalid quantity, Must be gearter than 0!");
        } else if (price <= 0) {
            throw new PortfolioException("Invalid Price, Must be gearter than 0!");
        } else {
            this.symbol = symbol;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.bookValue = bookValue;
        }
    }

    /**
     * The accessor and mutator functions because all variables are private and
     * cannot be accessed through other means.
     *
     *
     * @return returns the 2 decimal place format
     */
    public DecimalFormat returnFormat() {
        return df2;
    }

    /**
     *
     * @return returns the symbol
     */
    public String getSymbol() {
        String temp = symbol;
        return temp;
    }

    /**
     *
     * @return returns the name
     */
    public String getName() {
        String temp = name;
        return temp;
    }

    /**
     *
     * @return returns the quantity
     */
    public int getQuantity() {
        int temp = quantity;
        return temp;
    }

    /**
     *
     * @return returns the bookValue
     */
    public double getbookValue() {
        double temp = bookValue;
        return bookValue;
    }

    /**
     *
     * @return returns the Price
     */
    public double getPrice() {
        double temp = price;
        return temp;
    }

    /**
     *
     * @return returns the gain for this investment
     */
    public double getGain() {
        double temp = tradeGain;
        return temp;
    }
    //can be both positive or negative, no exceptions required

    /**
     *
     * @param tradeGain the gains from trading
     */
    public void setGain(double tradeGain) {
        this.tradeGain = tradeGain;
    }

    /**
     *
     * @param price the new price
     * @throws PortfolioException custom exception handler
     */
    public void setPrice(double price) throws PortfolioException {
        if (price <= 0) {
            throw new PortfolioException("Invalid price, cannot be less than 0!");
        }
        else {
            this.price = price;
        }
    }

    /**
     *
     * @param quantity quantity of stocks being set
     * @throws PortfolioException custom exception handler
     */
    public void setQuantity(int quantity) throws PortfolioException {
        if (quantity < 0) {
            throw new PortfolioException("Invalid quantity, cannot be less than 0!");
        }
        else {
            this.quantity = quantity;
        }
    }

    /**
     *
     * @param bookValue bookValue of investment
     * @throws PortfolioException custom exception handler
     */
    public void setBookValue(double bookValue) throws PortfolioException{
        if (bookValue < 0) {
            throw new PortfolioException("Book value can never be 0!");
        }
        else {
            this.bookValue = bookValue;
        }
    }

    /**
     * @param test contains the string to test whether it is a number or not
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
     * Converts the arrayList element into a string so it may be outputted by
     * the program in a organized manner
     *
     * @return returns the string format of the investment
     *
     */
    public abstract String toString();

    /**
     * To check if the instance of stock is equal to what the user is trying to
     * match
     *
     * @param symbol and name to compare
     * @param name name to compare with current object
     * @return returns whether a symbol equals or not
     *
     */
    public boolean equals(String symbol, String name) {

        return (this.symbol.toLowerCase().equals(symbol.toLowerCase()) && this.name.toLowerCase().equals(name.toLowerCase()));
    }

    /**
     * @param symbol to check if it equals the current Lists symbol
     * @return overloaded method of previous compare
     *
     */
    public boolean equals(String symbol) {

        return (this.symbol.toLowerCase().equals(symbol.toLowerCase()));
    }

    /**
     * Calculates the current bookValue and simply returns the answer
     *
     * @param quantity and price
     * @param price price of current investment
     * @return quantity * price
     *
     */
    public double calculateBookValue(int quantity, double price) {
        return quantity * price;
    }

    /**
     * @param quantity, price Function used to add shares to current instance of
     * Investment object
     * @param price price of the added Shares
     * 
     *
     */
    public abstract void addShares(int quantity, double price) throws PortfolioException;

    /**
     * @param quantity and price Function used to sell/delete shares in current
     * investment
     * @param price price of the sold shares
     * @return returns payment made for the shares
     * 
     *
     *
     */
    public abstract double sellShares(int quantity, double price) throws PortfolioException;

    /**
     * @param price used to update each price
     * 
     *
     */
    public void updatePrice(double price) throws PortfolioException {
        setGain(((double) getQuantity() * price) - getbookValue());
        setPrice(price);
    }

    /**
     *
     * @return returns the fee for type of investment
     */
    public abstract double getFee();

    /**
     *
     * @return returns type of investment
     */
    public abstract String getType();

    /**
     *
     * @param addGain adds the total gain from trades
     */
    public void addGain(double addGain) {
        tradeGain += addGain;
    }

}
