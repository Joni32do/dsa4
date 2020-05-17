package uebung4;

import java.util.List;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) {

		
		int size = 10;
		int numberOfKeys = 20;

		OffenesHashing hashMash = new OffenesHashing(size);
		for (int i = 0; i < numberOfKeys; i++) {
			String value = rnd();
			OffenesHashing.add(hashMash, value);
		}

		OffenesHashing.printInformation(hashMash, numberOfKeys);

		LinearesSondieren lin = new LinearesSondieren(size);
		int erfolgloseSondierungsschritte = 0;
		int exception = 0;
		for (int i = 0; i < numberOfKeys; i++) {
			String value = rnd();
			try {
				erfolgloseSondierungsschritte += LinearesSondieren.add(lin, value);
			} catch (Exception exc) {
				exception++;
			}
		}
		System.out.println();
		System.out.println("Es musste für das Einfügen von " + numberOfKeys + " Schlüsseln in eine Liste mit " + size
				+ " Listengliedern " + erfolgloseSondierungsschritte + " sondiert werden.");
		System.out.println("Es gab dabei " + exception
				+ " Exceptions, bei denen das letzte Listenglied verschoben werden sollte.");

	}

	private static String rnd() {
		StringBuilder s = new StringBuilder();
		s.append((Math.random()));
		return s.toString();
	}

}