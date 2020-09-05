package edu.uwf.cs.dsa.project2.dictionary;

import java.util.Set;

public class SuggestionsMain {
	public static void main(String[] args) {
		Set<String> s; 
		/*
		s = (new Suggestions(LanguageSettings.UK_EN)).getSuggestions("tge");
		System.out.println(s);
		s = (new Suggestions(LanguageSettings.US_EN)).getSuggestions("neighbour");
		System.out.println(s);
		s = (new Suggestions(LanguageSettings.UK_EN)).getSuggestions("neighbour");
		System.out.println(s);
		*/
		
		s = (new Suggestions(LanguageSettings.US_EN)).getSuggestions("neighbor");
		System.out.println(s);
	}
}
