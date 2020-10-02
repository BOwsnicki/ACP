package threads.deadlock;

public class BFirstRunnable implements Runnable {
	private DoubleLocked d;
	private String name;
	
	public BFirstRunnable(DoubleLocked d, String name) {
		this.d = d;
		this.name = name;
	}
	
	@Override
	public void run() {
		d.doB(name);
	}
}
