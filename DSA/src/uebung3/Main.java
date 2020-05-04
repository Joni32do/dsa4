package uebung3;

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
									1), 
							new Tree(
									new Tree(
											Tree.empty(),
											Tree.empty(),
											1), 
									Tree.empty(),
									1),
							1), 
					new Tree(
							Tree.empty(),
							new Tree(
									new Tree(
											Tree.empty(),
											Tree.empty(),
											1),
									Tree.empty(),
									1),
							1),
					1);
		
		
		System.out.println(t);
		Tree.binary(t,"");
		
		String s = Tree.treeToStringBracketsWithALotOfApplesAndPearsAndBeers(t);
		System.out.println(s);
				
		Tree tt = Tree.stringToTree("[[[]][[]]][][[]]");
		System.out.println(tt);
		System.out.println(Tree.treeToStringBracketsWithALotOfApplesAndPearsAndBeers(tt));
	}
}
