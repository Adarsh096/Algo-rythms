package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class SelectionSort {
    public static <T extends Comparable<T>> void sort(T[] a){
        //selection sort repeatedly finds the smallest number
        // and puts it at the start of the unsorted array.

        for(int c = 0; c<a.length;c++){
            int minIdx = c;
            for (int i = c+1; i < a.length; i++) {
                if(!less(a[c], a[i])){
                    minIdx = i;
                }
            }
            // swap the cth value with the ith value(new min value):
            if(minIdx!=c){
                swap(a, c,minIdx);
            }
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
        assert  isSorted(a);
        //show(a);
    }
}
