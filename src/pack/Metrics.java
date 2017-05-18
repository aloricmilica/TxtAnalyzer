/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

public class Metrics {

	int numWords;
	int numTypes;
	double TTR;
	double lettersPerWord;
	int numParagraphs;
	int numSentences;
	double wordsPerSentence;
	int determiners;
	int demonstratives;
	int numPronouns;
	int firstPersonPr;
	int secondPersonPr;
	int thirdPersonPr;
	int conjuncts;
	int connectives;
	int negations;
	int future;

        public Metrics(int numWords, int numTypes, double TTR, double 
                lettersPerWord, int numParagraphs, int numSentences, double 
                        wordsPerSentence, int determiners, int demonstratives, 
                        int numPronouns, int firstPersonPr, int secondPersonPr, 
                        int thirdPersonPr, int conjuncts, int connectives, int 
                                negations, int future) {
            this.numWords = numWords;
            this.numTypes = numTypes;
            this.TTR = TTR;
            this.lettersPerWord = lettersPerWord;
            this.numParagraphs = numParagraphs;
            this.numSentences = numSentences;
            this.wordsPerSentence = wordsPerSentence;
            this.determiners = determiners;
            this.demonstratives = demonstratives;
            this.numPronouns = numPronouns;
            this.firstPersonPr = firstPersonPr;
            this.secondPersonPr = secondPersonPr;
            this.thirdPersonPr = thirdPersonPr;
            this.conjuncts = conjuncts;
            this.connectives = connectives;
            this.negations = negations;
            this.future = future;
        }

	public int getNumWords() {
		return numWords;
	}

	public void setNumWords(int numWords) {
		this.numWords = numWords;
	}

	public int getNumTypes() {
		return numTypes;
	}

	public void setNumTypes(int numTypes) {
		this.numTypes = numTypes;
	}

	public double getTTR() {
		return TTR;
	}

	public void setTTR(double tTR) {
		TTR = tTR;
	}

	public double getLettersPerWord() {
		return lettersPerWord;
	}

	public void setLettersPerWord(double lettersPerWord) {
		this.lettersPerWord = lettersPerWord;
	}

	public int getNumParagraphs() {
		return numParagraphs;
	}

	public void setNumParagraphs(int numParagraphs) {
		this.numParagraphs = numParagraphs;
	}

	public int getNumSentences() {
		return numSentences;
	}

	public void setNumSentences(int numSentences) {
		this.numSentences = numSentences;
	}

	public double getWordsPerSentence() {
		return wordsPerSentence;
	}

	public void setWordsPerSentence(double wordsPerSentence) {
		this.wordsPerSentence = wordsPerSentence;
	}

	public int getDeterminers() {
		return determiners;
	}

	public void setDeterminers(int determiners) {
		this.determiners = determiners;
	}

	public int getDemonstratives() {
		return demonstratives;
	}

	public void setDemonstratives(int demonstratives) {
		this.demonstratives = demonstratives;
	}

	public int getNumPronouns() {
		return numPronouns;
	}

	public void setNumPronouns(int numPronouns) {
		this.numPronouns = numPronouns;
	}

	public int getFirstPersonPr() {
		return firstPersonPr;
	}

	public void setFirstPersonPr(int firstPersonPr) {
		this.firstPersonPr = firstPersonPr;
	}

	public int getSecondPersonPr() {
		return secondPersonPr;
	}

	public void setSecondPersonPr(int secondPersonPr) {
		this.secondPersonPr = secondPersonPr;
	}

	public int getThirdPersonPr() {
		return thirdPersonPr;
	}

	public void setThirdPersonPr(int thirdPersonPr) {
		this.thirdPersonPr = thirdPersonPr;
	}

	public int getConjuncts() {
		return conjuncts;
	}

	public void setConjuncts(int conjuncts) {
		this.conjuncts = conjuncts;
	}

	public int getConnectives() {
		return connectives;
	}

	public void setConnectives(int connectives) {
		this.connectives = connectives;
	}

	public int getNegations() {
		return negations;
	}

	public void setNegations(int negations) {
		this.negations = negations;
	}

	public int getFuture() {
		return future;
	}

	public void setFuture(int future) {
		this.future = future;
	}

    @Override
    public String toString() {
        return  "," +numWords +","+ numTypes +","+  TTR +","+  lettersPerWord +","+ 
                numParagraphs +","+ numSentences +","+ wordsPerSentence +","+  
                determiners +","+ demonstratives +","+ numPronouns +","+ 
                firstPersonPr +","+ secondPersonPr +","+ thirdPersonPr +","+ 
                conjuncts +","+ connectives +","+ negations +","+ future;
    }

}
