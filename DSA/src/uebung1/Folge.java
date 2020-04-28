package uebung1;

public class Folge {
	
	public int anfang;
	public int ende;
	public double wert;
	
	
	public Folge(int anfang, int ende, double wert) {
		super();
		this.anfang = anfang;
		this.ende = ende;
		this.wert = wert;
	}
	
	
	public int getAnfang() {
		return anfang;
	}
	public void setAnfang(int anfang) {
		this.anfang = anfang;
	}
	public int getEnde() {
		return ende;
	}
	public void setEnde(int ende) {
		this.ende = ende;
	}
	public double getWert() {
		return wert;
	}
	public void setWert(double wert) {
		this.wert = wert;
	}
	
	@Override 
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Die Teilsumme von ");
		s.append(anfang);
		s.append(" bis ");
		s.append(ende);
		s.append(" hat den Wert: ");
		s.append(wert);
		
		return s.toString();
	}
	
}
