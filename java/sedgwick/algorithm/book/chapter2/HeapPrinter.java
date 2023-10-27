package sedgwick.algorithm.book.chapter2;

public class HeapPrinter<T extends Comparable<T>> {
    public void printHeap(T[] heap) {
        int depth = (int) (Math.log(heap.length) / Math.log(2));
        int index = 0;
        int maxDigits = String.valueOf(heap[0]).length();

        for (int i = 0; i <= depth; i++) {
            int elementsInLevel = (int) Math.pow(2, i);
            int padding = (maxDigits + 1) * (int) Math.pow(2, depth - i);
            for (int j = 0; j < elementsInLevel && index < heap.length; j++) {
                printSpaces(padding / 2);
                System.out.print(String.format("%-" + maxDigits + "s", heap[index]));
                printSpaces(padding / 2);
                index++;
            }
            System.out.println();
        }
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        Integer[] heap = {92, 88, 33, 6, 8, 1, 4, 4, 2};
        HeapPrinter<Integer> heapPrinter = new HeapPrinter<>();
        heapPrinter.printHeap(heap);
    }
}
