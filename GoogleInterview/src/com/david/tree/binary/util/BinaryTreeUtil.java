package com.david.tree.binary.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import com.david.annotation.MetaInfo;
import com.david.annotation.MetaInfo.Priority;
import com.david.tree.binary.BinaryNode;

public class BinaryTreeUtil<T extends Comparable<T>> {
	Map<Integer, List<BinaryNode<T>>> mNodeLevelsMap = new TreeMap<Integer, List<BinaryNode<T>>>();
	
	/**
	 * Perform standard breadth-first traversal of binary tree given the root node and store traversal order in list 
	 * of lists, where the nodes are indexed based on their level within the tree structure.
	 * @param root BinaryNode<T>
	 * @return List<List<BinaryNode<T>>>
	 */
	@MetaInfo (
			priority = Priority.MEDIUM,
			difficulty = 4,
			tags = { "Breadth-First Traversal" }
	)
	public List<List<BinaryNode<T>>> breadthFirstLevelList(BinaryNode<T> root) {
		// Master list
		List<List<BinaryNode<T>>> masterList = new ArrayList<List<BinaryNode<T>>>();
		
		// Grab the BF traversal ordered list
		List<BinaryNode<T>> nodesList = breadthFirstList(root);
		
		// Initialize level at 0, increment after each successive level
		int level = 0;
		int i = 0;
		while (i < nodesList.size()) {
			// Number of nodes at level is determined by taking 2^level
			int numNodesAtLevel = (int) Math.pow(2, level);
			
			// Offset start index by 1 and add number of nodes at level to get end index
			int startIndex = numNodesAtLevel - 1;
			int endIndex = startIndex + numNodesAtLevel;
			
			// Store current level's list of nodes
			List<BinaryNode<T>> currentList = new ArrayList<BinaryNode<T>>();
			for (int j = startIndex; j < endIndex && j < nodesList.size(); j++, i++) {
				// Add all non-null values to the list
				BinaryNode<T> node = nodesList.get(j);
				if (node != null) {
					currentList.add(node);
				}
			}
			// Add all non-empty lists to the master list
			if (!currentList.isEmpty()) {
				masterList.add(currentList);
			}
			level++;
		}
		return masterList;
	}
	
	/**
	 * Perform standard breadth-first traversal of binary tree given the root node and store traversal order in list
	 * @param root BinaryNode<T>
	 * @return List<BinaryNode<T>>
	 */
	@MetaInfo (
			priority = Priority.HIGH,
			difficulty = 2,
			tags = { "Breadth-First Traversal" }
	)
	public List<BinaryNode<T>> breadthFirstList(BinaryNode<T> root) {
		// Result list that preserves the entire tree structure as we queue and enqueue nodes from the queue
		List<BinaryNode<T>> nodesList = new ArrayList<BinaryNode<T>>();
		Queue<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>() ;
	    if (root != null) {
		    queue.add(root);
		    while(!queue.isEmpty()){
		    	BinaryNode<T> node = queue.poll();
		    	// Add node to list, then proceed to recursively queue the current node's children
		    	nodesList.add(node);
		    	if (node != null) {
		    		queue.offer(node.left);
			    	queue.offer(node.right);
		    	}
		    }
	    }
	    return nodesList;
	}
	
	/**
	 * Depth-first traversal (pre-order traversal using recursion instead of pushing onto a stack)
	 * @param root BinaryNode<T>
	 * @return 
	 */
	@MetaInfo (
			priority = Priority.MEDIUM,
			difficulty = 3,
			tags = { "Depth-First Traversal" }
	)
	public List<List<BinaryNode<T>>> depthFirstLevelList(BinaryNode<T> overallRoot) {
		mNodeLevelsMap.clear();
		depthFirstList(overallRoot, 0);
		List<List<BinaryNode<T>>> nodesList = new ArrayList<List<BinaryNode<T>>>();
		for (int level : mNodeLevelsMap.keySet()) {
			nodesList.add(mNodeLevelsMap.get(level));
		}
		return nodesList;
	}
	
	@MetaInfo (
			priority = Priority.MEDIUM,
			difficulty = 3,
			tags = { "Depth-First Traversal" }
	)
	private void depthFirstList(BinaryNode<T> node, int level) {
		if (node != null) {
			List<BinaryNode<T>> currentList = mNodeLevelsMap.containsKey(level) ? mNodeLevelsMap.get(level) : new ArrayList<BinaryNode<T>>();
			currentList.add(node);
			mNodeLevelsMap.put(level, currentList);
			depthFirstList(node.left, level + 1);
			depthFirstList(node.right, level + 1);
		}
	}
}
