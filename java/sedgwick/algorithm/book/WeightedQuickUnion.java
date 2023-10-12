package sedgwick.algorithm.book;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/****
 *  Compilation:  javac UF.java
 *  Execution:    java UF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 **/

public class WeightedQuickUnion {
    private final int[] id;
    private final int[] sz;
    private int count; //number of components (connected entities)
    public WeightedQuickUnion(int N){
        // initialize N sites with integer names (0 to N-1)
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i; //initial assumption is that each point is its own component.
        }
        for (int i = 0; i <N; i++) {
            sz[i]= 1;// at the start each point has size 1 since it is its own tree.
        }
    }
    /*
        Weighted Quick Union has optimized union method.
     */
    public void union(int p, int q){
        // simply attaching p to q will not work as we need to move the entire branch of p and connect to q's root.
        int rootP = find(p);
        int rootQ = find(q);
        if(rootQ == rootP){
            return; // important: to prevent cycle formation.
        }
        //compare the sizes and then attach the smaller under the larger
        if(sz[rootP]<=sz[rootQ]){
            //make the root of p point to root of q:
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];//new
        }else {
            id[rootQ]=rootP;
            sz[rootP]+=sz[rootQ];//new
        }

        // the number of components reduces by 1 here also:
        count--;
    }

    /*
    Path compression can be applied to the find() method for more optimization.
     */
    public int find(int p){
        // returns the root node site name/number.
        while(p!= id[p]){
            //PATH COMPRESSION:
            //make the node point to the grandparent node.
            id[p] = id[id[p]];
            p = id[p]; // move up the new chain.
        }
        return p;//or id[p] since both are same at the root node.
    }

    public boolean connected(int p, int q){
        //return true if p and q are in the same component
        return find(p) == find(q);
    }

    public int count(){
        // number of components
        return count;
    }
    public static void main(String[] args)
    { // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();
        // Read number of sites.
        WeightedQuickUnion uf = new WeightedQuickUnion(N);
        // Initialize N components.
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (p < 0 || q < 0) {
                break;
            }
            // Read pair to connect.
            if (uf.connected(p, q)) continue; // Ignore if connected.
            uf.union(p, q);
            // Combine components
            StdOut.println(p + " " + q);//and print connection.
        }
        StdOut.println(uf.count() + " components");
    }
}
