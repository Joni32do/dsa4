package uebung6;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import uebung2.*;
import uebung3.Tree;

public class Huffman {

	PriorityQueue<TreeElem> pr = new PriorityQueue<TreeElem>();
	SearchTree search = new SearchTree();
	TreeElem finalTree;

	public Huffman(HashMap<Integer, Integer> amount) {
		super();
		for (Entry<Integer, Integer> elem : amount.entrySet()) {
			pr.add(new TreeElem(elem.getKey(), elem.getValue()));
		}
		pr = this.createHuffman();
		finalTree = pr.peek();
		createSearchTree();
	}

	public static void main(String args[]) {

		CharacterCoding faust = new CharacterCoding();
		try {
			int[] data = faust.readFromFile("faust.txt");
			HashMap<Integer, Integer> amount = new HashMap<Integer, Integer>();
			

//			zippedCode z = Main.zip(data, Main.generateAlphabet(120));
//			int[] zippedData = z.getZahlencode();
			
			for (Integer elem : data) {
				amount.putIfAbsent(elem, 0);
				amount.put(elem, amount.get(elem) + 1);
			}
			Huffman huff = new Huffman(amount);
//			huff.pr.forEach(x -> printBinary(x,""));
			String s = huff.translate(data);
			information(s);
			data = huff.reBinary(s);
			faust.writeToFile("Matze", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void information(String s) {
		System.out.println(s.length());
		System.out.println("Es funktioniert!");
		System.out.println("Die Zeichenkette hat die Größe von " + s.length()/8 + " Bytes");
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
		search = new SearchTree();
		createSearchTree("", this.finalTree);
	}

	private void createSearchTree(String s, TreeElem finalTree) {

		if (finalTree.left == null && finalTree.right == null) {
			search.insert(finalTree.chaChaChar, s);
		}
		if (finalTree.left != null) {
			s = s.concat("0");
			createSearchTree(s, finalTree.left);
		}
		if (finalTree.right != null) {
			s = s.concat("1");
			createSearchTree(s, finalTree.right);
		}
	}

	/**
	 * @requires String contains only 0 and 1
	 * @param s
	 * @return
	 */
	private int[] reBinary(String s) {
		LinkedList<Integer> output = new LinkedList<Integer>();
		TreeElem tree = this.finalTree;
		boolean end = false;
		int harald = 0;
		while (!end) {
			if (s.length()<=harald) {
				end = true;
			} else {
				if (s.charAt(harald) == '0' && tree.left != null) {
					tree = tree.left;
				} else if (s.charAt(harald) == '1' && tree.right != null) {
					tree = tree.right;
				} else {
					output.add(tree.chaChaChar);
				}
				harald++;
			}

		}
		int size = output.size();
		int[] outputArray = new int[size];
		for(int i = 0; i < size; i++) {
			outputArray[i] = output.get(i);
			System.out.println(i);
		}
		return outputArray;
	}

	public String translate(int[] input) {
		StringBuilder s = new StringBuilder();
		for (int elem : input) {
			s.append(charToBinary(elem));
		}
		return s.toString();
	}

	private String charToBinary(int elem) {

		return search.search((char) elem);
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

}
