package webloganalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * A class to read information from a file of web server accesses.
 * Currently, the log file is assumed to contain simply
 * date and time information in the format:
 *
 *    year month day hour minute
 * Log entries are sorted into ascending order of date.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
class LogfileReader implements Iterator<LogEntry> {
    private static final String DATA_FILE = "weblog.txt";
    // The data format in the log file.
    private String format;
    // Where the file's contents are stored in the form
    // of LogEntry objects.
    private final ArrayList<LogEntry> entries;
    // An iterator over entries.
    private Iterator<LogEntry> dataIterator;
    
    /**
     * Create a LogfileReader to supply data from a default file.
     */
    LogfileReader() {
        this(DATA_FILE);
    }
    
    /**
     * Create a LogfileReader that will supply data
     * from a particular log file. 
     * @param filename The file of log data.
     */
    LogfileReader(String filename) {
        // Determines absolute path for the log file
        // String filepath = System.getProperty("user.dir")
        //         + "/etc/" + filename;
        String filepath = System.getProperty("user.dir")
                + "/" + filename;
        // The format for the data.
        format = "Year Month(1-12) Day Hour Minute";       
        // Where to store the data.
        entries = new ArrayList<>();
        
        // Attempt to read the complete set of data from file.
        boolean dataRead;
        try {
            // Locate the file with respect to the current environment.
            // System.out.println("Trying to read " + filepath);
            Scanner logfile = new Scanner(new File(filepath));
            // Read the data lines until the end of file.
            while (logfile.hasNextLine()) {
                String logline = logfile.nextLine();
                // Break up the line and add it to the list of entries.
                LogEntry entry = new LogEntry(logline);
                entries.add(entry);
            }
            logfile.close();
            dataRead = true;
        }
        // catch (FileNotFoundException | URISyntaxException e) {
        catch (FileNotFoundException e) {
            System.out.println("Problem encountered: " + e);
            dataRead = false;
        }
        // If we couldn't read the log file, use simulated data.
        if (!dataRead) {
            System.out.println("Failed to read the data file: " + filepath);
            System.out.println("Using simulated data instead.");
            createSimulatedData(entries);
        }
        // Sort the entries into ascending order.
        Collections.sort(entries);
        reset();
    }
    
    /**
     * Does the reader have more data to supply?
     * @return true if there is more data available,
     *         false otherwise.
     */
    public boolean hasNext() {
        return dataIterator.hasNext();
    }
    
    /**
     * Analyze the next line from the log file and
     * make it available via a LogEntry object.
     * 
     * @return A LogEntry containing the data from the
     *         next log line.
     */
    public LogEntry next() {
        return dataIterator.next();
    }
    
    /**
     * Remove an entry.
     * This operation is not permitted.
     */
    public void remove() {
        System.err.println("It is not permitted to remove entries.");
    }
    
    /**
     * @return A string explaining the format of the data
     *         in the log file.
     */
    private String getFormat() {
        return format;
    }
    
    /**
     * Set up a fresh iterator to provide access to the data.
     * This allows a single file of data to be processed
     * more than once.
     */
    private void reset() {
        dataIterator = entries.iterator();
    }

    /**
     * Print the data.
     */    
    void printData() {
        for (LogEntry entry : entries) {
            System.out.println(entry);
        }
    }

    /**
     * Provide a sample of simulated data.
     * NB: To simplify the creation of this data, no
     * days after the 28th of a month are ever generated.
     * @param data Where to store the simulated LogEntry objects.
     */
    private void createSimulatedData(ArrayList<LogEntry> data) {
        LogfileCreator creator = new LogfileCreator();
        // How many simulated entries we want.
        int numEntries = 100;
        for (int i = 0; i < numEntries; i++) {
            data.add(creator.createEntry());
        }
    }
}
