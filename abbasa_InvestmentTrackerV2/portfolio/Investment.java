/*
 * Name : Abir Abbas
 * Date : 2017 - 10 - 16
 * Student ID : 0955448
 * Email : abbasa@mail.uoguelph.ca
 */
package portfolio;

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



public class Investment
{
    private static DecimalFormat df2 = new DecimalFormat(".##"); //format for 2 decimal places
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue; //a four digit number.
    private double tradeGain = 0;

    /* Initializing stocks
    calls the set function for stocks
    @param symbol contains the symbol of the stock/mutualfund
    @param name contains the name of the stock/mutualfund
    @param quantity contains the quantity of the stock/mutualfund
    @param price contains the price of the stock/mutualfund
    @param bookValue contains the bookValue of the stock/mutualfund
    */
    public Investment(String symbol, String name, int quantity, double price, double bookValue)
    {
        setStocks(symbol, name, quantity, price, bookValue);
    }
    /* Sets the stocks
    Gets variable from user and inputs it into the stock array element
    @param symbol, name, quantity, price, bookValue : used to set all the variables in the current instance of the object investment!
    */
    public void setStocks(String symbol, String name, int quantity, double price, double bookValue)
    {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }
    /* The accessor and mutator functions because all variables are private and cannot be accessed through other means.
    @param none
    */
    public DecimalFormat returnFormat() {
        return df2;
    }
    public String getSymbol( )
    {
        return symbol;
    }

    public String getName( )
    {
        return name;
    }
    
    public int getQuantity( )
    {
        return quantity;
    }
    
    public double getbookValue( )
    {
        return bookValue;
    }

    public double getPrice( )
    {
        return price;
    }
    
    public double getGain ( ) {
        return tradeGain;
    }
    
    public void setGain (double tradeGain) {
        this.tradeGain = tradeGain;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    /*
    @param test contains the string to test whether it is a number or not
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
    /* Converts the arrayList element into a string so it may be outputted by the program in a organized manner
    @param none
    */
   public String toString() {
       
       return ("Symbol : " +symbol + " Name : " + name + " Quantity : " + quantity + " Price : " + df2.format(price) + " Book Value : " + df2.format(bookValue));
   }
   /* To check if the instance of stock is equal to what the user is trying to match
   @param symbol and name to compare
    */
   public boolean equals(String symbol, String name)
   {
       
       return (this.symbol.toLowerCase().equals(symbol.toLowerCase()) && this.name.toLowerCase().equals(name.toLowerCase()));
   }
   
   /*
   @param symbol to check if it equals the current Lists symbol
   */
   public boolean equals(String symbol)
   {
      
       return (this.symbol.toLowerCase().equals(symbol.toLowerCase()));
   }
   /* Calculates the current bookValue and simply returns the answer
   @param quantity and price
   @return quantity * price
    */
   public double calculateBookValue(int quantity, double price) 
   {
       return quantity * price;
   }
   
   /*
   @param quantity, price
   Function used to add shares to current instance of Investment object
   */
   public void addShares(int quantity, double price) {
       
   }
   
   /*
   @param quantity and price
   Function used to sell/delete shares in currentl investment
   
   */
   public double sellShares(int quantity, double price) {
       return 0;
   }
   
   /*
   @param price
   used to update each price
   */
   public void updatePrice (double price) {
       
   }
   
   /*
   @param none
   used to return Fee for the particular object
   */
   public double getFee() {
       
       return 0;
   }
   
   
   /*
   @param none
   used to return type of current object
   */
   public String getType() {
        return "";
    }

    
}



