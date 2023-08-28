package fr.epu.bicycle.vehicles;

import fr.epu.bicycle.vehicles.components.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BikeTest {
	@Test
	void testPositionStation() {
		Station station = new Station(new Position(1, 1));
		Bike bike = new Bike(station);
		if(bike.getPosition().isPresent()) {
			assertEquals(bike.getPosition().get(), station.getPosition());
		}
		else {
			fail("Position is not present");
		}
	}
}