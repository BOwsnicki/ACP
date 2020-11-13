package edu.uwf.acp.mvcJFXML;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
	private Incrementer model = new Incrementer();
	
	@FXML private Label current;
	
	
    @FXML private void increment1() {
        current.setText(model.increment(1).toString());
    }
    
    @FXML private void increment2() {
        current.setText(model.increment(2).toString());
    }
}
