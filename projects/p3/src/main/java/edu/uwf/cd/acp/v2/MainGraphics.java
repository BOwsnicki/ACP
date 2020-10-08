package edu.uwf.cd.acp.v2;

import java.util.ArrayList;
import java.util.List;

public class MainGraphics {
	public static void main(String[] args) {
		List<Climber> cList = new ArrayList<>();
		
		cList.add(new Climber("Harry",20));
		cList.add(new Climber("Helen",20));
		cList.add(new Climber("Holly",20));
		cList.add(new Climber("Henry",20));
		cList.add(new Climber("Heidi",20));
		
		(new GraphicsRunner()).runAll(cList);
	}
}
