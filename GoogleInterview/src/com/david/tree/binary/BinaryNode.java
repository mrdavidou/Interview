package com.david.tree.binary;

import org.eclipse.jdt.annotation.Nullable;

/** 
 * BinaryNode is the basic node structure stored in unbalanced binary search trees
 */
public class BinaryNode<T> {
	public Comparable<T> data;
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    
    public BinaryNode(@Nullable Comparable<T> value) {
        this(value, null, null);
    }

    public BinaryNode(@Nullable Comparable<T> value, @Nullable BinaryNode<T> leftNode, @Nullable BinaryNode<T> rightNode) {
        this.data = value;
        this.left = leftNode;
        this.right = rightNode;
    }

	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BinaryNode)) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		BinaryNode<T> other = (BinaryNode<T>) obj;
		
		if (data == null && other.data != null || !data.equals(other.data)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return data.toString();
	}
    
}
