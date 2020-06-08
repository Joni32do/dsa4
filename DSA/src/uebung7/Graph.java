package uebung7;

import javax.rmi.CORBA.Tie;

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
	}

	/**
	 * Feld mit den Adjazenzlisten
	 */
	public Knotenliste[] nachbarn;

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
			if (kanten[i].length != 2) {
				throw new IllegalArgumentException();
			}
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
			throw new IllegalArgumentException();
		}
		// Adjazenzlisten initialisieren
		nachbarn = new Knotenliste[max + 1];
		for (int i = 0; i < nachbarn.length; i++) {
			nachbarn[i] = null;
		}
		// Kanten einfuegen
		for (int i = 0; i < kanten.length; i++) {
			int von = kanten[i][0], nach = kanten[i][1];
			nachbarn[von] = new Knotenliste(nachbarn[von], nach);
		}
	}

	/**
	 * Main
	 */
	public static void main(String args[]) {
		int[][] input = { { 0, 1 }, { 1, 0 }, { 0, 3 }, { 1, 3 }, { 3, 4 }, { 4, 2 }, { 2, 4 }, { 2, 2 } };
		Graph g = new Graph(input);
		g.printMatrix(g.adjazenMatrix());
		g.tiefenSuche(2,4);
		g.tiefenSuche(1,3);
		g.tiefenSuche(0, 4);
		g.tiefenSuche(3, 3);
	}

	/**
	 * Adjazenzmatrix des Graphen
	 * 
	 * @return n*n - Matrix von int, Komponenten (i,j) ist 1, falls Kante von i nach
	 *         j ex. sonst 0
	 */
	public int[][] adjazenMatrix() {
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
		return matrix;
	}

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

	public void printMatrix(int[][] mat) {
		System.out.println(stringMatrix(mat));
	}

	public void tiefenSuche(int begKnot, int endKnot) {
		boolean[] visited = new boolean[nachbarn.length];

		// initialisiere visisited
		for (int i = 0; i < nachbarn.length; i++) {
			visited[i] = false;
		}

		visited = tiefenSuche(visited, begKnot, endKnot);
		System.out.println();
	}

	
	/**
	 * In der jetzigen Version hört das Programm nicht mit der Rekursion auf, 
	 * wenn der Zielknoten gefunden wurde
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
	
	public Knotenliste topologischeSortierung() {
		
		Knotenliste list = nachbarn[1];
		
		
		//Speichern das man bereits besuchte Felder nicht erneute besuchen muss	
		
		if(cuurentKnot == elem) {
			throw new IllegalArgumentException("Zyklus entdeckt bei" + currentKnot);
		}
		return null;
	}

}