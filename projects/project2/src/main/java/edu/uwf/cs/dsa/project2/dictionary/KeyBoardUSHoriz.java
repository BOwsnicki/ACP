package edu.uwf.cs.dsa.project2.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyBoardUSHoriz {
	private static Map<Character, List<Character>> neighbors 
		= new HashMap<Character, List<Character>>();
	
	static {
		neighbors.put('q', new ArrayList<Character>(Arrays.asList('w')));
		neighbors.put('w', new ArrayList<Character>(Arrays.asList('q','e')));
		neighbors.put('e', new ArrayList<Character>(Arrays.asList('w','r')));
		neighbors.put('r', new ArrayList<Character>(Arrays.asList('e','t')));
		neighbors.put('t', new ArrayList<Character>(Arrays.asList('r','y')));
		neighbors.put('y', new ArrayList<Character>(Arrays.asList('t','u')));
		neighbors.put('u', new ArrayList<Character>(Arrays.asList('y','i')));
		neighbors.put('i', new ArrayList<Character>(Arrays.asList('u','o')));
		neighbors.put('u', new ArrayList<Character>(Arrays.asList('i','p')));
		neighbors.put('p', new ArrayList<Character>(Arrays.asList('o')));
		neighbors.put('a', new ArrayList<Character>(Arrays.asList('s')));
		neighbors.put('s', new ArrayList<Character>(Arrays.asList('a','d')));
		neighbors.put('d', new ArrayList<Character>(Arrays.asList('s','f')));
		neighbors.put('f', new ArrayList<Character>(Arrays.asList('d','g')));
		neighbors.put('g', new ArrayList<Character>(Arrays.asList('f','h')));
		neighbors.put('h', new ArrayList<Character>(Arrays.asList('g','j')));
		neighbors.put('j', new ArrayList<Character>(Arrays.asList('h','k')));
		neighbors.put('k', new ArrayList<Character>(Arrays.asList('j','l')));
		neighbors.put('l', new ArrayList<Character>(Arrays.asList('k')));
		neighbors.put('z', new ArrayList<Character>(Arrays.asList('x')));
		neighbors.put('x', new ArrayList<Character>(Arrays.asList('z','c')));
		neighbors.put('c', new ArrayList<Character>(Arrays.asList('x','v')));
		neighbors.put('v', new ArrayList<Character>(Arrays.asList('c','b')));
		neighbors.put('b', new ArrayList<Character>(Arrays.asList('v','n')));
		neighbors.put('n', new ArrayList<Character>(Arrays.asList('b','m')));
	}
	
	public static List<Character> neighbors(char c) {
		List<Character> l = neighbors.get(c);
		return l;
	}
}
