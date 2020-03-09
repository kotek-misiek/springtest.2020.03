package execution;

import enums.SortEnum;
import items.ComparableItems;
import methods.*;

public class SortFactory<V extends Comparable> {
    public static BaseSort create(ComparableItems comparableItems, boolean asc, SortEnum type) {
        switch (type) {
            case QUICK: return new QuickSort(comparableItems, asc);
            case HEAP: return new HeapSort(comparableItems, asc);
            case SHELL: return new ShellSort(comparableItems, asc);
            case SHAKER: return new ShakerSort(comparableItems, asc);
            case INSERTION: return new InsertionSort(comparableItems, asc);
            case SELECTION: return new SelectionSort(comparableItems, asc);
            case GNOME: return new GnomeSort(comparableItems, asc);
            case MERGE: return new MergeSort(comparableItems, asc);
            default: return new BubbleSort(comparableItems, asc);
        }
    }
}
