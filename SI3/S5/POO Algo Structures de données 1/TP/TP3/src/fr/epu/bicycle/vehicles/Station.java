package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.components.Position;

/**
 * Station where vehicles can be parked
 */
public class Station {
	private Position position;
	
	public Station(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
}
