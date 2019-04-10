package com.deepagar.problems.common;


import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 *
 * If you need more classes, simply define them inline
 */

class LRUNode<K, V> {
    K key;
    V value;
    LRUNode<K, V> pre;
    LRUNode<K, V> next;
  
    public LRUNode(K key, V value){
      this.key = key;
      this.value = value;
      pre = null;
      next = null;
    }
  
    public LRUNode(){
      pre = null;
      next = null;
    }
}

class LRUCache<K, V> {
    private HashMap<K, LRUNode<K, V>> map;
    private LRUNode<K,V> head, tail;
    private int numEntries, count;
  
    public LRUCache(int numEntries) {
      this.numEntries = numEntries;
      this.map = new HashMap<K, LRUNode<K, V>>();
      this.head = new LRUNode<K, V>();
      this.tail = new LRUNode<K, V>();
      head.next = tail;
      head.pre = null;
      tail.next = null;
      tail.pre = head;
      this.count = 0;
      
    }
  
    private void removeLRUNode(LRUNode<K, V> LRUNode) {
      LRUNode.pre.next = LRUNode.next;
      LRUNode.next.pre = LRUNode.pre;
    }
  
    private void addLRUNodeHead(LRUNode<K, V> LRUNode) {
      LRUNode.next = head.next;
      LRUNode.next.pre = LRUNode;
      LRUNode.pre = head;
      head.next = LRUNode;
    }
  
    public void printList() {
      LRUNode<K, V> LRUNode = head.next;
      
      while(LRUNode != tail) {
       System.out.println("key " + LRUNode.key + " value " + LRUNode.value);
        LRUNode = LRUNode.next;
      }
      
      System.out.println("End of List");
      
    }
    
    /**
      *  If key already exists, replace the current value with the new value.
      *  If the key doesn't exist, add the new key/value entry to the cache.
      *  If the addition of the new entry causes the number of entries to exceed numEntries, remove the oldest entry based on the last time the entry is accessed (either through put or get).
      */
    public void put(K key, V value){
        // if key is present in cache
        if(map.get(key) != null) {
          LRUNode<K, V> LRUNode = map.get(key);
          LRUNode.value = value;
          
          // remove LRUNode from LL and put to head
          removeLRUNode(LRUNode);
          addLRUNodeHead(LRUNode);
           
        } // if key is not present and a new key
        else {
          // create new LRUNode
          LRUNode<K, V> LRUNode = new LRUNode<K, V>(key, value);
          map.put(key,LRUNode);
          
          // check entry count for queue
          if(count < numEntries) {
            count++;
            addLRUNodeHead(LRUNode);
          }
          else {
            // if queue is full, remove LRUNode from tail and then add
            map.remove(tail.pre.key);
            removeLRUNode(tail.pre);
            
            addLRUNodeHead(LRUNode);
            // No need to change count         
          }
          
        }
      
      
    }
 
 
    // return the value associated with the key or null if the key doesn't exist.
    public V get(K key){
      
      // check for LRUNode lookup
      if(map.get(key) != null){
        // LRUNode present
        LRUNode<K, V> LRUNode = map.get(key);
        
        // remove LRUNode from LL and put to head
        removeLRUNode(LRUNode);
        addLRUNodeHead(LRUNode);
        
        // No need to change count
        
        return LRUNode.value;
      }
         
      // LRUNode not present return null
      return null;
      
    }
      

  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());
    
    LRUCache<Integer, String> cache = new LRUCache<Integer, String>(2);
    
    cache.printList();
    cache.put(1, "Hello1");
    
    cache.printList();
    cache.put(2, "Hello2");
    
    cache.printList();
    String value = cache.get(1);
    System.out.println("get value" + value);
    
    cache.printList();
    value = cache.get(4);
    System.out.println("get value" + value);

    cache.printList();
    cache.put(3, "Hello3");
    
    cache.printList();
    cache.put(3, "Hello3-new");
    
    cache.printList();
  }
}


/*********

End of List
key 1 value Hello1
End of List
key 2 value Hello2
key 1 value Hello1
End of List
get valueHello1
key 1 value Hello1
key 2 value Hello2
End of List
get valuenull
key 1 value Hello1
key 2 value Hello2
End of List
key 3 value Hello3
key 1 value Hello1
End of List
key 3 value Hello3-new
key 1 value Hello1
End of List

*********/
