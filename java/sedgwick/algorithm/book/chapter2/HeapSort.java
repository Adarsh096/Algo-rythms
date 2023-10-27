package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

/*
Zero based index implementation.
 */
public class HeapSort<T extends Comparable<T>> {
    private boolean less(T[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void sink(T[] a, int k, int N) {
        // for zero based indexing, the child nodes are at 2K+1 and 2K+2.
        while (2 * k+2 <= N) { //i.e., in heap last elements are mostly child elements and thus 2K+2 can not go beyond N-1
            int swapChildIdx = 2 * k+1;
            if (swapChildIdx < N && less(a, swapChildIdx, swapChildIdx + 1)) {
                swapChildIdx++;
            }
            if (!less(a, k, swapChildIdx)) {
                break;
            }
            swap(a, k, swapChildIdx);
            k = swapChildIdx;//i.e., the new position of the parent node which was just swapped.
        }
        // handle the last two elements:
        if(N==1 && less(a, 0,1)){
            swap(a, 0,1);
        }
    }

    public void sort(T[] a) {
        int N = a.length-1;

        // Create the max heap (heapify the array)
        for (int k = (N-1) / 2; k >= 0; k--) { // for zero based index the parent is at N/2 -1
            sink(a, k, N);
        }
        //System.out.println("Created heap from the array:");
        //HeapPrinter heapPrinter = new HeapPrinter<>();
        //heapPrinter.printHeap(a);
        // Sort the array
        //System.out.println("Sorting elements now:");
        while (N > 0) {
            swap(a, 0, N--);
            sink(a, 0, N);
            //show(a);
        }
    }

    public boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a, i, i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void show(T[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Integer[] a = {2, 4, 1, 6, 8, 33, 4, 88, 92};
        Random random = new Random();
        int sizeArr = 10000;
        Integer[] a = new Integer[sizeArr];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt();
        }
        Stopwatch stopwatch = new Stopwatch();
        HeapSort<Integer> heapSort = new HeapSort<>();
        heapSort.sort(a);
        System.out.println(stopwatch.elapsedTime());
        if (heapSort.isSorted(a)) {
            System.out.println("Array is sorted.");
        } else {
            System.out.println("Array is not sorted.");
        }

        //heapSort.show(a);
    }
}

