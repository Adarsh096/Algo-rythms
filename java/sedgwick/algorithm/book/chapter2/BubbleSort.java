package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class BubbleSort {
    public static <T extends Comparable<T>> void sort(T[] a){
        //Bubble sort repeatedly finds the largest number
        // by comparing adjacent elements and swaps the larger number to the right side.
        // In one pass of the Bubble sort we get the largest number at the right most part.
        for (int i = 0; i <a.length; i++) {
            boolean swappedAny = false;
            for (int j = 0; j < a.length-1-i; j++) {//GOTCHA a.length-1-i
                if(!less(a[j],a[j+1])){//GOTCHA don't use i here anywhere
                    swap(a, j, j+1);
                    swappedAny = true;
                }
            }
            if(!swappedAny){
                break;// array is already sorted otherwise there must have been at least one swap.
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
