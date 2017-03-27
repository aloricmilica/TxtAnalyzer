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

		System.out.println("The number of words in the text are: " + res.numWords);
		System.out.println("The number of sentences in the text are: " + res.numSentences);
		System.out.println("The number of paragraphs in the text are: " + res.numParagraphs);
		System.out.println("The average no of words per sentence is: " + res.wordsPerSentence);
		System.out.println("The average no of letters per word is: " + res.lettersPerWord);
		System.out.println("The number of word types in the text are: " + res.numTypes);
		System.out.println("Type token ratio is: " + res.TTR);
		System.out.println("The number of determiners is: " + res.determiners);
		
//		Text t = new Text();
//		t.fileLoc = pp.resLocation;
//		t.metrics = res;

	}

}
