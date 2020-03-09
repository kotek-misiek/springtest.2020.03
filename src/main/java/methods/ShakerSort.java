package methods;

import items.ComparableItems;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static enums.SortEnum.SHAKER;
import static utils.Utils.swap;

public class ShakerSort<V> extends BaseSort<V> {
    public ShakerSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, SHAKER);
    }

    @Override
    protected List<V> compute() {
        int left = 0;
        int right = getRawResult().size() - 1;
        while (left < right) {
            IntStream.range(left, right)
                    .forEach(i -> {
                        if (compare(getRawResult().get(i), getRawResult().get(i + 1)) > 0) {
                            swap(getRawResult(), i, i + 1, nSwaps);
                        }
                    });
            right--;
            // right to left
            IntStream.rangeClosed(left + 1, right)
                    .boxed()
                    .sorted(Collections.reverseOrder())
                    .forEach(i -> {
                        if (compare(getRawResult().get(i), getRawResult().get(i - 1)) < 0) {
                            swap(getRawResult(), i, i - 1, nSwaps);
                        }
                    });
            left++;
        };
        return getRawResult();
    }
}
