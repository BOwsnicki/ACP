package threads.deadlock;

public class AFirstRunnable implements Runnable {
	private DoubleLocked d;
	private String name;
	
	public AFirstRunnable(DoubleLocked d, String name) {
		this.d = d;
		this.name = name;
	}
	
	@Override
	public void run() {
		d.doA(name);
	}
}
