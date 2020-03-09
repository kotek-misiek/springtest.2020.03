package methods;

import items.ComparableItems;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static enums.SortEnum.QUICK;
import static java.util.stream.Collectors.groupingBy;
import static utils.Utils.swap;

@Getter
@Setter
public class QuickSort<V> extends BaseSort<V> {
    private int bgn;
    private int end;

    public QuickSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, QUICK);
        bgn = 0;
        end = getRawResult().size();
    }

    protected void bubbleSortTwoThree() {
        if (isWrongOrder(bgn, bgn + 1)) {
            swap(getRawResult(), bgn, bgn + 1, nSwaps);
        }
        if (end - bgn == 3) {
            if (isWrongOrder(bgn, bgn + 2)) {
                swap(getRawResult(), bgn, bgn + 2, nSwaps);
            }
            if (isWrongOrder(bgn + 1, bgn + 2)) {
                swap(getRawResult(), bgn + 1, bgn + 2, nSwaps);
            }
        }
    }

    protected Map<Boolean, List<V>> createFinalMap(int bgn, int end, V middle) {
        Map<Boolean, List<V>> map = getRawResult().subList(bgn, end)
                .stream()
                .collect(groupingBy(item -> compare(item, middle) <= 0));
        if (map.size() == 1) {
            map = getRawResult().subList(bgn, end)
                    .stream()
                    .collect(groupingBy(item -> compare(item, middle) < 0));
        }
        return map;
    }

    private void forkProcess(int trueSize) {
        try {
            QuickSort<V> qs1 = (QuickSort<V>) clone();
            QuickSort<V> qs2 = (QuickSort<V>) clone();

            qs1.setEnd(bgn + trueSize);
            qs2.setBgn(bgn + trueSize);

            qs1.fork();
            qs2.fork();

            qs1.join();
            qs2.join();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected List<V> compute() {
        if (end - bgn > 1) {
            if (end - bgn <= 3) {
                bubbleSortTwoThree();
            } else {
                Map<Boolean, List<V>> map = createFinalMap(bgn, end, getRawResult().get(bgn + (end - bgn) / 2));
                if (map.size() == 2) {
                    int trueSize = map.get(true).size();
                    int truePos = bgn + trueSize;
                    IntStream.range(bgn, end)
                            .forEach(i -> getRawResult().set(i, i < truePos ? map.get(true).get(i - bgn) : map.get(false).get(i - truePos)));
                    forkProcess(trueSize);
                } else {
                    List<V> list = map.containsKey(true) ? map.get(true) : map.get(false);
                    IntStream.range(bgn, end)
                            .forEach(i -> getRawResult().set(i, list.get(i - bgn)));
                }
            }
        }
        return getRawResult();
    }
}
