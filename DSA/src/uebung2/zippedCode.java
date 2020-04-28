package uebung2;

public class zippedCode {

	int[] zahlencode;
	List alphabet;
	public zippedCode(int[] zahlencode, List alphabet) {
		super();
		this.zahlencode = zahlencode;
		this.alphabet = alphabet;
	}
	public int[] getZahlencode() {
		return zahlencode;
	}
	public void setZahlencode(int[] zahlencode) {
		this.zahlencode = zahlencode;
	}
	public List getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(List alphabet) {
		this.alphabet = alphabet;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Das ist der Zahlencode: ");
		for(int i = 0; i < zahlencode.length; i++) {
			if(i != 0) {
				s.append(", ");
			}
			s.append(zahlencode[i]);
		}
		s.append(" Und das ist das Alphabet: ");
		s.append(alphabet.toString());
		return s.toString();
	}
	
}
