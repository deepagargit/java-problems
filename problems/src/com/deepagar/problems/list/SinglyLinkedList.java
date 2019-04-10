package com.deepagar.problems.list;


class SLLNode {
	SLLNode next;
	int data;
	
	public SLLNode() {
		this.next = null;
		this.data = 0;
	}
	
	public SLLNode(int data) {
		this.next = null;
		this.data = data;
	}
	
}

public class SinglyLinkedList {
	SLLNode ptr;
	
	public SinglyLinkedList() {
		this.ptr = null;
	}
	
	public void addNode(SLLNode node) {
		if(this.ptr == null)
			this.ptr = node;
		else {
			node.next = this.ptr;
			this.ptr = node;
		}
		
	}
	
	public void print(String tag) {
		SLLNode cur = this.ptr;
		System.out.print(tag + "Node:");
		while(cur != null) {
			System.out.print(" " + cur.data);
			cur = cur.next;
		}
		
		System.out.println();
	}

	public int countNode(int key) {
		SLLNode cur = this.ptr;
		int count = 0;
		
		while(cur != null) {
			if(cur.data == key)
				count++;
			
			cur = cur.next;
		}
		
		return count;
	}
	
	public int getNth(int index) {
		SLLNode cur = this.ptr;
		int count = 0;
		
		while(cur != null) {
			if(index == count)
				return cur.data;
			
			count++;
			cur = cur.next;
		}
		
		return -1;
	}
	
	public void deleteList() {
		while(this.ptr != null) {
			this.ptr = this.ptr.next;
		}
		
	}
	
	public int popNode() {
		SLLNode head = this.ptr;
		
		if(this.ptr != null)
			this.ptr = this.ptr.next;
		
		if(head != null)
			return head.data;
		else
			return -1;
	}
	
	public boolean insertNth(int index, int value) {
		SLLNode cur = this.ptr;
		int count = 0;
		
		SLLNode node = new SLLNode(value);
		
		if(index < 0)
			return false;
		
		if(index == 0) {
			node.next = this.ptr;
			this.ptr = node;
			return true;
		}
		
		while(cur != null && count != (index - 1)) {
			cur = cur.next;
			count++;
		}
		
		if(cur == null)
			return false;
		
		node.next = cur.next;
		cur.next = node;
		return true;
	}
	
	public void sortedInsert(SLLNode node) {
		SLLNode cur = this.ptr;
		
		// empty list
		if(this.ptr == null) {
			this.ptr = node;
			return;
		}
		
		// insert as head element
		if(this.ptr.data > node.data) {
			node.next = this.ptr;
			this.ptr = node;
			return;
		}
			
		// insert rest
		while(cur.next != null && cur.next.data < node.data)
			cur = cur.next;
		
		node.next = cur.next;
		cur.next = node;
	}
	
	public void insertSort() {
		SLLNode cur = this.ptr;
		this.ptr = null;
		
		while(cur != null) {
			SLLNode node = cur;
			cur = cur.next;
			
			node.next = null;
			this.sortedInsert(node);
		}
	}
	
	public void appendList(SLLNode listB) {
		SLLNode listA = this.ptr;
		
		if(this.ptr == null) {
			this.ptr = listB;
			return;
		}
		
		while(listA.next != null)
			listA = listA.next;
		
		listA.next = listB;
	}
	
	public void splitList() {
		SLLNode one = this.ptr;
		SLLNode two = this.ptr;
		
		if(one == null)
			return;
		
		while(two != null && two.next != null) {
			one = one.next;
			two = two.next.next;
		}
		
		one.next = null;
	}
	
	public void removeDuplicates() { // sorted list
		SLLNode cur = this.ptr;
		
		if(cur == null)
			return;
		
		int curData = cur.data;
		
		while(cur.next != null) {
			if(curData == cur.next.data)
				cur.next = cur.next.next;
			else {
				curData = cur.next.data;
				cur = cur.next;
			}	
		}
	}
	
	public void reverse() {
		SLLNode cur = this.ptr;
		SLLNode next = null;
		SLLNode result = null;
		
		while(cur != null) {
			next = cur.next;
			cur.next = result;
			result = cur;
			cur = next;
		}
		
		this.ptr = result;
	}
	
	public static void main(String[] args) {		
		// create SLL
		SinglyLinkedList sll = new SinglyLinkedList();
		
		int data = sll.popNode();
		sll.print("popNode ");
		System.out.println("Pop node: " + data);
		
		sll.addNode(new SLLNode(1));
		sll.addNode(new SLLNode(3));
		sll.addNode(new SLLNode(1));
		sll.addNode(new SLLNode(2));
		
		// count nodes with data 1
		sll.print("");
		int count = sll.countNode(1);
		System.out.println("Count with data 1: " + count);
		
		// get Nth node
		data = sll.getNth(2);
		System.out.println("Get Nth 2nd node: " + data);
		
		// delete list
		//sll.deleteList();
		//sll.print();
		
		// pop Node
		data = sll.popNode();
		sll.print("popNode ");
		System.out.println("Pop node: " + data);
		
		//insert nth Node
		boolean status = sll.insertNth(2, 4);
		sll.print("insertNth ");
		System.out.println("Insert Nth node status: " + status);
		
		status = sll.insertNth(0, 5);
		sll.print("insertNth ");
		System.out.println("Insert Nth node status: " + status);
		
		status = sll.insertNth(-1, 6);
		sll.print("insertNth ");
		System.out.println("Insert Nth node status: " + status);
		
		// insert sort
		sll.insertSort();
		sll.print("insertSort ");
		
		// append list
		SinglyLinkedList sll2 = new SinglyLinkedList();
		
		sll2.addNode(new SLLNode(6));
		sll2.addNode(new SLLNode(7));
		
		sll.appendList(sll2.ptr);
		sll.print("append list");
		
		// split list
		sll.splitList();
		sll.print("splitList ");
		
		// remove duplicates
		sll.removeDuplicates();
		sll.print("removeDuplicates ");
		
		// reverse list
		sll.reverse();
		sll.print("reverse ");
	}

}
