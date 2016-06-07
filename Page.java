package br.ufc.crateus.eda.st.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.ufc.crateus.eda.st.ordered.BinarySearchST;

public class Page<K extends Comparable<K>,V>{
	public final int M = 6;
	Page<K, V> root1;
	Node root;
	
	public class Node{
		Page<K,V> right;
		Page<K,V> left;
		Page<K,V> next = root1;
		BinarySearchST<K, V> bst = new BinarySearchST<>();
			public Node(Page<K, V>.Node page,K key, V value){
				root = page;
				bst.put(key, value);
			}
		
	}
	
	
	
	private boolean botton;
	
	Page(boolean booton) {
		this.botton = booton;
	}
	
	public void close() {
		
	}
	
	public void insert(K key,V value)  {
		root = insert(root, key, value); 
		System.out.println(root.bst.keys());
	}
	private Node insert(Node page,K key,V value){
		if(page == null) return new Node(page, key, value);
		root.bst.put(key, value);
		
		return root;
	}
	
	public void enter(Page<K,V> page) {
		try{
		System.out.println(page.root.bst);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//	root = new Node(page, min , page.root.bst.get(min));
		
	}
	
	public boolean isExternal() {
		return botton;
	}
	
	public boolean holds(K key) {
		
		return false;
	}
	
	
	public Page<K,V> next(K key) {
		Page<K, V> page = root1;
		page = next(page,key);
		return page;
	}
	private Page<K,V> next(Page<K,V> r,K key){
		if(r == null) return null;
		int cmd = key.compareTo(r.root.bst.min());
		if(cmd < 0) return next(r.root.left,key);
		else if(cmd > 0) return next(r.root.right, key);
		else return r;
	}
	
	public boolean hasOverflowed() {
		if(root.bst.size() == M) return true;
		return false;
	}
	

	public Page<K,V> split() {
		Page<K,V> rootPage = new Page<>(true);
		int mid = root.bst.size() / 2;
		int size = root.bst.size();
		System.out.println(mid+"|"+size);
		System.out.println("uxa");
		K key = root.bst.select(mid+1);
		System.out.println(key);
		V value =  root.bst.get(key);
		
	rootPage.root = insert(rootPage.root, key, value);
		
			
	insert(root, key, null);
		//System.out.println(rootPage.root.bst.keys());
	
		return rootPage;
	}
	
	Iterable<K> keys() {
		return null;
	}

	
}