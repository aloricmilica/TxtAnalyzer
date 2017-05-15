# Linguistic Metrics Analyzer - LMA
## Introduction
The purpose of this final project was creating a software library which calculates the linguistic metrics of a textual file, such as
the number of paraghraps, pronouns, negation, future etc. All in all, this application calculates all the metrics that can be calculated by Simple Natural Language Processing tool (SiNLP). The metrics which needed to be analyzed were determined based on the paper: *Crossley, S. A., Allen, L. K., Kyle, K., & McNamara, D.S. (2014). Analyzing discourse processing using a simple natural language processing tool (SiNLP). Discourse Processes, 51(5-6), pp. 511-534.2*. 

## About SiNLP
SiNLP is a software written in Python programming language which was a role-model for creating this project. It was developped by a group of researches with the support of the Institute for Education Sciences, in order to provide discourse researchers with an additional tool with which to identify and examine the mental representations and processes involved in the production and comprehension of language. 

In order to analyze their text, users are first asked from SiNLP GUI to select a custom list dictionary. These custom lists allow users to create their own custom word lists to analyze texts specific to their research questions. Included with SiNLP is a starter custom list dictionary that includes determiners, demonstratives, all pronouns, first person pronouns, second person pronouns, third person pronouns, conjuncts, connectives, negations, and future. After selecting a list dictionary, users select the input (text) files through a file-dialog prompt, choose where they want the output file to be saved, and click a button to process the texts.

SiNLP will also run without a custom list dictionary, and that way it will provide the name of each text processed, in addition to number of words, number of types, TTR, Letters per word, number of paragraphs, number of sentences, and number of words per sentence. After processing the texts, SiNLP saves a comma-separated values file (.csv) that can be opened in any spreadsheet software program. The output includes the filename for the text file analyzed and the calculated metrics.

## LMA - Software library for text metrics calculation
### Class Diagram
![alt text](https://github.com/aloricmilica/TxtAnalyzer/blob/master/src/images/ClassDiagram.png "Class Diagram")

Template Pattern was used as a solution to this problem, as it defines the operation algorithm sceleton while leaving some operation steps to be taken by subclasses. In this concrete case, Template Pattern defines the sceleton of calculate() method, while the steps of this algorithm (abstract methods) are redefined by subclasses - DefaultDictionaryCalc or CustomDictionaryCalc. This was done to differentiate the case when users want to run the calculation using a custom dictionary, from the case when they want to use a default one.

When running the application, users are first required to input certain information about the .txt file they want to analyze and the results file: input file path, output folder path, output file name and optionally custom dictionary file path, before they click the button Calculate. When the calculate() method is called, each of the redefined abstract methods calculates a needed metric, so an object of Metrics class featuring these calculated values is created. In the end, the program assigns these metrics to a new Text class object and creates an output .csv file with information about output file location and all the calculated metrics.

When making and using custom dictionaries, users are advised to only set the values for determiners, demonstratives, all pronouns, first person pronouns, second person pronouns, third person pronouns, negations and future. This is so users can be able to use this software efficiently even with texts written in foreign languages. If users choose to use a custom dictionary, they must first save them as a tab-delimited text file.


### Output file example
![alt text](https://github.com/aloricmilica/TxtAnalyzer/blob/master/src/images/resultsExample.png "Results Example")
Output file location is represented in the first column, while calculated metrics are:
- number of words
- number of types (unique appearances of a word)
- TTR (Type Token Ratio - the more types there are in comparison to the number of tokens, 
     then the more varied is the vocabulary, i.e. it there is greater lexical variety)
- Letters per word
- number of paragraphs
- number of sentences
- number of words per sentence
- determiners (a	an	the	this	that	these	those)
- demonstratives (this	that	these	those)
- all pronouns (i	me	we	us	my	our	myself	ourselves	you	your	yourself	youselves	he	she	her	him	his	they	them	their	himself	        herself	themselves)
- first person pronouns (i	me	we	us	my	our	myself	ourselves)
- second person pronouns (you your  yourself  yourselves)
- third person pronouns (he	she	her	him	his	they	them	their	himself	herself	themselves)
- conjuncts
- connectives 
- negations (no	none	nope	not	nothing	nowhere	*n't) 
- future (will *'ll)

## Technical representation
Linguistic Metrics Analyzer was written in Java programming language by using Stanford CoreNLP Java framework for text processing.

### Stanford CoreNLP
Stanford CoreNLP provides a set of natural language analysis tools. It can give the base forms of words, their parts of speech, indicate sentiment, extract particular or open-class relations between entity mentions etc. It was developped by The Stanford NLP Group which provides statistical NLP, deep learning NLP, and rule-based NLP tools for major computational linguistics problems, which can be incorporated into applications with human language technology needs.

Stanford CoreNLP’s goal is to make it very easy to apply a bunch of linguistic analysis tools to a piece of text. A tool pipeline can be run on a piece of plain text with just two lines of code. Stanford CoreNLP integrates many of Stanford’s NLP tools, including the part-of-speech (POS) tagger, the named entity recognizer (NER), the parser, the coreference resolution system, sentiment analysis, bootstrapped pattern learning, and the open information extraction tools.

## Resume
Linguistic Metrics Analyzer was developped in Java for a final project in Intelligent Systems class.
This software was built to accurately calculate various linguistic variables in a selected textual file, and to present those results in a table saved as .csv file, which can be vey helpful for text analysis conducted by linguistics as well as for statistical researchers.

The application runs from a simplified GUI, where users first give several I/O information. Then, after clicking on the button *Calculate!*, user gets a message from the app about the outcome of the calculation process. Users can repeat this process and analyze as many text as they want, since the program will pop out a dialog box after every calculation asking if the user wants to analyze another text.
