package examples;

import java.util.regex.*;

public class PatternExample {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(".xx.");
		Matcher matcher = pattern.matcher("MxxY");
		System.out.println("Input String matches regex - " + matcher.matches());
		// bad regular expression
		pattern = Pattern.compile("*xx*");

	}

}