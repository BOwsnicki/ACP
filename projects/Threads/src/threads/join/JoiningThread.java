package threads.join;

public class JoiningThread extends Thread {
	private String name;
	
	public JoiningThread(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(500);
				System.out.println("Current Thread: " + name);
			}

			catch (Exception ex) {
				System.out.println("Exception has" + " been caught" + ex);
			}
		}
		System.out.println(name + " terminated");
	}
}
