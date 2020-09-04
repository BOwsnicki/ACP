package edu.uwf.cs.dsa.project2.dictionary;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	private static final String dictFileName = "src/main/java/edu/uwf/cs/dsa/project2/dictionary/dictionary.txt";
	private Set<String> table;
	
	public Dictionary() {
		table = new HashSet<String>();
		try {
			Scanner s = new Scanner(new File(dictFileName));
			while (s.hasNextLine()) {
				String st = s.nextLine();
				insert(st);
			}
		} catch (Exception e) {
		}
	}

	public boolean lookup(String s) {
		return table.contains(s);
	}

	public void insert(String s) {
		table.add(s);
	}
}