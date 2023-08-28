package products;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Florian Latapie
 */
public class monMain {
    public static void main(String[] args) {
        /*Product[] stdProducts = new Product[3];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" + i));
        StockManager manager = new StockManager();
        // two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        manager.printProductDetails();*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" + i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        System.out.println(stdProducts[3] == manager.findProduct(3));*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        System.out.println(manager.numberInStock(2));*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        System.out.println(manager.numberInStock(17));*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        manager.delivery(5, 7);
        System.out.println(manager.numberInStock(5));*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        //manager.printProductDetails();
        manager.fewer(5);*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        int before = manager.numberOfProducts();
        manager.addProduct(new Product(666, "Awesome thingy"));
        System.out.println(manager.numberOfProducts() == before + 1);*/

        /*Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        int before = manager.numberOfProducts();
        manager.addProduct(new Product(8, "Awesome thingy"));
        System.out.println(manager.numberOfProducts() == before);*/

        Product[] stdProducts = new Product[10];
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
        StockManager manager = new StockManager();
// two different ways of accessing stored products
        Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
        IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
        System.out.println(manager.findProduct("Prod8") == stdProducts[8]);
    }
}
