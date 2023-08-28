package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Bike;
import fr.epu.bicycle.vehicles.EBike;
import fr.epu.bicycle.vehicles.Station;
import fr.epu.bicycle.vehicles.VehicleType;
import fr.epu.bicycle.vehicles.components.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FleetManagerTest {
	
	@Test
	void getFleets() {
		FleetManager fleetManager = new FleetManager();
		fleetManager.addFleet(new FleetOfEBike<>());
		fleetManager.addFleet(new FleetOfEBike<>());
		fleetManager.addFleet(new FleetOfEBike<>());
		assertEquals(3, fleetManager.getFleets().size());
	}
	
	@Test
	void addFleet() {
		FleetManager fleetManager = new FleetManager();
		fleetManager.addFleet(new FleetOfEBike<>());
		fleetManager.addFleet(new FleetOfEBike<>());
		fleetManager.addFleet(new FleetOfEBike<>());
		assertEquals(3, fleetManager.getFleets().size());
	}
	
	@Test
	void removeFleet() {
		FleetManager fleetManager = new FleetManager();
		FleetOfEBike<EBike> fleet1 = new FleetOfEBike<>();
		FleetOfEBike<EBike> fleet2 = new FleetOfEBike<>();
		FleetOfEBike<EBike> fleet3 = new FleetOfEBike<>();
		fleetManager.addFleet(fleet1);
		fleetManager.addFleet(fleet2);
		fleetManager.addFleet(fleet3);
		assertEquals(3, fleetManager.getFleets().size());
		fleetManager.removeFleet(fleet2);
		assertEquals(2, fleetManager.getFleets().size());
	}
	
	@Test
	void testAddFleetWithVehicleTypeAsParameter() {
		FleetManager fleetManager = new FleetManager();
		fleetManager.addFleet(VehicleType.EBIKE);
		
		assertEquals(1, fleetManager.getFleets().size());
		assertEquals(fleetManager.getFleets().get(0).getClass(), FleetOfEBike.class);
	}
	
	@Test
	void getPositionOfAllManagedVehicles() {
		FleetManager fleetManager = new FleetManager();
		FleetOfEBike<EBike> fleet1 = new FleetOfEBike<>();
		FleetOfBike<Bike> fleet2 = new FleetOfBike<>();
		
		EBike e1 = new EBike();
		EBike e2 = new EBike();
		EBike e3 = new EBike();
		
		Bike b1 = new Bike(new Station(new Position(0, 0)));
		Bike b2 = new Bike(new Station(new Position(0, 0)));
		Bike b3 = new Bike(new Station(new Position(0, 0)));
		
		fleet1.add(e1);
		fleet1.add(e2);
		fleet1.add(e3);
		fleet2.add(b1);
		fleet2.add(b2);
		fleet2.add(b3);
		
		fleetManager.addFleet(fleet1);
		fleetManager.addFleet(fleet2);
		
		assertEquals(6, fleetManager.getPositionOfAllManagedVehicles().size());
	}
	
	@Test
	void getFleetOf() {
		FleetManager fleetManager = new FleetManager();
		FleetOfEBike<EBike> fleet1 = new FleetOfEBike<>();
		FleetOfBike<Bike> fleet2 = new FleetOfBike<>();
		
		EBike e1 = new EBike();
		EBike e2 = new EBike();
		EBike e3 = new EBike();
		
		Bike b1 = new Bike();
		Bike b2 = new Bike();
		Bike b3 = new Bike();
		
		fleet1.add(e1);
		fleet1.add(e2);
		fleet1.add(e3);
		fleet2.add(b1);
		fleet2.add(b2);
		fleet2.add(b3);
		
		fleetManager.addFleet(fleet1);
		fleetManager.addFleet(fleet2);
		
		assertEquals(fleet1, fleetManager.getFleetOf(VehicleType.EBIKE).get());
	}
}