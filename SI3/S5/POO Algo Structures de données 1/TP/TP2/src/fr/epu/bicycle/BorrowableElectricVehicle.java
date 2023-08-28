package fr.epu.bicycle;

import java.util.Objects;

public abstract class BorrowableElectricVehicle extends ElectricVehicle implements Borrowable {
	private boolean borrowed;
	
	public BorrowableElectricVehicle() {
		super();
		this.borrowed = false;
	}
	
	/**
	 * Borrow the vehicle
	 * @throws NotBorrowableException if the vehicle is not borrowable
	 */
	@Override
	public void borrow() throws NotBorrowableException {
		if(!this.isBorrowed() && ((double)this.battery.getNiveau() / (double)this.battery.getCapacite()) * 100 > 0.2) {
			this.setBorrowed(true);
		}
		else {
			throw new NotBorrowableException("Not enough battery to borrow this vehicle or the vehicle is already borrowed");
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof BorrowableElectricVehicle that))
			return false;
		return isBorrowed() == that.isBorrowed();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isBorrowed());
	}
	
	/**
	 * Return the vehicle
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
	
	@Override
	public boolean isBorrowed() {
		return borrowed;
	}
	
	@Override
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
}
