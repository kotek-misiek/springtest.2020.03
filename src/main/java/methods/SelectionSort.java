package methods;

import items.ComparableItems;

import java.util.List;
import java.util.stream.IntStream;

import static enums.SortEnum.SELECTION;
import static java.util.stream.Collectors.toList;

public class SelectionSort<V> extends BaseSort<V> {
    public SelectionSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, SELECTION);
    }

    @Override
    protected List<V> compute() {
        int size = comparableItems.getItems().size();
        setRawResult(IntStream.range(0, size)
                .mapToObj(i -> {
                    List<V> items = comparableItems.getItems();
                    if (items.size() == 1) {
                        return items.remove(0);
                    }
                    V item = items
                            .stream()
                            .reduce(items.get(0), (item0, item1) -> (compare(item0, item1) < 0 ? item0 : item1));
                    items.remove(item);
                    return item;
                })
        .collect(toList()));
        return getRawResult();
    }
}
