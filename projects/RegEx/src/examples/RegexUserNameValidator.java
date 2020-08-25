package examples;

public class RegexUserNameValidator {
	public static void main(String args[]) {
		String strPattern = "^[a-zA-Z][a-zA-Z0-9_.]{7,20}$";

		String[] strUserNames = { "John", "Smith19", "Smith198", "Jason_max", "james.bond", "JamesBond@007",
				"_michael_clarke", ".jasonbourne", };

		for (String strUserName : strUserNames) {

			if (strUserName.matches(strPattern)) {
				System.out.println(strUserName + " is valid");
			} else {
				System.out.println(strUserName + " is not valid");
			}
		}
	}
}