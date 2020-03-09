package methods;

import items.ComparableItems;

import java.util.List;

import static enums.SortEnum.GNOME;
import static utils.Utils.swap;

public class GnomeSort<V> extends BaseSort<V> {
    public GnomeSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, GNOME);
    }

    @Override
    protected List<V> compute() {
        if (getRawResult().size() > 1) {
            int pos = 1;
            while (pos < getRawResult().size()) {
                if (isWrongOrder(pos - 1, pos)) {
                    swap(getRawResult(), pos - 1, pos, nSwaps);
                    if (pos > 1) {
                        pos--;
                    }
                } else {
                    pos++;
                }
            }
        }
        return getRawResult();
    }
}
