package br.ufc.crateus.eda.st.ordered;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.omg.CORBA.FREE_MEM;

import java.util.Map.Entry;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import br.ufc.crateus.eda.st.ordered.BinarySearchTree.Node;
import br.ufc.crateus.eda.utils.ListArray;

public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedST<K, V> {
	
	public Node root;
	private ArrayList<K> ar = new ArrayList<K>();
	private static BinarySearchTree<Integer, Integer> bsT = new BinarySearchTree<>();
	protected static Scanner sc =  new Scanner(System.in);
	protected class Node {
		K key;
		V value;
		int count;
		Node left, right;
		
		public Node(K key, V value, int count) {
			this.key = key;
			this.value = value;
			this.count = count;
		}
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);
		return (node != null)? node.value : null;
	}
	
	private Node getNode(K key) {
		Node r = root;
		while (r != null) {
			int cmp = key.compareTo(r.key);
			if (cmp < 0) r = r.left;
			else if (cmp > 0) r = r.right;
			else break;
		}
		return r;
	}

	@Override
	public void put(K key, V value) {
		root = put(root, key, value);
	}
	
	private Node put(Node r, K key, V value) {
		if (r == null) return new Node(key, value, 1);
		int cmp = key.compareTo(r.key);
		
		if (cmp < 0) r.left = put(r.left, key, value);
		else if (cmp > 0) r.right = put(r.right, key, value);
		else r.value = value;
		r.count = 1 + size(r.left) + size(r.right);
		return r;
	}

	@Override
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private Node delete(Node r, K key) {
		if (r == null) return null;
		int cmp = key.compareTo(r.key);
		if (cmp < 0) r.left = delete(r.left, key);
		else if (cmp > 0) r.right = delete(r.right, key);
		else {
			if (r.left == null) return r.right;
			if (r.right == null) return r.left;
			
			Node tmp = r;
			r = min(r.right); 
			r.left = tmp.left;
			r.right = deleteMin(tmp.right);
		}
		
		r.count = size(r.left) + size(r.right) + 1;
		return r;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size(root);
	}
	
	protected int size(Node node) {
		return (node != null)? node.count : 0;
	}

	@Override
	public boolean isEmpty() {
		return root != null;
	}

	@Override
	public Iterable<K> keys() {
		Queue<K> keys = new LinkedList<>();
		inorder(root, keys);
		return keys;
	}

	@Override
	public K min() {
		Node min = min(root);
		return (min != null)? min.key : null;
	}
	
	private Node min(Node r) {
		if (r != null) 
			while (r.left != null) r = r.left;
		return r;
	}

	@Override
	public K max() {
		Node max = max(root);
		return (max != null)? max.key : null;
	}
	
	private Node max(Node r) {
		if (r == null) return null; 
		Node max = max(r.right);
		return (max != null)? max : r;
	}

	@Override
	public K floor(K key) {
		Node node = floor(root, key);
		return (node != null)? node.key : null;
	}
	
	private Node floor(Node r, K key) {
		if (r == null) return null;
		int cmp = key.compareTo(r.key);
		if (cmp == 0) return r;
		if (cmp < 0) return floor(r.left, key);
		Node t = floor(r.right, key);
		return (t != null)? t : r;
	}

	@Override
	public K ceiling(K key) {
		Node node = ceiling(root, key);
		return (node != null)? node.key : null;
	}
	
	private Node ceiling(Node r, K key) {
		if (r == null) return null;
		int cmp = key.compareTo(r.key);
		if (cmp == 0) return r;
		if (cmp > 0) return ceiling(r.right, key);
		Node t = ceiling(r.left, key);
		return (t != null)? t : r;
	}

	@Override
	public int rank(K key) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private int rank(Node r, K key) {
		if (r == null) return 0;
		int cmp = key.compareTo(r.key);
		if (cmp == 0) return size(r.left);
		if (cmp < 0) return rank(r.left, key);
		return 1 + size(r.left) + rank(r.right, key);
	}

	@Override
	public K select(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node r) {
		if (r.left == null) return r.right;
		r.left = deleteMin(r.left);
		r.count = 1 + size(r.left) + size(r.right);
		return r;
	}

	@Override
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node r) {
		if (r.right == null) return r.left;
		r.right = deleteMax(r.right);
		r.count = 1 + size(r.left) + size(r.right);
		return r;
	}

	@Override
	public int size(K lo, K hi) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* Calculates height of some binary search tree */
public int height(Node r){
		if(r == null) return -1;
		int h1 = verifyHeight(r.right);
		int h2 = verifyHeight(r.left);
		
		if(h1<h2) return h2+1;
		else return h1+1;
	}

	/* Auxiliar method to calculate height of some binary tree */
	private int verifyHeight(Node r){
		if(r == null) return -1;
		if(size(r.left)>size(r.right))
			return verifyHeight(r.left)+1;
		else 
			return verifyHeight(r.right)+1;
	}
	/* This method calculates depth of some binary search tree */ 
	public int depth(Node r,K key){
		if(r == null) return -1;
		int cmp = key.compareTo(r.key);
		if(cmp<0)return depth(r.left,key)+1;
		else if(cmp>0) return depth(r.right,key)+1;
		else return 0;	
	}

	
	public int balancedTree(Node r,V value){
		int m = (ar.size()+0)/2;
		put(ar.get(m), value);
		int j = m+1;
		for(int i = m-1;i>=0;i--){
			put(ar.get(i), value);
		}
		for(int i = m+1;i<ar.size();i++){
			put(ar.get(i), value);
		}
		return 1;
	}

	
	public int printTree(Node r){
		if(r == null){  	
			return 0; 
		}
	
		int d = depth(root, r.key);
		while(d >= 0 && r!=null){
			System.out.print("*");
			if(d==0) System.out.println(" |"+r.key);
			
			d--;
		}
		int rValue = printTree(r.left);
		int rValue2 = printTree(r.right);
		d = depth(root, r.key);
		if(rValue==0 && rValue2 ==0){
			while(d>=0){
				System.out.print("  ");
				if(d==0)System.out.println("--");
				d--;
			}
		}
		
		return 1;
	}
	
	
	
	
	@Override
	public Iterable<K> keys(K lo, K hi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	void inorder(Node r, Queue<K> keys) {
		if (r != null) {
			inorder(r.left, keys);
			keys.add(r.key);
			inorder(r.right, keys);
		}
	}
	
	
	public static void main(String[] args){

		menu();

		}
	
	public static BinarySearchTree<Integer, Integer> inserir(){
//BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
		boolean t = true;
		//boolean f = false;
		do{
		
		System.out.print("::: Key valuation : ");
		int key = sc.nextInt();
		System.out.print("::: Value valuation : ");
		int value = sc.nextInt();
		
		bsT.put(key, value);
		
		System.out.println("Deseja inserir outros valores [1]-Sim [0]-Não");
		int op = sc.nextInt();
		
		if(op == 0) t = false;
		
		}while(t!=false);
		
		return bsT;
		
	}
	public static void imprimirArvore(BinarySearchTree<Integer, Integer> bst){
		try{
		if(bst.printTree(bst.root)==0){
			System.out.println("Tree is empty. How about insert values?[1]-YES [0]-NO!");
			int tmp = sc.nextInt();
				if(tmp == 1) inserir();
		}
			}catch(Exception e){
			System.out.println(e.getMessage());
			}
			menu();	
		}
			
	
	public static void menu(){
		
		System.out.println("The following program is an exemple of binary search tree's usage where Keys and Values are setted as <Integer,Integer> \n");
		System.out.println("	------------------PROGRAM TREE'S TREE--------------------");
		System.out.println("	[1] - Insert keys and values");
		System.out.println("	[2] - Search for key");
		System.out.println("	[3] - Check Tree");
		System.out.println("	[4] - Leave");
		
		System.out.print("	-> : ");
		int op = sc.nextInt();
		System.out.println("	---------------------------------------------------------");
		
		switch(op){
			case 1:
				System.out.println("	-----------------------Inserting------------------------");
				System.out.println("	[1] - Insert value in a Tree");
				System.out.println("	[2] - Insert values in an array");
				System.out.println("	[3] - Build brandnew tree with data from array");
				System.out.println("	---------------------------------------------------------");
				int option = sc.nextInt();
					switch(option){
							
					case 1: 
						bsT = inserir();
						break;
					case 2:
						System.out.println("Warning: This operation will delete all information on you current tree.");
						System.out.println("Do you really want to continue? [1]-Yes [0]-No");
						int decision = sc.nextInt();
							if(decision == 0) menu();
							else if( decision == 1){
								boolean t = true;
								do{
									System.out.println("Insert Key");
									int in = sc.nextInt();
									bsT.ar.add(in);
									
									System.out.println("Do you want to continue? [1]-Yes [0]-No");
									int opt = sc.nextInt();
									if(opt == 0) t = false;
									
								}while(t!=false);	
								
							System.out.println("Be aware that you must build the tree");	
							}
						break;
					case 3:
						bsT.root = null;
						bsT.balancedTree(bsT.root, 1);
						System.out.println("Tree sucessfully created");
						break;
					}
				menu();
			break;
			
			case 2:
				System.out.println("	-----------------------Searching------------------------");
				System.out.println("	[1] - Get a value given a key");
				System.out.println("	[2] - Get floor");
				System.out.println("	[3] - Get ceiling");
				System.out.println("	---------------------------------------------------------");
				int get = sc.nextInt();
					switch(get){
					case 1:
						System.out.print("Type Key: ");
						int temp = sc.nextInt();
							System.out.println("The value relative to the given key is : "+bsT.get(temp));
							menu();
						break;
					case 2: 
						System.out.print("Type Key: ");
						int temp2 = sc.nextInt();
						System.out.println("The floor value is:"+bsT.floor(temp2));
						menu();
						break;
					case 3: 
						System.out.print("Type Key: ");
						int temp3 = sc.nextInt();
						System.out.println("The floor value is:"+bsT.ceiling(temp3));
						menu();
					}
			break;
			
			
			case 3:
				System.out.println("	-----------------------CHECK TREE------------------------");
				System.out.println("	[1] - Select minimum key");
				System.out.println("	[2] - Select Maximum key");
				System.out.println("	[3] - Get tree's height");
				System.out.println("	[4] - Get a node's depth");
				System.out.println("	[5] - Print current shape of the tree");
				System.out.println("	---------------------------------------------------------");
					int tmp = sc.nextInt();
					switch(tmp){
						case 1:
							System.out.println(bsT.min());
							menu();
						break;
						
						case 2:
							System.out.println(bsT.max());
							menu();
						break;
						
						case 3:
							System.out.println("The tree's height is : "+bsT.height(bsT.root)+" Node(s)");
							menu();
						break;
						
						case 4:
							System.out.println("What key would you like to know its depth? ");
							int key = sc.nextInt(); // Apenas como exemplo 
							System.out.println("The node's depth is : "+bsT.depth(bsT.root, 23)+" Node(s)");
							menu();
						
						case 5:
							System.out.println(bsT.printTree(bsT.root));
							System.out.println("______________________");
							menu();
						break;	
					
					}
			break;	
		
			case 4: 
				System.out.println("Bye! :)");
				System.exit(1);
				
			default:
				System.out.println("Invalid operation!");
				menu();
		}
		
		
		
	}
	
}
