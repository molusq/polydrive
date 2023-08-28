package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Trackable;
import fr.epu.bicycle.vehicles.components.Position;

import java.util.ArrayList;
import java.util.List;

public class FleetOfTrackable<T extends Trackable> extends Fleet<T> {
	
	public FleetOfTrackable() {
		super();
	}
	
	/**
	 * Check all vehicles in a specific radius around a given position
	 *
	 * @param p      Position to check
	 * @param radius in meters
	 * @return List of vehicles around the position within the radius
	 */
	public List<T> around(Position p, double radius) {
		List<T> vehiclesAround = new ArrayList<>();
		
		for(T vehicle : this) {
			if(vehicle.getPosition().isPresent() && vehicle.getPosition().get().distanceTo(p) <= radius) {
				vehiclesAround.add(vehicle);
			}
		}
		
		return vehiclesAround;
	}
	
	public List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		
		for(T vehicle : this) {
			if(vehicle.getPosition().isPresent()) {
				positions.add(vehicle.getPosition().get());
			}
		}
		
		return positions;
	}
}
