package sedgwick.algorithm.book.chapter1;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }

    private void resize(int newMax){
        Item[] temp = (Item[]) new Object[newMax];
        for(int i = 0; i< N; i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item){
        if(N==a.length){
            resize(2*N);
        }
        a[N++] = item;// N++ since N starts from 0
    }
    public Item pop(){
        Item item = a[--N];
        // shrink the stack if required:
        a[N] = null;//***imp
        if(N>0 && N==a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }


    // inner class
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;//size of the array
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
