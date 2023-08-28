public class MintermCategory extends ArrayList<Minterm> {

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for(Minterm m : this){
            bld.append(" ").append(m.toString());
        }
        return bld.toString();
    }


    /**
     * It computes the list of minterms m, such that :
     * - either m results from  merging a minterm from the list "this" with a minterm from the other list ;
     * - either m belongs to the list this and could not be unified with a minterm of the other list.
     * @param otherCategory
     * @return  the list of merged minterms
     */
    public List<Minterm> merge(MintermCategory otherCategory){
        List<Minterm> result = new ArrayList<>();
        for (Minterm m : this){
            for (Minterm m2compare : otherCategory){
                Minterm unification = m.merge(m2compare);
                if (unification != null){
                    result.add(unification);
                    m.mark();
                    m2compare.mark();
                }

            }
            if (! m.isMarked()){
                result.add(m);
            }
        }
        return result;
    }

}
