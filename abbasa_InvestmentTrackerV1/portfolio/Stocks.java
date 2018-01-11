/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

/**
 *
 * @author Abir Abbas
 */
import java.util.Scanner;

public class Stocks
{
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue; //a four digit number.
    private double tradeGain = 0;

    /* Initializing stocks
    calls the set function for stocks
    */
    public Stocks(String symbol, String name, int quantity, double price, double bookValue)
    {
        setStocks(symbol, name, quantity, price, bookValue);
    }
    /* Sets the stocks
    Gets variable from user and inputs it into the stock array element
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
    */
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
    */
   public String toString() {
       
       return ("Symbol : " +symbol + " Name : " + name + " Quantity : " + quantity + " Price : " + price + " Book Value : " + bookValue);
   }
   /* To check if the instance of stock is equal to what the user is trying to match
    */
   public boolean equals(String symbol, String name)
   {
       
       return (this.symbol.toLowerCase().equals(symbol.toLowerCase()) && this.name.toLowerCase().equals(name.toLowerCase()));
   }
   
   public boolean equals(String symbol)
   {
      
       return (this.symbol.toLowerCase().equals(symbol.toLowerCase()));
   }
   /* Calculates the current bookValue and simply returns the answer
    */
   public double calculateBookValue(int quantity, double price) 
   {
       return quantity * price;
   }
   
   /* Adjusts the bookValue, and quantity based on the amount the user sold
   
    */
   public double sellShares(int quantity, double price) {
       
       double payment = ((quantity * price) - 9.99);
       bookValue = bookValue * (double)( (double)(this.quantity - quantity) / (double)this.quantity );
       this.quantity -= quantity;
       return payment;
       
    }
   
   /* When user buys more of the same shares, it increments bookValue and quantity at same price
    */
    public void addShares(int quantity, double price) {
        this.quantity = this.quantity + quantity;
        this.bookValue += quantity * price + 9.99;
        this.price = price;
    }
    
    /* Updates price when user enters new prices, also adjusts Gains from trade
    */
    public void updatePrice(double price) {
        tradeGain = ((double)this.quantity * price) - bookValue - 9.99;
        this.price = price;
    }

    
}



