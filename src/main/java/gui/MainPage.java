package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane TheFirstGridPane = new GridPane();
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920,1080);
        TheFirstGridPane.prefWidthProperty().bind(scene.widthProperty());
        TheFirstGridPane.prefHeightProperty().bind(scene.heightProperty());
        stage.setTitle("Earseable Pattern Mining");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}