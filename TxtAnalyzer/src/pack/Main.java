package pack;

import java.io.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Calculator c = new Calculator();
		PathProcessor pp = new PathProcessor();
		
		System.out.println("Enter the input file path in this form D:\\directory\\subdirectory\\filename.txt ");
		String ifp = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter the output folder path in this form D:\\directory\\subdirectory");
		String ofp = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter the dictionary file path in this form D:\\directory\\subdirectory\\dictname.tsv ");
		String dfp = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter the output file name");
		String ofn = new BufferedReader(new InputStreamReader(System.in)).readLine();
		pp.process(ifp, ofp, dfp, ofn);
		
		Metrics res = c.calculate(pp);

		System.out.println("Broj reci u tekstu je:");
		System.out.println(res.numWords);
		System.out.println("Broj recenica u tekstu je:");
		System.out.println(res.numSentences);
		System.out.println("Broj paragrafa u tekstu je:");
		System.out.println(res.numParagraphs);
		System.out.println("Prosecan broj reci po recenici u tekstu je:");
		System.out.println(res.wordsPerSentence);
		System.out.println("Prosecan broj slova po reci u tekstu je:");
		System.out.println(res.lettersPerWord);
		

		Text t = new Text();
		t.fileLoc = pp.resLocation;
		t.metrics = res;

	}

}
