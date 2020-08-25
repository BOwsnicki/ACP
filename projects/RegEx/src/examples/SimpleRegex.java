package examples;

public class SimpleRegex {

	public void checkStrings() {
		String s = "humbapumpa jim";
		if (s.matches(".*(jim|joe).*"))
			System.out.println("matches jim or joe");
		else
			System.out.println("no match for jim or joe");

		s = "humbapumpa jom";
		if (!s.matches(".*(jim|joe).*"))
			System.out.println("no match for jim or joe");
		else
			System.out.println("matches jim or joe");

		s = "humbaPumpa joe";
		if (s.matches(".*(jim|joe).*"))
			System.out.println("matches jim or joe");
		else
			System.out.println("no match for jim or joe");

		s = "humbapumpa joe jim";
		if (s.matches(".*(jim|joe).*"))
			System.out.println("matches jim or joe");
		else
			System.out.println("no match for jim or joe");
	}

	public void checkPhoneNumber() {
		// String pattern = "\\d\\d\\d([-,\\s])?\\d\\d\\d\\d"; // ugly!
		String pattern = "\\d{3}([-,\\s])?\\d{4}"; // cleaner!!!!!
		String[] s = { "1233323322", "1233323", "123,3323", "123 3323", "123-3323" };

		for (int i = 0; i < s.length; i++) {
			if (s[i].matches(pattern))
				System.out.println(s[i] + " legal phone number");
			else
				System.out.println(s[i] + " illegal phone number");
		}
	}

	public static void main(String[] args) {
		SimpleRegex sr = new SimpleRegex();
		sr.checkStrings();
		sr.checkPhoneNumber();

	}
}