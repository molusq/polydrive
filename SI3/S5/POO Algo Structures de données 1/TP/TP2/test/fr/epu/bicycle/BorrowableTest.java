package fr.epu.bicycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowableTest {
	
	@Test
	void borrow() {
		BorrowableElectricVehicle vehicle = new EBike();
		assertFalse(vehicle.isBorrowed());
		vehicle.setBorrowed(true);
		assertTrue(vehicle.isBorrowed());
		
		BorrowableElectricVehicle vehicle2 = new EBike();
		assertFalse(vehicle2.isBorrowed());
		try {
			vehicle2.borrow();
		}
		catch(NotBorrowableException e) {
			throw new RuntimeException(e);
		}
		assertTrue(vehicle2.isBorrowed());
		
	}
	
	@Test
	void returnBorrow() {
		BorrowableElectricVehicle vehicle = new EBike();
		assertFalse(vehicle.isBorrowed());
		vehicle.setBorrowed(true);
		assertTrue(vehicle.isBorrowed());
		try {
			vehicle.returnBorrow();
		}
		catch(NotBorrowedException e) {
			throw new RuntimeException(e);
		}
		assertFalse(vehicle.isBorrowed());
		
		BorrowableNonElectricVehicle vehicle3 = new Bike();
		assertFalse(vehicle3.isBorrowed());
		vehicle3.setBorrowed(true);
		assertTrue(vehicle3.isBorrowed());
		try {
			vehicle3.returnBorrow();
		}
		catch(NotBorrowedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	void borrow2() {
		BorrowableNonElectricVehicle vehicle = new Bike();
		assertFalse(vehicle.isBorrowed());
		vehicle.setBorrowed(true);
		assertTrue(vehicle.isBorrowed());
		
		Station station = new Station(new Position(1, 1));
		BorrowableNonElectricVehicle vehicle2 = new Bike(station);
		assertFalse(vehicle2.isBorrowed());
		try {
			vehicle2.borrow();
		}
		catch(NotBorrowableException e) {
			throw new RuntimeException(e);
		}
		assertTrue(vehicle2.isBorrowed());
		
	}
	
	@Test
	void returnBorrow2() {
		BorrowableNonElectricVehicle vehicle = new Bike();
		assertFalse(vehicle.isBorrowed());
		vehicle.setBorrowed(true);
		assertTrue(vehicle.isBorrowed());
		try {
			vehicle.returnBorrow();
		}
		catch(NotBorrowedException e) {
			throw new RuntimeException(e);
		}
		assertFalse(vehicle.isBorrowed());
	}
}