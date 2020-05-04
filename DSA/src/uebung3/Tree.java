package uebung3;

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

	private final Tree left;
	private final Tree right;
	private final int value;

	public Tree(Tree left, Tree right, int value) {
		super();
		this.left = left;
		this.right = right;
		this.value = value;
	}

	public static Tree empty() {
		return null;
	}

	public static int value(Tree t) {
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
				return new Tree(Tree.empty(), Tree.empty(), 1);
			} else {
				String l = s.substring(1, nextClosing);
				String r = s.substring(nextClosing + 1, s.length()); // the rest of the String

				if (l.length() > 0) {
					/**
					 * @variant l.length() is decreasing (so the recursion must be finite)
					 * @invariant l is the first fully closed brackets of the String just without them
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
		return new Tree(left, right, 1);
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
