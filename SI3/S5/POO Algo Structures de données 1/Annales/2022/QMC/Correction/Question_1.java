public class Minterm {


    /**
     *
     * @param decimal   the decimal number for which we want to calculate the number of bits necessary to represent it
     * @return          the minimum number of bits needed to encode this decimal in binary.
     */
    public static int numberOfBitsNeeded(int decimal) {
        if (decimal == 0)
            return 1;
        int res=0;
        while(decimal > 0){
            res++;
            decimal = decimal/2;
        }
        return res;
    }

    /*********************************************************
     * Management of the minterms structure
     ******************************************************** */
    private Set<Integer> combinations = new HashSet<>();

    /**
     * returns all the numbers that were used to build this minterm.
     * For example, [0*00] may have been created from 0 and 2 (* = -1)
     * @return all the numbers that were used to build this minterm.
     */
    public Collection<Integer> getCombinations() {
        return combinations;
    }


    /**
     * The maximum size
     */
    private final int binarySize;
    /**
     * @return  the number of bits used to encode the minterm. Be careful, it is not necessarily the sufficient number of bits.
     */
    public int getBinarySize() {
        return binary.length;
    }

    private final int[] binary;

    /**
     * returns the binary representation of the minterm as an array of integers whose elements belong to [-1,1]
     * @return returns the binary representation of the minterm
     */
    public int[] getBinary() {
        return binary;
    }

    private boolean marked = false;

    /**
     * marks the minterm as used to build another minTerm
     */
    public void mark(){
        marked = true;
    }

    /**
     *
     * @return <code>true</code> if the minterm has been used to build another minterm, <code>false</code> otherwise.
     */
    public boolean isMarked(){
        return marked;
    }

    /*********************************************************
     * Management of the minterms contents
     ******************************************************** */
    /**
     *
     * @return return the number of 0 in the minterm
     */
    public int numberOfZero() {
        return numberOf(0);
    }

    /**
     *
     * @return return the number of 1 in the minterm
     */
    public int numberOfOne() {
        return numberOf(1);
    }

    /** ------------------
     *
     * @param i number of i in the minterm.
     * @return the number of i in the minterm
     */
    private int numberOf(int i) {
        int numberOfI = 0;
        for (int j : binary) {
            if (j == i) {
                numberOfI += 1;
            }
        }
        return numberOfI;
    }


    /*********************************************************
     * Equality
     ******************************************************** */

    /**
     * @param o     Object to compare
     * @return true if the representation in base 2 is the same. Ignore the other elements.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minterm minterm = (Minterm) o;
        return //value == minterm.value &&
                binarySize == minterm.binarySize && Arrays.equals(binary, minterm.binary);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(binarySize);
        result = 31 * result + Arrays.hashCode(binary);
        return result;
    }



/* -------------------------------------------------------------------------
        Constructors
 ------------------------------------------------------------------------- */
    /**
     * Construct a minterm corresponding to the decimal passed in parameter
     * and encode it on the given number of bits.
     * The associated combination then contains <code>decimal</code>.
     * @param decimal       the decimal value representing the minterm
     * @param numberOfBits  the number of bits of encoding of the decimal
     */ public Minterm(int decimal, int numberOfBits) {
        binarySize = numberOfBits;
        binary = decimalToBinary(decimal);
        combinations.add(decimal);
    }


    /**
     * Builds a minterm from its representation in binary which can contain -1.
     * This constructor does not update the associated combinations.
     * The size of the binary representation corresponds to the number of parameters (binary.length).
     * @param binary    array in base two
     */
    protected Minterm(int... binary) {
        binarySize = binary.length;
        this.binary = binary;
    }

/* -------------------------------------------------------------------------
        Binary <-> Decimal
 ------------------------------------------------------------------------- */

    /**
     * Calculates the integer value of the binary representation.
     * But in case one of the binary elements is -1, it returns -1.
     * This method is private because it should not be used outside this class.
     * @return the value of the minterm calculated from its binary representation.
     */
    public int toIntValue(){
        int res = 0;
        int pow=1;
        for (int digitValue : this.binary) {
            if (digitValue==1) res += digitValue*pow;
            else {
                if (digitValue==-1) return -1;
            }
            pow = pow*2;
        }
        return res;
    }

    // Function converting decimal to binary

    /**
     * Convertdecimal to binary
     * This method is private because it should not be used outside this class.
     * @param decimal   value to transform
     * @return  returns the binary representation in an array of length fixed at the creation of the minterm.
     */
    private int[] decimalToBinary(int decimal) {
        // Creating and assigning binary array size
        int[] digits = new int[binarySize];
        int id = binarySize-1;
        // Number should be positive
        while (decimal > 0 && id >= 0) {
            digits[id--] = decimal % 2;
            decimal = decimal / 2;
        }
        assert decimal <= 0 : "the size is not large enough";
        //By default the other places are initialized to 0
        return digits;

    }


    /**
     * Compute the string showing the binary form of the minterm.
     * For example, "101" represents the minterm corresponding to 5, while "1-1" represents a minterm resulting, for example from the merge of 5 and 7
     * @return the string
     */
    @Override
    public String toString() {
        //Use a string even if a stream would be better ;
        String s = "";
        for (int i : binary){
            if (i==-1){
                s+= "-";
            }
            else s+= i;
        }
        return s;

    }


   /* -------------------------------------------------------------------------
        Union
 ------------------------------------------------------------------------- */


    /**
     * create a Minterm from the merge of two Minterms when it is posssible otherwise return null
     * Attention two minterms can only be merged if
     *  - they differ by one value at most.
     *  - they are of the same size.
     *  If a merge is possible, the returned minterm
     *  - has the same binary representation as the original minterm, but where at most one slot has been replaced by -1,
     *  - and it has, for the combinations, the merge of the combinations of both minterms <code>this</code> and <code>other</code>)
     *  - and the both mindterms  <code>this</code> and <code>other</code> are marked
     * @param other is another Minterm which we try to unify
     * @return a new Minterm when it is possible to unify, else null * @param other is another Minterm which we try to merge
     * @return a new Minterm when it is possible to merge, else null
     */
    public Minterm merge(Minterm other) {
        if (!checkMintermUnion(other)) return null;
        int[] res = new int[binary.length];
        int differences = 0;
        for (int i=0 ; (i<binarySize && differences<2) ; i++) {
            if (binary[i] == other.binary[i]){
                res[i] = binary[i];
            }
            else {
                res[i] = -1;
                differences++;

            }
        }
        if (differences>1) {
            return null;
        }
        Minterm resultingMinterm = new Minterm(res);
        resultingMinterm.combinations.addAll(this.combinations);
        resultingMinterm.combinations.addAll(other.combinations);
        this.mark();
        other.mark();
        return resultingMinterm;
    }

    private boolean checkMintermUnion(Minterm other) {
        return (this.binarySize == other.binarySize) &&
                (Math.abs(this.numberOfZero() - other.numberOfZero()) <2 );
    }


    /* -----------------------------
    To Know if a minterm was unifiable
     ------------------------------- */


    private void merge_combinations(Minterm m) {
        System.out.println(" merge combinations :" + this.combinations + " - " + m.combinations);
        if (this.equals(m)){
            this.combinations.addAll(m.getCombinations());

        } else {
            //@todo should throw an exception
            return;
        }
    }

    protected void addCombination(Integer... values) {
        combinations.addAll(Arrays.asList(values));
    }
}
