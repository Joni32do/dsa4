package uebung6;

import uebung5.Tree;

public class Heap extends Tree {
	
	public int popularityIndex; //How many of these appear in a text is used in uebung6
	public Heap parent; //speichert den Vater (oder die Mutter) ((oder das geschlechtsneutrale andere Elternteil))
	
	
	public Heap(Tree left, Tree right, String value) {
		super(left, right, value);
		
	}

	public Heap insert(String s, int popularityIndex) {
		
		Tree.setValues(this);
		
		Tree output = this;
		
		int height = this.height;
		int fuegEin = this.nodeCount - (int) (Math.pow(2, this.height) + 1);
		for(int i = 0; i < this.height; i++) {
			if(fuegEin > (int) Math.pow(2, height - i)) {
				fuegEin = fuegEin % (int) Math.pow(2, height - i);
				output = output.left;
			} else {
				
			}
		}
		return null;
	}

}
