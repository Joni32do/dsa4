package uebung7;

public class Floyd {

	private WeightedGraph g;
	private Matrix distance;
	private Matrix via;
	private int knots;

	public static void main(String args[]) {
		int[][] input = { { 0, 1, 2 }, { 0, 2, 6 }, { 0, 3, 10 }, { 1, 2, 3 }, { 1, 4, 8 }, { 1, 3, 5 }, { 1, 0, 4 },
				{ 2, 3, 4 }, { 3, 4, 1 }, {5,4,7} };

		WeightedGraph g = new WeightedGraph(input, 6);
		Floyd f = new Floyd(g);
		Dijkstra d = new Dijkstra(g);
		f.floyd();
		f.printFloyd();
		f.printPath(0,4);
//		d.dijkstra(0, 4);
	}

	public Floyd(WeightedGraph g) {
		this.g = g;
		this.knots = g.nachbarn.length;
		distance = g.weightedGraph.copy();
		via = new Matrix(knots);
		this.floyd();
	}

	private void floyd() {
		for (int i = 0; i < knots; i++) {
			for (int j = 0; j < knots; j++) {
				for (int k = 0; k < knots; k++) {
					if (distance.getValue(i, k) == Integer.MAX_VALUE || distance.getValue(k, j) == Integer.MAX_VALUE) {

					} else if (distance.getValue(i, j) > distance.getValue(i, k) + distance.getValue(k, j)) {
						distance.setValue(i, j, distance.getValue(i, k) + distance.getValue(k, j));
						via.setValue(i, j, k);
					}
				}
			}
		}

	}
	
	public void printFloyd() {
		System.out.println("Das sind die Ergebnisse des kürzesten Weges mit dem Floyd Algorithmus: \n");
		System.out.println("Das ist die neue Kostenmatrix:");
		System.out.println(this.distance);
		System.out.println("Das sind die Knoten über die Gegangen werden muss");
		System.out.println(this.via);
	}
	private void printPath(int anfang, int ende) {
		if(distance.getValue(anfang, ende) < Integer.MAX_VALUE) {
			System.out.println("Der Pfad von " + anfang + " zu " + ende + " kostet " + distance.getValue(anfang, ende));
			
			printOnlyPath(anfang, ende);
			System.out.println();
		} else {
			System.out.println("Der Pfad von " + anfang + " zu " + ende + " ist nicht erreichbar");
		}
	}

	private void printOnlyPath(int anfang, int ende) {
		if(via.getValue(anfang, ende) < Integer.MAX_VALUE) {
			printOnlyPath(anfang, via.getValue(anfang, ende));
			System.out.print(" -> " + ende);
		} else {
			System.out.print("Der Pfad: " + anfang + " -> " + ende);
		}
	}

}
