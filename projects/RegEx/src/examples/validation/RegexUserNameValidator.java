package examples.validation;

public class RegexUserNameValidator {

	private static final String strPattern = "^[a-zA-Z][a-zA-Z0-9_.]{7,20}$";
	private static final String[] strTokens = {
			"John", "Smith19", "Smith198", "Jason_max", "james.bond", 
			"JamesBond@007", "_michael_clarke", ".jasonbourne", };

	 public static void main(String[] args) {
		   for(String token : strTokens){
		    
		     if(token.matches(strPattern))
		        System.out.println("valid  : " + token);
		     else
		        System.out.println("invalid: " + token);
		   }
		 }
}