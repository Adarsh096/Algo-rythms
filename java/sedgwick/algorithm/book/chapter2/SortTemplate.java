package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.In;

public class SortTemplate {
    public static <T extends Comparable<T>> void sort(T[] a){
        //TODO
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
        String[] a = In.readStrings();
        sort(a);
        assert  isSorted(a);
        show(a);
    }
}
