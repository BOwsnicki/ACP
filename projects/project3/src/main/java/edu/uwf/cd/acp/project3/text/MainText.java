package edu.uwf.cd.acp.project3.text;

import java.util.ArrayList;
import java.util.List;

import edu.uwf.cd.acp.project3.classes.Climber;

public class MainText {
	public static void main(String[] args) {
		List<Climber> cList = new ArrayList<>();
		
		cList.add(new Climber("Harry",20));
		cList.add(new Climber("Helen",20));
		cList.add(new Climber("Holly",20));
		cList.add(new Climber("Henry",20));
		cList.add(new Climber("Heidi",20));
		
		(new TextRunner()).runAll(cList);
	}
}
