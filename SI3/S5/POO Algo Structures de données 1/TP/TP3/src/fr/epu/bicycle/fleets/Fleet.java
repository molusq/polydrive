package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * Fleet of T vehicles
 *
 * @author Marc Pinet
 */
public class Fleet<T extends Vehicle> extends ArrayList<T> {
	
	public Fleet() {
		super();
	}
}
