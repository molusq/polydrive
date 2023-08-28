package fr.epu.bicycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Fleet of trackable vehicles
 *
 * @author Marc Pinet
 */
public class Fleet extends ArrayList<Trackable>{
	
	public Fleet() {
		super();
	}
	
	/**
	 * Check all vehicles in a specific radius around a given position
	 *
	 * @param p Position to check
	 * @param radius in meters
	 * @return List of vehicles around the position within the radius
	 */
	public List<Trackable> around(Position p, double radius) {
		List<Trackable> vehiclesAround = new ArrayList<>();
		
		for(Trackable vehicle : this) {
			if(vehicle.getPosition().isPresent() && vehicle.getPosition().get().distanceTo(p) <= radius) {
				vehiclesAround.add(vehicle);
			}
		}
		
		return vehiclesAround;
	}
	
	public int getNbVehicles() {
		return super.size();
	}
	
	/**
	 * Add a vehicle to the fleet
	 *
	 * @param vehicle the vehicle to add
	 */
	public void addVehicle(Trackable vehicle) {
		super.add(vehicle);
	}
	
	/**
	 * Remove a vehicle from the fleet
	 *
	 * @param vehicle the vehicle to remove
	 */
	public void removeVehicle(Trackable vehicle) {
		super.remove(vehicle);
	}
	
	public List<Trackable> getVehicles() {
		return this;
	}
	
	public void setVehicles(List<Trackable> vehicles) {
		this.clear();
		this.addAll(vehicles);
	}
}
