package threads.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoubleLocked {
	private Lock lockA;
	private Lock lockB;
	
	public DoubleLocked() {
		lockA = new ReentrantLock();
		lockB = new ReentrantLock();
	}
	
	public void doA(String name) {
		lockA.lock();
		try {
			System.out.println(name + " owns lock A");
			Thread.sleep(200);
			doB(name);
		} catch (InterruptedException e) {
		} finally { lockA.unlock(); }
	}
	
	public void doB(String name) {
		lockB.lock();
		try {
			System.out.println(name + " owns lock B");
			Thread.sleep(200);
			doA(name);
		} catch (InterruptedException e) {
		} finally { lockB.unlock(); }
	}
}
