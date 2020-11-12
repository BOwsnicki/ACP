package lingo.app;

import lingo.mvc.Controller;
import lingo.mvc.Model;

public class App {
	public static void main(String[] args) {
		Model model = new LingoModel();
		Controller controller = new LingoController(model);

		SysOutView sov = new SysOutView(controller);
		
		model.notifyViews(model.getState());
	}
}
