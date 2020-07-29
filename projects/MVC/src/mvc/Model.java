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
	
	public void notifyViews(Object changed) {
		for (View v : views) {
			v.notify(changed);
		}
	}
	
	public abstract void setState(Object arg);
	public abstract Object getState();
}
