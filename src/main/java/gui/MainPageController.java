package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    protected void InsertDataButtonClick() throws IOException {
        gui.InsertNewData insertNewData = new gui.InsertNewData();
        insertNewData.start(new Stage());
    }
    @FXML
    protected void FileViewerButtonClick() throws IOException {
        gui.FileViewer FileViewer = new gui.FileViewer();
        FileViewer.start(new Stage());
    }
}