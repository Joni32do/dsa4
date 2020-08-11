package uebung7;

import java.util.LinkedList;

/**
 * Gerichtete Graphen mittels Adjazenzlisten
 * 
 * Knoten sind numeriert 0 .. n - 1, die Adjazenzliste eines Knotens i ist eine
 * einfach verkettete Liste mit Listenkopf in nachbarn[i]
 * 
 */
public class Graph {
	/**
	 * Lokale Klasse fuer die einfach verkette Listen mit Knotennummern
	 * 
	 */
	class Knotenliste {
		/**
		 * Knotennummer
		 */
		public int knoten;
		/**
		 * Nachfolger
		 */
		public Knotenliste succ;

		public Knotenliste(Knotenliste l, int v) {
			knoten = v;
			succ = l;
		}

		public int length() {
			int i = 1;
			Knotenliste lauf = this;
			while (lauf.succ != null) {
				i++;
				lauf = this.succ;
			}
			return i;
		}
	}

	/**
	 * Feld mit den Adjazenzlisten
	 */
	public Knotenliste[] nachbarn;

	public int[] eingangsgrad;
	public int[] ausgangsgrad;
	public int[] grad;
	public int[][] adjazenzMatrix;

	/**
	 * Graph aus Feld von Kanten erstellen
	 * 
	 * @param kanten Feld von Feldern von je zwei nichtneg. int, {i, j} fuegt Kante
	 *               von i nach j ein
	 */
	public Graph(int[][] kanten) {
		int min = 0, max = 0;
		// Maximale Knotennummer bestimmen,
		// dabei pruefen, dass alle Felder 2 nichtneg.
		// Zahlen enthalten
		for (int i = 0; i < kanten.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (kanten[i][j] < min) {
					min = kanten[i][j];
				}
				if (kanten[i][j] > max) {
					max = kanten[i][j];
				}
			}
		}
		if (min < 0) {
			throw new IllegalArgumentException("Knoten ist negativ");
		}
		// Adjazenzlisten initialisieren
		nachbarn = new Knotenliste[max + 1];
		eingangsgrad = new int[max + 1];
		ausgangsgrad = new int[max + 1];
		grad = new int[max + 1];

		for (int i = 0; i < nachbarn.length; i++) {
			nachbarn[i] = null;
			eingangsgrad[i] = 0;
			ausgangsgrad[i] = 0;
			grad[i] = 0;
		}
		// Kanten einfuegen
		for (int i = 0; i < kanten.length; i++) {
			int von = kanten[i][0], nach = kanten[i][1];
			nachbarn[von] = new Knotenliste(nachbarn[von], nach);
			ausgangsgrad[von]++;
			eingangsgrad[nach]++;
		}

		for (int i = 0; i < grad.length; i++) {
			grad[i] = eingangsgrad[i] + ausgangsgrad[i];
		}
	}

	/**
	 * Main
	 */
	public static void main(String args[]) {
//		int[][] input = { { 0, 1 }, { 1, 0 }, { 0, 3 }, { 1, 3 }, { 3, 4 }, { 4, 2 }, { 2, 4 }, { 2, 2 } };
//		int[][] input = { {0,1},{1,2},{2,3},{3,4},{3,5} };´
		int[][] input = { {6,1},{1,2},{2,3},{3,4},{3,5},{0,6} };
		Graph g = new Graph(input);
		g.printMatrix(g.adjazenzMatrix());
		g.tiefenSuche(2);
		g.tiefenSuche(1);
		g.tiefenSuche(0);
		g.tiefenSuche(3);
		LinkedList<Integer> test = g.topologischeSortierung(1);
		test.forEach(x -> System.out.println(x));
	}

	/**
	 * Adjazenzmatrix des Graphen
	 * 
	 * @return n*n - Matrix von int, Komponenten (i,j) ist 1, falls Kante von i nach
	 *         j ex. sonst 0
	 */
	public int[][] adjazenzMatrix() {
		if(adjazenzMatrix != null) {
			return adjazenzMatrix;
		} else {
		int dim = nachbarn.length;
		int[][] matrix = new int[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int k = 0; k < dim; k++) {
				matrix[i][k] = 0;
			}
			Knotenliste lauf = nachbarn[i];
			while (lauf != null) {
				matrix[i][lauf.knoten] = 1;
				lauf = lauf.succ;
			}
		}
		adjazenzMatrix = matrix;
		return matrix;
		}
	}

	
	/**
	 * Creates a String Representation of a matrix
	 * @param mat is a n * m matrix
	 * @returns the String representation
	 */
	public String stringMatrix(int[][] mat) {
		StringBuilder s = new StringBuilder();
		s.append("\n");
		for (int i = 0; i < mat.length; i++) {
			s.append("   |");
			for (int j = 0; j < mat.length; j++) { // da quadratisch kann auch mat.length
				s.append(" ");
				s.append(mat[i][j]);
				s.append(" ");
			}
			s.append("| \n");
		}
		return s.toString();
	}

	/**
	 * Prints the matrix out in the console
	 * @param mat
	 */
	public void printMatrix(int[][] mat) {
		System.out.println(stringMatrix(mat));
	}

	
	/**
	 * Beginn der Tiefensuche
	 * 
	 * Initialisiert boolean array und benutzt diesen für eine iterative Suche
	 * @param begKnot
	 */
	public void tiefenSuche(int begKnot) {
		boolean[] visited = new boolean[nachbarn.length];

		// initialisiere visisited
		for (int i = 0; i < nachbarn.length; i++) {
			visited[i] = false;
		}
		visited = tiefenSuche(visited, begKnot);
		System.out.println(); //Just to make space
	}

	/**
	 * rekursiver Aufruf mit liste die bereits bearbeitete Knoten enthält
	 */
	private boolean[] tiefenSuche(boolean[] visited, int currentKnot) {
		if (!visited[currentKnot]) {
			System.out.println("Bearbeitung des " + currentKnot + ". Knotens wird begonnen");

			visited[currentKnot] = true;
			Knotenliste lauf = nachbarn[currentKnot];
			while (lauf != null) {
				visited = tiefenSuche(visited, lauf.knoten);
				lauf = lauf.succ;

			}
			System.out.println("Bearbeitung des " + currentKnot + ". Knotens ist vollständig");
		}

		return visited;
	}



//	class Sequence {
//		boolean[] visited;
//		LinkedList<Integer> list;
//
//		public Sequence(boolean[] visited, LinkedList<Integer> list) {
//			super();
//			this.visited = visited;
//			this.list = list;
//		}
//
//	}

	public LinkedList<Integer> topologischeSortierung(int begKnot) {
		
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		boolean finished = false;
		int[] currentEinGrad = eingangsgrad.clone();

		boolean[] visited = new boolean[nachbarn.length];
		// initialisiere visisited
		for (int i = 0; i < nachbarn.length; i++) {
			visited[i] = false;
		}
		//Schleife statt iterativ bis fertig, da wenn Topologische Sortierung existiert,
		//alle Knoten einmal hinzugefügt werden müssen
		for(int j = 0; j < nachbarn.length;j++) {
			
			int i = 0;
			
			/** Sucht mir den neusten verfügbaren leeren Knoten, der den 
			*   Eingangsgrad 0 hat, erhöht mir mein i bis zu dem ersten Verfügbaren
			*   Knoten, gibt es diesen nicht, so wurde ein Zyklus entdeckt
			**/
			while (visited[i] || currentEinGrad[i] != 0) {
				i++;
				if (i >= nachbarn.length) {
					throw new IllegalArgumentException("Zyklus entdeckt !");
				}
			}
			linkedList.add(i);
			visited[i] = true;
			Knotenliste lauf = nachbarn[i];
			
			/**
			 * Geht die Adjazenzliste durch und vermindert für jeden der Knoten
			 * den geklonten Eingangsgrad um eins
			 */
			while (lauf != null) {
				currentEinGrad[lauf.knoten]--;
				lauf = lauf.succ;
			}
			
		}
		return linkedList;
	}

	
	
	
	
	
	
	
	
	/**
	 * Hier hab ich die Tiefensuche überladen, sodass man gegebenfalls auch nach
	 * einem speziellen Knoten suchen kann
	 */

	public void tiefenSuche(int begKnot, int endKnot) {
		boolean[] visited = new boolean[nachbarn.length];

		// initialisiere visisited
		for (int i = 0; i < nachbarn.length; i++) {
			visited[i] = false;
		}

		visited = tiefenSuche(visited, begKnot, endKnot);
	}

	/**
	 * In der jetzigen Version hört das Programm nicht mit der Rekursion auf, wenn
	 * der Zielknoten gefunden wurde
	 * 
	 * 
	 */
	private boolean[] tiefenSuche(boolean[] visited, int currentKnot, int endKnot) {
		if (!visited[currentKnot]) {
			System.out.println("Bearbeitung des " + currentKnot + ". Knotens wird begonnen");

			if (currentKnot == endKnot) {
				visited[currentKnot] = true;
				System.out.println("Der Endknoten wurde gefunden!");

			} else {
				visited[currentKnot] = true;
				Knotenliste lauf = nachbarn[currentKnot];
				while (lauf != null) {
					visited = tiefenSuche(visited, lauf.knoten, endKnot);
					lauf = lauf.succ;
				}
			}
			System.out.println("Bearbeitung des " + currentKnot + ". Knotens ist vollständig");
		}

		return visited;
	}

}