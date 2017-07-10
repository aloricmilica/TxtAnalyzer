/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;


import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import java.io.File;
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
import java.io.FileReader;
import java.util.Collection;

public class DefaultDictionaryCalc extends AbstractCalculator {
        
        public Text t = new Text();
        Annotation document;
        List<CoreMap> sentences;
        List<String> relationships = new ArrayList<String>();
        
        public DefaultDictionaryCalc(InputFileConfigurator ifc) throws FileNotFoundException, IOException {
            // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
            Properties props = new Properties();
             props.put("annotators","tokenize, ssplit, pos, lemma, parse");
            props.put("pos.model", "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            //+depparse, regexner
            // reads text from the file
           
            try {
                
            Scanner s = new Scanner(new FileReader(ifc.getInFilePath()));
            StringBuilder sb = new StringBuilder();
            
            
            //posebna metoda sa dva 
	    while (s.hasNextLine()) {
                String line = s.nextLine();
                sb.append(line);
                sb.append("\n");
            }
                                  
            String txt = sb.toString();
            t.setTxt(txt);
            //t.setFileLoc(ifc.getOutFolderPath());
            
           
            // creates an empty Annotation just with the given text
            document = new Annotation(txt);
            // run all Annotators on this text
	    pipeline.annotate(document);	        
            // create the annotated text
            File annotated = new File(ifc.getOutFolderPath()+"annotated.txt");
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
     
        ArrayList<String> types = new ArrayList<String>();
        @Override
        int countTypes() {
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
            return (double) types.size()/countWords();
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
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(PartOfSpeechAnnotation.class).equals("DT"))
                            determiners++;      
                }
            }
        }
            return determiners;
        }
        
        
        @Override
        int countDemonstratives() {
            int demonstratives = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(LemmaAnnotation.class).matches("this|that|these|those"))
                            demonstratives++;      
                }
            }
        }
            return demonstratives;
        }
        

        
        @Override
        int countPronouns() {
            int pronouns = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(PartOfSpeechAnnotation.class).matches("(PRP|PRP\\$|WP|WP\\$)"))
                            pronouns++;
                    }
            }
        }
            return pronouns;
        }
        
        
        @Override
        int count1stPersonPronouns() {
            int firstPersonPronouns = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(LemmaAnnotation.class).matches("we|us|our|ours|ourselves|"
                                + "I|me|my|mine|myself"))
                            firstPersonPronouns++;
                    }
            }
        }
            return firstPersonPronouns;
        }
        
        
        @Override
        int count2ndPersonPronouns() {
            int scndPersonPronouns = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(LemmaAnnotation.class).matches("you|your|yours|yourself|yourselves"))
                            scndPersonPronouns++;
                    }
            }
        }
            return scndPersonPronouns;
        }
        
        
        @Override
        int count3rdPersonPronouns() {
            int thirdPersonPronouns = 0;
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                         if(token.get(LemmaAnnotation.class).
                                matches("he|him|his|himself|she|her|hers|herself|"
                                        + "it|its|itself|they|them|their|theirs|themselves"))
                            thirdPersonPronouns++;
                    }
            }
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
            if(sentences != null){
                for(CoreMap sentence: sentences) {
                    
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                         if(token.get(LemmaAnnotation.class).matches("no|not|none|"
                                + "never|nobody|nothing|nowhere|nor|neither|"
                                + ".*(less)$|.*(n't)$"))
                            negations++;
                    }
                }
            }
            return negations;
        }
        

        
        @Override
        int countFuture() {
            int future = 0;
            if(sentences != null){
                String[] w = sentences.toString().split(" ");
                for (int i = 0; i < w.length; i++) {
                    if(w[i].toLowerCase().equals("going"))
                        if(w[i+1]!=null && w[i+1].equals("to"))
                            future++;
                    }
                for(CoreMap sentence: sentences){
                    for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                        if(token.get(LemmaAnnotation.class).matches("will|.*('ll)$"))
                            future++;
                    }
                } 
            }
            return future;
        }

        @Override
        Text returnTxt() {
            
                    // a CoreLabel is a CoreMap with additional token-specific methods
                    
            return t;
        }

        	
}    
