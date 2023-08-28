package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.exceptions.NotBorrowableException;
import fr.epu.bicycle.vehicles.exceptions.NotBorrowedException;

/**
 * Borrowable interface, so a vehicle can be borrowed and then returned
 *
 * @author Marc Pinet
 */
public interface Borrowable {
	void borrow() throws NotBorrowableException;
	
	void returnBorrow() throws NotBorrowedException;
	
	boolean isBorrowed();
	
	void setBorrowed(boolean borrowed);
}
