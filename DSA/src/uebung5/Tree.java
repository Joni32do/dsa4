package uebung5;

/**
 * Class creates a Tree, which itself contains two (smaller, baby) Trees or null
 * and an Value
 * 
 * It can manipulate the Tree and perform certain operations like binary or
 * String representation
 * 
 * Notes: It would have been much better to use Optional.of() instead of null
 * but we wanted to keep it simple Also you could make it generic but then the
 * Operations could't be static, so I choose integer as the value which a tree
 * is capable to hold
 * 
 * 
 * @version 1.1
 * @author Matthias Walter Gültig, Jona FUN Möllchen, Jonathan Schnitzler
 */
public class Tree {

	private Tree left;
	private Tree right;
	private String value;
	public int height; // height of subtree
	public int balance; // balance of node leftheight - rightheight
	public int nodeCount; // number of nodes in subtree
	public int depthSum; // sum of depths of all nodes in subtree

	public Tree(Tree left, Tree right, String value) {
		super();
		this.left = left;
		this.right = right;
		this.value = value;
	}

	public static void printValues(Tree u) {
		printValues(u, 0);
	}

	/**
	 * sets the values of the tree and then prints them out for all knots
	 * @param u
	 * @param rec remembers in which degree it is standing to the tree it prints out
	 */
	public static void printValues(Tree u, int rec) {
		if (u != null) {
			setValues(u);

			printValues(u.right, rec + 1);
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < rec; i++) {
				s = s.append(" | ");
			}
			s = s.append(u.value);
			s = s.append(", Balance: ");
			s = s.append(u.balance);
			s = s.append(", DepthSum: ");
			s = s.append(u.depthSum);
			s = s.append(", NodeCount: ");
			s = s.append(u.nodeCount);
			s = s.append(", Height ");
			s = s.append(u.height);
			System.out.println(s.toString());

			printValues(u.left, rec + 1);
		}
	}

	/**
	 * calculates the averageDepth of the tree
	 * calls the recursive function with the start depth 0 
	 * return averageDepth
	 */
	public static double averageDepth(Tree t) {
		return averageDepth(t, 0) / t.nodeCount;
	}

	/**
	 * counts the depth of the tree
	 * @return the sum of the Depth of all knots
	 */
	public static double averageDepth(Tree t, int rec) {
		double totalDepth = 0;

		if (t == null) {
			return 0;
		} else {
			totalDepth += rec;
			totalDepth += averageDepth(t.left, rec + 1);
			totalDepth += averageDepth(t.right, rec + 1);

			return totalDepth;
		}
	}

	/**
	 * horrible awful and ugly but our small brains haven't been capable of something that
	 * isn't creating cancer, so we just decided to implement all for cases
	 * 
	 * @param u
	 */
	public static void setValues(Tree u) {
		if (u.left == null && u.right != null) {
			setValues(u.right);
			u.height = u.right.height + 1;
			u.balance = 0 - u.right.balance;
			u.nodeCount = 0 + u.right.nodeCount + 1;
			u.depthSum = u.nodeCount - 1 + u.right.depthSum;
		}
		if (u.left != null && u.right == null) {
			setValues(u.left);
			u.height = u.left.height + 1;
			u.balance = u.left.balance;
			u.nodeCount = 0 + u.left.nodeCount + 1;
			u.depthSum = u.nodeCount - 1 + u.left.depthSum;

		}
		if (u.left == null && u.right == null) {
			u.height = 0;
			u.balance = 0;
			u.depthSum = 0;
			u.nodeCount = 1;
		}
		if (u.left != null && u.right != null) {
			Tree.setValues(u.left);
			Tree.setValues(u.right);

			if (u.left.height > u.right.height) {
				u.height = u.left.height + 1;
			} else {
				u.height = u.right.height + 1;
			}

			u.nodeCount = u.left.nodeCount + u.right.nodeCount + 1;
			u.balance = u.left.balance - u.right.balance;
			u.depthSum = u.nodeCount - 1 + u.left.depthSum + u.right.depthSum;
		}
	}

	/**
	 * Einfuegen in Subtree
	 * 
	 * @param u Suchbaum in den eingefuegt werden soll
	 * @param x Einzufuegender String
	 * @return Wurzel des neuen Baums (u, falls u != null)
	 */
	public static Tree insert(Tree u, String x) {
		if (u == null) {
			return new Tree(null, null, x);
		} else if (x.compareTo(u.value(u)) == 0) {
			return u;
		} else if (x.compareTo(u.value(u)) > 0) { // value kommt alphabettecnisch danach
			u.right = insert(u.right, x);
			return u;
		} else {
			u.left = insert(u.left, x);
			return u; // value kommt alphabettechnisch davor
		}
	}

	public static Tree empty() {
		return null;
	}

	public static String value(Tree t) {
		return t.value;
	}

	public static boolean isEmpty(Tree t) {
		if (t == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(" [ ");
		if (!Tree.isEmpty(left)) {
			s.append(left.toString());
		}
		if (!Tree.isEmpty(this)) {
			s.append(Tree.value(this));
		}
		if (!Tree.isEmpty(right)) {
			s.append(right.toString());
		}
		s.append(" ] ");
		return s.toString();
	}

	/**
	 * The only use of this is to make a recursion without using String "" as an
	 * input It's quite useless actually
	 */
	public static void binary(Tree t) {
		binary(t, "");
	}

	/**
	 * Prints all the binary numbers you could get from this tree
	 * 
	 * A binary number is build up from root to leave by following pattern if the
	 * current path has a left tree in the 2^(depth of this tree) its binary value
	 * is 0 if the current path has a right tree in the 2^(depth of this tree) its
	 * binary value is 1
	 * 
	 * @param t   The Tree from which all the binary numbers shall be printed
	 * @param The value which is increased with recursion of the current level
	 */
	public static void binary(Tree t, String s) {
		String sRight = s;
		if (!Tree.isEmpty(t.left)) {
			s = s.concat("0");
			/**
			 * @invariant s.length() = numbersOfRecursion
			 * @variant current depth of the knot to the depth of the leave
			 */
			binary(t.left, s);
		}
		if (!Tree.isEmpty(t.right)) {
			sRight = sRight.concat("1");
			binary(t.right, sRight);
		}
		if (Tree.isEmpty(t.right) && Tree.isEmpty(t.left)) {
			System.out.println(s);

		}

	}

	/**
	 * this method should help us remembering to enjoy the little things in life
	 * also it prints a Tree as a String according to 3c)
	 * 
	 * @param t The Tree we want to print
	 * @return the String representation
	 */
	public static String treeToStringBracketsWithALotOfApplesAndPearsAndBeers(Tree t) {
		String s = "";
		if (Tree.isEmpty(t)) {
			s.concat("");
		} else {
			/**
			 * @invariant !Tree.isEmpty() otherwise the recursion stops
			 * @variant current depth of the knot to the depth of the leave
			 */
			s = s.concat("[");
			s = s.concat(treeToStringBracketsWithALotOfApplesAndPearsAndBeers(t.left));
			s = s.concat("]");
			s = s.concat(treeToStringBracketsWithALotOfApplesAndPearsAndBeers(t.right));
		}
		return s;
	}

	/**
	 * returns a tree according to the String, without the value 1 in all inputs
	 * 
	 * Method works by recursion reducing the String in a left and right subtree,
	 * till the String is [] which indicates an empty tree
	 * 
	 * @requires "[" is at index 0
	 * @requires the String contains brackets "[" , "]"
	 * @requires it is possible to construct a tree with the encoding e. g. []]] is
	 *           impossible, all brackets also must be closed somewhere
	 * 
	 * @param s The String representation
	 * @return The tree according to the String
	 */
	public static Tree stringToTree(String s) {
		Tree left;
		Tree right;
		if (s.charAt(0) == 91) {
			int nextClosing = findNextClosingBracket(s);
			if (s.length() == 2) {
				return new Tree(Tree.empty(), Tree.empty(), "1");
			} else {
				String l = s.substring(1, nextClosing);
				String r = s.substring(nextClosing + 1, s.length()); // the rest of the String

				if (l.length() > 0) {
					/**
					 * @variant l.length() is decreasing (so the recursion must be finite)
					 * @invariant l is the first fully closed brackets of the String just without
					 *            them
					 */
					left = stringToTree(l);
				} else {
					left = Tree.empty();
				}

				if (r.length() > 0) {
					right = stringToTree(r);
				} else {
					right = Tree.empty();
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		return new Tree(left, right, "1");
	}

	/**
	 * Returns the index of the next "]"
	 * 
	 * @requires "[" is at index 0
	 * @requires the String contains brackets "[" , "]"
	 * @return index (start counting at 0) of the closing final bracket
	 */
	public static int findNextClosingBracket(String s) {
		int counterAuf = 0;
		int counterZu = 0;
		int index = 0;
		do {
			if (s.charAt(index) == 91) { // 91 is the char representation of [
				counterAuf++;
			} else {
				counterZu++;
			}
			index++;
		} while (counterAuf != counterZu);

		return index - 1;
	}
}
