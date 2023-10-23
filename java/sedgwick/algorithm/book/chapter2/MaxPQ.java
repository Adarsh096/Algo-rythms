package sedgwick.algorithm.book.chapter2;

public class MaxPQ<T extends Comparable<T>> {
    /*
    MaxPQ ensures that the parent is greater than the child.
    The remove happens from the root since root is the larges element.
    Insert is done and the heap is re-balanced or re-heapified
     */
    private T[] pq;
    private int N = 0; // 1..N will be used with pq[0] never used.

    public MaxPQ(int maxN){
        pq = (T[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void insert(T v){
        pq[++N]=v;
        swim(N);
    }
    public T delMax(){
        T key = pq[1];
        swap(1, N--);
        pq[N+1]=null;
        sink(1);
        return key;
    }
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j){
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j]=temp;
    }
    private void swim(int k){
        // swim up to the place where the parent is larger than node at N.
        while(k>1){
            if(less(k/2, k)){
                swap(k/2,k);
            }else {
                break;
            }
            k = k/2;
        }
    }

    private void sink(int k){
        while(2*k<N){ //GOTCHA don't check equality here otherwise 2k+1 will be out of bounds of array.
            int swapChildIdx = 2*k;
            if(less(swapChildIdx, swapChildIdx+1)){
                swapChildIdx++;
            }
            if(less(swapChildIdx, k)){
                break;
            }
            swap(swapChildIdx,k);
            //GOTCHA don't set k = swapChildIdx because if anytime swapChildIdx was set to
            // the right child ie 2k+1 then next iteration will double it k-> 2(2k+1)
            // and we will be missing to check the left child.
            k = 2*k;
        }
    }
}
