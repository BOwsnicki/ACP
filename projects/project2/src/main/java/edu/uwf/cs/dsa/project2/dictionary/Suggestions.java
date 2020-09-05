package edu.uwf.cs.dsa.project2.dictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Suggestions {
	private Dictionary d;
	private Set<String> suggestions = new HashSet<String>();
	private Locale activeLocale;
	private KeyboardMap kbMap;
	
	public Suggestions(Locale l) {
		activeLocale = l;
		d = new Dictionary(activeLocale);
		kbMap = KeyboardMapFactory.createKeyboardMap(activeLocale);
	}
	
	public Suggestions() {
		this(LanguageSettings.US_EN);
	}
	
	public void oneLetterAdded(String word) {
		int i;
		char ch;
		StringBuffer st = new StringBuffer(word);
		for (i = 0; i < st.length(); i++) // one letter added
		{
			ch = st.charAt(i);
			st.deleteCharAt(i);
			System.out.println("one letter added = [" + st + "]");
			boolean isThere = d.lookup(st.toString());
			if (isThere) {
				System.out.println("FOUND = " + st);
				suggestions.add(st.toString());
			}
			st.insert(i, ch);
		}
	}

	public void twoLettersReversed(String word) {
		int i;
		char ch1, ch2;
		for (i = 0; i < word.length() - 1; i++) // two letters reversed
		{
			StringBuffer st = new StringBuffer(word);
			ch1 = st.charAt(i);
			ch2 = st.charAt(i + 1);

			st.deleteCharAt(i);
			st.deleteCharAt(i);
			st.insert(i, ch1);
			st.insert(i, ch2);
			System.out.println("two letter reversed = [" + st + "]");
			boolean isThere = d.lookup(st.toString());
			if (isThere) {
				System.out.println("FOUND = " + st);
				suggestions.add(st.toString());
			}
		}
	}

	public void oneLetterDeleted(String word) {
		final int chars = 26;
		int i, k;
		int x = 0;
		for (i = 0; i <= word.length(); i++) {
			StringBuffer st = new StringBuffer(word);
			for (k = 0; k < chars; k++) {
				st.insert(i, (char) ('a' + k));
				System.out.println(x++ + " one letter deleted = [" + st + "]");
				boolean isThere = d.lookup(st.toString());
				if (isThere) {
					System.out.println("FOUND = " + st);
					suggestions.add(st.toString());
				}
				st.deleteCharAt(i);
			}
		}
	}

	public void horizontalShift(String word) {
		StringBuilder sb = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++)
		{
			char ch = word.charAt(i);
			List<Character> substitutions= kbMap.neighbors(ch);
			for (char c : substitutions) {
				sb.setCharAt(i,c);
				System.out.println(" horizontal shift = [" + sb + "]");
				if (d.lookup(sb.toString())) {
					System.out.println("FOUND = " + sb);
					suggestions.add(sb.toString());
				}
				sb.setCharAt(i,ch);
			}
		}
	}
	
	public Set<String> getSuggestions(String word) {
		suggestions.clear();
		oneLetterDeleted(word);
		twoLettersReversed(word);
		oneLetterAdded(word);
		horizontalShift(word);
		return suggestions;
	}
}
