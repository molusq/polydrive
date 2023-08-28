package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.exceptions.NotBorrowableException;
import fr.epu.bicycle.vehicles.exceptions.NotBorrowedException;

import java.util.Objects;

public abstract class BorrowableNonElectricVehicle extends NonElectricVehicle implements Borrowable {
	private boolean borrowed;
	
	protected BorrowableNonElectricVehicle() {
		super();
		this.borrowed = false;
	}
	
	protected BorrowableNonElectricVehicle(Station s) {
		super(s);
		this.borrowed = false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof BorrowableNonElectricVehicle that))
			return false;
		if(!super.equals(o))
			return false;
		return isBorrowed() == that.isBorrowed();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), isBorrowed());
	}
	
	/**
	 * Borrow the vehicle
	 *
	 * @throws NotBorrowableException if the vehicle is already borrowed
	 */
	@Override
	public void borrow() throws NotBorrowableException {
		if(super.getStation().isPresent() && !this.isBorrowed()) {
			this.setBorrowed(true);
		}
		else {
			throw new NotBorrowableException("The vehicle is not in a station or is already borrowed");
		}
	}
	
	/**
	 * Return the vehicle
	 *
	 * @throws NotBorrowedException if the vehicle is not borrowed
	 */
	@Override
	public void returnBorrow() throws NotBorrowedException {
		if(this.isBorrowed()) {
			this.setBorrowed(false);
		}
		else {
			throw new NotBorrowedException("The vehicle is not borrowed");
		}
	}
	
	public boolean isBorrowed() {
		return borrowed;
	}
	
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
}
