package items;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

public class IntegerItems implements ComparableItems<Integer> {
    private int n;
    private int maxValue;
    private List<Integer> items;
    private Comparator<Integer> comparator = (i0, i1) -> i0.compareTo(i1);

    public IntegerItems(List<Integer> items) {
        this.n = items.size();
        this.maxValue = items.stream().reduce(items.get(0), (i0, i1) -> i0 < i1 ? i1 : i0);
        this.items = items;
    }

    public IntegerItems(List<Integer> items, int maxValue, Comparator<Integer> comparator) {
        this.n = items.size();
        this.maxValue = maxValue;
        this.items = items;
        if (nonNull(comparator)) {
            this.comparator = comparator;
        }
    }

    public IntegerItems(int n, int maxValue, List<Integer> items, Comparator<Integer> comparator) {
        this.n = n;
        this.maxValue = maxValue;
        this.items = items;
        this.comparator = comparator;
    }

    public IntegerItems(int n, int maxValue, boolean avoidDuplicates) {
        this.n = n;
        this.maxValue = maxValue;
        if (avoidDuplicates) {
            this.items = IntStream.range(0, maxValue)
                    .boxed()
                    .collect(toList());
            Collections.shuffle(this.items);
            this.items.subList(0, maxValue - n).clear();
        } else {
            this.items = generateItems(n, false);
        }
    }

    @Override
    public Comparator<Integer> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Integer> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Integer> items) {
        this.items = items;
    }

    @Override
    public ComparableItems<Integer> clone() {
        return new IntegerItems(items.stream().collect(toList()), maxValue, comparator);
    }

    @Override
    public Integer generateItem(Random rnd) {
        return rnd.nextInt(maxValue);
    }
}
