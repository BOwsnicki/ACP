package lingo.app;

class LingoState { // actually more of a package visible struct!
	String guess;
	boolean[] inPlace;
	boolean[] outOfPlace;
	boolean solved;
	
	public LingoState() {
		inPlace = new boolean[5];
		outOfPlace = new boolean[5];
		solved = false;
	}
}
