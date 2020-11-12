package lingo.mvc;

public abstract class Controller {
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public Model getModel() {
		return model;
	}
	
	public abstract Object update(Object action);
}
