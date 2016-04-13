package ST;

public interface OrderedST<K extends Comparable<K>, V> extends ST<K, V> {
	K min();
	K max();
	K floor(K key);
	K ceiling(K key);
	int rank(K key);
	K select(int i);
	void deleteMin();
	void deleteMax();
	int size(K lo, K hi);
	Iterable<K> keys(K lo, K hi);
	
}