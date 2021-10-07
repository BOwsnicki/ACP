package ex3.threads;

public class RunnerBad {
	private static final int NUM_THREADS = 5;

	public static void main(String[] args) {
		Thread[] threads = new Thread[NUM_THREADS];
		for (int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new Thread(new CountingRunnable());
			threads[i].start();
		}
		for (Thread t : threads) {
			while (t.getState() != Thread.State.TERMINATED);
		}
		System.out.println("Done!");
	}
}
