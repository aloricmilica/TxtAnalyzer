 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.IOException;

/**
 *
 * @author Milica
 */
public abstract class AbstractCalculator {

    
    public Metrics calculate() throws ClassNotFoundException, IOException{
                   
            Metrics m = new Metrics(countWords(), countTypes(), countTTR(), 
                countLettersPerWord(), countParagraphs(), countSentences(), 
                countWordsPerSentence(), countDeterminers(), countDemonstratives(), 
                countPronouns(),count1stPersonPronouns(), count2ndPersonPronouns(), 
                count3rdPersonPronouns(), countConjuncts(), countConnectives(), 
                countNegations(), countFuture());
       
    
            return m;
        }
    
    abstract Text returnTxt();
    abstract int countWords();
    abstract int countTypes();
    abstract double countTTR();
    abstract double countLettersPerWord();
    abstract int countParagraphs();
    abstract int countSentences();
    abstract double countWordsPerSentence();
    abstract int countDeterminers();
    abstract int countDemonstratives();
    abstract int countPronouns();
    abstract int count1stPersonPronouns();
    abstract int count2ndPersonPronouns();
    abstract int count3rdPersonPronouns();
    abstract int countConjuncts();
    abstract int countConnectives();
    abstract int countNegations();
    abstract int countFuture();
}
