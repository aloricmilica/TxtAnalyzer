package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Calculator implements MetricsCalculator {

	public Metrics calculate(PathProcessor pp) throws ClassNotFoundException, IOException {
		
		Metrics m = new Metrics();
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
		Properties props = new Properties();
	    props.setProperty("annotators","tokenize, ssplit, pos,lemma");		    
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// read some text from the file..
	    try {
			BufferedReader br = new BufferedReader(new FileReader(pp.inFilePath));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			String txt = sb.toString();
			// create an empty Annotation just with the given text
		    Annotation document = new Annotation(txt);
		    // run all Annotators on this text
		    pipeline.annotate(document);	        
			// create the annotated text
		    File annotated = new File("D:\\Faks\\IV godina\\diplomski\\annotated.txt");
		    PrintWriter pw = new PrintWriter(new FileWriter(annotated));
			pipeline.prettyPrint(document, pw);
			
			
			// these are all the sentences in this document
		    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
			List<CoreMap> sentences = document.get(SentencesAnnotation.class);
				
			//counting the total number of words
			if(document != null){
				for(CoreMap sentence: sentences) {
					// traversing the words in the current sentence
				    // a CoreLabel is a CoreMap with additional token-specific methods
				    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				        if(!token.get(PartOfSpeechAnnotation.class).equals("."))
				        	m.numWords++;
					    }
					}
				}
				
				//counting the total number of sentences
				if(document!=null){
					m.numSentences = sentences.size();
				}
				
				//counting total number of paragraphs
				if(document != null){
					for (char ch: txt.toCharArray()) {
						if(equals('\r'))
							m.numParagraphs++;
						if(equals('\n'))
						m.numParagraphs++;
					}
					
				}
				
				
				//average no of words per sentence
				if(document != null){
					m.wordsPerSentence = m.numWords/m.numSentences;
				}
				
				//average no of letters per word
				if(document != null){
					int letters = 0;
					for ( int i = 0; i < txt.length(); i++ ) {
			            if(Character.isLetter(txt.charAt(i)))
			            letters++;
			        }
					m.lettersPerWord = letters/m.numWords;
				}
				
				br.close();
				pw.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}


		
		
			
			
			
			/*for(CoreMap sentence: sentences) {
			    // traversing the words in the current sentence
			    // a CoreLabel is a CoreMap with additional token-specific methods
			    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
			        // this is the text of the token
			        String word = token.get(TextAnnotation.class);
			        // this is the POS tag of the token
			        String pos = token.get(PartOfSpeechAnnotation.class);
			        // this is the NER label of the token
			        String ne = token.get(NamedEntityTagAnnotation.class);
			        
			      }
			*/
			
			
		
			
			/*
		
		
		
		//racunanje broja vrsta reci

	    /*for(CoreMap sentence: sentences) {
	      // traversing the words in the current sentence
	      // a CoreLabel is a CoreMap with additional token-specific methods
	      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
	        // this is the text of the token
	        String word = token.get(TextAnnotation.class);
	        // this is the POS tag of the token
	        String pos = token.get(PartOfSpeechAnnotation.class);
	        // this is the NER label of the token
	        String ne = token.get(NamedEntityTagAnnotation.class);
	        
	        System.out.println("word: " + word + " pos: " + pos + " ne:" + ne);
	      }
	      
	      
	    /*br = new BufferedReader(new FileReader("D:\\Faks\\IV godina\\diplomski\\output.txt"));
	    String pos = null;
	    while((readString = br.readLine())!= null){
	         String y=readString;
	         pw.println(y); 
	         pos=y;//System.out.println("OKKKKK"); 
	         
	    }
	    br.close (  ) ;
        System.out.println("Number of types: " + pos.length());
        */ 
	    /*  
		if(text != null){
			String[] pos;
			for (int i = 0; i < text.length(); i++) {
				String firstPOSTag = sent.posTag(0);				
			}
			String[] paragraphs = text.split("\n");
			m.numParagraphs = paragraphs.length;
		}
		
		
		 
		*/
	
			    
			
			
		    return m;
		}
	}
