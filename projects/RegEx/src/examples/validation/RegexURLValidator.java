package examples.validation;

public class RegexURLValidator {
	
	private static final String strRegEx = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/)([\\w\\-\\.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$";

	private static final String[] strTokens = { 
			"https://stackoverflow.com/questions/26078106/system-console-gives-nullpointerexception-in-netbeans/",
			"https://en.wikipedia.org/wiki/2006_Subway_500#Standings_after_the_race",
			"localhost:8080/foobar",
			"http://localhost:8080/foobar"
	};

	public static void main(String[] args) {
		for (String token : strTokens) {

			if (token.matches(strRegEx))
				System.out.println("valid  : " + token);
			else
				System.out.println("invalid: " + token);
		}
	}
}