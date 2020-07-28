package app;

import mvc.Controller;
import mvc.Model;

public class LingoController extends Controller {

	public LingoController(Model model) {
		super(model);
	}

	@Override
	public Object update(Object action) {
		String guess = action.toString().toUpperCase();
		if (guess.length() != 5) {
			return LingoStatus.INVALID_LENGTH;
		}
		for (int i = 0; i < 5; i++) {
			if (!Character.isUpperCase(guess.charAt(i))) {
				return LingoStatus.INVALID_CONTENTS;
			}
		}
		getModel().setState(guess);
		return LingoStatus.OK;
	}
}