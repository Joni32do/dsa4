package uebung8;

/**
 * Programmrahmen zur Implementierung von Sortierverfahren
 */
public class Sortieren {
	/** Beginn der Zeitrechnung von wieLange() **/
	static long startZeit;

	/**
	 * Speichert aktuelle Uhrzeit fuer die folgenden Aufrufe von wieLange()
	 * 
	 */
	static void starteUhr() {
		startZeit = System.currentTimeMillis();
	}

	/**
	 * Zeit seit dem letzten Aufruf von starteUhr()
	 * 
	 * @return Verstrichene Zeit in Sekunden
	 */
	static double wieLange() {
		long jetzt = System.currentTimeMillis();
		return (double) (jetzt - startZeit) / 1000.0;
	}

	/**
	 * Neues Feld mit Zufallszahlen
	 * 
	 * @param n   So viele Komponenten, bitte
	 * @param max Komponenten sind im Bereich 1 .. max
	 * @return Neues Feld
	 */
	static int[] erzeugeFeld(int n, int max) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = (int) (Math.random() * max);
		}
		return a;
	}

	/**
	 * Feld ausgeben
	 * 
	 * @param a       Auszugebendes Feld
	 * @param anfang  Text vor der ersten Komponente
	 * @param trenner Text zwischen zwei Komponenten
	 * @param ende    Text nach der letzten Komponente
	 */
	static void druckeFeld(int[] a, String anfang, String trenner, String ende) {
		System.out.print(anfang);
		for (int i = 0; i < a.length; i++) {
			if (i > 0) {
				System.out.print(trenner);
			}
			System.out.print(a[i]);
		}
		System.out.print(ende);
	}

	/**
	 * Bubblesort
	 * 
	 * @param a Feld, das sortiert wird.
	 */
	static void bubblesort(int[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					int p = a[j];
					a[j] = a[j + 1];
					a[j + 1] = p;
				}
			}
		}
	}
	
	/**
	 * Insertsort
	 * 
	 * @param a Feld, das sortiert wird
	 */
	static void insertsort(int[] a) {
		long vertauschungen = 0;
		long vergleiche = 0;
		
		for (int i = 1; i < a.length; i++) {
			int j = i - 1;
			int change = a[i];
			vergleiche++;
			while (j >= 0 && a[j] > change) {
				vertauschungen++;
				vergleiche++;
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = change;
		}
		System.out.println("Vertauschungen: " + vertauschungen);

		System.out.println("Vergleiche: " + vergleiche);
	}
	
	/**
	 * Quicksort
	 * 
	 */
	public static void Quicksort(){
		
	}

	public static void slowsort(int[] a) {
		slowsort(a, 0, a.length);
	}

	public static void slowsort(int[] a, int i, int j) {
		if(i >= j) {
			int m = (i+j)/2;
			slowsort(a,i,m);
			slowsort(a,m+1,j);
			if(a[j]<a[m]) {
				int p = a[m];
				a[m] = a[j];
				a[j] = p;
			}
			slowsort(a,i,j-1);
		}
	}

	/**
	 * Haupt-Methode (noch sehr unvollstaendig)
	 *
	 * @param args Kommandozeilenparameter (hier nicht verwendet)
	 */
	public static void main(String[] args) {
		int[] a;

		System.out.println("Bubblesort:");
		a = erzeugeFeld(5, 100);
//        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
		starteUhr();
		bubblesort(a);
		System.out.println(wieLange());
//        druckeFeld(a, "Test - Nachher: ", "-", ".\n");

		System.out.println("Insertsort:");
		a = erzeugeFeld(500000, 10000000);
//        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
		starteUhr();
		insertsort(a);
		System.out.println(wieLange());
//        druckeFeld(a, "Test - Nachher: ", "-", ".\n");
		
		System.out.println("Slowsort:");
		a = erzeugeFeld(5, 100);
        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
		starteUhr();
		slowsort(a);
		System.out.println(wieLange());
        druckeFeld(a, "Test - Nachher: ", "-", ".\n");
	}
}
