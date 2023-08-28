package fr.epu.bicycle.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScooterTest {
	@Test
	void testScooter() {
		Scooter scooter = new Scooter();
		assertEquals(0, scooter.getKm());
		assertEquals(100, scooter.getBattery().getLevel());
		assertEquals(100, scooter.getBattery().getCapacity());
		assertEquals(0, scooter.getGps().getPosition().getX());
		assertEquals(0, scooter.getGps().getPosition().getY());
	}
}