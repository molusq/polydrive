package fr.epu.bicycle;

/**
 * Station where vehicles can be parked
 */
public class Station {
	private int id;
	private String name;
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
