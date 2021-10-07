package edu.uwf.cs.acp.p3.matrix;

public class CountDown {
	private int count;
	public CountDown(int max) {
		count = max;
	}
	public synchronized int getCount() {
		return count;
	}
	public synchronized void decrement() {
		count--;
	}
}
