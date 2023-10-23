package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class QuickSortThreeWay {
    /*
     Three-way split is done to handle duplicate entries in the array
     and preventing the repeated partitioning of sub-arrays of same elements.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        StdRandom.shuffle(a);
        quickSort(a, 0,a.length-1);
    }

    private static <T extends  Comparable<T>> void quickSort(T[] a, int low, int hi) {
        if(low>=hi){
            return;
        }

        int lt = low;
        int i = low+1;
        int gt = hi;
        T pivot = a[low];

        while(i<=gt){// i to gt is not yet explored part of array.
            int comp = a[i].compareTo(pivot);
            if(comp<0){
                // lt to i is the equal to pivot region.
                // swapping the element at lt with i means
                // putting the smaller element at lt and at i the equal to element will be placed.
                // since all equal to elements are same so putting it at i means that we have shifted the equal-to
                // region one step ahead.
                swap(a,lt++, i++);
            } else if (comp >0) {
                // element at gt is unexplored element.
                // swapping with ith element means putting the greater element in between new gt to hi region.
                // gt will still have an unexplored element after gt--.
                swap(a, i, gt--);
            }else {
                i++;
            }
        }

        quickSort(a, low, lt-1);
        quickSort(a, gt+1, hi);
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
        //Integer[] a = {2, 4, 1, 0, 8, 33, 4, 88, 92};
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
