package edu.uwf.acp.mvcJFXML;

public class Incrementer {
	int count;

	public Incrementer() {
		super();
		count = 0;
	}

	public Integer increment(int incr) {
		count += incr;
		return count;
	}
}
