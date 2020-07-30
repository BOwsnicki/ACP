module javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens hello to javafx.fxml;
    exports hello;
}