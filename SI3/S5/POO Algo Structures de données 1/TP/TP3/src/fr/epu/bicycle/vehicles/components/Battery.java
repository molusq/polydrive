package fr.epu.bicycle.vehicles.components;

import java.util.Objects;

/**
 * Battery of an electric bicycle
 *
 * @author Marc Pinet
 */
public class Battery {
	private final int capacity;
	private int level;
	
	public Battery(int capacity) throws IllegalArgumentException {
		if(capacity <= 0) {
			throw new IllegalArgumentException("Capacity must be positive");
		}
		
		this.capacity = capacity;
		this.level = capacity;
	}
	
	public void ajouterCharge(int charge) {
		level += charge;
		if(level > capacity)
			level = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Battery battery))
			return false;
		return getCapacity() == battery.getCapacity() && getLevel() == battery.getLevel();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getCapacity(), getLevel());
	}
}
