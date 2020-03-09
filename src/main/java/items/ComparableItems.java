package items;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface ComparableItems<V> {
    default List<V> generateItems(int itemsCount, boolean avoidDuplicates) {
        Random rnd = new Random();
        List<V> list = new ArrayList<>();
        while (list.size() < itemsCount) {
            V item = generateItem(rnd);
            while (avoidDuplicates && list.contains(item)) {
                item = generateItem(rnd);
            }
            list.add(item);
        }
        return list;
    }

    Comparator<V> getComparator();
    void setComparator(Comparator<V> comparator);
    V generateItem(Random rnd);
    List<V> getItems();
    void setItems(List<V> list);
    ComparableItems<V> clone();
}
