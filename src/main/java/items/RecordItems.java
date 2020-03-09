package items;

import items.record.Record;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static utils.Utils.generateString;

public class RecordItems implements ComparableItems<Record> {
    private int maxValue;
    private List<Record> items;

    private Comparator<Record> comparator = (r0, r1) -> {
        if (isNull(r0.getValue()) && !isNull(r1.getValue())) {
            return -1;
        } else if (!isNull(r0.getValue()) && isNull(r1.getValue())) {
            return 1;
        }
        return r0.countSum().compareTo(r1.countSum());
    };

    public RecordItems(List<Record> items, int maxValue, Comparator<Record> comparator) {
        this.items = items;
        this.maxValue = maxValue;
        if (nonNull(comparator)) {
            this.comparator = comparator;
        }
    }

    public RecordItems(int itemsCount, int maxValue, boolean avoidDuplicates) {
        this.maxValue = maxValue;
        this.items = generateItems(itemsCount, avoidDuplicates);
    }

    @Override
    public Comparator<Record> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<Record> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Record> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Record> items) {
        this.items = items;
    }

    @Override
    public ComparableItems<Record> clone() {
        return new RecordItems(items.stream().collect(toList()), maxValue, comparator);
    }

    @Override
    public Record generateItem(Random rnd) {
        return new Record(rnd.nextInt(maxValue), rnd.nextInt(10) > 8 ? null : rnd.nextDouble() * maxValue, generateString(rnd, 10));
    }
}
