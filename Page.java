package br.ufc.crateus.eda.st.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.sun.xml.internal.txw2.Document;

import br.ufc.crateus.eda.st.ordered.BinarySearchST;
import br.ufc.crateus.eda.st.btree.BTreeSet;

public class Page<K extends Comparable<K>,V>{
	public static final int M = 6;
	private Page<K,V> raiz;
	
	Page<K,V> left;
	Page<K,V> right;
	private boolean botton;
	Node root;
	List<Node> list = new ArrayList<>();
	
	public class Node{	
		K key;
		V value;
		Page<K,V> next = raiz;
		BinarySearchST<K, V> bst = new BinarySearchST<>();
			public Node(Node r,K key, V value, Page<K,V> page,boolean bool){
				root = r;
				this.key = key;
				this.value = value;
				bst.put(key,value);
				raiz = page;
			
			}
	}
	
	
	public Page(boolean booton) {
		this.botton = booton;
	}
	
	public void close() {
		
		
	}
	
	public void insert(K key, V value)  {
		root = insert(root, key, value);
		if(raiz != null){
			raiz = new Page<>(true);
			System.out.println("Internal Page");
		}
		System.out.println("*-*:"+root.bst.keys());
	}
//	@SuppressWarnings("unchecked")
	private Node insert(Node r, K key, V value){
		if(r == null){
			
			return new Node(r, key, value,raiz,false);
		}
		if(raiz != null){
			System.out.println("Internal Page");
		}
		list.add(r);
		r.bst.put(key, value);
		return r;
		
	}
	public void enter(Page<K,V> page) {
		raiz = new Page<K,V>(false);
		raiz.insert(page.root.key, page.root.value);
		System.out.println("entered");
	}
	
	public boolean isExternal() {
		return botton;
	}
	
	public boolean holds(K key) {
		//int i = 0;
		int cmp;
		while(raiz.isExternal()){
			cmp = key.compareTo(raiz.root.bst.floor(key));
			if(cmp < 0) raiz = raiz.left;
			else if(cmp > 0) raiz = raiz.right;
			else return true;
		}
		
		return false;
	}
	
	public Page<K,V> next(K key) {
		//System.out.println("O.O "+raiz.root.bst.min());
		
		return next(raiz,key);
		
	}
	@SuppressWarnings("unused")
	private Page<K,V> next(Page<K,V> next, K key){
		if(next == null) return null;
		for(int i = 0;i < next.root.bst.size();i = i+1){
			
			int cmp = key.compareTo(next.root.bst.select(i));
			if(cmp == 0) return next;
			else if(cmp > 0) return next.right;
			else return next.left;
		}
		
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
			root.bst.delete(key);
			mid++;
		}
		
		root.next = right;//System.out.println("olha so: "+root.next.root.key);
		System.out.println("dividiu");
		raiz = new Page<>(true);
		raiz.right = right;
		//raiz.right = right;
	
		//System.out.println("--->"+raiz.right.root.bst.min());
		return right;
	}
	
	public Iterable<K> keys() {
		return root.bst.keys();
	}
	
	
	
	
}
