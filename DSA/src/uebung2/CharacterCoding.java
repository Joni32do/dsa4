package uebung2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Eine Zeichencodierung f&uuml;r druckbare Zeichen. Die Zeichen (Java-Datentyp
 * char bzw. Character) werden mit Codes - ganze Zahlen beginnend bei 0 -
 * numeriert. Es gibt Umwandlungen Zeichen in Code und umgekehrt sowie Methoden
 * zum Lesen und Schreiben von Text (Feld von Codes) aus/in Textdateien.
 * 
 * 
 * @version April 8, 2016
 * @author zimmersn
 *
 */
public class CharacterCoding {
	/**
	 * Liste der darstellbaren Zeichen - Index in der Liste ist der Code
	 */
	private List<Character> chars;
	/**
	 * Abbildung Zeichen auf Code
	 */
	private Map<Character, Integer> code;

	/**
	 * Erzeugt das Standardalphabet (95 druckbare Zeichen +
	 * &Auml;, &Ouml;, &Uuml;, &auml;, &ouml;, &uuml;, &szlig; und \n)
	 */
	public CharacterCoding() {
		this.chars = new ArrayList<>();
		this.code = new HashMap<>();
		add(' ');
		for (char c = '0'; c <= '9'; c++)
			add(c);
		for (char c = 'A'; c <= 'Z'; c++)
			add(c);
		// &Auml;, &Ouml;, &Uuml;
		for (char c : new char[] { '\u00c4', '\u00d6', '\u00dc' })
			add(c);
		for (char c = 'a'; c <= 'z'; c++)
			add(c);
		// &auml;, &ouml;, &uuml;, &szlig; und \n
		for (char c : new char[] { '\u00e4', '\u00f6', '\u00fc', '\u00df', '\n' })
			add(c);
		// Alle druckbaren Zeichen, sofern noch nicht drin
		for (char c = ' '; c <= '~'; c++)
			add(c);
	}

	/**
	 * Erzeugt Codierung aus den Zeichen eines Strings
	 * 
	 * @param chars
	 *            String, der die Zeichen der Codierung in der richtigen
	 *            Reihenfolge (erstes Auftreten) enth&auml;lt (f&uuml;r
	 *            Codierung c gibt new CharacterCoding(c.toString()) eine
	 *            identische Codierung)
	 */
	public CharacterCoding(String chars) {
		this.chars = new ArrayList<>();
		this.code = new HashMap<>();
		for (char c : chars.toCharArray()) {
			add(c);
		}

	}

	/**
	 * Zeichen zur Codierung hinzuf&uuml;gen (keine Wirkung, falls schon
	 * enthalten)
	 * 
	 * @param c
	 *            das einzuf&uuml;gende Zeichen
	 */
	public void add(char c) {
		if (this.code.get(c) == null) {
			int co = this.chars.size();
			this.chars.add(c);
			this.code.put(c, co);
		}
	}

	/**
	 * Umwandlung Zeichen in Code. Falls das Zeichen noch nicht im Alphabet
	 * enthalten ist, wird es hinten angef&uuml;gt.
	 * 
	 * @param c
	 *            das Zeichen
	 * @return den Code des Zeichens (Position in Codierung)
	 */
	public int getCode(char c) {
		Integer val = this.code.get(c);
		if (val == null) {
			add(c);
			val = this.code.get(c);
		}
		return val;
	}

	/**
	 * Umwandlung Code in Zeichen
	 * 
	 * @param val
	 *            Der Code des Zeichens (Position in Codierung)
	 * @return das Zeichen
	 */
	public char getChar(int val) {
		return chars.get(val);
	}

	/**
	 * Gr&ouml;&szlig;e der Codierung (Anzahl Zeichen)
	 * 
	 * @return die Gr&ouml;&szlig;e der Codierung (Anzahl Zeichen)
	 */
	public int size() {
		return chars.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int c : this.chars) {
			sb.append((char) c);
		}
		return sb.toString();
	}

	/**
	 * Liest eine Textdatei ein und liefert den Text in der Codierung des
	 * Alphabets (Feld von Positionen im Alphabet)
	 * 
	 * @param filename
	 *            Name der Textdatei
	 * @return den Inhalt der Datei in der Codierung des Alphabets (Feld von
	 *         Positionen im Alphabet)
	 * @throws IOException
	 *             Falls &Ouml;ffnen oder Lesen nicht m&ouml;glich
	 */
	public int[] readFromFile(String filename) throws IOException {
		LinkedList<Integer> buf = new LinkedList<>();
		try (FileReader f = new FileReader(filename)) {
			boolean end = false;
			while (!end) {
				int c = 0;
				try {
					c = f.read();
				} catch (IOException e) {
					end = true;
				}
				if (c != -1) {
					buf.add(getCode((char) c));
				} else {
					end = true;
				}
			}
		}
		int[] arr = new int[buf.size()];
		int i = 0;
		for (Integer ai : buf) {
			arr[i++] = ai;
		}
		return arr;
	}

	/**
	 * Schreibt einen Text (Feld von Positionen im Alphabet) als Textdatei
	 * 
	 * @param filename
	 *            Name der Textdatei (Datei wird &uuml;berschrieben, falls sie
	 *            schon existiert)
	 * @param txt
	 *            Text in der Codierung des Alphabets (Feld von Positionen im
	 *            Alphabet)
	 * @throws IOException
	 *             Falls &Ouml;ffnen oder Schreiben nicht m&ouml;glich
	 */
	public void writeToFile(String filename, int[] txt) throws IOException {
		FileWriter f = new FileWriter(filename);
		for (int ci : txt) {
			f.write(getChar(ci));
		}
		f.close();
	}

}
