package edu.uwf.cs.acp.jfxlingo.model;

public class LingoState { // actually more of a package visible struct!
	String guess;
	boolean[] inPlace;
	boolean[] outOfPlace;
	boolean solved;
	
	public LingoState() {
		inPlace = new boolean[5];
		outOfPlace = new boolean[5];
		solved = false;
	}
	
	// Transform booleans into a string of t/f
	private String boolRep(boolean[] b) {
		String s = "";
		for (int i = 0; i < b.length; i++) {
			s += (b[i] ? "t" : "f");
		}
		return s;
	}
	
	@Override
	public String toString() {
		return boolRep(this.inPlace) + " * " + this.guess + " * " + boolRep(this.outOfPlace);
	}
}
