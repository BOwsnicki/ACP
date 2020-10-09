package edu.uwf.cd.acp.project3.javafx;

import java.util.ArrayList;
import java.util.List;

public class Score {
	private static final List<Runnable> rl = new ArrayList<>();
	private static int index;
	public static void reset() {
		index = 0;
		rl.clear();
	}
	public static void register(Runnable r) {
		rl.add(index++,r);
	}
	public static Runnable[] standings() {
		int len = rl.size();
		Runnable[] result = new Runnable[len];
		for (int i = 0; i < len; i++) {
			result[i] = rl.get(i);
		}
		return result;
	}
}
