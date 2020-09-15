package examples.validation;

public class RegexEmailValidator {

	private static final String strPattern = "^\\w+@[a-zA-Z-]+.[a-zA-Z]{2,3}$";
	private static final String[] strTokens = {
			"me@you.com", "me@you-or-me.com","me.myself@you.com", 
			"heinz.klabuster@gmail.com", 
			"JamesBond@007", "user@sub.domain.com" };

	 public static void main(String[] args) {
		   for(String token : strTokens){
		    
		     if(token.matches(strPattern))
		        System.out.println("valid  : " + token);
		     else
		        System.out.println("invalid: " + token);
		   }
		 }
}