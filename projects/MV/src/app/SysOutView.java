package app;

import mv.Model;
import mv.View;

public class SysOutView implements View {
	
	@SuppressWarnings("unused")
	private Model model;	
	
	public SysOutView(Model model) {
		this.model = model;
		model.addView(this);
		System.out.println("Not a Frame!");
	}

	@Override
	public void notify(Object result) {
		System.out.print(" " + result);
	}
}
