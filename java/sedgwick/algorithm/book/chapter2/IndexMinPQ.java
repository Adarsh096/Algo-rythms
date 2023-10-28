package sedgwick.algorithm.book.chapter2;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
 Indexed priority queues are commonly used in algorithms like
 Dijkstra's algorithm for finding the shortest path,
 Prim's algorithm for minimum spanning trees, and A* search.
*/
public class IndexMinPQ <Key extends Comparable<Key>>{
    private int N; // number of elements in PQ
    private int[] pq; // binary heap with 1-based indexing. Index are position on heap and values are priorities.
    private int[] qp; // for inverse lookup. Index are the priorities from pq and values are the position(ie index of pq).

    // keys: the value associated with the priorities in pq array.
    // Index is the priority associated with the key.
    private Key[] keys;
    public IndexMinPQ(int maxN){
        keys = (Key[]) new Comparable[maxN+1];//since 1-based indexing.
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for(int i=0; i<maxN+1; i++){
            qp[i] = -1;// convention.
        }
    }

    public boolean isEmpty(){
        return N==0; // for 1-based indexing.
    }
    public boolean contains(int k){
        // checks for a priority assigned to any value or not.
        // Without qp we would have to travel the heap to find the priority.
        return qp[k]!= -1;
    }

    public void insert(int k, Key key){
        // insert key with a priority of k.
        N++;
        keys[k] = key;
        pq[N] = k;
        qp[k] = N;
        swim(N);
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

            k = swapChildIdx;
        }
    }
    private boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void swap(int keyIndex1, int keyIndex2){
        int temp = pq[keyIndex1];
        pq[keyIndex1] = pq[keyIndex2];
        pq[keyIndex2] = temp;

        qp[pq[keyIndex1]] = keyIndex1;
        qp[pq[keyIndex2]] = keyIndex2;
    }
    public Key min(){
        if(N==0){
            throw new NoSuchElementException("Priority queue underflow");
        }
        // pq has index as position so i=1 is the root and min-heap has the smallest element at root.
        // The i=1 index has index of value in keys array.
        return keys[pq[1]];
    }

    public int delMin(){
        // return the index of min from pq array
        int idxMin = pq[1];
        swap(1,N--);
        sink(1);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return idxMin;
    }
    public Key keyOf(int index) {
        if (!contains(index)) {
            throw new NoSuchElementException("Index is not in the priority queue");
        }

        return keys[index];
    }

    public int minIdx(){
        if(N==0){
            throw new NoSuchElementException("Priority queue underflow");
        }
        // returns the smallest priority.
        return pq[1];
    }

    public void change(int k, Key key){
        // changes the key associated with a given priority.
        keys[k] = key;
        swim(qp[k]); //TODO why?
        sink(qp[k]);
    }
    public void delete(int k){
        //TODO
    }
    public static void main(String[] args) {
        // Insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> priorityQueue =
                new IndexMinPQ<>(strings.length);

        for (int i = 0; i < strings.length; i++) {
            priorityQueue.insert(i, strings[i]);
        }

        StdOut.println("Key of index 4: " + priorityQueue.keyOf(4) + " .Expected: of");

        // Delete and print each key
        while (!priorityQueue.isEmpty()) {
            String key = priorityQueue.min();
            int index = priorityQueue.delMin();
            StdOut.println(index + " " + key);
        }
    }
}

