package uebung5;

public class Main {

	/**
	 * Testing our little Trees
	 */
	public static void main(String[] args) {
		
		// We plant our Tree and try to this in an ordered way (looks kind of beautiful to me)
		Tree t = new Tree(
					new Tree(
							new Tree(
									Tree.empty(),
									Tree.empty(),
									"a"), 
							new Tree(
									new Tree(
											Tree.empty(),
											Tree.empty(),
											"d"), 
									Tree.empty(),
									"e"),
							"c"), 
					new Tree(
							Tree.empty(),
							new Tree(
									new Tree(
											Tree.empty(),
											Tree.empty(),
											"o"),
									Tree.empty(),
									"r"),
							"n"),
					"m");
		
		
		System.out.println(t);
		
		//Tree.binary(t,"");
		
		String s = Tree.treeToStringBracketsWithALotOfApplesAndPearsAndBeers(t);
		System.out.println(s);
		//t = Tree.insert(t, "j");
		//System.out.println(t);
		//System.out.println(Tree.treeToStringBracketsWithALotOfApplesAndPearsAndBeers(t));
		
		Tree.setValues(t);
		Tree.printValues(t);
		
		//Tree tt = Tree.stringToTree("[[[]][[]]][][[]]");
		//System.out.println(tt);
		//System.out.println(Tree.treeToStringBracketsWithALotOfApplesAndPearsAndBeers(tt));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		int n = 80;
		Tree j = new Tree(null, null, randomString());
		j = insertNTimes(j, n);
		Tree.setValues(j);
		System.out.println(Tree.averageDepth(j));
		System.out.println(2 * Math.log(2)* Math.log(n)/Math.log(2));
	}
	
	

	private static Tree insertNTimes(Tree t, int n) {
		for(int i = 0; i < n; i++) {
			t = Tree.insert(t, randomString());
		}
		return t;
	}

	private static String randomString() {
		Double d = Math.random();
		return d.toString();
	}
}
