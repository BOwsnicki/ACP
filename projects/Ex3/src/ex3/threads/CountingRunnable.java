package ex3.threads;

public class CountingRunnable implements Runnable {

	@Override
	public void run() {
		int count = 20;
		while (count != 0) {
			System.out.println(this + " " + count--);
			try {
				Thread.sleep((int) Math.random() * 100);
			} catch (InterruptedException e) {
			}
		}
	}
}
