package uebung6;


public class TreeElem implements Comparable<TreeElem> {

	public int popularity;
	public int chaChaChar; // once was a char
	public TreeElem left;
	public TreeElem right;

	public TreeElem(int chaChaChar, int popularity) {
		super();
		this.popularity = popularity;
		this.chaChaChar = chaChaChar;
	}

	public TreeElem(TreeElem remove, TreeElem remove2) {
		this.left = remove;
		this.right = remove2;
		this.popularity = remove.popularity + remove2.popularity;
	}

	@Override
	public int compareTo(TreeElem t) {
		if (this.popularity > t.popularity) {
			return 1;
		} else if (this.popularity == t.popularity) {
			return 0;
		} else {
			return -1;
		}
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public static boolean isEmpty(TreeElem t) {
		if (t == null) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	
	
	
	
	
	public int height; // height of subtree
	public int balance; // balance of node leftheight - rightheight
	public int nodeCount; // number of nodes in subtree
	public int depthSum; // sum of depths of all nodes in subtree
	
	
	public static void printValues(TreeElem u, int rec) {
		if (u != null) {
			setValues(u);

			printValues(u.right, rec + 1);
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < rec; i++) {
				s = s.append(" | ");
			}
			s = s.append(u.chaChaChar);
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
	
	
	
	
	
	
	public static void setValues(TreeElem u) {
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
			setValues(u.left);
			setValues(u.right);

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
}
