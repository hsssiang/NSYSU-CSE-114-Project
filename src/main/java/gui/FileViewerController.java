package gui;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileViewerController {
    public static ObservableList<gui.FileReader> input_file(){
        String InfileName = "database_2.txt";
        ArrayList<gui.FileReader> FileReaderDB = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(InfileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int PIDInt = Integer.parseInt(parts[0]);
                int ProfitInt = Integer.parseInt(parts[2]);
                StringBuilder sb = new StringBuilder();
                for (char c : parts[1].toCharArray()) sb.append(c);
                String ProductStr = sb.toString();
                FileReaderDB.add( new gui.FileReader(PIDInt, ProductStr, ProfitInt) );
                //for (gui.FileReader i : FileReaderDB) {
                    //System.out.println( i.getPid() + " " + i.getProduct() + " " + i.getProfit());
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(FileReaderDB);
    }

    @FXML
    private TableView<gui.FileReader> tableView;

    @FXML
    private TableColumn<gui.FileReader, String> productColumn;

    @FXML
    private TableColumn<gui.FileReader, Integer> profitColumn;

    @FXML
    private TableColumn<gui.FileReader, Integer> pidColumn;

    @FXML
    private TextField delfield;

    @FXML
    public void initialize() {
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("Product"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        tableView.setItems( input_file() );
    }
    @FXML
    protected void hitdelbutton() {
        // 獲取輸入框的文本
        String index = delfield.getText();
        DelData delData = new DelData();
        delData.insert(index);
        showInfoAlert("Command Execution", "The data has been successfully delete.");
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("Product"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        tableView.setItems( input_file() );
        delfield.clear();
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