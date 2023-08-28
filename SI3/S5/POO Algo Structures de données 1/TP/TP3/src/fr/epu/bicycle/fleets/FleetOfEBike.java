package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.EBike;

import java.util.List;

public class FleetOfEBike<T extends EBike> extends FleetOfTrackable<T> {
	
	public FleetOfEBike() {
		super();
	}
	
	/**
	 * Check all EBike of the fleet with a minimum km
	 *
	 * @param maxKm minimum km
	 * @return List of EBike with a minimum km
	 */
	public List<T> eBikesWithMileageOver(int maxKm) {
		return this.stream().filter(e -> e.getKm() > maxKm).toList();
	}
}
