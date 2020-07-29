package app;

import mvc.Model;

class LingoModel extends Model {

	private String targetWord;
	private LingoState state;

	// Create a random 5 letter word to guess
	private String createTargetWord() {
		return "PRICE"; // subtle improvements are imaginable :-)
	}

	public LingoModel() {
		state = new LingoState();
		targetWord = createTargetWord();
		buildState(targetWord.charAt(0) + "....");
	}
	
	private void buildState(String guess) {
		state.guess = guess;
		state.solved = true;
		for (int i = 0; i < 5; i++) {
			state.inPlace[i] = (targetWord.charAt(i) == guess.charAt(i));
			if (!state.inPlace[i]) {
				state.solved = false;
			}
			state.outOfPlace[i] = (!state.inPlace[i] && targetWord.contains(""+guess.charAt(i)));
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
