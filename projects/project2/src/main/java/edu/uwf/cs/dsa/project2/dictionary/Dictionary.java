package edu.uwf.cs.dsa.project2.dictionary;

import java.io.File;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	private static final String dictFileName = "src/main/java/edu/uwf/cs/dsa/project2/dictionary/dictionary_";
	private Set<String> table;
	
	public Dictionary(Locale l) {
		table = new HashSet<String>();
		try {
			Scanner s = new Scanner(new File(dictFileName+l.getCountry()+"_"+l.getLanguage()+".txt"));	
			while (s.hasNextLine()) {
				String st = s.nextLine();
				insert(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean lookup(String s) {
		return table.contains(s);
	}

	public void insert(String s) {
		table.add(s);
	}
}