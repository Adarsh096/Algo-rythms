package sedgwick.algorithm.book;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
    private int[] id;
    private int count; //number of components (connected entities)
    public QuickUnion(int N){
        // initialize N sites with integer names (0 to N-1)
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i; //initial assumption is that each point is its own component.
        }
    }
    /*
        Quick Union has optimized union and find methods.
        Worst case: we need to call union N-1 times.
        Each union calls find() twice which eventually will need to traverse
        the entire tree if we have just a single branched tree.
        Over all this has time complexity little faster than quadratic.
        Improvement of this algorithm is weighted quick union.
     */
    public void union(int p, int q){
        // simply attaching p to q will not work as we need to move the entire branch of p and connect to q's root.
        int rootP = find(p);
        int rootQ = find(q);
        if(rootQ == rootP){
            return; // important: to prevent cycle formation.
        }
        //make the root of p point to root of q:
        id[rootP] = rootQ;
        // the number of components reduces by 1 here also:
        count--;
    }

    public int find(int p){
        // returns the root node site name/number.
        while(p!= id[p]){
            p = id[p]; // move up the chain.
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
        QuickUnion uf = new QuickUnion(N);
        // Initialize N components.
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            // Read pair to connect.
            if (uf.connected(p, q)) continue; // Ignore if connected.
            uf.union(p, q);
            // Combine components
            StdOut.println(p + " " + q);//and print connection.
        }
        StdOut.println(uf.count() + " components");
    }
}
