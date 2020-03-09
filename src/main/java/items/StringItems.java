package items;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static utils.Utils.generateString;

public class StringItems implements ComparableItems<String> {
    private int maxLen;
    private List<String> items;
    private Comparator<String> comparator = (s0, s1) -> s0.toUpperCase().compareTo(s1.toUpperCase());

    public StringItems(int itemsCount, int maxLen, boolean avoidDuplicates) {
        this.maxLen = maxLen;
        this.items = generateItems(itemsCount, avoidDuplicates);
    }

    public StringItems(int maxLen, List<String> items, Comparator<String> comparator) {
        this.maxLen = maxLen;
        this.items = items;
        if (nonNull(comparator)) {
            this.comparator = comparator;
        }
    }

    @Override
    public List<String> getItems() {
        return items;
    }

    @Override
    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public ComparableItems<String> clone() {
        return new StringItems(maxLen, items.stream().collect(toList()), comparator);
    }

    @Override
    public Comparator<String> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<String> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String generateItem(Random rnd) {
        return generateString(rnd, maxLen);
    }
}
