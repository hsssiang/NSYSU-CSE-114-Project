package gui;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileViewerController {
    public static ObservableList<gui.FileReader> input_file(){
        String InfileName = "/Users/jimmywang/Desktop/114專題/JAVA/GUI-Aug-2024/src/main/java/database_2.txt";
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
    public void initialize() {
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("Product"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        tableView.setItems( input_file() );
    }


}