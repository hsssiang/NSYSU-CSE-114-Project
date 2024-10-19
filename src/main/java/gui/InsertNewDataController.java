package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        showInfoAlert("Command Execution", "The data has been successfully insert.");
        pdfield.clear();
        profitfield.clear();
    }

    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);  // Optional: can leave header empty
        alert.setContentText(content);

        // Show the alert and wait for user response (modal dialog)
        alert.showAndWait();
    }
}