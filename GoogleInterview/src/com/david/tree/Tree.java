package com.david.tree;

/**
 * Interface Tree defines the operations that all implementations support
 * 
 * ******************PUBLIC OPERATIONS*********************
 *
 *  void insert(x)       	--> Insert x
 *  void remove(x)       	--> Remove x
 *  Comparable find(x)   	--> Return item that matches x
 *  Comparable findMin()  	--> Return smallest item
 *  Comparable findMax()  	--> Return largest item
 *  boolean isEmpty()     	--> Return true if empty; else false
 *  void makeEmpty()      	--> Remove all items
 *  void printTree()      	--> Print tree in sorted order
 *  
 */
public interface Tree<T extends Comparable<T>> {
	
	/**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert(Comparable<T> x);
    
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove(Comparable<T> x);
    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable<T> findMin();
    
    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public Comparable<T> findMax();
    
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable<T> find(Comparable<T> x);
    
    /**
     * Make the tree logically empty.
     */
    public void makeEmpty();
    
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty();
    
    /**
     * Print the tree contents in sorted order.
     */
    public void printTree();
}
