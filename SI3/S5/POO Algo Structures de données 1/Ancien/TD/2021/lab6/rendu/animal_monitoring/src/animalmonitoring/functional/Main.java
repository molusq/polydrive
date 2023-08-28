package animalmonitoring.procedural;

import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AnimalMonitor monitor = new AnimalMonitor();
        String sightingsFile = System.getProperty("user.dir")
                + "/data/sightings.csv";
        monitor.addSightings(sightingsFile);
        Set<String> animals = monitor.getAnimals();

        System.out.println("Exercise 1");
        monitor.printList();
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 2 (Monitoring animal populations (again) question 8)");
        monitor.printEndangered(new ArrayList<String>(animals), 25);
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 6 (Monitoring animal populations (again) question 2)");
        monitor.printSightingsOf("Arceus");
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 7");
        monitor.printSightingsOn(1);
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 8");
        monitor.printSightingsOf("Arceus", 0);
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 9");
        monitor.printSightingsBy(0);
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 10");
        System.out.println("oui, cela pose un problème : le .map modifie les type d'objet en sortie, \nil n'est donc" +
                " plus possible de filtrer sur les memes critères");
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 12 (Monitoring animal populations (again) question 7)");
        System.out.println(monitor.getCount("Greninja"));
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 13 (Monitoring animal populations (again) question 7)");
        System.out.println(monitor.getCount("Mew", 3, 1));
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 14");
        System.out.println(monitor.getAnimals(0,0));
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 15");
        monitor.removeZeroCounts();
        System.out.println("\n--------------------------------\n");

        System.out.println("Exercise 16");
        System.out.println("Avant supression :");
        monitor.printSightingsBy(0);
        monitor.removeSpotter(0);
        System.out.println("Après supression :");
        monitor.printSightingsBy(0);
        System.out.println("\n--------------------------------\n");
    }
}
