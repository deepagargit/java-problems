package com.deepagar.problems.tree;

class NodeBinaryTree {
	int key;
	NodeBinaryTree left;
	NodeBinaryTree right;

	public NodeBinaryTree(int item)
	{
		key = item;
		left = null;
		right = null;
	}
}

class BinaryTree
{
	NodeBinaryTree root;

	public BinaryTree()
	{
		root = null;
	}

	public BinaryTree(int item)
	{
		root = new NodeBinaryTree(item);
	}
	
	public void print(NodeBinaryTree node)
	{
		if (null == node)
			return;
			
		print(node.left);
		
		System.out.println(node.key);

		print(node.right);
	}

	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();

		tree.root = new NodeBinaryTree(1);

		tree.root.left = new NodeBinaryTree(2);
		tree.root.right = new NodeBinaryTree(3);

		tree.root.left.left = new NodeBinaryTree(4);

		tree.print(tree.root); // Output 4 2 1 3

	}
}


