package methods;

import items.ComparableItems;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static enums.SortEnum.INSERTION;

public class InsertionSort<V> extends BaseSort<V> {
    private List<V> list = new ArrayList<>();

    public InsertionSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, INSERTION);
    }

    @Override
    protected List<V> compute() {
        while (getRawResult().size() > 0) {
            int size = list.size();
            list.add(getRawResult().get(0));
            getRawResult().remove(0);
            IntStream.range(0, size)
                    .filter(i -> Utils.isWrongOrder(list, comparableItems.getComparator(), i, size, asc))
                    .forEach(i -> Utils.swap(list, i, size, nSwaps));
        }
        setRawResult(list);
        return getRawResult();
    }
}
