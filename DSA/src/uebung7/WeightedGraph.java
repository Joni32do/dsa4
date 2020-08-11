package uebung7;

public class WeightedGraph extends Graph {
	

	Matrix weightedGraph;
	
	/**
	 * @requires in has not two values for the same path
	 * 
	 */
	public WeightedGraph(int[][] in, int knots) {
		super(in);
		weightedGraph = new Matrix(knots);

		for(int i = 0; i < in.length; i++) {
			weightedGraph.setValue(in[i][0], in[i][1], in[i][2]);
		}
		
	}
	


}
