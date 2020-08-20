module edu.uwf.cs.acp.jfxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.uwf.cs.acp.jfxml to javafx.fxml;
    exports edu.uwf.cs.acp.jfxml;
}