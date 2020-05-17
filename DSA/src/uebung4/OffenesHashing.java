package uebung4;

import java.util.HashMap;
import java.util.Map;


/**
 * Bitte darf ich das nächste mal wieder nicht statische Methoden benutzen (dynamische Methoden)
 * @author Jonathan
 *
 */
public class OffenesHashing {

	public String value;
	public OffenesHashing nextKey;
	public OffenesHashing succesor;
	public int size;

	public OffenesHashing(String value) {
		this.value = value;
	}

	public OffenesHashing(Integer size) {
		if (size > 0) {
			this.size = size;
			this.succesor = new OffenesHashing(size - 1);
		}
	}

	
	public static void add(OffenesHashing hash, String value) {
		add(hash, OffenesHashing.generateHashCode(hash, value),value);
	}
	
	/**
	 * Adds the String to the hash Code
	 */
	public static void add(OffenesHashing hash, Integer hashCode, String value) {
		if (hashCode != 0) {
			add(hash.succesor, hashCode - 1, value);
		} else {
			if (hash.value == null) {
				hash.value = value;
			} else if (hash.nextKey == null) {
				hash.nextKey = new OffenesHashing(value);
			} else {
				add(hash.nextKey, hashCode, value);
			}
		}
	}

	public static int generateHashCode(OffenesHashing hash, String s) {
		int hashCode = s.chars().sum();
		hashCode = hashCode % (hash.size); //vielleicht -1 wenn ich blöd bin
		return hashCode;
	}
	
	/**
	 * Ich weiß ich weiß, warum benutz ich jetzt hier eine Map
	 * @param hash
	 * @return
	 */
	public static Map<Integer, Integer> count(OffenesHashing hash){
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		int size = hash.size;
		for(int i = 0; i < size; i++) {
			Integer keyCount = countKeys(hash);
			if(map.containsKey(keyCount)) {
				map.put(keyCount, map.get(keyCount) + 1);
			}
			else {
				map.put(keyCount, 1);
			}
			hash = hash.succesor;
		}
		return map;
	}

	
	
	private static Integer countKeys(OffenesHashing hash) {
		if(hash.value == null) {
			return 0;
		}
		else if(hash.nextKey == null) {
			return 1;
		}
		else {
			return countKeys(hash.nextKey) + 1;
		}
	}
	
public static void printInformation(OffenesHashing hashMash, int totalNumberOfKeys) {
		
		Map<Integer, Integer> map = OffenesHashing.count(hashMash);
		
		System.out.println("Groesse der Hashtabelle: " + hashMash.size);
			
		System.out.println("Anzahl eingefuegter Schluessel: " + totalNumberOfKeys);
		
		map.forEach((keysInContainer,container) -> System.out.println("In " + container + " Containern sind " + 
				keysInContainer + " Schlüssel. Die zu erwartende Wahrscheinlichkeit wäre " + 
				getProbability(hashMash, keysInContainer)));		
	}

	private static String getProbability(OffenesHashing hashMash, int keysInContainer) {
			double probability = 1 / (double) hashMash.size;
			Double result = hashMash.size * (binKoeffizient(hashMash.size, keysInContainer))*
					(Math.pow(probability, keysInContainer) * (Math.pow(1- probability, hashMash.size - keysInContainer)));
			return result.toString();
		
	}
	
	private static long binKoeffizient(long n, long k) {
		if (k == 0) return 1;
		else if (k > n) return 0;
		else return binKoeffizient(n-1, k-1) + binKoeffizient(n-1, k);
    }
	

}
