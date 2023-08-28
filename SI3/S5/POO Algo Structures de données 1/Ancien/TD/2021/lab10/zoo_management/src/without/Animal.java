package without;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;


/**
 * Represents talking animals.
 * @author (not) Peter Sander
 * @author Florian Latapie
 */
interface Animal {
    /**
     * An animal talks.
     * @param animal The species doing the talking.
     * @return Their favourite saying.
     */
    String talk() ;
}
