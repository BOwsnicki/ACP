package app;

import java.util.Scanner;

import mvc.Controller;
import mvc.View;

class SysOutView extends View {
	private Scanner in;

	public SysOutView(Controller controller) {
		super(controller);
		in = new Scanner(System.in);
		controller.getModel().addView(this);
	}

	// Simple prompted input for the next guess
	private String getGuess() {
		System.out.print("Your guess: ");
		return in.nextLine();
	}

	// Transform booleans into a string of t/f
	private String boolRep(boolean[] b) {
		String s = "";
		for (int i = 0; i < b.length; i++) {
			s += (b[i] ? "t" : "f");
		}
		return s;
	}

	@Override
	public void notify(Object result) {
		LingoState state = (LingoState) result;
		System.out.println(boolRep(state.inPlace));
		System.out.println(state.guess);
		System.out.println(boolRep(state.outOfPlace));

		if (!state.solved) {
			UpdateStatus s = null;
			do {
				String guess = getGuess();
				s = (UpdateStatus) controller.update(guess);
				System.out.println(s);
			} while (s != UpdateStatus.OK);
		} else {
			System.out.println("YAY!!!!");
		}
	}
}
