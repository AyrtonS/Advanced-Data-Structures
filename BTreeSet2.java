package br.ufc.crateus.eda.st.btree;

	public class BTreeSet2<K extends Comparable<K>,V> {
		protected Page2<K,Object> root;
		
		public BTreeSet2(K sentinel,V value) {
			root = new Page2<>(true,6);
			root.insert(sentinel, value);
		}
		
		@SuppressWarnings("unchecked")
		public boolean contains(K key) {
			return contains((Page2<K, Object>) root, key);
		}
		
		private boolean contains(Page2<K, Object> page2, K key) {
			if (page2.isExternal()) page2.holds(key);
			return contains(page2.next(key), key);
		}
		
		public void add(K key,V value) {
			add(root, key,value);
			if (root.hasOverflowed()) {
				Page2<K,Object> left = root.auxSplit();
				Page2<K,Object> right = root.split();
				root = new Page2<>(false,6);
				System.out.println("root interno"+left.bst.keys());
				System.out.println("root interno"+right.bst.keys());
				root.enter(left);
				root.enter(right);
			}
		}
		
		private void add(Page2<K,Object> r, K key,V value) {
			if (r.isExternal()) r.insert(key,value);
			else{
				@SuppressWarnings("unchecked")
				Page2<K,Object> next = r.next(key);
				System.out.println("whoa::"+next+"::key::"+key);
				add(next, key,value);
				if (next.hasOverflowed()) r.enter(next.split());
			}
		
		}
	
	public static void main(String[] args) {
		BTreeSet2<String, String> pg = new BTreeSet2<>("*","");
		pg.add("F", "k stands for Key");
		pg.add("D", "D stands for duck");
		pg.add("Z", "Z stands for zzz");
		pg.add("K", "G stands for galaxy");
		pg.add("L", "L stands for luck");
		pg.add("G", "F stands for fried chicken");
		pg.add("J", "G stands for galaxy");
		pg.add("Z", "L stands for luck");
		pg.add("O", "F stands for fried chicken");
		pg.add("B", "G stands for galaxy");
		pg.add("E", "L stands for luck");
		pg.add("M", "F stands for fried chicken");
		
		System.out.println("success:" +pg.root.bst.keys());
		
		
		System.out.println("+_+_+_+_+_+_+_+_+_+_+_+_+_");
		//System.out.println(pg.root.holds("O"));

	}	
}
