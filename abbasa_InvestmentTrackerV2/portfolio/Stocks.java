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
public class Stocks extends Investment {
    
    /*
    @param symbol, name, quantity, price, bookValue : are all sent into the super function where they are used as values for the current object
    */
    public Stocks(String symbol, String name, int quantity, double price, double bookValue) {
        super(symbol, name, quantity, price, bookValue);
    }
    
    /*
    @param quantity and price
    Sells the shares based on user input and user price
    @returns the payment made
    Overridden function
    */
    public double sellShares(int quantity, double price) {
       
       double payment = ((quantity * price) - 9.99);
       setBookValue(getbookValue() * (double)((double)(getQuantity() - quantity) / (double)getQuantity()));
       setQuantity(getQuantity() -quantity);
       return payment;
       
    }
   
    /*
    @param quantity and price
    add shares based on user input at user price
    Overriden function
    */
    public void addShares(int quantity, double price) {
        setQuantity(getQuantity() + quantity);
        setBookValue(getbookValue() + quantity * price + 9.99);
        setPrice(price);
    }
    
    /*
    @param price
    simply updates price of current instance of object
    overriden function
    */
    public void updatePrice(double price) {
        setGain((double)getQuantity() * price - getbookValue());
        setBookValue(price * getQuantity() + 9.99);
        setPrice(price);
    }
    
    /*
    @param none
    simply returns the fee of the given object
    overriden function
    */
    public double getFee() {
       
       return 9.99;
    }
    
    /*
    @param none
    simply returns the type of the given object
    overriden function
    */
    public String getType() {
        return "stock";
    }
    
}
