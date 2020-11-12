module edu.uwf.acp.mvcJFXML {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.uwf.acp.mvcJFXML to javafx.fxml;
    exports edu.uwf.acp.mvcJFXML;
}