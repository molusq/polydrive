package fr.epu.bicycle.vehicles.components;

import java.util.Objects;

/**
 * GPS of electric bicycle
 *
 * @author Marc Pinet
 */
public class GPS {
	private Position position;
	
	public GPS() {
		this.position = new Position();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof GPS gps))
			return false;
		return Objects.equals(getPosition(), gps.getPosition());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getPosition());
	}
	
	/**
	 * Move the GPS to a new position
	 *
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		this.position = new Position(x, y);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
}
