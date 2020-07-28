package app;

import mvc.Controller;
import mvc.View;

public class SysOutView extends View {
	
	public SysOutView(Controller controller) {
		super(controller);
		controller.getModel().addView(this);
		System.out.println("Not a Frame!");
	}

	@Override
	public void notify(Object result) {
		LingoState state = (LingoState)result;
		System.out.println(state.guess);
		for (int i = 0; i < 5; i++) {
			System.out.print(state.inPlace[i]);
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			System.out.print(state.outOfPlace[i]);
		}
		System.out.println();
		
	}
}
