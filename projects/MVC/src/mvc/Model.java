package mvc;

import java.util.ArrayList;
import java.util.List;

public abstract class Model {
	private List<View> views;
	
	public Model () {
		 this.views = new ArrayList<>();
	}
	
	public void addView(View v) {
		views.add(v);
	}
	
	public List<View> getViews() {
		// manual shallow copy here (tricky decision!)
		List<View> copy = new ArrayList<>();
		for (View v : views) {
			copy.add(v);
		}
		return copy;
	}
	
	public void notifyViews(Object changed) {
		for (View v : views) {
			v.notify(changed);
		}
	}
	
	
	public abstract void setState(Object arg);
	public abstract Object getState();
}
