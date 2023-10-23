package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class QuickSort {
    /*
    Unlike the merge sort, the quick sort algorithm does the work before the recursion.
    It partitions the array and makes sure the elements on left are smaller than the pivot element
    and the elements on the right are greater than the pivot.
    The recursion divides the array into sub-array, and we do the same process on the sub-arrays(left and right).
    It has Nlog(N) average time complexity, and it is in-place algorithms, so it does not need additional space.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        StdRandom.shuffle(a);
        quickSort(a, 0,a.length-1);
    }

    private static <T extends  Comparable<T>> void quickSort(T[] a, int low, int hi) {
        if(low>=hi){
            return;
        }
        int pivot = partition(a, low, hi);
        quickSort(a, low, pivot);
        quickSort(a, pivot+1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int low, int hi) {
        int pivot = low;
        int i = low;
        int j = hi + 1; // Incremented by 1 to include the pivot element

        while (true) {
            while (less(a[++i], a[pivot])) {
                if (i == hi) break;
            }

            while (less(a[pivot], a[--j])) {
                if (j == low) break;//This is redundant. Check why?
            }

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, pivot, j); // Place the pivot in its correct position
        return j;
    }
    /*
    Following partition2() method also works but it is slower.
    Find out why?
     */
    private static <T extends  Comparable<T>> int partition2(T[] a, int low, int hi){
        int pivot = low;

        int i = low;
        int j = hi;
        while(true){
            while(less(a[i],a[pivot])) {
                i++;
            }
            while(less(a[pivot], a[j])){
                j--;
            }
            if(i>=j) break;
            //System.out.println("i:: "+i+" j:: "+j);
            //System.out.println("swapping "+a[i]+" with "+a[j]);
            if (a[i].compareTo(a[j]) == 0) {
                //to handle duplicate entries.
                i++;
                j--;
            } else {
                swap(a, i, j);
            }
            //show(a);
        }
        swap(a, pivot,j);// j is at the place where the pivot should be.
        return j;
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
