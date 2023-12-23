package sedgwick.algorithm.book.chapter3;

public class BST <Key extends Comparable<Key>, Val> {
    // representation of data such that the left child is less than the parent
    // and the right child is greater than the parent.

    //Using tree structure, we get logarithmic get and put time complexities.

    // In order traversal of the tree will give the elements in ascending order.

    private class Node{
        private Key key;
        private Val value;
        public Node left, right;
        private int N;//size of this tree rooted at this node.
        public Node(Key key, Val value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){ //GOTCHA private method.
        if(node==null){
            return 0;
        }else{
            return node.N;
        }
    }

    public Val get(Key key){
        return get(root, key);
    }

    private Val get(Node x, Key key){
        //recursive method implementation:
        if(x==null){
            return null;
        }
        int cmp = x.key.compareTo(key);
        if(cmp==0){
            return x.value;
        } else if (cmp<0) {
            // node x's key is less than key
            // we need to check in right side
            return get(x.right, key);
        }else {
            return get(x.left, key);
        }
    }
    public void put(Key key, Val value){
        put(root, key, value);
    }
    private void put(Node x, Key key, Val value){
        if(x==null){
            root = new Node(key, value,1);
            return;
        }
        int cmp = key.compareTo(x.key);
        if(cmp==0){
            //key is found so update the value only
            x.value = value;
        } else if (cmp<0) {
            // passed key is less than node x's key
            // need to check on left for any match
            if(x.left==null){
                x.left = new Node(key, value, 1);
            }else {
                put(x.left, key, value);
            }
        }else {
            if(x.right == null){
                x.right = new Node(key,value,1);
            }else {
                put(x.right, key, value);
            }
        }
        // update the node's size.
        // this runs after the recursive calls.
        x.N = size(x.left) + size(x.right) + 1;
    }

    //Order based operations:
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x==null){
            return null;
        }
        if(x.left==null){
            return x;
        }else {
            return min(x.left);
        }
    }

    public Key floor(Key key){
        return floor(root, key);
    }
    private Key floor(Node x, Key key){
        if(x==null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp==0){
            return key;
        }else if(cmp<0){
            // the current node's key is greater than passed key
            // move left for possible match
            if(x.left==null){
                return key;
            }else {
                return floor(x.left, key);
            }
        }else{
            // our key is greater than the current node's key
            // move right to find possible exact match
            if(x.right==null){
                return x.key;
            }else{
                return floor(x.right, key);
            }
        }
    }

    //RANK
    public int rank(Key key) {
        // Returns zero if no element smaller than key is found.
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0; // If the tree is empty, the rank is 0.
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            // If the given key is smaller than the current node's key,
            // recursively find the rank in the left subtree.
            return rank(x.left, key);
        } else if (cmp > 0) {
            // If the given key is greater than the current node's key,
            // add 1 for the current node and recursively find the rank in the right subtree.
            // i.e., the root and all left elements are smaller for sure, some from right of root can also be smaller
            return 1 + size(x.left) + rank(x.right, key);
        } else {
            // If the given key is equal to the current node's key,
            // return the size of the left subtree as the rank.
            return size(x.left);
        }
    }

    public Key select(int rank){
        return select(root, rank);
    }
    private Key select(Node x, int r){
        if(x==null){
            return null;
        }
        //NOTE: size(x.left) is the number of nodes on left side i.e., smaller than x
        int szx = size(x.left); //GOTCHA size of x is including right part too.
        if(szx==r){
            return x.key;
        } else if(szx < r){
            // check on right side
            return select(x.right, r-szx-1);//GOTCHA now the required rank should be reduced as right nodes dont know any count of the root's left nodes
        } else {
            // check on left side
            return select(x.left, r);
        }
    }

    //DELETE
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x==null){
            return null;
        }
        // min is the leftmost node of the tree.
        // Strategy: return left nodes by calling recursively.
        // Only for case when left node is null i.e., we have reach the min, return right node.
        // This will effectively remove the min node
        // as it is replaced by second highest from before delete operation.
        if(x.left==null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        // update the size after all recursion are over:
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //delete any node:
    //recursively change links by returning the nodes
    public void delete(Key key){
        root = delete(root, key);
    }
    private Node delete(Node x,Key key){
        //base case
        if(x==null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0){
            // current node's key is greater than the key passed
            // search on left for a match
            x.left = delete(x.left,key);
        }else if(cmp>0){
            // search on right
            x.right = delete(x.right,key);
        }else{
            // match found
            // when we delete this node, who else can take its place
            // since all nodes in left subtree will be smaller
            // we need the smallest element from the right subtree.
            // GOTCHA but before that we need to check if we have the left or the right subtrees
            if(x.left==null){
                // return the right part instead of returning x which will unlink x from tree.
                return x.right;
            }else if(x.right == null){
                // if no right subtree is there then the left child is the alternative to replace x.
                return x.left;
            }
            //smallest right child:
            // Error Prone!!
            Node minNode = min(x.right);
            x.key = minNode.key; // Update the key value instead of replacing the entire node
            x.right = deleteMin(x.right);
            //NOTE: no need to update left links because the left subtree is unchanged.
        }
        //update size
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    //print BST
    public void printTree() {
        printTree(root, "", true);
    }
    private void printTree(Node root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.key+"(N="+root.N+")");
            printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args){
        BST<String,Integer> tree = new BST<>();
        tree.put("g",23);
        tree.put("k",24);
        tree.put("x",78);
        tree.put("n",45);
        tree.put("v",5);
        tree.put("t",25);
        tree.put("l",54);
        tree.put("h",9);
        tree.printTree();
        System.out.println("====Deleting the min node");
        tree.deleteMin();
        tree.printTree();
        System.out.println("====Deleting the given node");
        tree.delete("k");
        tree.printTree();
    }
}
