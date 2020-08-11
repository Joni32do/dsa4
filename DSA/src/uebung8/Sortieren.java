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
	public static int inversioncount = 0;

	public static void quicksort(int[] a) {
		quicksort(a, 0, a.length - 1);
	}

	public static void quicksort(int[] a, int from, int to) {
		if (to > from) {
			int pivot = a[(from + to) / 2]; // willkürliche Wahl des Pivot Elements
			int x = from;
			int y = to;
			boolean inv = false;
			while (x < y) {
				inv = !inv;
				while (a[x] < pivot) {
					x++;
				}
				while (a[y] > pivot) {
					y--;
				}
				if (x <= y) {
					int memo = a[x];
					a[x] = a[y];
					a[y] = memo;
					if (inv) {
						inversioncount += 1 + (y - x - 1) * 2;
					} else {
						inversioncount -= 1 + (y - x - 1) * 2;
					}
					x++;
					y--;
				}
			}
			quicksort(a, from, y);
			quicksort(a, x, to);
		}
	}

	public static void slowsort(int[] a) {
		slowsort(a, 0, a.length);
	}

	public static void slowsort(int[] a, int i, int j) {
		if (i >= j) {
			int m = (i + j) / 2;
			slowsort(a, i, m);
			slowsort(a, m + 1, j);
			if (a[j] < a[m]) {
				int p = a[m];
				a[m] = a[j];
				a[j] = p;
			}
			slowsort(a, i, j - 1);
		}
	}

	public static void mergesort(int[] a) {
		mergesort(a, 0, a.length - 1);
	}

	public static void mergesort(int[] a, int from, int to) {
		if ((to - from) > 0) {
			int divide = (from + to)/2;
			mergesort(a, from, divide);
			mergesort(a, divide + 1, to);
			int x = 0;
			int y = divide + 1;
			int[] memory = new int[divide - from + 1];
			for (int i = 0; i < divide - from + 1; i++) {
				memory[i] = a[from + i];
			}
			int i = from;
			while (x < memory.length) {
				if (y > to) {
					a[i] = memory[x];
					x++;
				} else if (memory[x] < a[y]) {
					a[i] = memory[x];
					x++;
				} else {
					a[i] = a[y];
					y++;
					inversioncount += memory.length - x; 
				}
				i++;
			}

		}
	}

	/**
	 * Haupt-Methode (noch sehr unvollstaendig)
	 *
	 * @param args Kommandozeilenparameter (hier nicht verwendet)
	 */
	public static void main(String[] args) {
		int[] a = { 27, 4, 7, 0, 2, 9, 15 };
//		quicksort(a);
//		druckeFeld(a, "", "-", "");

		System.out.println("Quicksort:");
		a = erzeugeFeld(5, 10);
		druckeFeld(a, "Test - Vorher: ", "-", ".\n");
		starteUhr();
		quicksort(a);
		System.out.println(wieLange());
		druckeFeld(a, "Test - Nachher: ", "-", ".\n");
		System.out.println("Inversioncount: " + inversioncount);
		inversioncount = 0;

		System.out.println("Mergesort:");
		a = erzeugeFeld(10, 4);
		druckeFeld(a, "Test - Vorher: ", "-", ".\n");
		starteUhr();
		mergesort(a);
		System.out.println(wieLange());
		druckeFeld(a, "Test - Nachher: ", "-", ".\n");
		System.out.println("Inversioncount: " + inversioncount);

//		System.out.println("Bubblesort:");
//		a = erzeugeFeld(5, 100);
////        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
//		starteUhr();
//		bubblesort(a);
//		System.out.println(wieLange());
////        druckeFeld(a, "Test - Nachher: ", "-", ".\n");
//
//		System.out.println("Insertsort:");
//		a = erzeugeFeld(50000, 10000000);
////        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
//		starteUhr();
//		insertsort(a);
//		System.out.println(wieLange());
//        druckeFeld(a, "Test - Nachher: ", "-", ".\n");

//		System.out.println("Slowsort:");
//		a = erzeugeFeld(5, 100);
//        druckeFeld(a, "Test - Vorher:  ", "-", ".\n");
//		starteUhr();
//		slowsort(a);
//		System.out.println(wieLange());
//        druckeFeld(a, "Test - Nachher: ", "-", ".\n");
	}
}
