package fr.epu.bicycle;

/**
 * Non-Electric Bike
 *
 * @author Marc Pinet
 */
public class Bike extends BorrowableNonElectricVehicle {
	public Bike() {
		super();
	}
	
	public Bike(Station station) {
		super(station);
	}
}
