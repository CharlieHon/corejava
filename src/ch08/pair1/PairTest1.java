package ch08.pair1;

/**
 *
 */
public class PairTest1 {

    public static void main(String[] args) {
        String[] words = new String[] {"charlie", "life", "happy", "bruce"};
        Pair<String> minmax = ArrayAlg.minmax(words);
        System.out.println("min=" + minmax.getFirst());     // bruce
        System.out.println("max=" + minmax.getSecond());    // life
    }

}

class ArrayAlg {
    /**
     * 得到字符串数组中字典序最小和最大值组成的Pair
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}
