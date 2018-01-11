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

/*
Same comments as Stocks.java, look at that file!
*/

public class MutualFunds
{
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue; //a four digit number.
    private double tradeGain = 0;


    public MutualFunds(String symbol, String name, int quantity, double price, double bookValue)
    {
        setStocks(symbol, name, quantity, price, bookValue);
    }

    public void setStocks(String symbol, String name, int quantity, double price, double bookValue)
    {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }
    //Accessor and Mutator Functions
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
    //Print Function
    public String toString() {
       
       return ("Symbol : " +symbol + " Name : " + name + " Quantity : " + quantity + " Price : " + price + " Book Value : " + bookValue);
    }
   
    public boolean equals(String symbol, String name)
    {
       
        return (this.symbol.toLowerCase().equals(symbol.toLowerCase()) && this.name.toLowerCase().equals(name.toLowerCase()));
    }
   
    public boolean equals(String symbol)
    {
      
       return (this.symbol.toLowerCase().equals(symbol.toLowerCase()));
    }
   
    public double calculateBookValue(int quantity, double price) 
    {
        return quantity * price;
    }
   
    public double sellShares(int quantity, double price) {
       
       double payment = ((quantity * price) - 45.00);
       bookValue = bookValue * (double)( (double)(this.quantity - quantity)  / (double)this.quantity );
       this.quantity -= quantity;
       return payment;
       
    }
    
    public void addShares(int quantity, double price) {
        this.quantity = this.quantity + quantity;
        this.bookValue += quantity * price;
        this.price = price;
    }
    
    public void updatePrice(double price) {
        tradeGain = ((double)this.quantity * price) - bookValue - 45.00;
        this.price = price;
    }

    
}   



