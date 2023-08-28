package fr.epu.bicycle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest
{
	
	@Test
	void getBikesAroundPosition()
	{
		Fleet fleet = new Fleet();
		EBike bike1 = new EBike();
		EBike bike2 = new EBike();
		
		fleet.addVehicle(bike1);
		fleet.addVehicle(bike2);
		
		Position p = new Position(2, 5);
		bike1.getGps().move(1, 1);
		bike2.getGps().move(3, 3);
		
		assertEquals(1, fleet.around(p, 3).size());
	}
}