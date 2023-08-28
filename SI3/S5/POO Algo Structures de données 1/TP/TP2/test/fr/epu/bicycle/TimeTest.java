package fr.epu.bicycle;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class TimeTest {
	
	@Test
	void evaluateTime4around() {
		Fleet oneFleet = new Fleet();
		oneFleet.addVehicle(new Bike(new Station(new Position(1, 1))));
		oneFleet.addVehicle(new Bike(new Station(new Position(2, 0))));
		oneFleet.addVehicle(new Bike(new Station(new Position(0, 3))));
		oneFleet.addVehicle(new Bike(new Station(new Position(4, 2))));
		oneFleet.addVehicle(new Bike(new Station(new Position(4, 3))));
		oneFleet.addVehicle(new Bike(new Station(new Position(6, 2))));
		oneFleet.addVehicle(new Bike(new Station(new Position(3, 5))));
		oneFleet.addVehicle(new Bike(new Station(new Position(2, 4))));
		oneFleet.addVehicle(new Bike(new Station(new Position(1, 3))));
		oneFleet.addVehicle(new Bike(new Station(new Position(0, 2))));
		oneFleet.addVehicle(new Bike(new Station(new Position(2, 0))));
		oneFleet.addVehicle(new Bike(new Station(new Position(4, 0))));
		oneFleet.addVehicle(new Bike(new Station(new Position(6, 0))));
		oneFleet.addVehicle(new Bike(new Station(new Position(8, 2))));
		oneFleet.addVehicle(new Bike(new Station(new Position(8, 4))));
		oneFleet.addVehicle(new Bike(new Station(new Position(6, 6))));
		oneFleet.addVehicle(new Bike(new Station(new Position(18, 19))));
		oneFleet.addVehicle(new Bike(new Station(new Position(19, 18))));
		oneFleet.addVehicle(new Bike(new Station(new Position(18, 17))));
		oneFleet.addVehicle(new Bike(new Station(new Position(17, 18))));
		oneFleet.addVehicle(new Bike(new Station(new Position(18, 19))));
		
		
		long totalTime = 0;
		Position currentPosition = new Position(7, 7);
		for(int i = 0; i < 1000; i++) {
			long startTime = System.nanoTime();
			oneFleet.around(currentPosition, 10);
			long endTime = System.nanoTime();
			long durationInNano = (endTime - startTime);  //Total execution time in nano seconds
			totalTime += durationInNano;
		}
		System.out.println("total time in nano : " + totalTime);
		System.out.println("total time in milli : " + TimeUnit.NANOSECONDS.toMillis(totalTime));
	}
}