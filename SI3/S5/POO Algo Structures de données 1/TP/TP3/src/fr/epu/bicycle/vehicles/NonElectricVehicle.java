package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.components.Position;

import java.util.Objects;
import java.util.Optional;

/**
 * Non-Electric Vehicle, working with a station
 *
 * @author Marc Pinet
 */
public abstract class NonElectricVehicle implements Trackable {
	
	private Station station;
	
	protected NonElectricVehicle() {
		this.station = null;
	}
	
	protected NonElectricVehicle(Station station) {
		this.station = station;
	}
	
	/**
	 * Returns the position of the vehicle, retrieved from the station
	 *
	 * @return the position of the vehicle as Optional type
	 */
	@Override
	public Optional<Position> getPosition() {
		if(station == null)
			return Optional.empty();
		return Optional.ofNullable(station.getPosition());
	}
	
	public Optional<Station> getStation() {
		return Optional.ofNullable(station);
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof NonElectricVehicle that))
			return false;
		return Objects.equals(getStation(), that.getStation());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getStation());
	}
	
}
