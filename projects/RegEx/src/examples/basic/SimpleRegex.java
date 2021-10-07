package examples.basic;

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
		
		s = "humbapumpa joe jim";
		if (s.matches(".*(jim|joe).*"))
			System.out.println("matches jim or joe");
		else
			System.out.println("no match for jim or joe");
		
		s = "humbapumpa jim";
		if (s.matches(".*(jim|joe)."))
			System.out.println("matches jim or joe");
		else
			System.out.println("no match for jim or joe");
	}

	public static void main(String[] args) {
		SimpleRegex sr = new SimpleRegex();
		sr.checkStrings();
	}
}