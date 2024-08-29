module org.example.guiaug2024 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens gui to javafx.fxml;
    exports gui;
}