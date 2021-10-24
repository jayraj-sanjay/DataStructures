// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    /** New Methods Implemented Below
    */
    public int nodeCount()
    {
        return nodeCount(root);
    }
    private int nodeCount( BinaryNode<AnyType> node )
    {
        if(node == null)
            return 0;
        else
            return 1 + nodeCount(node.left) + nodeCount(node.right);
    }
    public boolean isFull()
    {
        return isFull(root);
    }
    private boolean isFull(BinaryNode<AnyType> node)
    {
        if(node.left == null && node.right == null)
            return true;
        else if(node.left != null && node.right != null)
            return isFull(node.left) && isFull(node.right);
        else
            return false;
    }
    public boolean compareStructure(BinarySearchTree<AnyType> u)
    {
        if(this.root == null && u.root == null)
            return true;
        else
            return compareStructure(this.root, u.root);
    }
    private boolean compareStructure(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
    {
        if(node1 == null && node2 == null)
            return true;
        else if(node1 != null && node2 != null)
            return (compareStructure(node1.left, node2.left) && compareStructure(node1.right, node2.right));
        else
            return false;
    }
    public boolean isEqual(BinarySearchTree<AnyType> v)
    {
        if(this.root == null && v.root == null)
            return true;
        else
            return isEqual(this.root, v.root);
    }
    private boolean isEqual(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
    {
        if(node1 == null && node2 == null)
            return true;
        else if(node1 != null && node2 != null)
            return (node1.element == node2.element && isEqual(node1.left, node2.left) && isEqual(node1.right, node2.right));
        else
            return false;
    }
    public BinarySearchTree<AnyType> makeCopy()
    {
        BinarySearchTree<AnyType> newRoot = new BinarySearchTree<>();
        newRoot.root = makeCopy(this.root);
        return newRoot;
    }
    private BinaryNode<AnyType> makeCopy(BinaryNode<AnyType> node)
    {
        if(node == null)
            return null;
        else
            return new BinaryNode<AnyType>(node.element, makeCopy(node.left), makeCopy(node.right));
    }
    public BinarySearchTree<AnyType> mirrorTree()
    {
        BinarySearchTree<AnyType> mirroredRoot = new BinarySearchTree<>();
        mirroredRoot.root = mirrorTree(this.root);
        return mirroredRoot;
    }
    private BinaryNode<AnyType> mirrorTree(BinaryNode<AnyType> node)
    {
        if(node == null)
            return null;
        else
            return new BinaryNode<AnyType>(node.element, mirrorTree(node.right), mirrorTree(node.left));
    }
    public boolean isMirror(BinarySearchTree<AnyType> t2)
    {
        if(this.root == null && t2.root == null)
            return true;
        else
            return isMirror(this.root, t2.root);
    }
    private boolean isMirror(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
    {
        if(node1 == null && node2 == null)
            return true;
        else if(node1 != null && node2 != null)
            return (isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left));
        else
            return false;
    }

    private boolean search(AnyType x, BinaryNode<AnyType> t,boolean isLeft)
    {
        if (t == null)
            return false;
        int compareResult = x.compareTo( t.element );
        if (compareResult == 0)
        {
            if (isLeft){
                root = rotateLeft(root);
            }else {
                root = rotateRight(root);
            }
            return true;
        }

        if (compareResult > 0)
        {
            compareResult = x.compareTo( t.right.element);
            if( compareResult == 0 )
            {
                if (isLeft){
                    t.right = rotateLeft(t.right);
                }else {
                    t.right = rotateRight(t.right);
                }
                return true;
            }
            else //if( compareResult > 0 )
                return search(x, t.right,isLeft);
        }
        else // if (compareResult < 0)
        {
            compareResult = x.compareTo( t.left.element);
            if( compareResult == 0 )
            {
                if (isLeft){
                    t.left = rotateLeft(t.right);
                }else {
                    t.left = rotateRight(t.right);
                }
                return true;
            }
            else //if( compareResult > 0 )
                return search(x, t.left,isLeft);
        }
    }
    public void rotateLeft(AnyType x)
    {
        if(search(x, this.root,true))
            System.out.println(this);
        else
            System.out.println("there is no rotation");
    }
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t)
    {
        BinaryNode<AnyType> s = t.right;
        t.right = s.left;
        s.left = t;
        return s;
    }
    public void rotateRight(AnyType x)
    {
        if(search(x, this.root,false))
            System.out.println(this);
        else
            System.out.println("there is no rotation");
    }
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t)
    {
        BinaryNode<AnyType> s = t.left;
        t.left = s.right;
        s.right = t;
        return s;
    }
    public void printLevels( )
    {
        Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
        queue.add(root);
        List<List<AnyType>> list = new ArrayList<>();
        while(!queue.isEmpty())
        {
            int size = queue.size();
            List<AnyType> l = new ArrayList<>();
            for(int i=0;i< size;i++){
                BinaryNode<AnyType> node = queue.poll();
                l.add(node.element);
                if(node.left != null){
                    queue.add(node.left);
                                    }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
//            System.out.print(node.element+ " ");
//            if(node.left != null)
//                queue.add(node.left);
//            if(node.right != null)
//                queue.add(node.right);
            list.add(l);
        }
        System.out.println("Printing levels :");
        System.out.println(list);
    }
    public String toString()
    {
        if( isEmpty( ) )
            return "Tree is Empty";
        else
            return "In-order: " + inOrderTreeToString(root) + "\n" + "pre-order: " + preOrderTreeToString(root);

    }
    private String inOrderTreeToString( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            return inOrderTreeToString( t.left ) + " " + t.element.toString() + " " + inOrderTreeToString( t.right );
        }
        return "";
    }
    private String preOrderTreeToString( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            return t.element.toString() + " " + preOrderTreeToString( t.left ) + " " + preOrderTreeToString( t.right );
        }
        return "";
    }
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
        // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


    /** The tree root. */
    private BinaryNode<AnyType> root;


    // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
//        final int NUMS = 4000;
//        final int GAP  =   37;
//
//        System.out.println( "Checking... (no more output means success)" );
//
//        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
//            t.insert( i );
//
//        for( int i = 1; i < NUMS; i+= 2 )
//            t.remove( i );
//
//        if( NUMS < 40 )
//            t.printTree( );
//        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
//            System.out.println( "FindMin or FindMax error!" );
//
//        for( int i = 2; i < NUMS; i+=2 )
//            if( !t.contains( i ) )
//                System.out.println( "Find error1!" );
//
//        for( int i = 1; i < NUMS; i+=2 )
//        {
//            if( t.contains( i ) )
//                System.out.println( "Find error2!" );
//        }
        final int []array = {33, 20, 30, 40, 50, 41, 31,  21, 11};
        for( int i = 0; i < 9; i++ )
        {
            t.insert( array[i] );
        }
        System.out.println("Printing tree t: ");
        System.out.println(t);
        System.out.println("Node count for tree t is: " + t.nodeCount());
        System.out.println("Is the tree t full?: "+t.isFull());
        BinarySearchTree<Integer> u = new BinarySearchTree<>( );
        final int[] array1 = {43, 30, 40, 50, 60, 51, 41,  31, 21};
        for( int i = 0; i < 9; i++ )
        {
            u.insert(array1[i]);
        }
        System.out.println("Printing Tree u:");
        System.out.println(u);
        System.out.println("Comparing tree structure t and u: " + t.compareStructure(u));
        BinarySearchTree<Integer> v = new BinarySearchTree<>( );
        for( int i = 0; i < 9; i++ )
        {
            v.insert(array[i]);
        }
        System.out.println("Printing tree v: ");
        System.out.println(v);
        System.out.println("Checking if tree t equal tree v: "+t.isEqual(v));
        System.out.println("Copying tree t and printing the new tree: ");
        System.out.println(t.makeCopy());
        BinarySearchTree<Integer> mirroredTree = t.mirrorTree();
        System.out.println("Printing mirroredTree from t");
        System.out.println(mirroredTree);
        System.out.println("Rotating the tree right at element 30: ");
        t.rotateRight(30);
        System.out.println("Rotating the tree left at element 30: ");
        t.rotateLeft(30);
        t.printLevels();
    }
}
