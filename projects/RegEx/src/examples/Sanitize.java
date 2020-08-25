package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sanitize {
	public static void main(String[] args) {
		String inject = "Robert\"; DROP TABLE USERS; -- ";
		System.out.println(inject);
		String blackListRegex = "[\"(--);]";
		Pattern pat = Pattern.compile(blackListRegex);
		Matcher matcher = pat.matcher(inject);
		String clean = matcher.replaceAll("?");
		System.out.println(clean);
	}
}