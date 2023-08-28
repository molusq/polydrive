package fr.epu.bicycle.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EBikeTest {
	
	@Test
	void testCreateEBike() {
		ElectricVehicle bike = new EBike();
		assertEquals(EBike.INITIAL_DISTANCE, bike.getKm());
	}
	
	@Test
	void testAddKm() {
		ElectricVehicle bike = new EBike();
		int value = 5;
		bike.addKm(value);
		assertEquals(value + EBike.INITIAL_DISTANCE, bike.getKm());
	}
	
	@Test
	void testAddNegativeKm() {
		ElectricVehicle bike = new EBike();
		int value = -5;
		bike.addKm(value);
		assertEquals(EBike.INITIAL_DISTANCE, bike.getKm());
	}
}