package uebung4;

public class TestMain {

	
	public static void main(String[] args) {
		
		int hashSize = 10;
		int numberOfKeys = 20;
		
		OffenesHashing HashMash = new OffenesHashing(hashSize, "SuperDuperSchokoflakes");
		int HashKey = 0;
		for(int i = 0; i < numberOfKeys; i++) {
			HashKey = createPseudoHashKey();
			System.out.println(HashKey);
		}
		
		printInformation(hashSize, numberOfKeys, HashMash),
	}

	private static void printInformation(int hashSize, int numberOfKeys, OffenesHashing hashMash) {
		
		System.out.println("Groesse der Hashtabelle: " + hashSize);
		System.out.println("Anzahl eingefuegter Schluessel: " + numberOfKeys);
		int alreadyCountedKeys = 0;
		int keyCombi = 0; //How many keys in a container will be searched
		while(alreadyCountedKeys < numberOfKeys) {
			int currentAmount = countKeysPerContainer(hashMash, keyCombi);
			double expectedValue = 1;
			System.out.println(" Behälter mit " + keyCombi + " "
					+ "Schluesseln (erwartet: " + expectedValue + " )");
			keyCombi++;
		}
		
	}

	private static int countKeysPerContainer(OffenesHashing hashMash, int keyCombi) {
		int countOfContainerWithKeyCombi =  
		
	}

	private static int createPseudoHashKey() {
		double d = Math.random() * 10;
		return (int) d;
	}
	
	
//	private static double binomialVerteilung(double n, double k, double p) {
//		
//	}
}
