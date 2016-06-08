
package br.ufc.crateus.eda.st.btree;

public class BTreeSet<K extends Comparable<K>,V> {
	private Page<K,V> root;
	
	public BTreeSet(K sentinel,V value) {
		root = new Page<>(true);
		root.insert(sentinel,value);
	}
	
	public boolean contains(K key) {
		return contains(root, key);
	}
	
	private boolean contains(Page<K,V> r, K key) {
		if (r.isExternal()) r.holds(key);
		return contains(r.next(key), key);
	}
	
	public void add(K key,V value) {
		add(root, key,value);
		if (root.hasOverflowed()) {
			Page<K,V> left = root;
			Page<K,V> right = root.split();
			root = new Page<>(false);
			root.enter(left);
			root.enter(right);
		}
	}
	
	private void add(Page<K,V> r, K key,V value) {
		if (r.isExternal()) r.insert(key,value);
		
		Page<K,V> next = r.next(key);
		if(next!=null){	
		add(next, key,value);
		if (next.hasOverflowed()) r.enter(next.split());
		}
		
	}

	public static void main(String[] args) {
		BTreeSet<String, String> pg = new BTreeSet<>("*","");
		pg.add("H", "");
		pg.add("A", "");
		pg.add("G", "");
		pg.add("B", "");
		pg.add("R", "");
		pg.add("F", "");
		pg.add("L", "");
		

	}
}
