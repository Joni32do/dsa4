package uebung6;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map.Entry;

import uebung2.*;

public class Huffman {

	PriorityQueue<TreeElem> pr = new PriorityQueue<TreeElem>();
	SearchTree searchTree = new SearchTree();
	TreeElem finalTree;

	public Huffman(HashMap<Integer, Integer> amount) {
		super();
		for (Entry<Integer, Integer> elem : amount.entrySet()) {
			pr.add(new TreeElem(elem.getKey(), elem.getValue()));
		}
		pr = this.createHuffman();
		finalTree = pr.peek();
		createSearchTree();
//		TreeElem.printValues(finalTree, 0);
	}

	public static void main(String args[]) {

		CharacterCoding faust = new CharacterCoding();
		try {
			int[] data = faust.readFromFile("faust.txt");

			//Without move-to-front transformation
			data = doTheHuffman(data);
			faust.writeToFile("Without.txt", data);
			
			//With move-to-front transformation
			zippedCode z = Main.zip(data, Main.generateAlphabet(120));
			data = z.getZahlencode();
			data = doTheHuffman(data);
			z.setZahlencode(data);
			data = Main.unzip(z);
			faust.writeToFile("With.txt", data);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Encrypt the data with the huffmann coding and instantly decrypt it
	 * @param data
	 * @return
	 */
	public static int[] doTheHuffman(int[] data) {
		HashMap<Integer, Integer> amount = new HashMap<Integer, Integer>();
		for (Integer elem : data) {
			amount.putIfAbsent(elem, 0);
			amount.put(elem, amount.get(elem) + 1);
		}
		Huffman huff = new Huffman(amount);
		String s = huff.translate(data);
		information(s);

		data = huff.reBinary(s);
		return data;
	}

	private static void information(String s) {

		System.out.println("----------------------------------------------------");
		System.out.println("    D A S   S I N D   D I E   W I C H T I G E N     ");
		System.out.println("            I N F O R M A T I O N E N               ");
		System.out.println("Die Zeichenkette hat die Größe von " + s.length() / 8 + " Bytes");
		System.out.println("----------------------------------------------------");
	}

	public PriorityQueue<TreeElem> createHuffman() {

		if (this.pr.size() < 1) {
			throw new IllegalAccessError();
		}
		if (this.pr.size() == 1) {
			return this.pr;
		} else {
			this.pr = smallElemTree(this.pr);
			return createHuffman();
		}
	}

	private PriorityQueue<TreeElem> smallElemTree(PriorityQueue<TreeElem> pr2) {
		TreeElem tree = new TreeElem(pr2.remove(), pr2.remove());
		pr2.add(tree);
		return pr2;
	}

	private void createSearchTree() {
		searchTree = new SearchTree();
		createSearchTree("", this.finalTree);
	}

	private void createSearchTree(String s, TreeElem finalTree) {
		if (finalTree.left == null && finalTree.right == null) {
			searchTree.insert(finalTree.chaChaChar, s);
		}
		if (finalTree.left != null) {
			createSearchTree(s.concat("0"), finalTree.left);
		}
		if (finalTree.right != null) {
			createSearchTree(s.concat("1"), finalTree.right);
		}
	}

	/**
	 * From binary String back to int[]
	 * @requires String contains only 0 and 1
	 * @requires String is able to build a int[] with this.finalTree
	 * @param s
	 * @return
	 */
	private int[] reBinary(String s) {
		TreeElem tree = this.finalTree;
		int size = s.length();
		int[] outputArray = new int[getOutputSize(s)]; 
		int typer = 0;
		for (int i = 0; i < size; i++) {
			if (s.charAt(i) == '0' && tree.left != null) {
				tree = tree.left;
			} else if (s.charAt(i) == '1' && tree.right != null) {
				tree = tree.right;
			} else {
				outputArray[typer] = tree.chaChaChar;
				i--;
				typer++;
				tree = this.finalTree;
			}
		}
		outputArray[typer] = tree.chaChaChar; // necessary for last digit
		return outputArray;
	}

	/**
	 * A method which should not exist but is a simple way to gain the length of the output
	 * This could also be achieved by saving the length in the huff as a variable  
	 */
	private int getOutputSize(String s) {
		int output = 0;
		int size = s.length();
		TreeElem tree = this.finalTree;
		for (int i = 0; i < size; i++) {
			if (s.charAt(i) == '0' && tree.left != null) {
				tree = tree.left;
			} else if (s.charAt(i) == '1' && tree.right != null) {
				tree = tree.right;
			} else {
				output++;
				i--;
				tree = this.finalTree;
			}
			
		}
		return output + 1;
	}

	public String translate(int[] input) {
		StringBuilder s = new StringBuilder();
		for (int elem : input) {
			s.append(charToBinary(elem));
		}
		return s.toString();
	}

	private String charToBinary(int elem) {

		return searchTree.search(elem);
	}

}
