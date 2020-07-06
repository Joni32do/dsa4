package uebung9;

import java.util.ArrayList;
import java.util.Comparator;

public class Hintransformation implements Comparator<BWElement> {

	ArrayList<BWElement> cooleListe = new ArrayList<BWElement>();
	int[] output;
	int start;

	public Hintransformation(int[] f) {
		generateTable(f);
		cooleListe.sort((o1, o2) -> compare(o1, o2));
		output = lastRow();
		start = searchForStart();
	}

	private int searchForStart() {
		int output = 0;
		for(int i = 0; i < cooleListe.size(); i++) {
				if(cooleListe.get(i).index == 0) {
					output = i;
			}
		}
		return output;
	}

	private int[] lastRow() {
		int lastIndex = cooleListe.size() - 1;
		int[] output = new int[cooleListe.size()];
		for (int i = 0; i < cooleListe.size(); i++) {
			if (cooleListe.get(i).index == 0) {
				output[i] = cooleListe.get(i).textRef[lastIndex];
			} else {
				output[i] = cooleListe.get(i).textRef[cooleListe.get(i).index - 1];
			}
		}
		return output;
	}

	/**
	 * @invariant o1.textref == o2.textref
	 * @requires o1.equals(o2) == false for all o
	 */
	@Override
	public int compare(BWElement o1, BWElement o2) {
		if (o1.textRef[o1.index] < o2.textRef[o2.index]) {
			return -1;
		}
		if (o1.textRef[o1.index] > o2.textRef[o2.index]) {
			return 1;
		} else {
			return compare(new BWElement(o1.textRef, Math.floorMod(o1.index + 1, o1.textRef.length)),
					new BWElement(o2.textRef, Math.floorMod(o2.index + 1, o2.textRef.length)));
			// vielleicht ist neue Elemente zu erzeugen zu kostspielig
		}
	}

	public void generateTable(int[] f) {

		for (int i = 0; i < f.length; i++) {
			cooleListe.add(new BWElement(f, i));
		}
	}
}
