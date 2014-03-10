package com.david.tree.binary;

import com.david.tree.Tree;

/** 
 * Class BinarySearchTree implements an unbalanced binary search tree.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
	/** The tree overallRoot. */
    private BinaryNode<T> overallRoot;
    
    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        overallRoot = null;
    }

    public BinaryNode<T> getOverallRoot() {
		return overallRoot;
	}

	@Override
	public void insert(T x) {
        overallRoot = insert(x, overallRoot);
    }

    @Override
    public void remove(T x) {
        overallRoot = remove(x, overallRoot);
    }

    @Override
    public T findMin() {
        return dataAt(findMin(overallRoot));
    }

    @Override
    public T findMax() {
        return dataAt(findMax(overallRoot));
    }

    @Override
    public T find(T x) {
        return dataAt(find(x, overallRoot));
    }

    @Override
    public void makeEmpty() {
        overallRoot = null;
    }

    @Override
    public boolean isEmpty() {
        return overallRoot == null;
    }

    @Override
    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree");
        else
            printTree(overallRoot);
    }

    /**
     * Private helper method to get data field.
     * @param t the node.
     * @return the data field or null if t is null.
     */
	private T dataAt(BinaryNode<T> t) {
        return (T) (t == null ? null : t.data);
    }

    /**
     * Private helper method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that overallRoots the tree.
     * @return the new overallRoot.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
    	if (t == null) {
    		t = new BinaryNode<T>(x, null, null);
    	} else if(x.compareTo((T) t.data) < 0) {
    		t.left = insert(x, t.left);
    	} else if(x.compareTo((T) t.data) > 0) {
    		t.right = insert(x, t.right);
    	} else {
    		;  												// Duplicate; do nothing
    	}
    	return t;
    }

    /**
     * Private helper method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that overallRoots the tree.
     * @return the new overallRoot.
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if(t == null) {
        	// Item not found; do nothing
            return t;   									
        } if (x.compareTo((T) t.data) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo((T) t.data) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { 		
        	// If two children, replace with either left/right
            t.data = findMin(t.right).data;
            t.right = remove((T) t.data, t.right);
        } else {
        	// If only one child, replace it with child
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    /**
     * Private helper method to find the smallest item in a subtree.
     * @param t the node that overallRoots the tree.
     * @return node containing the smallest item.
     */
    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if(t == null) {
            return null;
        } else if(t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * Private helper method to find the largest item in a subtree.
     * @param t the node that overallRoots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if(t != null) {
            while(t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * Private helper method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that overallRoots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode<T> find(T x, BinaryNode<T> t) {
        if(t == null) {
            return null;
        } if(x.compareTo((T) t.data) < 0) {
        	return find(x, t.left);
        } else if(x.compareTo((T) t.data) > 0) {
            return find(x, t.right);
        } else {
            return t;    									// Match
        }
    }

    /**
     * Private helper method to print a subtree in sorted order.
     * @param t the node that overallRoots the tree.
     */
    private void printTree(BinaryNode<T> t) {
        if(t != null) {
            printTree(t.left);
            System.out.println(t.data);
            printTree(t.right);
        }
    }

    // Test program
    public static void main(String [ ] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println("Checking... (no more output means success)");

        for(int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(new Integer(i));

        for(int i = 1; i < NUMS; i+= 2)
            t.remove(new Integer(i));

        if(((Integer)(t.findMin())).intValue() != 2 ||
            ((Integer)(t.findMax())).intValue() != NUMS - 2)
            System.out.println("FindMin or FindMax error!");

        for(int i = 2; i < NUMS; i+=2)
             if(((Integer)(t.find(new Integer(i)))).intValue() != i)
                 System.out.println("Find error1!");

        for(int i = 1; i < NUMS; i+=2)
        {
            if(t.find(new Integer(i)) != null)
                System.out.println("Find error2!");
        }
    }
}
