package app;

import mvc.Model;

public class LingoModel extends Model {

	private String word;
	private LingoState state;

	private String getNewWord() {
		return "PRICE"; // subtle improvements are imaginable :-)
	}

	public LingoModel() {
		word = getNewWord();
		buildState(word.charAt(0) + "....");
	}

	private void buildState(String guess) {
		state.guess = guess;
		for (int i = 0; i < 5; i++) {
			state.inPlace[i] = (word.charAt(i) == guess.charAt(i));
			state.outOfPlace[i] = (!state.inPlace[i] && word.contains(""+guess.charAt(i)));
		}
	}
	
	@Override
	public void setState(Object action) {
		buildState(action.toString().toUpperCase());
		notifyViews(state);
	}

	@Override
	public Object getState() {
		return state;
	}
}
