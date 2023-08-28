package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Bike;
import fr.epu.bicycle.vehicles.Station;
import fr.epu.bicycle.vehicles.components.Position;

import java.util.HashSet;
import java.util.Set;

public class FleetOfBike<T extends Bike> extends FleetOfTrackable<T> {
	
	public FleetOfBike() {
		super();
	}
	
	public Set<Station> stationAround(Position currentPosition, int maxKm) {
		Set<Station> stations = new HashSet<>();
		for(T bike : this) {
			if(bike.getPosition().isPresent() && bike.getPosition().get().distanceTo(currentPosition) <= maxKm) {
				stations.add(bike.getStation().get());
			}
		}
		return stations;
	}
}
