
class InsertionSortTest extends AbstractSortTest{

    @Override
    protected <T extends Comparable> void sort(T[] array) {
        SimpleSorting.insertion(array);
    }


}
