package fr.epu.bicycle;

/**
 * Borrowable interface, so a vehicle can be borrowed and then returned
 *
 * @author Marc Pinet
 */
public interface Borrowable {
	public void borrow() throws NotBorrowableException;
	
	public void returnBorrow() throws NotBorrowedException;
	
	public boolean isBorrowed();
	
	public void setBorrowed(boolean borrowed);
}
