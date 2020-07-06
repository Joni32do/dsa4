package uebung9;

import java.io.IOException;
import java.util.Arrays;

import uebung2.*;
import uebung6.*;

public class Main {

	public static void main(String[] args) {
		
		
		CharacterCoding faust = new CharacterCoding();
		try {
			int[] data = faust.readFromFile("faust.txt");
			Hintransformation h = new Hintransformation(data);
			data = h.output;
			
			//With move-to-front transformation
			zippedCode z = uebung2.Main.zip(data, uebung2.Main.generateAlphabet(120));
			data = z.getZahlencode();
			data = Huffman.doTheHuffman(data);
			z.setZahlencode(data);
			data = uebung2.Main.unzip(z);
			
			long time = System.currentTimeMillis();
			Ruecktransformation r = new Ruecktransformation(new BWElement(data,h.start));
			time = (System.currentTimeMillis() - time)/1000;
			System.out.println("Die Ruecktransformation hat " + time + " Sekunden gedauert.");
			
			data = r.originalData;
			faust.writeToFile("Testeri.txt", data);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] small = {1,2,3,4,5,6,7,7,7,34623,23,6,1,6,2,33,33,532,3623,34623};
		Hintransformation h = new Hintransformation(small);
		System.out.println(Arrays.toString(h.output));
		Ruecktransformation r = new Ruecktransformation(new BWElement(h.output, h.start));
		System.out.println(Arrays.toString(r.originalData));
	}

}
