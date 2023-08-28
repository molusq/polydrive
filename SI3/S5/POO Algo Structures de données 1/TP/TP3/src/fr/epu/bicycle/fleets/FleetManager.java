package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Trackable;
import fr.epu.bicycle.vehicles.Vehicle;
import fr.epu.bicycle.vehicles.VehicleType;
import fr.epu.bicycle.vehicles.VehicleTypeNotSupportedException;
import fr.epu.bicycle.vehicles.components.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FleetManager {
	private final List<Fleet<? extends Vehicle>> fleets;
	
	public FleetManager(VehicleType... types) throws VehicleTypeNotSupportedException {
		fleets = new ArrayList<>();
		for(VehicleType type : types) {
			switch(type) {
				case BIKE -> fleets.add(new FleetOfBike<>());
				case EBIKE -> fleets.add(new FleetOfEBike<>());
				case TRACKABLE -> fleets.add(new FleetOfTrackable<>());
				default -> throw new VehicleTypeNotSupportedException();
			}
		}
	}
	
	public List<Fleet<? extends Vehicle>> getFleets() {
		return fleets;
	}
	
	public void addFleet(Fleet<? extends Vehicle> f) {
		fleets.add(f);
	}
	
	public void removeFleet(Fleet<? extends Vehicle> f) {
		fleets.remove(f);
	}
	
	public void addFleet(VehicleType t) throws VehicleTypeNotSupportedException {
		switch(t) {
			case BIKE -> fleets.add(new FleetOfBike<>());
			case EBIKE -> fleets.add(new FleetOfEBike<>());
			case TRACKABLE -> fleets.add(new FleetOfTrackable<>());
			default -> throw new VehicleTypeNotSupportedException();
		}
	}
	
	public List<Position> getPositionOfAllManagedVehicles() {
		List<Position> positions = new ArrayList<>();
		
		for(Fleet<? extends Vehicle> f : fleets) {
			for(Vehicle v : f) {
				if(v instanceof Trackable t && t.getPosition().isPresent())
					positions.add(t.getPosition().get());
			}
		}
		
		return positions;
	}
	
	public Optional<Fleet<? extends Vehicle>> getFleetOf(VehicleType t) throws VehicleTypeNotSupportedException {
		for(Fleet<? extends Vehicle> f : fleets) {
			switch(t) {
				case BIKE -> {
					if(f instanceof FleetOfBike)
						return Optional.of(f);
				}
				case EBIKE -> {
					if(f instanceof FleetOfEBike)
						return Optional.of(f);
				}
				case TRACKABLE -> {
					if(f instanceof FleetOfTrackable)
						return Optional.of(f);
				}
				default -> throw new VehicleTypeNotSupportedException();
			}
		}
		return Optional.empty();
	}
}
