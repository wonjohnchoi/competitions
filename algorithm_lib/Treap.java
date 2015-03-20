import java.util.*;

// Treap class
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is found
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// http://users.cis.fiu.edu/~weiss/dsaajava2/code/Treap.java
public class Treap {
    TreapNode<Node> root;
    TreapNode<Node> nullNode;
    public Treap() {
	nullNode = new TreapNode<Node>(null);
	nullNode.left = nullNode.right = nullNode;
	nullNode.priority = Integer.MAX_VALUE;
	root = nullNode;
    }
    public void insert(Node x) {
	root = insert(x, root);
    }
    public void remove(Node x) {
	root = remove(x, root);
    }
    public Node findMin() {
	if(isEmpty()) throw new RuntimeException("Treap is empty");
	TreapNode<Node> ptr = root;
	while(ptr.left != nullNode)
	    ptr = ptr.left;
	return ptr.element;
    }
    public Node findMax() {
	if(isEmpty()) throw new RuntimeException("Treap is empty");
	TreapNode<Node> ptr = root;
	while(ptr.right != nullNode)
	    ptr = ptr.right;
	return ptr.element;
    }
    public boolean contains(Node x) {
	TreapNode<Node> current = root;
	nullNode.element = x;
        while (true) {
            int compareResult = x.compareTo(current.element);
            if (compareResult < 0)
                current = current.left;
            else if (compareResult > 0 )
                current = current.right;
            else
                return current != nullNode;
        }
    }
    public void makeEmpty() {
	root = nullNode;
    }
    public boolean isEmpty() {
	return root == nullNode;
    }
    private TreapNode<Node> insert(Node x, TreapNode<Node> t) {
	if( t == nullNode )
	    return new TreapNode<Node>( x, nullNode, nullNode );
	int compareResult = x.compareTo( t.element );
	if( compareResult < 0 ) {
            t.left = insert( x, t.left );
            if( t.left.priority < t.priority )
                t = rotateWithLeftChild( t );
        } else if( compareResult > 0  ) {
            t.right = insert( x, t.right );
            if( t.right.priority < t.priority )
                t = rotateWithRightChild( t );
        }
	return t;
    }
    private TreapNode<Node> remove( Node x, TreapNode<Node> t ) {
        if( t != nullNode ) {
            int compareResult = x.compareTo( t.element );
            if( compareResult < 0 )
                t.left = remove( x, t.left );
            else if( compareResult > 0 )
                t.right = remove( x, t.right );
            else {
                // Match found
                if( t.left.priority < t.right.priority )
                    t = rotateWithLeftChild( t );
                else
                    t = rotateWithRightChild( t );
                if( t != nullNode )     // Continue on down
                    t = remove( x, t );
                else
                    t.left = nullNode;  // At a leaf
            }
        }
	return t;
    }
    private TreapNode<Node> rotateWithLeftChild( TreapNode<Node> k2 )
    {
	TreapNode<Node> k1 = k2.left;
	k2.left = k1.right;
	k1.right = k2;
	return k1;
    }
    private TreapNode<Node> rotateWithRightChild( TreapNode<Node> k1 )
    {
	TreapNode<Node> k2 = k1.right;
	k1.right = k2.left;
	k2.left = k1;
	return k2;
    }
    private static class TreapNode<Node>
    {
        Node element;      // The data in the node
        TreapNode<Node> left;         // Left child
        TreapNode<Node> right;        // Right child
        int priority;     // Priority
	private static Random randomObj = new Random();
	// Constructors
	TreapNode( Node theElement ) {
            this( theElement, null, null );
        }
	TreapNode( Node theElement, TreapNode<Node> lt, TreapNode<Node> rt ) {
            element  = theElement;
            left     = lt;
            right    = rt;
            priority = randomObj.nextInt();
        }
    }
    static class Node implements Comparable<Node> {
        int val;
        Node(int val) {
            this.val = val;
        }
        public int compareTo(Node n) {
            return Integer.compare(val, n.val);
        }
    }
    // Test program
    public static void main( String [ ] args )
    {
	Treap t = new Treap( );
	final int NUMS = 40000;
	final int GAP  =   307;

	System.out.println( "Checking... (no bad output means success)" );

	for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
	    t.insert(new Node(i));
	System.out.println( "Inserts complete" );

	for( int i = 1; i < NUMS; i+= 2 )
	    t.remove(new Node(i));
	System.out.println( "Removes complete" );

	if( t.findMin().val != 2 || t.findMax().val != NUMS - 2 )
	    System.out.println( "FindMin or FindMax error!" );

	for( int i = 2; i < NUMS; i+=2 )
	    if( !t.contains(new Node(i)) )
		System.out.println( "Error: find fails for " + i );

	for( int i = 1; i < NUMS; i+=2 )
	    if( t.contains(new Node(i)) )
		System.out.println( "Error: Found deleted item " + i );
    }
}
