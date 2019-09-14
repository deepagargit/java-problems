package com.deepagar.problems.tree;

import java.util.*;


class SerealizeTree {
	/* Binary Tree -> N <-2 <- 1 -> 3 -> N
	 * Header: id (2 bytes), size (4 bytes - total size), type (2 bytes - e.g. tree), algorithm (1 byte), node-offset (4 byte)
	 * header extension feasible due to node-offset
	 * Node: node-count (4 bytes), [offset-left (4 bytes), offset-right (4 bytes), offset-data (4 bytes)] 
	 * Data: data-count (4 bytes), [type(1 byte e.g. int), size(1 byte), data (size bytes)] 
	 */
}

class BinaryTree
{
	Node root;

	public BinaryTree()
	{
		root = null;
	}

	public BinaryTree(int item)
	{
		root = new Node(item);
	}
	
	public String serialize() {
		if(root == null)
			return "";
		
		StringBuffer sb = new StringBuffer();
		LinkedList<Node> queue = new LinkedList<Node>();
		
		if(null != root)
			queue.add(root);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node == null)
				sb.append("#,");
			else {
				String val = String.valueOf(node.key)+",";
				sb.append(val);
				//System.out.println(sb);
				queue.add(node.left);
				queue.add(node.right);
			}
			
			
			
		}
		
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	
	public Node deserialize(String data) {
		if(data == null || data.length() == 0)
			return null;
			
		String[] arr = data.split(",");
		Node root = new Node(Integer.parseInt(arr[0]));
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		int i = 1;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(!arr[i].equals("#")){
				node.left = new Node(Integer.parseInt(arr[i]));
				queue.add(node.left);
			}
			i++;
			
			if(!arr[i].equals("#")){
				node.right = new Node(Integer.parseInt(arr[i]));
				queue.add(node.right);
			}
			i++;
		}
		
		return root;
	}

	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();

		tree.root = new Node(1);

		tree.root.left = new Node(2);
		tree.root.right = new Node(3);

		tree.root.right.left = new Node(4);

		Traverse.print(tree.root); // Output 4 2 1 3
		
		String blob = tree.serialize();
		System.out.println("blob: " + blob);
		
		Node node = tree.deserialize(blob);
		
		Traverse.print(node); // Output 4 2 1 3
		
		//Traverse.traverseLevelOrder(tree.root);
		
		Traverse.printPathToLeaf(tree.root);
	}
}


