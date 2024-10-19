package gui;

import Project.Main;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class AlgorithmController {
    @FXML
    private TextField mgtfield;

    @FXML
    public void hitbutton(javafx.event.ActionEvent actionEvent) {
        String MGT = mgtfield.getText();
        String[] args = { MGT };
        Main.main( args );
        CallPythonScript.main();
        showInfoAlert("Command Execution", "The Algorithm has been successfully execute. Please click Anamation button to see the result.");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
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