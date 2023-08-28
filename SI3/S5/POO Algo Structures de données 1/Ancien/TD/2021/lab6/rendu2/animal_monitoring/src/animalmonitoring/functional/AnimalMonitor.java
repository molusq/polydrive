package animalmonitoring.functional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (imperative)
 */
class AnimalMonitor {
    // Records of all the sightings of animals.
    private final List<Sighting> sightings = new ArrayList<>();

    /**
     * Add the sightings recorded in the given filename to the current list.
     *
     * @param filename A CSV file of Sighting records.
     */
    void addSightings(String filename) {
        SightingReader sr = new SightingReader();
        sightings.addAll(sr.getSightings(filename));
    }

    List<Sighting> getSightings() {
        return sightings;
    }

    /**
     * Gets the set of all animals spotted.
     *
     * @return Animals spotted.
     */
    Set<String> getAnimals() {
        return sightings.stream().map(Sighting::getAnimal).collect(Collectors.toSet());
    }

    Set<String> getAnimals(int spotter, int period) {
        return sightings.stream().filter(
                sighting -> sighting.getSpotter() == spotter && sighting.getPeriod() == period
        ).map(Sighting::getAnimal).collect(Collectors.toSet());
    }

    /**
     * Print details of all the sightings.
     */
    void printList() {
        sightings.forEach(sighting -> System.out.println(sighting.getDetails()));
    }

    /**
     * Print the details of all the sightings of the given animal.
     *
     * @param animal The type of animal.
     */
    void printSightingsOf(String animal) {
        this.getSightingsOf(animal).forEach(
                sighting -> {
                    System.out.println(sighting.getDetails());
                }
        );
    }

    void printSightingsOf(String animal, int period) {
        sightings.stream().filter(
                sighting -> sighting.getAnimal().equals(animal)
        ).filter(
                sighting -> sighting.getPeriod() == (period)
        ).forEach(
                sighting -> System.out.println(sighting.getDetails())
        );
    }

    void printSightingsOn(int period) {
        sightings.stream().filter(sighting ->
                sighting.getPeriod() == period).forEach(
                sighting -> System.out.println(sighting.getDetails())
        );
    }

    /**
     * Print all the sightings by the given spotter.
     *
     * @param spotter The ID of the spotter.
     */
    void printSightingsBy(int spotter) {
        /*sightings.forEach(sighting -> {
                    if (sighting.getSpotter() == spotter) {
                        System.out.println(sighting.getDetails());
                    }
                }
        );*/

        sightings.stream().filter(
                sighting -> sighting.getSpotter() == spotter
        ).map(Sighting::getDetails).forEach(details -> System.out.println(details));
    }

    /**
     * Print all the sightings by the given spotter.
     *
     * @param spotter The ID of the spotter.
     */
    void printSightingsBy2(int spotter) {
        /*
        sightings.stream().map(Sighting::getDetails).filter(
                sighting -> sighting.getSpotter() == spotter
        ).m.forEach(details -> System.out.println(details));
        */
    }

    /**
     * Print a list of the types of animal considered to be endangered.
     *
     * @param animals         A list of animals names.
     * @param dangerThreshold Counts less-than or equal-to to this level
     *                        are considered to be endangered.
     */
    void printEndangered(List<String> animals, int dangerThreshold) {
        animals.stream().filter(s -> getCount(s) < dangerThreshold).forEach(s -> System.out.println(s + " is endangered."));
    }

    /**
     * Return a count of the number of sightings of the given animal.
     *
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    int getCount(String animal) {
        return sightings.stream().filter(sighting ->
                sighting.getAnimal().equals(animal)
        ).map(Sighting::getCount).reduce(Integer::sum).orElse(-1);

    }

    /**
     * Remove from the sightings list all of those records with
     * a count of zero.
     */
    void removeZeroCounts() {
        sightings.removeIf(sighting -> sighting.getCount() <= 0);
    }

    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     *
     * @param animal The type of animal.
     * @param area   The ID of the area.
     * @return A list of sightings.
     */
    List<Sighting> getSightingsInArea(String animal, int area) {
        return sightings.stream().filter(sighting -> sighting.getArea() == area && sighting.getAnimal().equals(animal)).collect(Collectors.toList());
    }

    /**
     * Return a list of all the sightings of the given animal.
     *
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    List<Sighting> getSightingsOf(String animal) {
        return sightings.stream().filter(sighting -> sighting.getAnimal().equals(animal)).collect(Collectors.toList());
    }

    int getCount(String animal, int spotter, int period) {
        return sightings.stream().filter(sighting ->
                sighting.getAnimal().equals(animal) && sighting.getSpotter() == spotter && sighting.getPeriod() == period
        ).map(Sighting::getCount).reduce(Integer::sum).orElse(-1);
    }

    void removeSpotter(int spotter) {
        sightings.removeIf(sighting -> sighting.getSpotter() == spotter);
    }
}