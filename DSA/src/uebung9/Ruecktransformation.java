package uebung9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Ruecktransformation implements java.util.Comparator<BWElement> {

	public int[] originalData;

	
	//Hier steckt das (ungenutzte) Potential
	@Override
	public int compare(BWElement arg0, BWElement arg1) {
		
		return 0;
	}
	

	
	/**
	 * Die Ruecktransformation der Burrow-Wheeler Transformation, um die Orginal
	 * Datei zu erhalten
	 * 
	 * Funktioniert über die gespeicherten Informationen aus der letzten und
	 * Codierten Zeile und der ersten die über Sortieren wieder erstellt werden
	 * kann.
	 * 
	 * Dauer: Zu lang! Etwa 50-60s auf meinem Surface Go beim Faust
	 * 
	 * 
	 * TODO: Ist es möglich die zwei for-Schleifen um den Zeiger anzupassen zu
	 * ersetzen
	 * 
	 * @param elem
	 */
	public Ruecktransformation(BWElement elem) {
		int[] sorted = elem.textRef.clone();
		Arrays.sort(sorted);
		originalData = new int[sorted.length];

		// Output wird mit jeder Iteration weiter aufgefüllt
		for (int i = 0; i < sorted.length; i++) {

			originalData[i] = sorted[elem.index];
			// Zeiger herausfinden (zaehlen der wie vielte Buchstabe)
			int countSameLetter = 1;
			for (int j = 0; j < elem.index; j++) {
				if (originalData[i] == sorted[j]) {
					countSameLetter++;
				}
			}
			// Zeiger anpassen (Schauen wann der x-te Buchstabe im Codierten)
			// Verbesserungsmöglichkeiten
			int k = 0;
			while (countSameLetter > 0) {
				if (elem.textRef[k] == originalData[i]) {
					countSameLetter--;
				}
				k++;
			}
			elem.index = k - 1;

			// Noch etwas unschoene Schleife

		}

	}

	/**
	 * Statt neu berechnen Array Abfrage
	 */
	public Ruecktransformation(BWElement elem, int c) {

		int[] sorted = elem.textRef.clone();
		Arrays.sort(sorted);
		int[] sortedNthLatter = new int[sorted.length];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < sorted.length; i++) {
			map.computeIfPresent(sorted[i], (x, y) -> y + 1);
			map.putIfAbsent(sorted[i], 1);
			sortedNthLatter[i] = map.get(sorted[i]);
		}
		map.clear();
		int[] elemNthLatter = new int[sorted.length];
		for (int i = 0; i < sorted.length; i++) {
			map.computeIfPresent(elem.textRef[i], (x, y) -> y + 1);
			map.putIfAbsent(elem.textRef[i], 1);
			elemNthLatter[i] = map.get(elem.textRef[i]);
		}
		Map<valueAndAmount, Integer> mapper = new HashMap<valueAndAmount, Integer>();
	
		for(int i = 0; i < sorted.length; i++) {
			mapper.put(new valueAndAmount(elem.textRef[i], elemNthLatter[i]), i);
		}
		System.out.println(mapper.size());
		mapper.forEach((x,y) -> System.out.println(x.amount + " amount  " + x.value + " value  " + y + " index"));
		originalData = new int[sorted.length];

		for (int i = 0; i < sorted.length; i++) {
			
			originalData[i] = sorted[elem.index];
			if(mapper.containsKey(new valueAndAmount(sorted[elem.index],
					sortedNthLatter[elem.index]))) {
			elem.index = mapper.get(new valueAndAmount(sorted[elem.index],
					sortedNthLatter[elem.index]));
			} else {
				System.out.println("Wieso funktioniert das .equals nicht!");
				}
		}
	}


//	
//	/*
//	 * Schlechteste, ABER funktionierende Methode!!!
//	 */
//	public Ruecktransformation(int[] data, int start, boolean willManWirklichDieseVerwenden) {
//		ArrayList<int[]> list = new ArrayList<int[]>();
//		for(int i = 0;i<data.length; i++) {
//			int[] array = new int[data.length];
//			for(int j = 0; j < data.length; j++) {
//				array[j] = -1;
//			}
//			list.add(array);
//		}
//		for (int j = data.length - 1; j >= 0; j--) {
//			for (int i = 0; i < data.length; i++) {			
//				list.get(i)[j] = data[i];
//			}
//			list.sort((o1, o2) -> compare(o1, o2));
////			for(int[] elem: list) {
////				System.out.println(Arrays.toString(elem));
////			}
//		}
//		originalData = list.get(start);
//		
//	}
//
//	public int[] originalData;
//
//	@Override
//	public int compare(int[] arg0, int[] arg1) {
//		if (arg0.length == arg1.length) {
//			for (int i = 0; i < arg0.length; i++) {
//				if (arg0[i] == -1 || arg1[i] == -1) {
////					System.out.println("Dann funktonierts halt nicht!");
//				} else {
//					if (arg0[i] > arg1[i]) {
//						return 1;
//					}
//					if (arg0[i] < arg1[i]) {
//						return -1;
//					}
//				}
//			}
//			return 0;
//		} else {
//			return 0;
//		}
//	}

}
