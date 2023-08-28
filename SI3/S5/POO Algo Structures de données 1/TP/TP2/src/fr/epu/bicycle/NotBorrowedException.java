package fr.epu.bicycle;

public class NotBorrowedException extends Exception {
	public NotBorrowedException(String message) {
		super(message);
	}
}
