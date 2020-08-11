package uebung7;

public class Matrix {
	
	int[][] mat;
	
	public Matrix(int n) {
		mat = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				mat[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\n");
		s.append("    .      0    .     1    .     2    .     3    .     4    . \n \n");
		for (int i = 0; i < this.mat.length; i++) {
			s.append(i + "   |");
			for (int j = 0; j < this.mat.length; j++) { // da quadratisch kann auch mat.length
				s.append(" ");
				Integer cell = this.mat[i][j];
				Double buffer = (double) cell.toString().length();
				//Buffer
				for(int k = 0; k < 5 - Math.floor(buffer/2); k++) {
					s.append(" ");
				}
				if(cell == Integer.MAX_VALUE) {
					s.append(" Infinity ");
				} else {
					s.append(cell);
				}
				//Buffer
				for(int k = 0; k < 5 - Math.ceil(buffer/2); k++) {
					s.append(" ");
				}
				
			}
			s.append("| \n");
		}
		return s.toString();
	}
	
	public Matrix(int[][] clone) {
		mat = clone;
	}

	public int getValue(int row, int col) {
		return mat[row][col];
	}
	
	public void setValue(int row, int col, int val) {
		mat[row][col] = val;
	}
	
	public Matrix copy() {
		return new Matrix(this.mat.clone());
	}

}
