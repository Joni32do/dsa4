package uebung6;

import java.awt.geom.IllegalPathStateException;

/**
 * Would be best if it would be an AVL Tree but it's not
 * 
 * @author Jonathan
 *
 */
public class SearchTree {

	public SearchTree left;
	public SearchTree right;
	public int value;
	public String stringRepresentation;

	public SearchTree() {

	}

	public SearchTree(int val, String stringRep) {
		value = val;
		stringRepresentation = stringRep;
	}

	public void insert(int val, String stringRep) {

		if (this.value > val) {
			if (this.left != null) {
				this.left.insert(val, stringRep);
			} else {
				left = new SearchTree(val, stringRep);
			}
		} else if (this.value == val) {
			System.out.println("Sollte es aber nicht! Der Wert ist " + val);
		} else {
			if (this.right != null) {
				this.right.insert(val, stringRep);
			} else {
				right = new SearchTree(val, stringRep);
			}

		}
	}
	
	public String search(char val) {
		if(this.value == val) {
			return this.stringRepresentation;
		} else if(this.value > val) {
			return this.left.search(val);
		} else if (this.value < val){
			return this.right.search(val);
		} else {
			throw new IllegalPathStateException();
		}
	}
	
	
}
