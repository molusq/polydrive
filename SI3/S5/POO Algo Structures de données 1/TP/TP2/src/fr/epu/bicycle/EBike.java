package fr.epu.bicycle;

/**
 * Electric Bike
 *
 * @author Marc Pinet
 */
public class EBike extends BorrowableElectricVehicle {
	protected static final int INITIAL_DISTANCE = 1;
	
	public EBike() {
		super();
		super.km = INITIAL_DISTANCE;
	}
	
}