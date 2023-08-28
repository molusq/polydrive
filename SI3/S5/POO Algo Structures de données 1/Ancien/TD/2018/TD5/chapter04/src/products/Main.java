package products;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Main{
	public static void main(String[] args){
	Product[] stdProducts = new Product[10];
IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i] = new Product(i, "Prod" +i));
StockManager manager = new StockManager();
// two different ways of accessing stored products
Arrays.asList(stdProducts).forEach(p -> manager.addProduct(p));
IntStream.range(0, stdProducts.length).forEach(i -> stdProducts[i].increaseQuantity(i + 1));
System.out.println(manager.findProduct("Prod8") == stdProducts[8]);
	}
}