package mvc;

public abstract class View {
	private Controller controller;
	public View(Controller controller) {
		this.controller = controller;
	}
	
	protected Controller getController() {
		return controller;
	}
	
	public abstract void notify(Object result);
}
