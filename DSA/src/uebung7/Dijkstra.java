package uebung7;

import java.util.HashMap;
import java.util.Map;

import uebung7.Graph.Knotenliste;

public class Dijkstra {

	private WeightedGraph g;
	private Matrix distance;
	private Matrix via;
	private int knotsCount;

	public Dijkstra(WeightedGraph w) {
		this.g = w;
		this.knotsCount = g.nachbarn.length;
		distance = g.weightedGraph.copy();
		via = new Matrix(knotsCount);
	}

	public void dijkstra(int startKnoten, int zielKnoten) {
		// Initializing
		int[] rot = new int[knotsCount];
		int[] gelb = new int[knotsCount];
		int[] gruen = new int[knotsCount];
		int[] lengthOfPath = new int[knotsCount];
		for (int i = 0; i < knotsCount; i++) {
			rot[i] = 1;
		}
		rot[startKnoten] = 0;
		gruen[startKnoten] = 1;
		Knotenliste k = g.nachbarn[startKnoten];
		while (k != null) {
			gelb[k.knoten] = 1;
			rot[k.knoten] = 0; 
			k = k.succ;
		}
		printArray(gruen);
		printArray(gelb);
		printArray(rot);
		int counter = 0;
		while (!arrayContains(gruen, zielKnoten) && !arrayEmpty(gelb) && counter < 10) {
			counter++;
			System.out.println("gruen:");
			printArray(gruen);

			System.out.println("gelb:");
			printArray(gelb);

			System.out.println("rot:");
			printArray(rot);
			// gruener Menge hinzufügen
			int min = Integer.MAX_VALUE;
			int newKnot = -1;
			for (Integer i : gruen) {
				for (Integer j : gelb) {
					if (distance.getValue(i, j) + lengthOfPath[i] < min) {
						min = distance.getValue(i, j) + lengthOfPath[i];
						newKnot = j;
					}
				}
			}
			if (min < Integer.MAX_VALUE) {
				gruen[newKnot] = 1;
				gelb[newKnot] = 0;
				lengthOfPath[newKnot] = min;
			}

			// gelber Menge hinzufügen

			for (Integer i : gelb) {
				for (Integer j : rot) {
					if (distance.getValue(i, j) < Integer.MAX_VALUE) {
						gelb[j] = 1;
						rot[j] = 0;
					}
				}
			}
		}

		if (arrayContains(gruen, zielKnoten)) {
			System.out.println("Der kürzeste Weg ist " + lengthOfPath[zielKnoten]);
		} else {
			System.out.println("Der Knoten ist von diesem Anfang aus nicht erreichbar!");
			;
		}

	}

	private boolean arrayEmpty(int[] arr) {
		for (int i : arr) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}

	private boolean arrayContains(int[] arr, int val) {
		for (int i : arr) {
			if (i == val) {
				return true;
			}
		}
		return false;
	}

	private void printArray(int[] arr) {
		System.out.print("Array: [");
		boolean first = true;
		for (int i : arr) {
			if (first) {
				System.out.print(i);
				first = !first;
			} else {
				System.out.print(", " + i);
			}
		}
		System.out.print(" ] \n");
	}

}
