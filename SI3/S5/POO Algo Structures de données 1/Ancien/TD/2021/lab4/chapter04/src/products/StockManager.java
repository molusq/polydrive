package products;

import java.util.ArrayList;

/**
 * Manage the stock in a business. The stock is described by zero or more
 * Products.
 *
 * @author Florian Latapie
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
     * @param item The item to be added.
     */
    void addProduct(Product item) {
       if (this.findProduct(item.getID()) == null){
           item.increaseQuantity(1);
       }
        stock.add(item);
    }

    /**
     * Receive a delivery of a particular product. Increase the quantity of the
     * product by the given amount.
     *
     * @param id     The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    void delivery(int id, int amount) {
        if (findProduct(id) != null) {
            this.findProduct(id).increaseQuantity(amount);
        } else {
            System.out.println("problème ajout delivery");
        }
    }

    /**
     * Try to find a product in the stock with the given id.
     *
     * @return The identified product, or null if there is none with a matching
     * ID.
     */
    Product findProduct(int id) {
        boolean trouve = false;
        int indice = -1;
        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getID() == id) {
                trouve = true;
                indice = i;
                break;
            }
        }

        if (trouve) {
            return stock.get(indice);
        } else {
            return null;
        }
    }

    Product findProduct(String name ) {
        boolean trouve = false;
        int indice = -1;
        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getName().equals(name)){
                trouve = true;
                indice = i;
                break;
            }
        }

        if (trouve) {
            return stock.get(indice);
        } else {
            return null;
        }
    }

    /**
     * Locate a product with the given ID, and return how many of this item are
     * in stock. If the ID does not match any product, return zero.
     *
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    int numberInStock(int id) {
        if (this.findProduct(id) != null) {
            return this.findProduct(id).getQuantity();
        } else {
            return 0;
        }
    }

    /**
     * Print details of all the products.
     */
    void printProductDetails() {
        for (int i = 0; i < stock.size(); i++) {
            System.out.println(stock.get(i));
        }
    }

    public void fewer(int i) {
        for (int j = 0; j < stock.size(); j++) {
            if (this.numberInStock(stock.get(j).getID()) < i) {
                System.out.println(stock.get(j));
            }
        }
    }

    public int numberOfProducts() {
        int total = 0;
        for (int i = 0; i < stock.size(); i++) {
            total += stock.get(i).getQuantity();
        }
        return total;
    }
}
