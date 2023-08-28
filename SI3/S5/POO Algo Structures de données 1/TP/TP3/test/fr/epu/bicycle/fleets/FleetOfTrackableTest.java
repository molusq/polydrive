package fr.epu.bicycle.fleets;

import fr.epu.bicycle.vehicles.Trackable;
import fr.epu.bicycle.vehicles.Unicycle;
import fr.epu.bicycle.vehicles.components.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetOfTrackableTest {
	
	FleetOfTrackable<Unicycle> fUC = new FleetOfTrackable<>();
	Unicycle u1 = new Unicycle();
	Unicycle u2 = new Unicycle();
	Unicycle u3 = new Unicycle();
	Unicycle u4 = new Unicycle();
	
	@BeforeEach
	void setUp() {
		fUC = new FleetOfTrackable<>();
		fUC.add(u1);
		u1.getGps().move(10, 10);
		fUC.add(u2);
		u2.getGps().move(20, 15);
		fUC.add(u3);
		u3.getGps().move(13, 11);
		fUC.add(u4);
		u4.getGps().move(9, 9);
	}
	
	@Test
	void fleetOfUnicyleTest() {
		assertEquals(4, fUC.size());
	}
	
	@Test
	void getPositionsTest() {
		List<Position> list = fUC.getPositions();
		assertEquals(4, list.size());
		assertTrue(list.contains(new Position(9, 9)));
	}
	
	@Test
	void closeToTest() {
		Position currentPosition = new Position(0, 0);
		List<Unicycle> list = fUC.around(currentPosition, 15);
		assertTrue(list.contains(u1), getDistanceMessage(u1, currentPosition));
		assertFalse(list.contains(u2), getDistanceMessage(u2, currentPosition));
		assertFalse(list.contains(u3), getDistanceMessage(u3, currentPosition));
		assertTrue(list.contains(u4), getDistanceMessage(u4, currentPosition));
		assertEquals(2, list.size());
	}
	
	
	private String getDistanceMessage(Trackable v, Position currentPosition) {
		return v + " should be in, distance from " + currentPosition + " : " + v.getPosition().get().distanceTo(currentPosition);
	}
}
