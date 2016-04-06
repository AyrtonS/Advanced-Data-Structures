package ST;

import java.util.Map.Entry;

public class STEntry<K,V> implements Entry<K, V>{
private K key;
private V value;

	public STEntry(K key,V value) {
		this.key = key;
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		V aux = value;
		this.value = value;
		return aux;
	}

	public K getKey() {
		return key;
	}
	
}
