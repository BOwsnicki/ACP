module edu.uwf.cs.acp.jfxml {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens edu.uwf.cs.acp.jfxml to javafx.fxml;
    exports edu.uwf.cs.acp.jfxml;
}