package br.ufc.crateus.eda.st.btree;

public class BTreeSet<K extends Comparable<K>,V> {
	protected Page<K,V> root;
	
	public BTreeSet(K sentinel,V value) {
		root = new Page<>(true);
		root.insert(sentinel,value);
	}
	
	public boolean contains(K key) {
		return contains(root, key);
	}
	
	private boolean contains(Page<K,V> r, K key) {
		if (r.isExternal()) r.holds(r,key);
		return contains(r.next(key), key);
	}
	
	public void add(K key,V value) {
		add(root, key,value);
		if (root.hasOverflowed()) {
			
			System.out.println("*******OVERFLOWED********");
			//Page<K,V> left = root;
			int mid = root.M /2;
			int size = root.M;
			Page<K,V> right = root.split();
			System.out.println("EPAAAAAA"+right.root.key);
			while(mid<=size){
				System.out.println("-:>"+root.root.bst.select(mid));
				if(root.root.bst.select(mid)!=null)
					root.insert(root.root.bst.select(mid), null);
				
				size--;
			}
			Page<K,V> left = root;
		
			root = new Page<>(false);
			root.left = left;
			root.right = right;
			
			System.out.println("OLHA AQUI --> ("+right.root.key+")E ESSE TAMBÉM ("+left.root.key+")");
			root = root.enter(root, left);
			root = root.enter(root, right);
			System.out.println("LEFT NODE:"+root.left.root.bst.keys());
			System.out.println("RIGHT NODE:"+root.right.root.bst.keys());
			System.out.println("THE NODE:"+root.root.bst.keys());
		}
	}
	
	private void add(Page<K,V> r, K key,V value) {
		if (r.isExternal()){
			System.out.println("External");
			r.insert(key,value);
		}
		else {
			Page<K,V> floor= floor(r, key);
			Page<K,V> next = r.next(floor,key);
			System.out.println("*************************************");
			System.out.println(next);
			if(next!=null){	
			System.out.println("Internal Page"+key);
			add(next, key,value);
			if (next.hasOverflowed()) r.enter(r,next.split());
		}
		
		}
		
	}
	
	public Page<K,V> floor(Page<K,V> r, K key){
		if(r == null) return r;
		int getRank = rank(r,key);
		K k = select(getRank);
		System.out.println(key+"|"+k);
		int cmp = key.compareTo(k);
		System.out.println("[-"+cmp+"-]");
		if(cmp < 0){
			System.out.println("WENT TO THE LEFT NODE");
			return r.left;
		}
		else if(cmp > 0){
			System.out.println("WENT TO THE RIGHT NODE");
			return r.right;
		}
		else{
			System.out.println("EQUALS");
			return r;
		}
	}
	
	public K ceiling(Page<K,V> r, K key){
		int lo = 0, hi = r.root.bst.size();
		K aux = null;
		while(lo<=hi){
			int mid = lo+(hi-lo)/2;
			if(select(mid)==null) break;
			System.out.println(key+"-->"+select(mid));
			int cmp = key.compareTo(select(mid));
			System.out.println(cmp);
			if(cmp > 0){
				System.out.println("retornou aqui no menor");
				return select(mid);
			}
			
			else if(cmp < 0){
			lo = mid + 1;
			}
			else return key;
			 aux = select(mid);
		}
		
		return aux;
	}
	
	public int rank(Page<K,V> r,K key){
		int hi = root.root.bst.size(),lo = 0,save = 0;
		int mid;
		mid = lo+(hi-lo)/2;
		
		if(!r.isExternal()){
			K tmp = ceiling(r,key);
			System.out.println(key+"[RANK TEMPORARIO]"+tmp);
			save = key.compareTo(tmp);
			
			if(save < 0){
				System.out.println("lower"+save);
				hi = mid - 1;
			}
			else return mid;
		}
	return lo;
	}
	
	public K select(int i){
		K key = root.root.bst.select(i);
		System.out.println("----"+key);
		return key;
	}
	
	public static void main(String[] args) {
		BTreeSet<String, String> pg = new BTreeSet<>("*","");
		pg.add("H", "");
		pg.add("E", "");
		pg.add("V", "");
		pg.add("K", "");
		pg.add("J", "");
		pg.add("B", "");
		pg.add("E", "");
	
		pg.add("A", "");
		pg.add("Z", "");
		pg.add("S", "");
		pg.add("R", "");
		
		System.out.println("success");
		
		//pg.root.keys();
		System.out.println("Main Page"+pg.root.keys(pg.root));
		System.out.println("LEFT PAGE"+pg.root.keys(pg.root.left));
		System.out.println("RIGHT PAGE"+pg.root.keys(pg.root.right));
		
		System.out.println("+_+_+_+_+_+_+_+_+_+_+_+_+_");
		
		

	}
}
