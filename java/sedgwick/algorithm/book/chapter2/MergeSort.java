package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class MergeSort {
    /*
    Merge sort has Nlog(N) time complexity.
    At each step we need to access N elements during the merge.
    When we divide the elements in halves we get log(N) steps of division.
     */
    //Arrays contents are passed by reference. Array location pointer is passed by value.
    public static <T extends Comparable<T>> void sort(T[] a) {
        //Merge Sort splits the array recursively and sorts the array and merge the sorted halves.
        a = mergeSort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> T[] mergeSort(T[] a, int low, int hi) {
        if (hi == low) {
            // if only single element returned then return same array
            return a;
        }

        int mid = (low + hi) / 2;
        T[] lsorted = mergeSort(a, low, mid);
        T[] rsorted = mergeSort(a, mid + 1, hi);

        // merge the sorted arrays:
        int i = low, j = mid+1, k = low;// Imp indices
        while (i <= mid && j <= hi) { // Imp break condition for loop
            if (less(lsorted[i], rsorted[j])) {
                a[k] = lsorted[i];
                i++;
            } else {
                a[k] = rsorted[j];
                j++;
            }
            k++;
        }
        while (i <= mid) { // Imp break condition for loop
            a[k++] = lsorted[i++];
        }
        while (j <= hi) { // Imp break condition for loop
            a[k++] = rsorted[j++];
        }

        return a;
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
//        Integer[] a = {2,4,1,0,8,33,4,88,92};
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
