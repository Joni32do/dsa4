package uebung1;

import java.util.LinkedList;
import java.util.List;

public class aufgabe2b {

	public static void main(String[] args) {
		// Initialisiere irgendeine Liste
		List<Double> list = new LinkedList<Double>();
		list.add(-5.0);
		list.add(-21.0);
		list.add(-52.0);
		list.add(-12345.0);
		list.add(-423.01);
		list.add(-5213.0);
		list.add(23513.0);
		list.stream().forEach(x -> System.out.println(x));
		
		System.out.println(calculateMaximumPartialSum(list));
	}
	
	/**
	 * Calculates the greatest partial sum
	 * 
	 */
	public static Folge calculateMaximumPartialSum(List<Double> input) {
		
		double gT = 0; //greatest partial sum
		double aS = 0; //current Sum
		int beg = 0; //begin
		int pBeg = 0; //possible begin
		int end = 0; //end
		boolean shift = false; //if there had been a shift and so there might be a new start
		
		
		for(int i = 0; i < input.size(); i++) {
			if(aS + input.get(i) > gT) {
				gT = aS + input.get(i);
				aS = aS + input.get(i);
				end = i;
				if(pBeg != 0) {
					beg = pBeg;
					pBeg = 0;
				}
				if(shift) {
					beg = i;
					shift = false;
				}
				
			}
			else if (aS + input.get(i) > 0) {
				aS = aS + input.get(i);
				if(shift) {
					pBeg = i;
					shift = false;
				}
				
			}
			else {
				aS = 0;
				shift = true;
			}
		}
		return new Folge(beg,end,gT);
	}

}
