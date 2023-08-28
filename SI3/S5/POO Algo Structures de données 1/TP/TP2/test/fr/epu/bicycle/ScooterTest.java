package fr.epu.bicycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScooterTest
{
	@Test
	void testScooter()
	{
		Scooter scooter = new Scooter();
		assertEquals(0, scooter.getKm());
		assertEquals(100, scooter.getBattery().getNiveau());
		assertEquals(100, scooter.getBattery().getCapacite());
		assertEquals(0, scooter.getGps().getPosition().getX());
		assertEquals(0, scooter.getGps().getPosition().getY());
	}
}