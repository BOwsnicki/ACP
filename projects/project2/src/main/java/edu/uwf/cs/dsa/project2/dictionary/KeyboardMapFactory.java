package edu.uwf.cs.dsa.project2.dictionary;

import java.util.Locale;

public class KeyboardMapFactory {
	public static KeyboardMap createKeyboardMap(Locale l) {
		if (l.getLanguage().contentEquals("en")) {
			return new KeyboardMap_EN();
		}
		return null;
	}
}
