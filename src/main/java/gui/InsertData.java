package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InsertData {
    public static void main(String[] args) {

    }
    public void insert(String PDName, String profit) {
        int index = 0;
        String FileName = "database_2.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(FileName, true)) { //續寫
            index++;
            writer.write(index + " " + PDName + " " + profit + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("發生錯誤: " + e.getMessage());
        }
    }
}
