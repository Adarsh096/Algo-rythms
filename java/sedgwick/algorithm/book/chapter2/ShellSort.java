package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class ShellSort {
    public static <T extends Comparable<T>> void sort(T[] a){
        //Shell sort creates a partially sorted array by sorting
        // elements at h-intervals where h decreases quickly.
        // h = 3h +1
        // first value of h = max value in 3h +1 less than N/3
        // stopping condition is h = 1 where shell sort acts as insertion sort.
        int N = a.length;
        int h = -1;

        // first value of h calculation

        while(h<N/3){
            h = 3*h + 1;
        }
        // h sorting the array:
        for(int i = 1; i< N; i++){
            for (int j = i; j>0 && h>0 && j>=h; j=j-h) {
                if(less(a[j], a[j-h])){
                    swap(a,j, j-h);
                }else{
                    break;
                }
                h = h/3;
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
