package com.deepagar.problems.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Traverse {
	
	public static void print(Node node)
	{
		if (null == node)
			return;
			
		print(node.left);
		
		System.out.println(node.key);

		print(node.right);
	}

	public static void traverseInOrder(Node ptr)
	{
		if(null != ptr)
		{
			traverseInOrder(ptr.left);
			System.out.println(ptr.key);
			traverseInOrder(ptr.right);
			
		}
	}
	
	public static void traversePreOrder(Node ptr)
	{
		if(null != ptr)
		{
			System.out.println(ptr.key);
			traversePreOrder(ptr.left);
			traversePreOrder(ptr.right);
			
		}
	}
	
	public static void traversePostOrder(Node ptr)
	{
		if(null != ptr)
		{
			traversePostOrder(ptr.left);
			traversePostOrder(ptr.right);
			System.out.println(ptr.key);
		}
	}
	
	public static void traverseLevelOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	 
	    Queue<Node> nodes = new LinkedList<>();
	    nodes.add(root);
	 
	    while (!nodes.isEmpty()) {
	 
	        Node node = nodes.remove();
	 
	        System.out.print(" " + node.key);
	 
	        if (node.left != null) {
	            nodes.add(node.left);
	        }
	 
	        if (node.right!= null) {
	            nodes.add(node.right);
	        }
	    }
	}
	
	public static String pathSeparator = " -> ";
	
	public static String createPath(String parentPath, int key) {
		if(parentPath.isEmpty())
			return Integer.toString(key);
		
		String curPath = parentPath + pathSeparator + key;
		return curPath;
		
	}
	
	public static void printPathToLeafRecurse(Node ptr, String parentPath) {
		
		if(null != ptr) {
			String curPath = createPath(parentPath, ptr.key);
			
			if(null == ptr.left &&  null == ptr.right)
				System.out.println(curPath);
			
			if(null != ptr.left)
				printPathToLeafRecurse(ptr.left, curPath);
			
			if(null != ptr.right)
				printPathToLeafRecurse(ptr.right, curPath);
		}
	}
	
	public static void printPathToLeaf(Node root) {
	    if (root == null) {
	        return;
	    }
	 
	    Queue<Node> nodes = new LinkedList<>();
	    HashMap<Node, String> nodePathMap = new HashMap<Node, String>();
	    
	    nodes.add(root);
	    nodePathMap.put(root, createPath("", root.key));
	 
	    while (!nodes.isEmpty()) {
	 
	        Node node = nodes.remove();
	        String path = nodePathMap.get(node);
	        nodePathMap.remove(node);
	        
	        if (node.left == null && node.right == null) {
	        	System.out.println(path);
	        	continue;
	        }
	 
	        if (node.left != null) {
	            nodes.add(node.left);
	            nodePathMap.put(node.left, createPath(path, node.left.key));
	        }
	 
	        if (node.right!= null) {
	            nodes.add(node.right);
	            nodePathMap.put(node.right, createPath(path, node.right.key));
	        }
	    }
	}

	
}

