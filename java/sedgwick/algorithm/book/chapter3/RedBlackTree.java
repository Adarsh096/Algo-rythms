package sedgwick.algorithm.book.chapter3;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;
    private class Node{
        Node left, right;
        boolean colour;
        int N;
        Key key;
        Value value;

        public Node(Key key, Value value,int N,boolean colour) {
            this.key = key;
            this.value = value;
            this.colour = colour;
            this.N = N;
        }
    }

    private boolean isRED(Node x){
        if(x==null){
            return false;
        }
        return x.colour==RED;
    }
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
    //rotation methods
    private Node rotateLeft(Node h){
        // this is required when h.right has colour=RED
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        //GOTCHA
        x.colour = h.colour;// earlier h pointed to old parent using some colour so we set the same to new root x
        h.colour = RED;// New colour after left rotation
        //GOTCHA : Size need to be readjusted
        h.N = 1+size(h.left) + size(h.right);
        return x;//since x is the new root of the subtree.
    }

    private Node rotateRight(Node h){
        // when we have h.left with RED colour
        //save the current node's left
        Node x = h.left;
        h.left = x.right;// since x's right is bigger than x and less than h.
        x.right = h;
        x.colour = h.colour;
        h.colour = RED;
        //size adjustment
        h.N = 1+size(h.left)+size(h.right);
        return x;//new root of this subtree
    }
    private void flipColours(Node h){
        // change the colour of left and right and of the link to parent
        h.colour = !h.colour;
        h.left.colour = !h.left.colour;
        h.right.colour = !h.right.colour;
    }

    //PUT Operation:
    public void put(Key key, Value value){
        root =  put(root,key, value);
        //GOTCHA : Need to make the root's colour black since nothing is above it so default is black.
        root.colour = BLACK;
    }
    private Node put(Node h, Key key, Value value){
        if(h==null){
            //we just add a new node with default values
            return new Node(key,value,1,RED);
        }
        //basic BST insert code:
        int cmp = key.compareTo(h.key);
        if(cmp<0){
            //passed key is smaller so add to left
            h.left = put(h.left,key,value);
        }else if(cmp > 0) {
            //add to right
            h.right = put(h.right, key, value);
        }else {
            h.key = key;
            h.value = value;
        }
        // red-black tree logic:
        //rotate left the middle node of a consecutive red right leaning node
        if(isRED(h.right) && !isRED(h.left)){
            h = rotateLeft(h); //GOTCHA save the returned node
        }
        // then rotate the parent node to the right so that we have red links on both left and right
        if(isRED(h.left) && isRED(h.left.left)){
            h = rotateRight(h);
        }
        // then flip colours
        if(isRED(h.left) && isRED(h.right)){
            flipColours(h);
        }
        //size calc
        h.N = 1 + size(h.left)+size(h.right);
        return h;
    }

    //DELETE
    //TODO
}
