# Linguistic Metrics Analyzer - LMA
## Introduction
The purpose of this project is creating a software library that calculates linguistic metrics of a textual file. This application copies all the features provided by the Simple Natural Language Processing tool (SiNLP) [1].

## About SiNLP
SiNLP is a software written in Python programming language which was an inspiration for this project. It was developped by a group of researches with the support of the Institute for Education Sciences in order to provide discourse researchers with an additional tool with which to identify and examine the mental representations and processes involved in the production and comprehension of language [1].

In order to have a text analyzed, a user is first asked by SiNLP GUI to select a custom list dictionary. Custom lists allow users to create their own custom word lists to analyze texts specific to their research questions. Included within SiNLP is a starter custom list dictionary that includes determiners, demonstratives, all pronouns, first person pronouns, second person pronouns, third person pronouns, conjuncts, connectives, negations, and future. After selecting a list dictionary, a user selects input (text) files, chooses where to output the results, and initiates the metric calculation over the texts.

SiNLP can also run without a custom list dictionary, and in that mode it will provide the name of each text processed, in addition to number of words, number of types, TTR, letters per word, number of paragraphs, number of sentences, and number of words per sentence. After processing the texts, SiNLP saves results in CSV file. The output includes the filename for the text file analyzed and the calculated metrics [1].

## LMA - Software library for text metrics calculation
### Class Diagram
![alt text](https://github.com/aloricmilica/TxtAnalyzer/blob/master/src/main/resources/ClassDiagram.png "Class Diagram")

When running the application, a user is first required to input certain information about the text files to be analyzed and the results file. A user should input file path, output folder path, output file name and optionally custom dictionary file path. Metric calclualtion is initiated by clicking on the button Calculate.

When making and using custom dictionaries, users are advised to only set the values for determiners, demonstratives, all pronouns, first person pronouns, second person pronouns, third person pronouns, negations and future. This is so users could use this software efficiently even with texts written in foreign languages. If users choose to use a custom dictionary, they must first save them as a tab-delimited text file.

In the resources directory of LMA library, there is an example of a textual file that can be used as an input file for testung the application.


### Output file example
![alt text](https://github.com/aloricmilica/TxtAnalyzer/blob/master/src/main/resources/resultsExample.png "Results Example")
Output file location is represented in the first column, while the calculated metrics are:
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
- conjuncts (alternatively	altogether	consequently	conversely...)
- connectives (a relationship type between different parts of the sentence)
- negations (no	none	nope	not	nothing	nowhere	*n't) 
- future (will *'ll)

## Technical representation
Linguistic Metrics Analyzer was written in Java programming language and uses Stanford CoreNLP Java framework for text processing.

### Stanford CoreNLP
Stanford CoreNLP provides a set of natural language analysis tools. It can give the base forms of words, their parts of speech, indicate sentiment, extract particular or open-class relations between entity mentions etc. It was developped by The Stanford NLP Group which provides statistical NLP, deep learning NLP, and rule-based NLP tools for major computational linguistics problems, which can be incorporated into applications with human language technology needs [2].

Stanford CoreNLP’s goal is to make it very easy to apply a bunch of linguistic analysis tools to a piece of text. A tool pipeline can be run on a piece of plain text with just two lines of code. Stanford CoreNLP integrates many of Stanford’s NLP tools, including the part-of-speech (POS) tagger, the named entity recognizer (NER), the parser, the coreference resolution system, sentiment analysis, bootstrapped pattern learning, and the open information extraction tools [2].

Using CoreNLP in this project enabled an easy parsing of sentences, identifying different parts of speech and many more things that were crucial in writing the code for all of the methods that calculate the wanted metrics.

## Conclusion
Linguistic Metrics Analyzer is built to accurately calculate various linguistic variables of a text(s). These metrics can be vey helpful for text analysis conducted by linguistics, as well as for statistical researchers.

The application runs from a simplified GUI, where users first provide paths to input and output files, as weel as to the custom dictionary. After clicking on the button *Calculate!*, user is prompted with a message about the calculation process outcome.

## References

[1] Crossley, S. A., Allen, L. K., Kyle, K., & McNamara, D.S. (2014). *Analyzing discourse processing using a simple natural language processing tool (SiNLP). Discourse Processes, 51(5-6), pp. 511-534.*

[2] Manning, C. D., Surdeanu, M., Bauer, J., Finkel, J. R., Bethard, S., & McClosky, D. (2014, June). The stanford corenlp natural language processing toolkit. In ACL (System Demonstrations) (pp. 55-60).
