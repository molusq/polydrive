package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Bike;
import fr.epu.bicycle.vehicles.Station;
import fr.epu.bicycle.vehicles.components.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FleetOfBikeTest {
	
	@Test
	void stationAround() {
		FleetOfBike<Bike> fB = new FleetOfBike<>();
		Bike b1 = new Bike();
		Bike b2 = new Bike();
		Bike b3 = new Bike();
		Bike b4 = new Bike();
		fB.add(b1);
		b1.setStation(new Station(new Position(10, 10)));
		fB.add(b2);
		b2.setStation(new Station(new Position(20, 15)));
		fB.add(b3);
		b3.setStation(new Station(new Position(13, 11)));
		fB.add(b4);
		b4.setStation(new Station(new Position(9, 9)));
		assertEquals(2, fB.stationAround(new Position(0, 0), 15).size());
	}
}