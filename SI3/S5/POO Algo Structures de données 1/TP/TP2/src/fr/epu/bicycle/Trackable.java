package fr.epu.bicycle;

import java.util.Optional;

/**
 * Vehicles that can be tracked with their position by the system
 */
public interface Trackable {
	public Optional<Position> getPosition();
}
