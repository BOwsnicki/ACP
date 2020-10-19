package exercise3.simple;


import java.util.ArrayList;
import java.util.List;

public class Runner {
	public void runAll(List<Climber> cList) {
		Thread myself = Thread.currentThread();	
		List<Thread> threadList = new ArrayList<>();
	
		cList.forEach(c -> {
			threadList.add(new Thread(new ClimberRunnable(c)));
		});
		
		threadList.forEach(t -> {t.start();});
	}
}
