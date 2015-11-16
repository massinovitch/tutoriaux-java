package com.tagger;

import java.io.IOException;



import rita.wordnet.jwnl.wndata.POS;

import com.properties.GeneralConstants;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class TagText {
	
	private static TagText _instance = null;
	
	private static final String fileName = "taggers/left3words-wsj-0-18.tagger"; //$NON-NLS-1$	
	
	private MaxentTagger maxentTagger;

	private TagText() {
		try {
			maxentTagger = new MaxentTagger(fileName);
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public synchronized static TagText getInstance() {
		if (_instance == null)
			_instance = new TagText();
		return _instance;
	}
			
	public MaxentTagger getMaxentTagger() {
		return maxentTagger;
	}

	public void setMaxentTagger(MaxentTagger maxentTagger) {
		this.maxentTagger = maxentTagger;
	}
	
	public POS getTypeOfWord(String tag) {
		if (tag.equals(GeneralConstants.CC)) {
			return null;
		} else if (tag.equals(GeneralConstants.CD)) {
			return POS.ADJECTIVE;
		} else if (tag.equals(GeneralConstants.DT)) {
			return null;
		} else if (tag.equals(GeneralConstants.EX)) {
			return null;
		} else if (tag.equals(GeneralConstants.FW)) {
			return null;
		} else if (tag.equals(GeneralConstants.IN)) {
			return null;
		} else if (tag.equals(GeneralConstants.JJ)) {
			return POS.ADJECTIVE;
		} else if (tag.equals(GeneralConstants.JJR)) {
			return null;
		} else if (tag.equals(GeneralConstants.JJS)) {
			return null;
		} else if (tag.equals(GeneralConstants.LS)) {
			return null;
		} else if (tag.equals(GeneralConstants.MD)) {
			return null;
		} else if (tag.equals(GeneralConstants.NN)) {
			return POS.NOUN;
		} else if (tag.equals(GeneralConstants.NNS)) {
			return POS.NOUN;
		} else if (tag.equals(GeneralConstants.NNP)) {
			return POS.NOUN;
		} else if (tag.equals(GeneralConstants.NNPS)) {
			return POS.NOUN;
		} else if (tag.equals(GeneralConstants.PDT)) {
			return null;
		} else if (tag.equals(GeneralConstants.POS)) {
			return null;
		} else if (tag.equals(GeneralConstants.PRP)) {
			return null;
		} else if (tag.equals(GeneralConstants.PRP$)) {
			return null;
		} else if (tag.equals(GeneralConstants.RB)) {
			return POS.ADVERB;
		} else if (tag.equals(GeneralConstants.RBR)) {
			return POS.ADVERB;
		} else if (tag.equals(GeneralConstants.RBS)) {
			return POS.ADVERB;
		} else if (tag.equals(GeneralConstants.RP)) {
			return null;
		} else if (tag.equals(GeneralConstants.SYM)) {
			return null;
		} else if (tag.equals(GeneralConstants.TO)) {
			return null;
		} else if (tag.equals(GeneralConstants.UH)) {
			return null;
		} else if (tag.equals(GeneralConstants.VB)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.VBD)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.VBG)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.VBN)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.VBP)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.VBZ)) {
			return POS.VERB;
		} else if (tag.equals(GeneralConstants.WDT)) {
			return null;
		} else if (tag.equals(GeneralConstants.WP)) {
			return null;
		} else if (tag.equals(GeneralConstants.WP$)) {
			return null;
		} else if (tag.equals(GeneralConstants.WRB)) {
			return null;			
		} else {
			return null;			
		}
	}
}