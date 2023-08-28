package fr.epu.bicycle.vehicles.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BatteryTest {
	
	@Test
	void ajouterCharge() {
		Battery battery = new Battery(100);
		battery.ajouterCharge(50);
		assertEquals(100, battery.getLevel());
	}
	
	@Test
	void getCapacity() {
		Battery battery = new Battery(100);
		assertEquals(100, battery.getCapacity());
	}
	
	@Test
	void getNiveau() {
		Battery battery = new Battery(100);
		assertEquals(100, battery.getLevel());
	}
}