package fr.epu.bicycle;

import java.util.Objects;
import java.util.Optional;

/**
 * Electric Vehicle, working with a GPS and a Battery
 *
 * @author Marc Pinet
 */
public abstract class ElectricVehicle implements Vehicle {
	protected static final int INITIAL_CHARGE = 100;
	protected double km;
	protected GPS gps;
	protected Battery battery;
	
	protected ElectricVehicle() {
		super();
		this.gps = new GPS();
		this.battery = new Battery(INITIAL_CHARGE);
		this.km = 0;
	}
	
	/**
	 * Modifies the number of km traveled by adding <code>nbKmToAdd</code> km.
	 *
	 * @param nbKmToAdd the number of km to add
	 */
	public void addKm(double nbKmToAdd) {
		if(nbKmToAdd > 0) {
			this.km += nbKmToAdd;
		}
	}
	
	/**
	 * Returns the position of the vehicle, retrieved from the GPS
	 *
	 * @return the position of the vehicle as Optional type
	 */
	@Override
	public Optional<Position> getPosition() {
		return Optional.ofNullable(gps.getPosition());
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof ElectricVehicle that))
			return false;
		return Double.compare(that.getKm(), getKm()) == 0 && Objects.equals(getGps(), that.getGps()) && Objects.equals(getBattery(), that.getBattery());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getKm(), getGps(), getBattery());
	}
	
	public double getKm() {
		return this.km;
	}
	
	public void setKm(double km) {
		this.km = km;
	}
	
	public GPS getGps() {
		return this.gps;
	}
	
	public void setGps(GPS gps) {
		this.gps = gps;
	}
	
	public Battery getBattery() {
		return this.battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
}
