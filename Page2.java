package br.ufc.crateus.eda.st.btree;

import br.ufc.crateus.eda.st.ordered.BinarySearchST;

public class Page2<K extends Comparable<K>, V> {
public int m;	
private boolean botton;
BinarySearchST<K, Object> bst;	
	Page2(boolean booton,int m) {
		this.botton = booton;
		this.m = m;
		bst = new BinarySearchST<>();
	}
	
	public void close() {
		
	}
	
	public void insert(K key,Object object)  {
		bst.put(key, object);
		//System.out.println("Sucesso : "+key+"V:"+object);
	}
	
	public void enter(Page2<K,Object> page) {
		insert(page.bst.min(), page);
	}
	
	public boolean isExternal() {
		return botton;
	}
	
	public boolean holds(K key) {
		if(holds(next(key),key)!=null){
			return true;
		}
		else return holds(key);
	}
	private Page2<K,Object> holds(Page2<K,Object> p,K key){
		if(p.bst.contains(key))return p;
		return null;
	}
	@SuppressWarnings("unchecked")
	Page2<K, Object> next(K key) {
				//if((Page2<K, Object>) bst.get(bst.floor(key)) != null)
					return (Page2<K, Object>) bst.get(bst.floor(key));
			//	else return (Page2<K, Object>) bst.get(bst.floor(bst.min()));
	}
	
	public boolean hasOverflowed() {
		if(bst.size() == m){
			System.out.println("hell yeah");
			return true;
		}
		return false;
	}
	
	public Page2<K,V> auxSplit(){
		Page2<K,V> leftPage = new Page2<>(true, 6);
		int mid = bst.size() / 2;
		int size = m;
			while(mid<=size){
			System.out.println("-:>"+bst.select(mid));
			if(bst.select(mid)!=null){
				leftPage.insert(bst.select(mid),bst.get(bst.select(mid)));
				insert(bst.select(mid), null);
			}
				
			size--;
		}
		return leftPage;
	}
	public Page2<K,V> split() {
		Page2<K,V> newPage = new Page2(true, 6);
		int mid = bst.size() / 2;
		int size = m;
		while(mid<bst.size()){
			newPage.insert(bst.select(mid), bst.get(bst.select(mid)));
			mid++;
		}

		System.out.println("ok saiu");
		return newPage;
	}
	
	Iterable<K> keys() {
		return bst.keys();
	}
}
