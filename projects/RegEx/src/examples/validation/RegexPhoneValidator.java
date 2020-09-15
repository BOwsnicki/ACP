package examples.validation;

public class RegexPhoneValidator {

	private static final String strPattern = "^((\\(\\d{3}\\) ?)|(\\d{3}-))?\\d{3}-\\d{4}$";
	private static final String[] strTokens = {
			"(800) 555-6119", "800-555-6199","555-6119", 
			"001-800-754-3010", "+1-800-754-3010" };

	 public static void main(String[] args) {
		   for(String token : strTokens){
		    
		     if(token.matches(strPattern))
		        System.out.println("valid  : " + token);
		     else
		        System.out.println("invalid: " + token);
		   }
		 }
}