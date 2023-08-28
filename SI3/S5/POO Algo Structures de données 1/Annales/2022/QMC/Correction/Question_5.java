package td7;

import java.util.*;

public class QMC {


    private final int numberOfMinterms;
    private final int maxValue;
    //Combinaisons
    private final int[] initialValues;
    private final List<Minterm> initialMinterms;

    private final int nbBits;

    public int getNbBits() {
        return nbBits;
    }

    /**
     * Initialize the algorithm
     * @param values    decimals
     */
    public QMC(int... values) {
        initialValues = values;
        numberOfMinterms = values.length;
        initialMinterms = new ArrayList<>();

        //To find the largest value
        maxValue = Arrays.stream(values).max().getAsInt();
        nbBits = Minterm.numberOfBitsNeeded(maxValue);
        for (int val : values) {
            initialMinterms.add(new Minterm(val, nbBits));
        }
    }

    /**
     * Calculates and returns the necessary and sufficient minterms.
     */
    public List<Minterm> computePrimeImplicants(){
        List<Minterm> minterms = reduceMinterms(getInitialMinterms());
        minterms = findPrimeImplicants(minterms);
        return minterms;
    }

    /* --- Usefull to test  ------

     */


    @Override
    public String toString() {
        return "QMC{" +
                "numberOfMinterms=" + numberOfMinterms +
                ", maxValue=" + maxValue +
                ", initialValues=" + Arrays.toString(initialValues) +
                ", initialMinterms=" + initialMinterms +
                '}';
    }

    protected int getMaxValue() {
        return maxValue;
    }

    protected int[] getInitialValues() {
        return initialValues;
    }

    protected List<Minterm> getInitialMinterms() {
        return initialMinterms;
    }




    /**
     * Construit PrimeImplicantGrid
     * Extract essential prime implicants
     * Reduce PrimeImplicantGrid
     * extract primeImplicant --- (0,2,3,4,7) ou (0,2,5,6,7)
     * Pbme avec 0,1 2,5,6,7 => petrick's method 00_, _10, 11_
     * @param minterms
     * @return
     */
    protected List<Minterm> findPrimeImplicants(List<Minterm> minterms) {
        PrimeImplicantChart chart = new PrimeImplicantChart(this.getInitialValues(),minterms);

        //Extract primary terms and prime implicant
        List<Minterm> essentialPrimaryTerms = chart.extractEssentialPrimeImplicants();

        List<Minterm> primaryImplicants = chart.extractRemainingImplicants();
        //Pour ceux qui restent

        primaryImplicants.addAll(essentialPrimaryTerms);
        primaryImplicants = new ArrayList<>(
                new HashSet<>(primaryImplicants));
        return primaryImplicants;
    }




    /**
     * QMC : Reduit à la liste des Minterms tant que c'est possible, c'est à dire que
     * l'unification des Categories modifie les minterms
     * @param origin
     * @return la liste des minterms réduites à chaque classe
     */
    protected List<Minterm> reduceMinterms(List<Minterm> origin) {
        CategoryManager manager = new CategoryManager(origin,nbBits);
        List<Minterm> newMinterms = manager.mergeCategories();
        if (!manager.isLastTurn()) {
            newMinterms = reduceMinterms(newMinterms);
        }
        return newMinterms;
    }


}
