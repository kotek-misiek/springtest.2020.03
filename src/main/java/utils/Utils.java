package utils;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Utils {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHLEN = ALPHABET.length();

    public static String generateString(Random rnd, int maxLen) {
        int len = rnd.nextInt(maxLen) + 1;
        return IntStream.range(0, len)
                .mapToObj(i -> ALPHABET.charAt(rnd.nextInt(ALPHLEN)))
                .collect(Collector.of(StringBuilder::new, (sb, st) -> sb.append(st), StringBuilder::append, StringBuilder::toString));
    }

    public static String generateFixedLenString(Random rnd, int len) {
        return IntStream.range(0, len)
                .mapToObj(i -> ALPHABET.charAt(rnd.nextInt(ALPHLEN)))
                .collect(Collector.of(StringBuilder::new, (sb, st) -> sb.append(st), StringBuilder::append, StringBuilder::toString));
    }

    public static int compare(Comparator comparator, Object item0, Object item1, boolean asc) {
        return asc
                ? comparator.compare(item0, item1)
                : comparator.compare(item1, item0);
    }

    public static boolean isWrongOrder(List list, Comparator comparator, int j, int i, boolean asc) {
        return compare(comparator, list.get(j), list.get(i), asc) > 0;
    }

    public static boolean isWrongValueOrder(Comparator comparator, Object item0, Object item1, boolean asc) {
        return compare(comparator, item0, item1, asc) > 0;
    }

    public static void swap(List list, int i, int j, long[] nSwaps) {
        Object tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
        nSwaps[0]++;
    }
}
