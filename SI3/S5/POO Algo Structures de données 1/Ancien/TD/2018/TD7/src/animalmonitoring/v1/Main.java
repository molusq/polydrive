package animalmonitoring.v1;

import java.util.Set;
import java.util.ArrayList;

class Main{
	public static void main(String[] args) {
AnimalMonitor monitor = new AnimalMonitor();
String sightingsFile = System.getProperty("java.class.path")
        + "/animalmonitoring/v1/sightings.csv";
monitor.addSightings(sightingsFile);
Set<String> animals = monitor.getAnimals();
monitor.printEndangered(new ArrayList<String>(animals), 25);
}
}