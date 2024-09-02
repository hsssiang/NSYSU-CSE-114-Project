package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    private ImageView AlogorithmAnimation;
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

    @FXML
    protected void AnmationButtonClick() throws IOException {
        // 載入圖片並設置到ImageView
        while (true) {
            Image image = new Image(getClass().getResourceAsStream("/images/sample.png"));
            AlogorithmAnimation.setImage(image);
        }
    }
}