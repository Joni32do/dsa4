package uebung6;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map.Entry;

import uebung2.*;
import uebung3.Tree;

public class Huffman {

	PriorityQueue<TreeElem> pr = new PriorityQueue<TreeElem>();

	public Huffman(HashMap<Integer, Integer> amount) {
		super();
		for (Entry<Integer, Integer> elem : amount.entrySet()) {
			pr.add(new TreeElem(elem.getKey(), elem.getValue()));
		}
		pr = this.createHuffman();
	}
	

	public static void main(String args[]) {

		CharacterCoding faust = new CharacterCoding();
		try {
			int[] data = faust.readFromFile("faust.txt");
			HashMap<Integer, Integer> amount = new HashMap<Integer, Integer>();
			for (Integer elem : data) {
				amount.putIfAbsent(elem, 0);
				amount.compute(elem, (x, y) -> y++);
			}
			Huffman huff = new Huffman(amount);
			huff.pr.forEach(x -> binary(x,""));
			data
			
//			zippedCode z = Main.zip(data, Main.generateAlphabet(120));
//			faust.writeToFile("Matze", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PriorityQueue<TreeElem> createHuffman() {

		if(this.pr.size() < 1) {
			throw new IllegalAccessError();
		}
		if(this.pr.size() == 1) {
			return this.pr;
		} else {
			this.pr = smallElemTree(this.pr);
			return createHuffman();
		}
	}

	private PriorityQueue<TreeElem> smallElemTree(PriorityQueue<TreeElem> pr2) {
		TreeElem tree = new TreeElem(pr2.remove(),pr2.remove());
		pr2.add(tree);
		return pr2;
	}
	
	public static void printBinary(TreeElem t, String s) {
		String sRight = s;
		if (!TreeElem.isEmpty(t.left)) {
			s = s.concat("0");
			/**
			 * @invariant s.length() = numbersOfRecursion
			 * @variant current depth of the knot to the depth of the leave
			 */
			printBinary(t.left, s);
		}
		if (!TreeElem.isEmpty(t.right)) {
			sRight = sRight.concat("1");
			printBinary(t.right, sRight);
		}
		if (TreeElem.isEmpty(t.right) && TreeElem.isEmpty(t.left)) {
			System.out.println(s);

		}

	}
	
	public String translate(int[] input) {
		StringBuilder s = new StringBuilder();
		for(int elem: input) {
			s.append(charToBinary());
		}
		return s.toString();
	}


	private String charToBinary() {
		
		return "Hallo";
	}


}
