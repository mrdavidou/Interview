package com.david.tree.binary;


/** 
 * Class BinaryTree implements an unbalanced binary search tree with minimal functionality: insert, makeEmpty and printTree
 */
public class BinaryTree<T extends Comparable<T>> {
	/** The tree overallRoot. */
    private BinaryNode<T> overallRoot;
    
    /**
     * Construct the tree.
     */
    public BinaryTree() {
        overallRoot = null;
    }

    public BinaryNode<T> getOverallRoot() {
		return overallRoot;
	}

	public void insert(Comparable<T> x) {
        overallRoot = insert(x, overallRoot);
    }

    public void makeEmpty() {
        overallRoot = null;
    }

    public boolean isEmpty() {
        return overallRoot == null;
    }
    
    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree");
        else
            printTree(overallRoot);
    }

    /**
     * Private helper method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that overallRoots the tree.
     * @return the new overallRoot.
     */
    @SuppressWarnings("unchecked")
	private BinaryNode<T> insert(Comparable<T> x, BinaryNode<T> t) {
    	if (t == null) {
    		t = new BinaryNode<T>(x, null, null);
    	} else if(x.compareTo((T) t.data) < 0) {
    		t.left = insert(x, t.left);
    	} else if(x.compareTo((T) t.data) > 0) {
    		t.right = insert(x, t.right);
    	} else {
    		// Duplicate; do nothing  												
    	}
    	return t;
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
}
