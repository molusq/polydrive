
class QuickSortTest extends AbstractSortTest{

    @Override
    protected <T extends Comparable> void sort(T[] array) {
        QuickSort.sort(array);
    }


}
