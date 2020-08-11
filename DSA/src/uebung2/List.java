package uebung2;

import java.util.LinkedList;

/**
 * Einfach verkettete Liste Leere Liste <> ist null; nichtleere Liste
 * <a_ß,...,a_(n-1)> besteht aus einem Listenkopf mit Beschrifung a_ß und dem
 * (Verweis auf den) Rest <a_1, ..., a_(n-1)>
 */

public class List {
	/**
	 * Das Element am Listenkopf
	 */
	private int value;
	/**
	 * Der Nachfolger des Listenkopfs
	 */
	private List succ;

	/**
	 * @param e Neuer Listenkopf param l Rest der Liste
	 */
	List(int e, List l) {
		value = e;
		succ = l;
	}

	/**
	 * 
	 * @return
	 */
	public static List empty() {
		return null;
	}

	public static int first(List a) {
		return a.value;
	}

	public static List rest(List a) {
		return a.succ;
	}

	/**
	 * If the change is not saved list remains unaltered
	 */
	public static List append(int x, List a) {
		return new List(x, a);
	}

	/**
	 * changes the list permanently
	 */
	public static List concat(List a, List b) {
		if (a.succ != null) {
			concat(a.succ, b);
		} else {
			a.succ = b;
		}
		return a;
	}

	@Override
	public String toString() {
		if (List.isEmpty(this)) {
			return "Leere Liste";
		} else {
			StringBuilder c = new StringBuilder();
			List copy = this;
			c.append("( ");
			c.append(copy.value);
			while (copy.succ != null) {
				copy = copy.succ;
				c.append(", ");
				c.append(copy.value);
			}
			c.append(" )");
			return c.toString();
		}
	}

	private static boolean isEmpty(List a) {
		if (a == null) {
			return true;
		} else {
			return false;
		}
	}

	public static List copy(List l) {
		if (l == null) {
			return null;
		}
		List copy;
		if (l.succ == null) {
			copy = new List(l.value, null);
		} else {
			copy = new List(l.value, List.copy(l.succ));
		}
		return copy;
	}

	public static int size(List l) {
		if (List.isEmpty(l)) {
			return 0;
		} else if (l.succ != null) {
			return 1 + size(l.succ);
		} else {
			return 1;
		}
	}
	
	public static List get(List l, int n) {
//		if(List.size(l) <= n) {
//			throw new IllegalArgumentException("Index out of Bound");
//		}
		for(int i = 0; i < n; i++) {
			l = l.succ;
		}
		return l;
	}
/**
 * Returns the index of the given int in the list
 * If the value does not exist the value is negative 
 * @requires l.size() < 2.147.483.648
 */
	public static int indexInList(List l, int obj) {
		if (List.first(l) == obj) {
			return 0;
		} else if (List.rest(l) != null) {
			return indexInList(List.rest(l), obj) + 1;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static boolean inList(List l, int obj) {
		boolean output = true;
		try {
			indexInList(l,obj);
		}
		catch(Exception exc){
			output = false;
		}
		return output;
	}

	public static int nthElement(List l, int n) {
		List copy = List.copy(l);
		for (int i = 0; i < n; i++) {
			copy = copy.succ;
		}
		return List.first(copy);
	}
	
	
	public static List listBefore(List l, int n) {
		List end = List.copy(l);
		List beg = List.empty();
		int value = List.first(end);
		end = end.succ;
		for (int i = 0; i < n; i++) {
			if (List.isEmpty(beg)) {
				beg = new List(value, null);
			} else {
				beg = List.concat(beg, new List(value, null));
			}
			value = List.first(end);
			end = end.succ;
		}
		return beg;
	}

	public static List listAfter(List l, int n) {
		List end = List.copy(l);
		for (int i = 0; i <= n; i++) {
			end = end.succ;
		}
		return end;
	}

	public static List NthToFirst(List l, int n) {
		List output = List.get(l, n);
		List memory = List.rest(output);
		output.succ = l;
		List.get(output, n).succ = memory;		
		
		return output;
		
	}
	
	
	public static List removeNthElement(List l, int n) {
		if (n == 0) {
			return List.listAfter(l, n);
		} else {
			return List.concat(List.listBefore(l, n), List.listAfter(l, n));
		}
	}

	public static List firstToNth(List list, int elem) {
		int first = List.first(list);
		if (elem <= 0) {
			return list;
		} else if (elem == List.size(list) - 1) {
			List beg = List.copy(list);
			beg = beg.succ;
			beg = List.concat(beg, new List(first, null));
			return beg;
		} else if (elem >= List.size(list)) {
			System.out.println("Ungültiger Index");
			return null;
		} else {
			elem++;
			int nTh = List.nthElement(list, elem);
			List beg = List.listBefore(list, elem);
			beg = beg.succ;
			List end = List.listAfter(list, elem);
			beg = List.concat(beg, new List(first, new List(nTh, null)));
			beg = List.concat(beg, end);
			return beg;
		}
	}

}

//public static void print(List a) {
//	List copy = a;
//	System.out.print("(" + List.first(a));
//	
//	while(!List.isEmpty(List.rest(copy))) {
//		copy = copy.succ;
//		System.out.print(", " + copy.value);
//	}
//	System.out.print(")");
//	
//}