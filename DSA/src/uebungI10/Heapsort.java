package uebungI10;

import java.util.Arrays;
import java.util.PriorityQueue;

import uebung6.TreeElem;

public class Heapsort {

	public static void main(String[] args) {
		PriorityQueue<TreeElem> pr = new PriorityQueue<TreeElem>();
		System.out.println("Hallo");
	}
	
	int x;
	int[] heap;

	public Heapsort(int[] a) {
		heap = a;
	}
}

//
//	/**
//	 * Prueft, ob a[l],...a[r] ein Max-Heap ist ( <= l < r < a.length)
//	 * 
//	 * @param a Feld mit zu ueberpruefendem Abschnitt
//	 * 
//	 * @param l Erste Komponente im Abschnitt
//	 * 
//	 * @param r Letzte Komponente im Abschnitt
//	 * 
//	 * @return true genau dann, wenn a[l],...a[r] ein Max-Heap ist
//	 */
//	public boolean isPartialHeap(int[] a, int l, int r) {
//		boolean b = true;
//		for (int i = l; i < r / 2 + 1; i++) {
//			if (2 * i + 1 <= r) {
//				if (!(a[2 * i + 1] <= a[i] && a[2 * i + 2] <= a[i])) {
//					b = false;
//					break; // sei nicht böse bitte  die Laufzeit freut sich vielleicht
//				}
////			b = false;
//			}
//		}
//		return b;
//	}
//
//	/**
//	 * Element a[l] in Heap a[l+1] ... a[r] einsinken lassen mit 0 <= l < r <
//	 * a.length
//	 * 
//	 * @param a      Vorher: Max-Heap in Komponenten l + 1 ... r, Nachher: um a[l]
//	 *               erweiterter Heap in Komponenten l .. r Uebrige Komponenten
//	 *               unveraendert
//	 * 
//	 * @param l      Erste Komponente im bisherigen Heap
//	 * 
//	 * @param Letzte Komponente im bisherigen Heap
//	 */
//
//	public int[] reheap(int[] a, int l, int r) {
//		int i = 0;
//		boolean isFinished = false;
//		while (!isFinished && 2*i+2 > r) {
//			int saveIt = a[i];
//			if (a[i] < a[2 * i + 1]) {
//				a[i] = a[2 * i + 1];
//				a[2 * i + 1] = saveIt;
//			} else if (a[i] < a[2 * i + 2]) {
//				a[i] = a[2 * i + 2];
//				a[2 * i + 2] = saveIt;
//			} else {
//				isFinished = true;
//			}
//			i++;
//		}
//		return a;
//	}
//	
//	public int[] sorteEsJetzt(int[] a) {
//		for(int i = a.length-1; i>=0 ; i--) {
////			System.out.println("hä");
//			if(isPartialHeap(a, a.length-i-1, i)) {
//				int saveIt = a[i];
//				a[i] = a[0];
//				a[0] = saveIt;
//				a = reheap(a, 0, i-1);
//				System.out.println(Arrays.toString(a));
//			}else{
//				System.out.println("throw_new_Exception(Warum_ist_das_so?); "+ i);
//			}
//		}
//		return a;
//	}
//}