package exercise3.tricky;


import java.util.ArrayList;
import java.util.List;

public class Runner {
	public void runAll(List<Climber> cList) {
		Thread myself = Thread.currentThread();	
		List<Thread> threadList = new ArrayList<>();
	
		cList.forEach(c -> {
			threadList.add(new Thread(new ClimberRunnable(c,myself)));
		});
		
		threadList.forEach(t -> {t.start();});
		
		synchronized (myself) {
			try {
				myself.wait();
			} catch (InterruptedException e) {
				threadList.forEach(t -> {if (t.isAlive()) 
											{t.interrupt();}});
			}
		}
	}
}
