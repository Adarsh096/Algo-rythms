package sedgwick.algorithm.book.chapter3;

public class BinarySearchST <Key extends Comparable<Key>, Val>{
    // rank method returns the index of the key if present otherwise returns the index where key should be inserted.
    // binary search will be done on the keys array. Values array is updated only for put operation.

    private Key[] keys;
    private Val[] values;
    private int N;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Val[]) new Comparable[capacity];
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }
    public Val get(Key key){
        if(key==null || isEmpty()){
            return null;
        }

        int i = rank(key,0,N-1);
        if(i<N && key.compareTo(keys[i])==0){
            return values[i];
        }else{
            return null; // not found case
        }
    }
    public int rank(Key key,int lo, int hi){
        if(key==null){
            throw new IllegalArgumentException("key can not be null");
        }
        // iterative :
        while(lo<=hi){
            int mid = (lo +hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp>0){
                // searched key is larger than the mid-value.
                lo = mid + 1;
            }else if(cmp<0){
                // searched key is smaller than the mid-value.
                hi = mid -1;
            }else{
                // match found
                return mid;
            }
        }
        // lo crossed the hi
        return lo;
    }

    public void put(Key key, Val val){
        // maintains the sort order.
        int i = rank(key,0,N-1);
        if(i<N && key.compareTo(keys[i])==0){
            //key already present, update its value
            values[i] = val;
        }else{
            //make space for new entry in both keys and values array at index i:
            for(int j=N-1; j>i ; j--){
                keys[j+1] = keys[j];
                values[j+1] = values[j];
            }
            keys[i] = key;
            values[i] = val;
            N++;
        }
    }

    public void delete(Key key){
        int i = rank(key,0,N-1);
        if(i<N && key.compareTo(keys[i])==0){
            //key present
            //start from i and shift all elements to left.
            for(int j=i; j<N-1; j++) {//GOTCHA j<N-1 otherwise IndexOutOfBounds exception.
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            keys[N-1] = null;
            values[N-1] = null;
            N--;
        }
    }

    // order based operations:
    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        //returns element with rank k
        // obviously the elements at index k will have k elements less than them
        return keys[k];
    }

    public Key ceiling(Key key){
        // the largest value smaller than or equal to the given key
        int i = rank(key, 0, N-1);
        if(i<N && key.compareTo(keys[i])==0){
            return key;
        }else if (i!=0){
            // i.e., key is not found
            return keys[i-1];
        }
        return null;
    }

    public Key floor(Key key){
        // the smallest value larger than or equal to the given key
        int i = rank(key, 0, N-1);
        return keys[i];
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);

        // Insert some key-value pairs
        st.put("Alice", 1);
        st.put("Bob", 2);
        st.put("Charlie", 3);
        st.put("David", 4);
        System.out.println("Ceiling for C: " + st.ceiling("C")); // Output: Bob
        System.out.println("Ceiling for Costco: " + st.ceiling("Costco")); // Output: Charlie
        System.out.println("Floor for C: " + st.floor("C")); // Output: Charlie
        System.out.println("Floor for Costco: " + st.floor("Costco")); // Output: David
    }
}
