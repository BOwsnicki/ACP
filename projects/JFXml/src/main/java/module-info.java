module JFXML.jfxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens JFXML.jfxml to javafx.fxml;
    exports JFXML.jfxml;
}