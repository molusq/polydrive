package td7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CategoryManager {

    MintermCategory[] mindtermCategories;

    /**
     * CategoryManager : compute the categories from a list of minterms according to the number of 11
     * @param mintermList
     * @return
     */
    public CategoryManager(List<Minterm> mintermList, int nbBits) {
        mindtermCategories = new MintermCategory[nbBits + 1];
        for (Minterm m : mintermList) {
            int categoryNumber = m.numberOfOne();
            if (mindtermCategories[categoryNumber] == null) {
                mindtermCategories[categoryNumber] = new MintermCategory();
            }
            mindtermCategories[categoryNumber].add(m);
        }
    }


    //By default it's not the last turn.
    private boolean isLastTurn = false;

    public int potentialNumberOfCategories(){
        return mindtermCategories.length;
    }
    public int numberOfCategories(){
        int i = 0;
        for (MintermCategory mintermCategory : mindtermCategories){
            if (mintermCategory != null)
                i+=1;
        }
        return i;
    }


    /**
     *
     * @param numberOfOne
     * @return the Category Of Minterms containing  numberOfOne
     */
    public MintermCategory getCategory(int numberOfOne){
        if (numberOfOne< mindtermCategories.length)
            return mindtermCategories[numberOfOne];
        return null;
    }


    /**
     *  isLastTurn()
     * @return true is it's the last turn.
     */
    public boolean isLastTurn() {
        return isLastTurn;
    }

    /**
     * Merge the categories two by two if they have only one "one" between them.
     * The minterms are the result of the merging of the categories.
     * Be careful for a category of n "one", if the category of "n+1" has no minterms,
     *    you must recover the minterms of the category of n "one" which were not marked.
     * This is the last round if no terms could be merged.
     * @return the merged terms
     */
    public List<Minterm> mergeCategories() {
        return mergeCategories(mindtermCategories);
    }
    private List<Minterm> mergeCategories(MintermCategory[] categories) {
        isLastTurn = true;
        List<Minterm> res = new ArrayList<>();
        for (int i = 0; i + 1 < categories.length; i++) {
            if (categories[i] != null) {
                if (categories[i + 1] != null) {
                    List<Minterm> toAdd = categories[i].merge(categories[i + 1]);
                    res.addAll(toAdd);
                    if (!(categories[i].containsAll(toAdd))) {
                        isLastTurn = false;
                    }
                } else {
                    //tous ceux qui ne sont pas marqués ont les ajoute
                    isLastTurn = addNotMarkedMinterms(res, categories[i]) && isLastTurn ;
                }
            }
        }
        //for the last mintermCategory
        // check if one minterm is not marked and add it.
        isLastTurn = addNotMarkedMinterms(res, categories[categories.length - 1]) && isLastTurn;

        return new ArrayList<>(new HashSet<>(res));
    }

    /*
      return false when one term at least has been unified
     */
    private boolean addNotMarkedMinterms(List<Minterm> res, MintermCategory category) {
        boolean couldBeLastTurn = true;
        if (category == null)
            return true;
        for (Minterm m : category) {
            if ((!m.isMarked())) {
                res.add(m);
            } else {
                couldBeLastTurn = false;
            }
        }
        return couldBeLastTurn;
    }


    public  void printCategories() {
        int i = 0;
        for (MintermCategory c : mindtermCategories) {
            if (c != null) {
                System.out.println(" category of " + i + " : " + c);
            }
            i++;
        }
    }
}
