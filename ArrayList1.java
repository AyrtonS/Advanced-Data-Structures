package br.ufc.crateus.eda.st.ordered;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.stylesheets.LinkStyle;

import br.ufc.crateus.eda.st.STEntry;

public class BinarySearchST<K extends Comparable<K>, V> implements OrderedST<K, V> {

	private List<STEntry<K,V>> list = new ArrayList<>();
	
	
	private Entry<K, V> getEntry(K key) {
		for (Entry<K, V> e : list)
			if (e.getKey().equals(key))
				return e;
		return null;
	}
	
	public void put(K key, V value) {
		STEntry<K,V> a = new STEntry<>(key,value);
		int getRank = size();
		if(value == null){
			 getRank = rank(key); //log n
			 System.out.println(getRank);
			 System.out.println("deletado");
			list.remove(getRank); // ~1
			}
		else{
				
			Entry<K,V> entry =  getEntry(key);
				if(entry == null){
					try{
						for(Entry<K,V> e : list){
							
							if(key.compareTo(e.getKey())<0){
								System.out.println("Entrou");
								getRank = rank(e.getKey());//log n
								
								break;
							}
							
						}
						}catch(ConcurrentModificationException e){}
					list.add(getRank, a); // ~1
				}
				
		}
		
		}
		
		
	 

	public V get(K key) {
		Entry<K,V> entry = getEntry(key);
		try{
			return entry.getValue();
		}catch(NullPointerException e){
			System.err.println("The key submitted does not exist!> Err ocourred > "+e.getMessage());
		}
		return null;
	}

	public void delete(K key) {
		//put(key, null);
		list.remove(key);
	}

	public boolean contains(K key) {
		return list.contains(key);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public Iterable<K> keys() {
		List<K> keys = new ArrayList<>();
		for (Entry<K, V> e : list) 
			keys.add(e.getKey());
		
		return keys;
	}

	public K min() {
		return select(0);
	}

	public K max() {
		return select(list.size()-1);
	}

	public K floor(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry == null){
			int getRank = rank(key);
			return select(getRank);
		}		
		return key;
	}

	public K ceiling(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry == null){
			int getRank = rank(key);
			System.out.println("VR: "+getRank);
			K Ceiling =  select(getRank);
				if(Ceiling==null) return select(getRank-1);
				else return Ceiling;
		}		
		return select(rank(key)+1);
	}

	public int rank(K key) {
		
		int lo = 0, hi = list.size(),keep;
		
		int mid;
		
	while(lo <= hi){
		 mid = lo+(hi-lo)/2;
		 
		 	//System.out.println("olha o meioo"+ select(mid));
		 	if(select(mid)==null) return mid-1;
		 	else keep = key.compareTo(select(mid));
			//System.out.println("Opa:"+keep);
		
		 	if(keep < 0)hi = mid - 1;
			else if(keep > 0)lo = mid + 1; 
			else return mid;
		}
return lo;
	}
	
	
	public K select(int i) {
		try{
			for(Entry<K,V> e : list){
				if(e.equals(list.get(i)))
					return e.getKey();	
			}
		}catch(IndexOutOfBoundsException e){
			System.err.println(e.getMessage());
		}
	return null;
	
		}

	public void deleteMin() {
		list.remove(0);
	}

	public void deleteMax() {
		list.remove(list.size()-1);
	}

	public void listar(){
		for(Entry<K,V> e : list){
			System.out.println(e.getKey());
		}
	}
	
	
	
	public int size(K lo, K hi) {
		Entry<K,V> entry = getEntry(lo);
		Entry<K,V> entry2 = getEntry(hi);
		// 2n -> ~ n
		if(entry == null || entry2 == null)
			return 0;
		
		int c1=0,c2=0;
		c1 = rank(lo);
		c2 = rank(hi);
		//2logn -> ~ log n
		return c2-c1+1;
	}

	public Iterable<K> keys(K lo, K hi) {
		List<K> sub = new ArrayList<>();

		int k1 = rank(lo),k2 = rank(hi);
		
		while(k1 < k2){
			sub.add(select(k1));
			k1++;
		}
		
		return sub;
	}
	public void removeComplete(K key){
		list.remove(key);
		System.out.println("Sucessfully erased");
	}
	public static void main(String[] args) {
		/*BinarySearchST<String, String> bst = new BinarySearchST<>();
		bst.put("H", "");
		bst.put("J", "");
		bst.listar();
		bst.delete("H");
		bst.listar();*/
	}


}
