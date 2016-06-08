package br.ufc.crateus.eda.st.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.ufc.crateus.eda.st.ordered.BinarySearchST;

public class Page<K extends Comparable<K>,V> {
	public static final int M = 6;
	private boolean botton;
	Node root;
	List<Node> list = new ArrayList<>();
	
	public class Node{	
		K key;
		V value;
		Page<K,V> next;
		BinarySearchST<K, V> bst = new BinarySearchST<>();
			public Node(Node r,K key, V value){
				root = r;
				this.key = key;
				this.value = value;
				bst.put(key,value);
			}
	}
	
	
	Page(boolean booton) {
		this.botton = booton;
	}
	
	public void close() {
		
	}
	
	public void insert(K key, V value)  {
		root = insert(root, key, value);
		System.out.println(root.bst.keys());
	}
	@SuppressWarnings("unchecked")
	private Node insert(Node r, K key, V value){
		if(r == null) return new Node(r, key, value);
		
		r.key = key;
		r.value = value;
		r.bst.put(key, value);
		list.add(r);
		
		return r;
		
	}
	public void enter(Page<K,V> page) {
		Page<K,V> newPage = new Page<>(false);
		newPage.insert(page.root.key, page.root.value);
		System.out.println("entered");
	}
	
	public boolean isExternal() {
		return botton;
	}
	
	public boolean holds(K key) {
		return false;
	}
	
	public Page<K,V> next(K key) {
		return next(root.next,key);
	}
	private Page<K,V> next(Page<K,V> next, K key){
		if(next == null) return null;
		int cmp = key.compareTo(next.root.key);
		if(cmp == 0) return next;
		if(cmp<0) return next(next.root.next,key);
		
		return next;
	}
	
	public boolean hasOverflowed() {
		if(list.size() == M) return true;
		return false;
	}
	
	public Page<K,V> split() {
		Page<K,V> right = new Page<>(true);
		int mid = list.size() / 2;
		
		while(mid<=list.size()){
			System.out.println(root.bst.select(mid));
			K key = root.bst.select(mid);
			V value = root.bst.get(root.bst.select(mid));
			right.insert(key, value);
			mid++;
		}
		
		return right;
	}
	
	Iterable<K> keys() {
		return null;
	}
}
