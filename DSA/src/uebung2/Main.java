package uebung2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) {

		testList();
//		
//		testTheZip();

		
		
//		
//		CharacterCoding coco = new CharacterCoding();
//		try {
//			int[] data = coco.readFromFile("faust.txt");
//			zippedCode z = zip(data, generateAlphabet(120)); 
//			data = unzip(z);
//			coco.writeToFile("Testung", data);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	

	}

	/**
	 * Generates a List with the first n digits
	 * @param n
	 * @return
	 */
	public static List generateAlphabet(int n) {
		if(n > 0) {
			return new List(n, generateAlphabet(n - 1));
		}
		else if(n == 0) {
			return new List(0, null); 
		}
		else {
			return null;
		}
	}

	private static void testTheZip() {
		int[] blabla = { 1, 2, 3, 4, 5, 1 , 1, 1, 1, 1, 2, 2};
		List blub = new List(1, new List(2, new List(3, new List(4, new List(5, null)))));
		zippedCode c = zip(blabla, blub);

		System.out.println(c);
		int[] output = unzip(c);
		for(int i: output) {
			System.out.println(i);
		}
	}

	/**
	 * @requires all Integer value of input must also be at least (and best) once in
	 *           the alphabet
	 * @requires input && alphabet are non-null
	 * @param input
	 * @param alphabeet
	 * @return
	 */
	public static zippedCode zip(int[] input, List alphabeet) {
		int i = 0;
		for (int elem : input) {
			if (List.inList(alphabeet, elem)) {
				int n = List.indexInList(alphabeet, elem);
				alphabeet = List.removeNthElement(alphabeet, n);
				alphabeet = List.append(elem, alphabeet);
				input[i] = n;
				i++;
			}

			else {
				System.out.println("Fuck me!");
			}
		}
		return new zippedCode(input, alphabeet);
	}


	@Test
	public void test() {
		List list = new List(1, new List(2, new List(3, new List(4, null))));
		assertEquals(List.first(list), 1);
//		assertEquals(List.rest(list), new List(2, new List( 3, new List(4,null))));
//		assertEquals(List.append(3, list));
	}


	public static int[] unzip(zippedCode c) {
		List alphabeet = c.getAlphabet();
		int[] output = c.getZahlencode().clone();
		int length = output.length;
		int[] copy = c.getZahlencode().clone();
		for(int i = 0; i < output.length; i++) {
			output[i] = List.first(alphabeet);
			alphabeet = List.firstToNth(alphabeet, copy[length-(i+1)]);
		}
		return invert(output);
		
		
	}
	
	private static int[] invert(int[] input) {
		int[] output = input.clone();
		for(int i = 0; i < input.length; i++) {
			output[i] = input[input.length-(i+1)];
		}
		return output;
	}

	

	public static void print(List a) {
		System.out.println(a);
	}
	
	public static void printi(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}
	
	
	
	
	
	

	
	/**
	 * testing the new Implemented List
	 */
	private static void testList() {
		List list = new List(1, new List(2, new List(3, new List(4, new List(2, new List(3, new List(4, null)))))));
		print(list);
		for(int i = 0; i < 7; i++) {
			list = List.NthToFirst(list, i);
			print(list);
		}
//		System.out.println(List.indexInList(list,3));
//		System.out.println(list);
//		System.out.println(List.size(list));
//		print(List.rest(list));
//		print(List.concat(list, new List(2, new List(3, new List(4, null)))));
//		print(List.concat(list, new List(4, null)));
//		print(List.append(2, list));
//		print(list);
//		System.out.println(List.indexInList(list, 4));
//		System.out.println(List.inList(list, 5));
//		System.out.println(list);
//		System.out.println(List.removeNthElement(list, 1));
//		System.out.println(List.removeNthElement(list, 0));
//		print(list);
//		print(List.firstToNth(list, 5));
//		print(List.firstToNth(list, 0));
//		print(list);

	}
}
	
