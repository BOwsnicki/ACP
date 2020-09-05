package edu.uwf.cs.dsa.project2.dictionary;

import java.util.Locale;

public class LanguageSettings {
	public static final Locale US_EN = new Locale("EN","US");
	public static final Locale UK_EN = new Locale("EN","UK");
	private Locale currentLocale = US_EN;
	
	public Locale getLocale() {
		return currentLocale;
	}
	
	public void setLocale(Locale l) {
		currentLocale = l;
	}
	
	public static void main(String[] args) {
		System.out.println(US_EN.getCountry() + " " + US_EN.getLanguage());
		System.out.println(UK_EN.getCountry() + " " + UK_EN.getLanguage());
	}
}
