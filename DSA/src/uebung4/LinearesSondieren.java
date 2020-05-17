package uebung4;

public class LinearesSondieren {

	public String value;
	public LinearesSondieren succesor;
	public int size;
	
	public LinearesSondieren(int size) {
		if(size > 0) {
			this.size = size;
			this.succesor = new LinearesSondieren(size - 1);
		}
	}
	
	public static int add(LinearesSondieren lin, String value) {
		return add(lin, LinearesSondieren.generateHashCode(lin, value), value);
	}
	
	private static int add(LinearesSondieren lin, int hashCode, String value) {
		if(hashCode != 0) {
			return add(lin.succesor ,hashCode - 1, value);
		} else {
			if(lin.value == null) {
				lin.value = value;
				return 0;
			} else {
				if(lin.succesor == null) {
					throw new IllegalArgumentException();
				} else {
				int output = add(lin.succesor, hashCode, lin.value);
				lin.value = value;
				return output+ 1;
				}
			}	
		}
	}

	public static int generateHashCode(LinearesSondieren lin, String value) {
		
		int hashCode = value.chars().sum();
		hashCode = hashCode % lin.size;
		return hashCode;
	}
	
	
}
