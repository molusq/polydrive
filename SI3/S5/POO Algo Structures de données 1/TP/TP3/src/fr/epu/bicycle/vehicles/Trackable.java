package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.components.Position;

import java.util.Optional;

/**
 * Vehicles that can be tracked with their position by the system
 */
public interface Trackable extends Vehicle {
	Optional<Position> getPosition();
}
