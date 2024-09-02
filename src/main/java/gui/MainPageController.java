package gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import static java.lang.Thread.sleep;

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
    protected void AnmationButtonClick() throws IOException, InterruptedException {
        // 載入圖片並設置到ImageView
        // String content = new String(Files.readAllBytes(Paths.get("level.txt")), StandardCharsets.UTF_8).trim();
        // 將讀取的內容轉換為整數
        //int level = Integer.parseInt(content);
        String imagepath = "output_gif.png";
        Image image = new Image(Files.newInputStream(Paths.get(imagepath)));
        AlogorithmAnimation.setImage(image);
        //current_display++;
        //if (current_display > level) {
        //    current_display = 1;
        //}
    }
}