package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class MergeSortIterative {
    /*
    Merge sort has Nlog(N) time complexity.
    At each step we need to access N elements during the merge.
    When we divide the elements in halves we get log(N) steps of division.
     */
    //Arrays contents(in heap) are passed by reference.
    //Array location pointer(in stack memory) is passed by value.
    public static <T extends Comparable<T>> void sort(T[] a) {
        a = mergeSortI(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> T[] mergeSortI(T[] a, int low, int hi) {
        int n = a.length;
        T[] aux = a.clone(); // Create an auxiliary array for merging
        for (int i = 1; i < n ; i=i*2) {
            for (int j = 0; j <n-i; j=j+i*2) {
                int mid = low + i - 1;
                int high = Math.min(low + i * 2 - 1, n - 1);
                merge(a, aux, low, mid, high);
            }

        }

        return a;
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
            // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
            if (i > mid) a[k] = aux[j++];
            else if (j > hi ) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static <T extends Comparable<T>> void show(T[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    // main method:
    public static void main(String[] args) {
        //Integer[] a = {2,4,1,0,8,33,4,88,92};
        Random random = new Random();
        int sizeArr = 10000;
        Integer[] a = new Integer[sizeArr];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt();
        }
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        System.out.println(stopwatch.elapsedTime());
        assert isSorted(a);
        //show(a);
    }
}
