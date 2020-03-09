package methods;

import items.ComparableItems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static enums.SortEnum.SHELL;
import static java.util.stream.Collectors.toList;
import static utils.Utils.swap;

public class ShellSort<V> extends BaseSort<V> {
    private final int[] CIURA = new int[] {1, 4, 10, 23, 57, 132, 301, 701, 1750};
    private final int[] SEQUENCE;

    public ShellSort(ComparableItems<V> comparableItems, boolean asc) {
        super(comparableItems, asc, SHELL);
        SEQUENCE = fillSequence();
    }

    private int[] fillSequence() {
        List<Integer> list = Arrays.stream(CIURA).boxed().collect(toList());
        int numInterval = CIURA.length - 1;
        while ((getRawResult().size() - 1) / list.get(numInterval) + 1 > 4) {
            list.add((int) (list.get(numInterval) * 2.25));
            numInterval++;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private int countSize(int numInterval) {
        return (getRawResult().size() - 1) / SEQUENCE[numInterval] + 1;
    }

    @Override
    protected List<V> compute() {
        Stream.iterate(SEQUENCE.length - 1, numInterval -> numInterval - 1)
                .takeWhile(numInterval -> numInterval >= 0)
                .filter(numInterval -> countSize(numInterval) > 1)
                .forEach(numInterval -> IntStream.range(0, SEQUENCE[numInterval])
                            .forEach(k -> IntStream.range(0, countSize(numInterval) - 1)
                                        .forEach(i -> IntStream.range(i + 1, countSize(numInterval))
                                                .filter(j -> isWrongOrder(i, j))
                                                .forEach(j -> swap(getRawResult(), i, j, nSwaps)))));
        return getRawResult();
    }
}
