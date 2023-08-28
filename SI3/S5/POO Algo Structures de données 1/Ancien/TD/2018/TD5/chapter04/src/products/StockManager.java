package products;

import java.util.ArrayList;

/**
 * Manage the stock in a business. The stock is described by zero or more
 * Products.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
class StockManager {
    // A list of the products.
    private final ArrayList<Product> stock;

    /**
     * Initialise the stock manager.
     */
     StockManager() {
        stock = new ArrayList<>();
    }

    /**
     * Add a product to the list.
     * 
     * @param item
     *            The item to be added.
     */
     void addProduct(Product item) {
        if(findProduct(item.getID()) == null){
            stock.add(item);
        } else return;
        
    }

    int numberOfProducts(){
        int number = 0;
        for(Product product : stock){
            number++;
        }
        return number;
    }

    /**
     * Receive a delivery of a particular product. Increase the quantity of the
     * product by the given amount.
     * 
     * @param id
     *            The ID of the product.
     * @param amount
     *            The amount to increase the quantity by.
     */
     void delivery(int id, int amount) {
        if(findProduct(id) != null)
                findProduct(id).increaseQuantity(amount);
    
    }

    /**
     * Try to find a product in the stock with the given id.
     * 
     * @return The identified product, or null if there is none with a matching
     *         ID.
     */
     Product findProduct(int id) {
        Boolean found = false;
        int i = 0;
        while(found == false && i < stock.size()){
            if(stock.get(i).getID() == id) return stock.get(i);
            i++;
        }
        return null;
    }

    /**
     * Locate a product with the given ID, and return how many of this item are
     * in stock. If the ID does not match any product, return zero.
     * 
     * @param id
     *            The ID of the product.
     * @return The quantity of the given product in stock.
     */
     int numberInStock(int id) {
        if(findProduct(id) != null)
            return findProduct(id).getQuantity();
        return 0;
    }

    /**
     * Print details of all the products.
     */
     void printProductDetails() {
        for(Product product : stock){
            System.out.println(product.toString());
        }
    }

    void fewer(int numbers){
        for(Product product : stock){
            if(product.getQuantity() < numbers){
                System.out.println(product.toString());
            }
        }
    }

    Product findProduct(String name){
        Boolean found = false;
        int i = 0;
        while(found == false && i < stock.size()){
            if(stock.get(i).getName().equals(name)) return stock.get(i);
            i++;
        }
        return null;
    }
}
