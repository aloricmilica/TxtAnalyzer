package pack;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;

public class Calculator implements MetricsCalculator {
	
	public Metrics calculate(PathProcessor pp) throws ClassNotFoundException, IOException {
		
		Metrics m = new Metrics();
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
		Properties props = new Properties();
	    props.setProperty("annotators","tokenize, ssplit, pos,lemma, depparse");		    
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// reads some text from the file
	    try {
			//BufferedReader br = new BufferedReader(new FileReader(pp.inFilePath));
	    	Scanner s = new Scanner(new FileReader(pp.getInFilePath()));
	    	StringBuffer sb = new StringBuffer();

	    	//posebna metoda sa dva 
//	    	int counter = 0;
//			while (s.hasNextLine()) {
//				String line = s.nextLine();
//				sb.append(line);
//				sb.append("\n");
//				
//				if(line.isEmpty())
//					counter++;
//			}
//			//counting the total number of paragraphs
//			m.numParagraphs = counter+1;
			
			String txt = sb.toString();
			// create an empty Annotation just with the given text
		    Annotation document = new Annotation(txt);
		    // run all Annotators on this text
		    pipeline.annotate(document);	        
			// create the annotated text
		    // TODO: delete
		    File annotated = new File("D:\\Faks\\IV godina\\diplomski\\annotated.txt");
		    PrintWriter pw = new PrintWriter(new FileWriter(annotated));
			pipeline.prettyPrint(document, pw);
		    
			
			// these are all the sentences in this document
		    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
			List<CoreMap> sentences = document.get(SentencesAnnotation.class);
			ArrayList<String> types = new ArrayList<String>();
				
			//counting the total number of words & word types (unique items)
			if(sentences != null){
				for(CoreMap sentence: sentences) {
					// traversing the words in the current sentence
				    // a CoreLabel is a CoreMap with additional token-specific methods
				    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				        if(!token.get(PartOfSpeechAnnotation.class).equals(".")){
				        	m.numWords++;
				        	if(!types.contains(token.get(TextAnnotation.class).toLowerCase()))
				        		types.add(token.get(TextAnnotation.class).toLowerCase());
				        	if(token.get(PartOfSpeechAnnotation.class).equals("DT"))
				        		m.determiners++;
				        	if(token.get(PartOfSpeechAnnotation.class).equals("PRO:DEM"))
				        		m.demonstratives++;
				        	if(token.get(PartOfSpeechAnnotation.class).equals("PP"))
				        		m.numPronouns++;
				        }
				    }
				    
		        // TODO: Template pattern
				    Tree sentenceTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
			        // Output dependency tree
			        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
			        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
			        GrammaticalStructure gs = gsf.newGrammaticalStructure(sentenceTree);
			        Collection<TypedDependency> tdl = gs.typedDependenciesCollapsed();

			        //System.out.println("typedDependencies: "+tdl);*/
				}
				m.numTypes = types.size();
			}
				
			//counting the total number of sentences
			if(sentences!=null){
				m.numSentences = sentences.size();
			}	
				
			//average no of words per sentence
			if(document != null){
				m.wordsPerSentence = (double) m.numWords/m.numSentences;
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
			
			//calculating TTR -> the more types there are in comparison to the number of tokens, 
			//then the more varied is the vocabulary, i.e. it there is greater lexical variety.
			m.TTR = (double) m.numTypes/m.numWords;
			
			//determiners
			

			
			s.close();
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
	
		    return m;
		}
	}
