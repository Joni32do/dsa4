package uebung6;

public class TreeElem implements Comparable<TreeElem> {

	public int popularity;
	public int chaChaChar;
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

}
