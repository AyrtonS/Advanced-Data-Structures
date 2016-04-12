package ST;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map.Entry;

public class ArrayList1<K,V> implements OrderedST<K, V>,Comparable<K>{

	private List<STEntry<K,V>> list = new ArrayList<>();
	
	
	private Entry<K, V> getEntry(K key) {
		for (Entry<K, V> e : list)
			if (e.getKey().equals(key))
				return e;
		return null;
	}
	
	@Override
	public void put(K key, V value) {
		STEntry<K,V> a = new STEntry<>(key,value);
		int getRank = size();
		if(value == null){
			 getRank = rank(key); //log n
			 System.out.println(getRank);
			list.remove(getRank); // ~1
			}
		else{
				
			Entry<K,V> entry =  getEntry(key);
				if(entry == null){
					try{
						for(Entry<K,V> e : list){
							
							if(compareTo(key, e.getKey())<0){
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
		
		
	 

	@Override
	public V get(K key) {
		Entry<K,V> entry = getEntry(key);
		try{
			return entry.getValue();
		}catch(NullPointerException e){
			System.err.println("The key submitted does not exist!> err information> "+e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(K key) {
		put(key, null);
	}

	@Override
	public boolean contains(K key) {
		return list.contains(key);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterable<K> keys() {
		List<K> keys = new ArrayList<>();
		for (Entry<K, V> e : list) 
			keys.add(e.getKey());
		
		return keys;
	}

	@Override
	public K min() {
		return select(list.size()-list.size());
	}

	@Override
	public K max() {
		return select(list.size()-1);
	}

	@Override
	public K floor(K key) {
		Entry<K,V> entry = getEntry(key);
		if(entry == null){
			int getRank = rank(key);
			return select(getRank-1);
		}		
		return key;
	}

	@Override
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

	@Override
	
	public int rank(K key) {
		
		int lo = 0, hi = list.size(),keep;
		
		int mid;
		
	while(lo <= hi){
		 mid = lo+(hi-lo)/2;
		 
		 
			keep = compareTo(key, select(mid));
			System.out.println("Opa:"+keep);
		
		 	if(keep < 0)hi = mid - 1;
			else if(keep > 0)lo = mid + 1; 
			else return mid;
		}
return lo;
	}
	
	
	@Override
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

	@Override
	public void deleteMin() {
		list.remove(0);
	}

	@Override
	public void deleteMax() {
		list.remove(list.size()-1);
	}

	public void listar(){
		for(Entry<K,V> e : list){
			System.out.println(e.getKey());
		}
	}
	
	
	
	@Override
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

	@Override
	public Iterable<K> keys(K lo, K hi) {
		List<K> sub = new ArrayList<>();

		int k1 = rank(lo),k2 = rank(hi);
		
		while(k1 < k2){
			sub.add(select(k1));
			k1++;
		}
		
		return sub;
	}


	public int compareTo(K key,K keys2) {
		try{
			if(key instanceof String){
				String key1,key2;
				key1 = (String) key;
				key2 = (String) keys2;
				return key1.compareTo(key2);
			}
		}catch(NullPointerException e){
		}
		
		if(key instanceof Integer){
				
				int key1,key2;
				key1 = (Integer) key;
				key2 = (Integer) keys2;
				
					if(key1<key2) return key1-key2;
					else if(key1>key2) return key1-key2;
					else return 0;
				
		}
		
		
		return 0;
	}

	
	public static void main(String[] args){
		ArrayList1<String,Integer> a = new ArrayList1<String,Integer>();
		@SuppressWarnings("unused")
		ArrayList1<Integer,Integer> b = new ArrayList1<Integer,Integer>();
		a.put("Clo", 20);
		a.put("Blo", 10);
		a.put("Qee", 5);
		a.put("Alo", 5);
		a.put("Zee", 5);
		a.put("Blow", 8);
	
		/*b.put(100, 10);
		b.put(23, 5);
		b.put(200, 5);
		b.put(5, 5);
		b.put(1, 8);
		b.put(25, 8);*/
		System.out.println("----------------");
		a.listar();
		System.out.println("Chaves existentes: "+a.keys());
		System.out.println("Tamanho: "+a.size());
		System.out.println("CE lo -> hi:"+a.keys("B", "T"));
	}

	@Override
	public int compareTo(K o) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	

	
}
