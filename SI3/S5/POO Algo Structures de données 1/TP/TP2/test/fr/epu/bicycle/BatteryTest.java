package fr.epu.bicycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatteryTest {
	
	@Test
	void ajouterCharge() {
		Battery battery = new Battery(100);
		battery.ajouterCharge(50);
		assertEquals(100, battery.getNiveau());
	}
	
	@Test
	void getCapacite() {
		Battery battery = new Battery(100);
		assertEquals(100, battery.getCapacite());
	}
	
	@Test
	void getNiveau() {
		Battery battery = new Battery(100);
		assertEquals(100, battery.getNiveau());
	}
}