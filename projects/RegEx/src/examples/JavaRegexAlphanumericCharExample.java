package examples;

public class JavaRegexAlphanumericCharExample {

	public static void main(String args[]) {

		String strPattern = "^[a-zA-Z0-9]*$"; // Kleene star - zero or more-allows empty string
		// String strPattern = "^[a-zA-Z0-9]+$"; // one or more char - no empty string
		String inputString1 = "StringWithSpecialCharacters,Numbers01And@Symbol";
		String inputString2 = "01AlphanumericString";
		String inputString3 = "";
		if (inputString1.matches(strPattern))
			System.out.println(inputString1 + ": is alphanumeric");
		else
			System.out.println(inputString1 + ": is not alphanumeric");

		if (inputString2.matches(strPattern))
			System.out.println(inputString2 + ": is alphanumeric");
		else
			System.out.println(inputString2 + ": is not alphanumeric");

		if (inputString3.matches(strPattern))
			System.out.println(inputString3 + ": allows the empty string");
		else
			System.out.println(inputString3 + ": does not allow empty string");

	}
}