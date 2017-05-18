/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;


import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomDictionaryCalc extends AbstractCalculator {
        
       
        Map<String, String> dictionary;
        public Text t = new Text();
        Annotation document;
        List<CoreMap> sentences;
        List<String> relationships = new ArrayList<String>();
                 
        public CustomDictionaryCalc(InputFileConfigurator ifc) throws FileNotFoundException, IOException {
            // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
            dictionary = new HashMap<String, String>();
            Properties props = new Properties();
	    props.setProperty("annotators","tokenize, ssplit, pos, lemma, parse");		    
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            
            // reads text from the file
            try {
                
            Scanner s = new Scanner(new FileReader(ifc.getInFilePath()));
            StringBuilder sb = new StringBuilder();
            
	    while (s.hasNextLine()) {
                String line = s.nextLine();
                sb.append(line);
                sb.append("\n");
               
            }
                                  
            String txt = sb.toString();
            t.setTxt(txt);
            t.setFileLoc(ifc.getOutFolderPath());
            
            Scanner dict = new Scanner(new FileReader(ifc.getDictPath()));
            StringBuilder dictsb = new StringBuilder();
            
            while(dict.hasNextLine()){
                dictsb.append(dict.nextLine());
                dictsb.append("\n");
            }
            
            String[] customDict = dictsb.toString().split("\n");
            for (int i = 0; i < customDict.length; i++) {
                    String[] tabWords = customDict[i].split("\t");
                    //Metrics cm = new
                    String value = "";
                    for (int j = 1; j < tabWords.length; j++) {
                        value+=tabWords[j]+" ";
                    }
                
                    dictionary.put(tabWords[0], value);
            }
            
            // creates an empty Annotation just with the given text
            document = new Annotation(txt);
            // run all Annotators on this text
	    pipeline.annotate(document);	        
            // create the annotated text
            File annotated = new File(t.getFileLoc()+"annotated.txt");
	    PrintWriter pw = new PrintWriter(new FileWriter(annotated));
            pipeline.prettyPrint(document, pw);
            
            sentences = document.get(SentencesAnnotation.class);
            
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    Tree sentenceTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

                    // Output dependency tree
                    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
                    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
                    GrammaticalStructure gs = gsf.newGrammaticalStructure(sentenceTree);
                    Collection<TypedDependency> tdl = gs.allTypedDependencies();
                    for (TypedDependency td : tdl) {
                        relationships.add(td.reln().getShortName());
                    }
                }
            }
            s.close();
            pw.close();
            } catch(IOException e) {

			e.printStackTrace();
            }
        }
     
        @Override
        int countWords() {
            int words = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences){
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if((!token.get(PartOfSpeechAnnotation.class).equals("."))&&
                            (!token.get(PartOfSpeechAnnotation.class).equals(","))){
                                if(!token.get(TextAnnotation.class).toLowerCase().equals("n't")&&
                                    (!token.get(TextAnnotation.class).toLowerCase().equals("'ll")))
                                words++;
                            }
                    }
                }
            }
            return words;
        }
     
        
        @Override
        int countTypes() {
            ArrayList<String> types = new ArrayList<String>();
            if(sentences != null){
                for(CoreMap sentence: sentences){
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if((!token.get(PartOfSpeechAnnotation.class).equals("."))&&
                            (!token.get(PartOfSpeechAnnotation.class).equals(","))){
                                if(!types.contains(token.get(TextAnnotation.class).toLowerCase()))
                                    types.add(token.get(TextAnnotation.class).toLowerCase());
                        }
                    }
                }
            }
            return types.size();
        }

        //calculating TTR -> the more types there are in comparison to the number of tokens, 
        //then the more varied is the vocabulary, i.e. it there is greater lexical variety.
        @Override
        double countTTR() {
            return (double) countTypes()/countWords();
        }
        
        int letters = 0;
        @Override
        double countLettersPerWord() {
            if(sentences != null && countWords() != 0){
		for ( int i = 0; i < t.getTxt().length(); i++ ) {
                   if(Character.isLetter(t.getTxt().charAt(i)))
			letters++;
		}
            }
            return (double) letters/countWords();
        }
        
        
        @Override
        int countParagraphs() {
            int paragraphs = 0;
            String txt = t.getTxt();
            String[] lines = txt.split("\r\n|\r|\n");
            for (String line : lines) {
                if(line.isEmpty())
                    paragraphs++;
            }
            
            return paragraphs+1;
        }

        @Override
        int countSentences() {
            if(sentences!=null)
            return sentences.size();
            else return 0;
        }	
            
        @Override
        double countWordsPerSentence() {
            if(sentences!=null)
            return (double) countWords()/sentences.size();
            else return 0;
        }
        
        
        @Override
        int countDeterminers() {
            int determiners = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("determiners")){
                    value = entry.getValue().split(" ");
                    
                }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                determiners++;      
                        }
            return determiners;
        }
        
        
        @Override
        int countDemonstratives() {
            int demonstratives = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("demonstratives")){
                    value = entry.getValue().split(" ");
                }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    demonstratives++;      
                                }
            
            return demonstratives;
        }
        

        
        @Override
        int countPronouns() {
            int pronouns = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("pronouns")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                pronouns++;
                        }
                
            
            return pronouns;
        }
        
        
        @Override
        int count1stPersonPronouns() {
            int firstPersonPronouns = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("first person pronouns")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    firstPersonPronouns++;      
                                }
                
            
            return firstPersonPronouns;
        }
        
        
        @Override
        int count2ndPersonPronouns() {
            int scndPersonPronouns = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("second person pronouns")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    scndPersonPronouns++;      
                                }
                
           
            return scndPersonPronouns;
        }
        
        
        @Override
        int count3rdPersonPronouns() {
            int thirdPersonPronouns = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("third person pronouns")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    thirdPersonPronouns++;      
                                }
                
            
            return thirdPersonPronouns;
        }
        
        
        int conjuncts = 0;
        @Override
        int countConjuncts() {
            for (String s : relationships) {
                if (s.equals("conj")|| s.equals("conj:and")) 
                        conjuncts++;
                        
            }     
        return conjuncts;
        }
        
        
        int connectives = 0;
        @Override
        int countConnectives() {
            for (String s : relationships) {
                if (s.equals("cc")|| s.equals("in")) 
                        connectives++;      
            }     
        return connectives;
        }

        @Override
        int countNegations() {
            int negations = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("negations")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    negations++;      
                                }
                
            
            return negations;
        }
        

        
        @Override
        int countFuture() {
            int future = 0;
            String[] value = null;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if(entry.getKey().equals("future")){
                    value = entry.getValue().split(" ");
                    }
            }
            for(String s : value)
                if(sentences != null)
                    for(CoreMap sentence: sentences) 
                        for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                            if(s.equals(token.get(LemmaAnnotation.class)))
                                    future++;      
                                }
                
            
            return future;
        }


        @Override
        Text returnTxt() {
            return t;
        }

        	
}    
