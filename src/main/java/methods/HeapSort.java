package methods;

import items.ComparableItems;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static enums.SortEnum.HEAP;

public class HeapSort<V> extends BaseSort<V> {
    private List<V> heap = new ArrayList<>();

    public HeapSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, HEAP);
    }

    private void buildHeap() {
        while (getRawResult().size() > 0) {
            heap.add(getRawResult().get(0));
            getRawResult().remove(0);
            heapifyUp(heap.size() - 1);
        }
    }

    private void heapifyUp(int i) {
        int parent = parent(i);
        if (parent != -1) {
            if (Utils.isWrongOrder(heap, getComparableItems().getComparator(), parent, i, asc)) {
                Utils.swap(heap, i, parent, nSwaps);
                heapifyUp(parent);
            }
        }
    }

    private void heapifyDown(int i) {
        try {
            int child = child(i);
            if (Utils.isWrongOrder(heap, getComparableItems().getComparator(), i, child, asc)) {
                Utils.swap(heap, i, child, nSwaps);
                heapifyDown(child);
            }
        } catch (Exception e) {
        }
    }

    private int parent(int i) {
        return  i == 0 ? -1 : (i - 1) / 2;
    }

    private int child(int i) throws Exception {
        int i0 = i * 2 + 1;
        int i1 = i0 + 1;
        if (i0 >= heap.size()) i0 = -1;
        if (i1 >= heap.size()) i1 = -1;

        if (i0 == -1 && i1 == -1) {
            throw new Exception("There are no children");
        }
        return i1 == -1 ? i0 : (Utils.isWrongOrder(heap, getComparableItems().getComparator(), i0, i1, asc) ? i1 : i0);
    }

    private V peekRoot() {
        V root = heap.get(0);
        Utils.swap(heap, 0, heap.size() - 1, nSwaps);
        heap.remove(heap.size() - 1);
        heapifyDown(0);

        return root;
    }

    @Override
    protected List<V> compute() {
        buildHeap();
        while (heap.size() > 0) {
            getRawResult().add(peekRoot());
        }
        return getRawResult();
    }
}
