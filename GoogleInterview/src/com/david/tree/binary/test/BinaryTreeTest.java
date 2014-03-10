package com.david.tree.binary.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.david.annotation.MetaInfo;
import com.david.tree.binary.BinaryNode;
import com.david.tree.binary.BinarySearchTree;
import com.david.tree.binary.util.BinaryTreeUtil;

public class BinaryTreeTest {
	BinaryTreeUtil<Integer> mTreeUtil;
	BinarySearchTree<Integer> mTree;
	List<List<BinaryNode<Integer>>> mExpected;
	
	@Before
	public void setUp() {
		mTreeUtil = new BinaryTreeUtil<Integer>();
		mTree = new BinarySearchTree<Integer>();
		mTree.insert(5);
		mTree.insert(2);
		mTree.insert(7);
		mTree.insert(4);
		mTree.insert(10);
		mTree.insert(1);
		mTree.insert(3);
		mTree.insert(15);
		mExpected = new ArrayList<List<BinaryNode<Integer>>>();
		mExpected.add(new ArrayList<BinaryNode<Integer>>(Arrays.asList(new BinaryNode<Integer>(5))));
		mExpected.add(new ArrayList<BinaryNode<Integer>>(Arrays.asList(new BinaryNode<Integer>(2), new BinaryNode<Integer>(7))));
		mExpected.add(new ArrayList<BinaryNode<Integer>>(Arrays.asList(new BinaryNode<Integer>(1), new BinaryNode<Integer>(4), new BinaryNode<Integer>(10))));
		mExpected.add(new ArrayList<BinaryNode<Integer>>(Arrays.asList(new BinaryNode<Integer>(3), new BinaryNode<Integer>(15))));
	}
	
	@After
	public void finish() {
		mTreeUtil = null;
		mTree = null;
		mExpected = null;
		System.out.println();
	}
	
	@Test
	public void testDepthFirst() {
		System.out.println("Depth First Test");
		List<List<BinaryNode<Integer>>> actual = mTreeUtil.depthFirstLevelList(mTree.getOverallRoot());
		System.out.println("Actual: " + actual);
		System.out.println("Expected: " + mExpected);
		assertEquals(mExpected, actual);
	}
	
	@Test
	public void testBreadthFirst() {
		System.out.println("Breadth First Test");
		List<List<BinaryNode<Integer>>> actual =  mTreeUtil.breadthFirstLevelList(mTree.getOverallRoot());
		System.out.println("Actual: " + actual);
		System.out.println("Expected: " + mExpected);
		assertEquals(mExpected, actual);
	}
	
	@Test
	public void testNull() {
		BinaryNode<Integer> nullNode = new BinaryNode<Integer>(null);
		BinaryNode<Integer> nonNullNode = new BinaryNode<Integer>(5);
		assertFalse(nonNullNode.equals(nullNode));
	}
	
	private void logAnnotations() {
		Class<BinaryTreeUtil> obj = BinaryTreeUtil.class;
		for (Method method : obj.getDeclaredMethods()) {
			// Process @TesterInfo
			if (method.isAnnotationPresent(MetaInfo.class)) {
		 
				Annotation annotation = method.getAnnotation(MetaInfo.class);
				MetaInfo metaInfo = (MetaInfo) annotation;
		 
				System.out.printf("%nProblem: %s", method.getName());
				System.out.printf("%nPriority: %s", metaInfo.priority());
				System.out.printf("%nDifficulty: %s", metaInfo.difficulty());
				System.out.printf("%nCreatedBy: %s", metaInfo.createdBy());
				System.out.printf("%nTags: {");
		 
				int tagLength = metaInfo.tags().length;
				for (String tag : metaInfo.tags()) {
					if (tagLength > 1) {
						System.out.print(tag + ", ");
					} else {
						System.out.print(tag);
					}
					tagLength--;
				}
				System.out.printf("}");
		 
				System.out.printf("%nLastModified: %s%n", metaInfo.lastModified());
			}
		}
	}
}
