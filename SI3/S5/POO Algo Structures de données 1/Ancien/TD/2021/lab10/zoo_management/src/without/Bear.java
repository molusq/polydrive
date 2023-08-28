package without;


/**
 * Represents talking animals.
 *
 * @author (not) Peter Sander
 * @author  Florian Latapie
 */
class Bear implements Animal{

    @Override
    public String talk() {
        return "Bear says: Smarter than the average beer.";
    }
}
