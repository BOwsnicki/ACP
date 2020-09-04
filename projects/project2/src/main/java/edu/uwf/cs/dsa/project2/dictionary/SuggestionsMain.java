package edu.uwf.cs.dsa.project2.dictionary;

import java.util.Set;

public class SuggestionsMain {
	public static void main(String[] args) {
		Set<String> s = Suggestions.getSuggestions("tge");
		System.out.println(s);
	}
}
