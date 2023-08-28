public class ItemDictionnary<T extends Identifiable & Matchable > extends HashMap<String, List<T>> {


    public void addItem(T item) {
        if (! (this.containsKey(item.getId())) ) {
            this.put(item.getId(), new ArrayList<>());
        }
        this.get(item.getId()).add(item) ;

    }


    public boolean removeItem(T element){
        if (element == null)
            return false;
        String index = element.getId();
        if (! (this.containsKey(index)) ) {
            return false;
        }
         List<T> toKeep = new ArrayList<>();

        boolean removed = false;
        for (T item : this.get(index)) {
            if (item == element) {
                removed = true;
            } else {
                toKeep.add(item);
            }
        }
        if (removed){
            if (toKeep.isEmpty()) {
                this.remove(index);
            }
            else {
                this.put(index, toKeep);
            }
        }
        return removed;
    }


    public List<T> findItems(T item){
        if (item == null)
            return null;
        return this.get(item.getId());
    }


    public List<T> findMatchableItems(String query){
        ArrayList<T> searchResults = new ArrayList<>();
        for (Collection<T> items : values()) {
            for (T item : items){
                if (item.match(query)) {
                    searchResults.add(item);
                }
            }
        }
        return searchResults;
    }

}
