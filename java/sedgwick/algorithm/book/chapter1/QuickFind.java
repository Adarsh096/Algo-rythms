package sedgwick.algorithm.book.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/*
Quick find has quadratic time complexity.
It is not good for large problems.
 */
public class QuickFind {
    private int[] id;
    private int count; //number of components (connected entities)
    public QuickFind(int N){
        // initialize N sites with integer names (0 to N-1)
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i; //initial assumption is that each point is its own component.
        }
    }
    public void union(int p, int q){
        //add connection between p and q only if p and q are not connected either indirectly or directly.
        int idP = find(p);
        int idQ = find(q);
        if(idP == idQ){
            return;
        }
        //otherwise make every occurrence of id of p same as id of q
        for(int i=0; i<id.length;i++){
            if(find(id[i])==idP){
                id[i]=idQ;
            }
        }
        //very imp: now the number of components will reduce by one!
        count --;
    }

    public int find(int p){
        // component identifier for p (0 to N-1)
       return id[p];
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
        QuickFind uf = new QuickFind(N);
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
