package gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
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
        String content = new String(Files.readAllBytes(Paths.get("level.txt")), StandardCharsets.UTF_8).trim();
        // 將讀取的內容轉換為整數
        int level = Integer.parseInt(content);
        System.out.println(level);
        String imagepath = "output_image_" + 2 + ".png";
        System.out.println(imagepath);
        while (level > 0) {
            for (int i = 1; i < level; i++) {
                imagepath = "output_image_" + level + ".png";
                System.out.println(imagepath);
                Image image = new Image(Files.newInputStream(Paths.get(imagepath)));
                AlogorithmAnimation.setImage(image);
            }
        }
    }
}