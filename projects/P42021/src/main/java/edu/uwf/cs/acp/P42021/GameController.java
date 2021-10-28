package edu.uwf.cs.acp.P42021;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
	private static List<String> words;
	private String chosenWord;

	static {
		words = new ArrayList<>();
		try {
			Scanner s = new Scanner(new File("words.txt"));
			while (s.hasNextLine()) {
				words.add(s.nextLine().toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GameController() {
		reset();
	}

	public void reset() {
		chosenWord = randomString();
	}
	private String randomString() {
		int index = (int)(Math.random()*words.size());
		return words.get(index);
	}
	
	public String clue() {
		return chosenWord.charAt(0) + "____";
	}
	
	public String respondTo(String guess) {
		if (guess.length() != 5) {
			return "_____";
		}
		String response = "";
		for (int i = 0; i < 5; i++) {
			char c = guess.charAt(i);
			if (chosenWord.charAt(i) == c) {
				response += c;
			} else {
				if (chosenWord.indexOf(c) >= 0) {
					response += ("" + c).toLowerCase();
				} else {
					response += "_";
				}
			}
		}
		return response;
	}
	
	public int correct(String state) {
		int count = 0;
		for (int i = 0; i < state.length(); i++) {
			if (state.charAt(i) >= 'A' && state.charAt(i) <= 'Z') count++;
		}
		return count;
	}
	private String replaceChar(String str, char ch, int index) {
	    return str.substring(0, index) + ch + str.substring(index+1);
	}
	public String merge(String s1, String s2) {
		String merged = "_____";		
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) >= 'A' && s1.charAt(i) <= 'Z') {
				merged = replaceChar(merged,s1.charAt(i),i);
			}
			if (s2.charAt(i) >= 'A' && s2.charAt(i) <= 'Z') {
				merged = replaceChar(merged,s2.charAt(i),i);
			}
		}
		return merged;
	}
	
	public String display(String state) {
		String top = "|";
		for (int i = 0; i < state.length(); i++) {
			top += (" " + state.charAt(i)).toUpperCase() + " |";
		}
		String middle = "+---+---+---+---+---+";
		String bottom = "|";
		for (int i = 0; i < state.length(); i++) {
			if (state.charAt(i) >= 'a' && state.charAt(i) <= 'z') {
				bottom += " ? |";
			} else {
				bottom += "   |";
			}
		}
		return middle + "\n" + top + "\n" + middle + "\n" + bottom + "\n" + middle;
	}
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		Scanner in = new Scanner(System.in);
		String state = gc.clue();
		String best = state;
		System.out.println(state);
		System.out.println(best);
		while (gc.correct(state) < 5) {
			System.out.print("> ");
			String guess = in.nextLine();
			state = gc.respondTo(guess);
			System.out.println(gc.display(state));
			best = gc.merge(best,state);
			System.out.println(state);
			System.out.println(best);
		}	
	}
}