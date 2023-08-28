package fr.epu.bicycle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BikeTest
{
	@Test
	void testPositionStation()
	{
		Station station = new Station(new Position(1, 1));
		Bike bike = new Bike(station);
		if(bike.getPosition().isPresent()) {
			assertEquals(bike.getPosition().get(), station.getPosition());
		} else {
			fail("Position is not present");
		}
	}
}