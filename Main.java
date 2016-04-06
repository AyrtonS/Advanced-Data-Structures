package ST;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> a = new ArrayList<>();
		STLinkedList<String, String> lst = new STLinkedList<String, String>();
		lst.put("192.168.1.1", "localhost");
		lst.put("192.168.2.1", "google.com");
		
		System.out.println(lst.get("192.168.2.1"));
	}

}
