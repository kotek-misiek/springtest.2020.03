package methods;

import enums.SortEnum;
import items.ComparableItems;
import utils.Utils;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public abstract class BaseSort<V> extends RecursiveTask<List<V>> implements Cloneable {
    protected SortEnum type;
    protected boolean asc = true;
    protected ComparableItems<V> comparableItems;
    protected long[] nSwaps;

    public BaseSort() {
        nSwaps = new long[] {0};
    }

    public BaseSort(ComparableItems<V> comparableItems, boolean asc, SortEnum type) {
        this.type = type;
        this.asc = asc;
        this.comparableItems = comparableItems;
        setRawResult(comparableItems.getItems());
        nSwaps = new long[] {0};
    }

    public ComparableItems<V> getComparableItems() {
        return comparableItems;
    }

    public boolean getAsc() {
        return asc;
    }

    public SortEnum getType() {
        return type;
    }

    public long getSwaps() {
        return nSwaps[0];
    }

    protected int compare(V item0, V item1) {
        return Utils.compare(getComparableItems().getComparator(), item0, item1, asc);
    }

    protected boolean isWrongOrder(int j, int i) {
        return Utils.isWrongOrder(getRawResult(), getComparableItems().getComparator(), j, i, asc);
    }

    protected boolean isWrongValueOrder(V item0, V item1) {
        return Utils.isWrongValueOrder(getComparableItems().getComparator(), item0, item1, asc);
    }

    public List<V> execute() {
        exec();
        return getRawResult();
    }
}
