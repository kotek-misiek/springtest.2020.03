package methods;

import items.ComparableItems;
import utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static enums.SortEnum.MERGE;
import static java.util.stream.Collectors.toList;
import static utils.Utils.swap;

public class MergeSort<V> extends BaseSort<V> {
    public MergeSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, MERGE);
    }

    private List<List<V>> divide(List<V> source) {
        List<List<V>> list = new ArrayList<>();
        if (source.size() > 2) {
            int n = source.size() / 2;
            list.add(source.stream().limit(n).collect(toList()));
            list.add(source.stream().skip(n).collect(toList()));
        } else {
            list.add(source);
        }
        return list;
    }

    private List<V> merge(List<List<V>> list) {
        List<V> result = new ArrayList<>();
        Comparator<V> comparator = comparableItems.getComparator();
        while (!list.get(0).isEmpty() && !list.get(1).isEmpty()) {
            if (Utils.compare(comparator, list.get(0).get(0), list.get(1).get(0), getAsc()) < 0) {
                result.add(list.get(0).remove(0));
            } else {
                result.add(list.get(1).remove(0));
            }
        }
        if (list.get(0).isEmpty()) {
            result.addAll(list.get(1));
        } else {
            result.addAll(list.get(0));
        }
        list.clear();
        return result;
    }

    private List<V> sortList(List<V> source) {
        List<List<V>> list = divide(source);
        if (list.size() == 1) return source;
        list.stream()
                .filter(subList -> subList.size() > 1)
                .forEach(subList -> {
                    if (subList.size() > 2) {
                        List<V> sortedList = sortList(subList);
                        IntStream.range(0, subList.size())
                                .forEach(i -> subList.set(i, sortedList.get(i)));
                    } else if (isWrongValueOrder(subList.get(0), subList.get(1))) {
                        swap(subList, 0, 1, nSwaps);
                    }
                });
        return merge(list);
    }

    @Override
    protected List<V> compute() {
        setRawResult(sortList(getRawResult()));
        return getRawResult();
    }
}
