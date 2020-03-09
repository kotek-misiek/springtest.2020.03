package methods;

import items.ComparableItems;

import java.util.List;
import java.util.stream.IntStream;

import static enums.SortEnum.BUBBLE;
import static utils.Utils.swap;

public class BubbleSort<V> extends BaseSort<V> {
    public BubbleSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, BUBBLE);
    }

    @Override
    protected List<V> compute() {
        IntStream.range(1, getRawResult().size())
                .forEach(i -> IntStream.range(0, i)
                            .filter(j -> isWrongValueOrder(getRawResult().get(j), getRawResult().get(i)))
                            .forEach(j -> swap(getRawResult(), j, i, nSwaps)));
        return getRawResult();
    }
}
