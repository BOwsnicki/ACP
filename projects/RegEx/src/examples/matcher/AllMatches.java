package examples.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AllMatches {
	public static void main(String[] args) {
		  Matcher m = Pattern.compile("aaa")
		     .matcher("aaa bb aaa");
		 // Matcher m = Pattern.compile("aaa|b")
		 //	     .matcher("aaa bb aaa");
		 // Matcher m = Pattern.compile(".*(jim|joe).*")
		 //	     .matcher("humbapumpa joe jim");
		 // Matcher m = Pattern.compile(".*?(jim|joe).*?")
		 //	     .matcher("humbapumpa joe jim");
		 while (m.find()) {
		   System.out.println(m.group());
		 }
	}
}
