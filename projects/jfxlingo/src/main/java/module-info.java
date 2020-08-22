module edu.uwf.cs.acp.jfxlingo {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens edu.uwf.cs.acp.jfxlingo to javafx.fxml;
    exports edu.uwf.cs.acp.jfxlingo;
}