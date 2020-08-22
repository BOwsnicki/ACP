package edu.uwf.cs.acp.jfxlingo;

import edu.uwf.cs.acp.jfxlingo.model.LingoModel;
import edu.uwf.cs.acp.jfxlingo.model.LingoState;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 
public class FxFXMLController 
{
	private LingoModel model;
	
    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField inputText;
     
    // The reference of outputText will be injected by the FXML loader
    @FXML
    private TextArea outputText;
     
    // location and resources will be automatically injected by the FXML loader 
    @FXML
    private URL location;
     
    @FXML
    private ResourceBundle resources;
     
    // Add a public no-args constructor
    public FxFXMLController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	model = new LingoModel();
    }
     
    @FXML
    private void printOutput() 
    {
    	model.setState(inputText.getText());
    	LingoState state = (LingoState)(model.getState());
        outputText.appendText(state.toString() + "\n");
    }
}