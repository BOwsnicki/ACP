module edu.uwf.cs.acp.jfxXMLDemo {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens edu.uwf.cs.acp.jfxXMLDemo to javafx.fxml;
    exports edu.uwf.cs.acp.jfxXMLDemo;
}