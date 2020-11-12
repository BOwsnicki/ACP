package lingo.mvc;

public abstract class View {
	protected Controller controller;

	public View(Controller controller) {
		this.controller = controller;
	}
	
	public abstract void notify(Object result);
}
