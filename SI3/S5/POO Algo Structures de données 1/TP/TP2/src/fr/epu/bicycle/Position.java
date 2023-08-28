package fr.epu.bicycle;

import java.util.Objects;

/**
 * Position of electric bicycle
 *
 * @author Marc Pinet
 */
public class Position {
	private double x;
	private double y;
	private static final double EPSILON = 0.001;
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculate the distance between two positions
	 *
	 * @param p
	 * @return True if the distance is less than EPSILON, false otherwise
	 */
	public boolean isEquivalent(Position p) {
		return Math.abs(x - p.x) < EPSILON && Math.abs(y - p.y) < EPSILON;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Position position))
			return false;
		return Double.compare(position.getX(), getX()) == 0 && Double.compare(position.getY(), getY()) == 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}
	
	/**
	 * Calculate the distance between two positions
	 *
	 * @param p
	 * @return Distance between the two positions
	 */
	public double distanceTo(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow( y - p.y, 2));
	}
}
