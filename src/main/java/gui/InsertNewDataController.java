package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InsertNewDataController {
    @FXML
    private TextField pdfield;
    @FXML
    private TextField profitfield;

    @FXML
    protected void hitfinishbutton() {
        // 獲取輸入框的文本
        String name = pdfield.getText();
        String profit = profitfield.getText();
        InsertData insertData = new InsertData();
        insertData.insert(name, profit);
    }
}