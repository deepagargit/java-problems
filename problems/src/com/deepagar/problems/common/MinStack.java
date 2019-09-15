package com.deepagar.problems.common;

import java.util.*;

public class MinStack {
	
	Stack<Integer> s;
	Integer minElem;
	
	public MinStack() {
		s = new Stack<Integer>();
		
	}
	
	void push(Integer x) {
		if(s.isEmpty()) {
			s.push(x);
			this.minElem = x;
			System.out.println("push: " + x + " push to stack: " + (x));
		}
		else {
			if(x < minElem) {
				// 2x - minElem will be less than x
				// minElem will become x
				// Pushed element would be less than minElem
				Integer new_x = 2*x - this.minElem;
				s.push(new_x);
				this.minElem = x;
				
				System.out.println("push: " + x + " push to stack: " + new_x);
			}
			else {
				s.push(x);
				System.out.println("push: " + x + " push to stack: " + (x));
			}
		}
	}
	
	void pop() {
		if(s.isEmpty())
			System.out.println("Stack empty");
		
		Integer x = s.pop();
		Integer new_x = x;
		if(x < this.minElem) {
			new_x = minElem;
			minElem = 2*minElem -x;
		}
		
		System.out.println("pop: " + new_x + " minElem: " + minElem);
	}
	
	void peek() {
		if(s.isEmpty())
			System.out.println("Stack empty");
		else {
			Integer x = s.peek();
			if(x < minElem) // element is stored in minElem
				System.out.println("peek: " + minElem);
			else
				System.out.println("peek: " + x);
		}
			
	}
	
	void getMin() {
		if(s.isEmpty())
			System.out.println("Stack empty");
		else 
			System.out.println("minElem: " + minElem);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinStack min = new MinStack(); 
		min.push(3); 
		min.push(5); 
		min.getMin(); 
		min.push(2); 
		min.push(1); 
		min.getMin(); 
		min.pop(); 
		min.getMin(); 
		min.pop(); 
		min.peek();
		min.push(3); 
		min.push(5); 
		min.getMin(); 
		min.push(2); 
		min.push(1); 
		min.getMin(); 
		min.pop(); 
		min.getMin(); 
		min.pop(); 
		min.peek(); 

	}

}
