package gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import Project.Main;

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

//    int current_display = 1;
    @FXML
    protected void AnmationButtonClick() throws IOException {
        // 載入圖片並設置到ImageView
        // String content = new String(Files.readAllBytes(Paths.get("level.txt")), StandardCharsets.UTF_8).trim();
        // 將讀取的內容轉換為整數
        //int level = Integer.parseInt(content);
        String imagepath = "output_gif.gif";
        Image image = new Image(Files.newInputStream(Paths.get(imagepath)));
        AlogorithmAnimation.setImage(image);
        //current_display++;
        //if (current_display > level) {
        //    current_display = 1;
        //}
    }
    @FXML
    protected void AlgorithmButtonClick() throws IOException {
        Main.main();
        CallPythonScript.main();
    }

    public void initialize() throws IOException {
        // 加載圖片到 ImageView 中
        String imagepath = "output_gif.gif";
        Image image = new Image(Files.newInputStream(Paths.get(imagepath)));
        AlogorithmAnimation.setImage(image);
    }
}