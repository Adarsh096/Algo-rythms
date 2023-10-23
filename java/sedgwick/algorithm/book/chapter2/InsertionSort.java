package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] a){
        // Insertion sort : add the element a[i] on or the left of ith index in correct order.
        /* The inner loop is to do the swap till we reach-
         * -the correct order place on the left for the ith entry.
         * It is better than selection sort. For partially sorted arrays this works significantly better.
         * (N^2)/4 compares.
         */
        for (int i = 0; i < a.length; i++) {
            for (int j = i ; j>0 ; j--) {
                if(a[j].compareTo(a[j-1])<0){
                    swap(a,j,j-1);
                }else {
                    break;
                }
            }
        }
    }
    public static <T extends Comparable<T>> void optimizedInsertionSort(T[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            T key = a[i];
            int j;

            for (j = i; j > 0 && a[j - 1].compareTo(key) > 0; j--) {
                a[j] = a[j - 1]; // Move larger elements one position to the right
            }

            a[j] = key;
        }
    }

    private static <T extends Comparable<T>> boolean  less(T v, T w){
        return v.compareTo(w)<0;
    }
    private static <T extends Comparable<T>> void swap(T[] a, int i, int j){
        T temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private static <T extends Comparable<T>> void show(T[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    // main method:
    public static void main(String[] args){
        Integer[] a = {2,4,1,0,8,33,4,88,92};
        /*Random random = new Random();
        int sizeArr = 10000;
        Integer[] a = new Integer[sizeArr];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt();
        }*/
        Stopwatch stopwatch = new Stopwatch();
        sort(a);
        System.out.println(stopwatch.elapsedTime());
        assert  isSorted(a);
        show(a);
        optimizedInsertionSort(a);
        show(a);
    }
}
