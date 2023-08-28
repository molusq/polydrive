package animalmonitoring.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @author Florian S
 * @version 2016.02.29 (imperative)
 */
public class AnimalMonitor {
    // Records of all the sightings of animals.
    private List<Sighting> sightings = new ArrayList<>();
    private SightingReader sightR = new SightingReader(); 
    
    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    public void addSightings(String filename) {
        sightings = sightR.getSightings(filename);
    }

    /**
     * Gets the set of all animals spotted.
     * @return Animals spotted.
     */
    public Set<String> getAnimals() {
        Set<String> animals = new HashSet<String>();
        sightings.forEach(sight -> animals.add(sight.getAnimal()));
        return animals;
    }
    
    /**
     * Print details of all the sightings.
     */
    public void printList() {}
    
    /**
     * Print the details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal) {
        sightings.forEach(
            sight ->
                {if(animal.equals(sight.getAnimal()))
                    System.out.println(sight.getDetails());});
    }
        
    
    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter) {}
    
    /**
     * Print a list of the types of animal considered to be endangered.
     * @param animals A list of animals names.
     * @param dangerThreshold Counts less-than or equal-to to this level
     *                        are considered to be endangered.
     */
    public void printEndangered(List<String> animals, int threshold) {
        animals.forEach(
            animal ->
                {if(getCount(animal) < threshold)
                    System.out.println(animal + " is endangered.");});
    }
    
    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal) {
        return sightings.stream()
            .filter(sighting -> animal.equals(sighting.getAnimal()))
            .map(sighting -> sighting.getCount())
            .reduce(0, (total, count) -> total + count);
    }

    public int getCount(String animal, int spotter, int period){
        return sightings.stream()
            .filter(sighting -> animal.equals(sighting.getAnimal()))
            .filter(sighting -> spotter == sighting.getSpotter())
            .filter(sighting -> period == sighting.getPeriod())
            .map(sighting -> sighting.getCount())
            .reduce(0, (total, count) -> total + count);
    }
    
    /**
     * Remove from the sightings list all of those records with
     * a count of zero.
     */
    public void removeZeroCounts() {}
    
    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     * @param animal The type of animal.
     * @param area The ID of the area.
     * @return A list of sightings.
     */
    //public List<Sighting> getSightingsInArea(String animal, int area) {}
    
    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    //public List<Sighting> getSightingsOf(String animal) {}
}
