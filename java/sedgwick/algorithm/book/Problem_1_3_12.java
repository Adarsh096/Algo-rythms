package sedgwick.algorithm.book;

import java.util.Iterator;
import java.util.function.Consumer;

public class Problem_1_3_12 {
    /*
    Write an iterable Stack client that has a static method copy() that takes a stack
    of strings as argument and returns a copy of the stack. Note : This ability is a prime
    example of the value of having an iterator, because it allows development of such
    functionality without changing the basic API.
     */
    public static void main(String[] args){
        IterableStack<String> stack = new IterableStack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        stack.push("G");
        stack.push("H");
        stack.push("I");
        stack.push("J");
        stack.push("K");
        stack.push("L");
        stack.push("I");
        IterableStack<String> stackCopy = IterableStack.staticCopy(stack);
        System.out.println("size: "+stackCopy.size());
        System.out.println("current capacity of stack: "+stackCopy.maxCurrentSize());
        System.out.println("Popped: "+stack.pop());
        for(String entry: stackCopy){
            System.out.println("=> "+entry);
        }
    }
}

class IterableStack<T> implements Iterable<T>{
    T[] arr = (T[]) new Object[10];//initial capacity 10.
    int N = 0;

    public void push(T t){
        if(N==arr.length){
            //resize array
            int newSize = 2* arr.length;
            resizeArr(newSize);
        }
        arr[N++] = t;
    }
    private void resizeArr(int newSize){
        T[] temp = (T[]) new Object[newSize];
        for(int i=0; i< arr.length; i++){
            temp[i] = arr[i];
        }
        arr = temp;//name of arrays are pointers to location.
    }

    public T pop(){
        T t = null;
        if(N>0){
            t = arr[N - 1];//last elm
            arr[N - 1] = null;
            N--;
        }
        return t;
    }
    public IterableStack<T> copy(IterableStack<T> stack){
        IterableStack<T> temp = new IterableStack<>();
        IterableStack<T> copy = new IterableStack<>();

        // temp will be reverse stack:
        for(T t : stack){
            temp.push(t);
        }
        // copy will have the same order as stack arg:
        for(T t: temp){
            copy.push(t);
        }
        return copy;
    }

    //using a different generic variable name allows creating static methods:
    //NOTICE : additional <U> to introduce a new generic type U.
    public static <U> IterableStack<U> staticCopy(IterableStack<U> stack){
        IterableStack<U> temp = new IterableStack<>();
        IterableStack<U> copy = new IterableStack<>();

        // temp will be reverse stack:
        for(U u : stack){
            temp.push(u);
        }
        // copy will have the same order as stack arg:
        for(U u: temp){
            copy.push(u);
        }
        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator<T>();
    }

    public int size() {
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int maxCurrentSize(){
        return arr.length;
    }
    class StackIterator<T> implements Iterator<T>{
        private int itrPos = N;
        @Override
        public boolean hasNext() {
            return itrPos>0;
        }

        @Override
        public T next() {
            //return (T) arr[itrPos--]; //MISTAKE: This will print null first and miss the last elm of stack.
            /*
            Reason: N is the count of elements where as we use index for accessing array elements.
             */
            return (T) arr[--itrPos];
        }
    }
}