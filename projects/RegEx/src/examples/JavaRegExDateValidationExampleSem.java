package examples;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class JavaRegExDateValidationExampleSem {

	public static void main(String[] args) {

		// Dates to validate in yyyy-mm-dd format
		String[] strDates = { "2018-10-31", "2012-12-12", "2005-3-11", "2015-55-34", "2015-99-99", "2015-13-23",
				"2015-12-32", };

		for (String strDate : strDates) {
			validateDate(strDate);
		}
	}

	private static void validateDate(String strDate) {

		boolean isValid = false;

		String strDateRegEx = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])";

		if (strDate.matches(strDateRegEx)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				sdf.parse(strDate);
				isValid = true;
			} catch (ParseException e) {
			}

		}

		System.out.println("Is date [" + strDate + "] valid? " + isValid);
	}

}