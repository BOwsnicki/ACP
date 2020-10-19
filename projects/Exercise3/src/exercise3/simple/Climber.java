package exercise3.simple;


public class Climber
{
	private String name;
	private int currentRung;
	private int lastRung;
	
	public Climber(String name, int lastRung) {
		currentRung = 0;
		this.name = name;
		this.lastRung = lastRung;
	}
	
	public void climb() {
		currentRung++;
	}
	
	public void reset() {
		currentRung = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCurrentRung() {
		return currentRung;
	}
	
	public boolean done() {
		return (currentRung == lastRung);
	}
}