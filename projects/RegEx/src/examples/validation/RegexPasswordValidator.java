package examples.validation;

public class RegexPasswordValidator {
	
	private static final String strRegEx = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";

	private static final String[] strTokens = { "mypassword", "00000000", 
			"AlphaRomeo4c", "fiatlinea2014", "F@rd1co",
			"F@rd1coSports", "Suzuki@lpha2016", "!vwvento2015", "!@#$%^&*Aa1", 
			"myDream1@$$", "Hello World@001" };

	public static void main(String[] args) {
		for (String token : strTokens) {

			if (token.matches(strRegEx))
				System.out.println("valid  : " + token);
			else
				System.out.println("invalid: " + token);
		}
	}
}