package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.EBike;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FleetOfEBikeTest {
	
	@Test
	void eBikesWithMileageOver() {
		FleetOfEBike<EBike> fEB = new FleetOfEBike<>();
		EBike e1 = new EBike();
		EBike e2 = new EBike();
		EBike e3 = new EBike();
		EBike e4 = new EBike();
		fEB.add(e1);
		e1.setKm(10);
		fEB.add(e2);
		e2.setKm(20);
		fEB.add(e3);
		e3.setKm(13);
		fEB.add(e4);
		e4.setKm(9);
		assertEquals(2, fEB.eBikesWithMileageOver(10).size());
	}
}