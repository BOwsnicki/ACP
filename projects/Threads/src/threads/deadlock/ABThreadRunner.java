package threads.deadlock;

public class ABThreadRunner {
	public static void main(String[] args) {
		DoubleLocked d = new DoubleLocked();

		Runnable rA = new AFirstRunnable(d,"A first");
		Runnable rB = new BFirstRunnable(d,"B first");

		Thread tA = new Thread(rA);
		Thread tB = new Thread(rB);
		
		tA.start();
		tB.start();
	}
}
