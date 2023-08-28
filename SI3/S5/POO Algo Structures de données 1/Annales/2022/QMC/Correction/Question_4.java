import java.util.ArrayList;
import java.util.List;

public class PrimeImplicantChart {

    private int[][] grid;
    private List<Minterm> mintermList;
    private List<Minterm> essentialPrimeImplicants;
    private List<Minterm> remainingPrimeImplicants;

    /**
     * Initializes the grid with the original minterms and values.
     * @param values        Initial decimal values (they are also included in the combinations of minterms).
     * @param mintermList   The list of minterms reduced by merging the categories
     */
    public PrimeImplicantChart(int[] values, List<Minterm> mintermList) {
        // Assign mintermList to the field of the same name
        this.mintermList = mintermList;
        int numberOfRows = mintermList.size();
        int numberOfColumns = values.length;
        grid = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (mintermList.get(i).getCombinations().contains(values[j])) {
                    grid[i][j] = 1;
                }
            }
        }
        essentialPrimeImplicants = new ArrayList<>();
        remainingPrimeImplicants = new ArrayList<>();
    }

    /**
     * Extracts only the essential minterms; they correspond to the minterms that are the only ones to represent one of the initial values.
     * @return essential minterm list
     */
    public List<Minterm> extractEssentialPrimeImplicants() {
        for (int j = 0; j < grid[0].length; j++) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    count++;
                    index = i;
                }
            }
            if (count == 1) {
                essentialPrimeImplicants.add(mintermList.get(index));
                for (int k = 0; k < grid.length; k++) {
                    grid[k][j] = 0;
                }
            }
        }
        return essentialPrimeImplicants;
    }

    /**
     * After removing the initial values covered by the essential minterms,
     * choose a minterm for each remaining value not covered by an essential minterm.
     */
    public List<Minterm> extractRemainingImplicants() {
        // Initialize a list of remaining initial values
        List<Integer> remainingValues = new ArrayList<>();
        for (int i = 0; i < grid[0].length; i++) {
            remainingValues.add(i);
        }
        // Remove the initial values covered by essential minterms
        for (int i = 0; i < grid.length; i++) {
            if (essentialPrimeImplicants.contains(mintermList.get(i))) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        remainingValues.remove((Integer)j);
                    }
                }
            }
        }
        // Add the minterms that cover the remaining initial values to the list of remaining minterms
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && remainingValues.contains(j)) {
                    remainingPrimeImplicants.add(mintermList.get(i));
                    remainingValues.remove((Integer)j);
                    break;
                }
            }
        }
        return remainingPrimeImplicants;
    }
}