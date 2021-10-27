package edu.uwf.cs.acp.P42021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
	private List<String> words;

	public GameController() {
		words = new ArrayList<>();
		try {
			Scanner s = new Scanner(new File("words.txt"));
			while (s.hasNextLine()) {
				String st = s.nextLine();
				insert(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insert(String s) {
		words.add(s);
	}
	
	private String randomString() {
		int index = (int)(Math.random()*words.size());
		return words.get(index);
	}
}