package gui;

import Project.Main;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}