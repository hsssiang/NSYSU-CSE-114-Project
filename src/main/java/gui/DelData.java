package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DelData {
    public static void main(String[] args) {

    }
    public void insert(String delindex) {
        int index = 0;
        String FileName = "database_2.txt";
        String[][] DB = new String[100][4];
        try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                DB[index] = line.split(" ");
                System.out.println(DB[index][0] + " " + DB[index][1] + " " + DB[index][2]);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int correct_num = 1;
        try (FileWriter writer = new FileWriter(FileName)) {
            for (int i = 0; i < index; i++) {
                if (i == Integer.parseInt(delindex) - 1) {
                    continue;
                }
                writer.write(correct_num + " " + DB[i][1] + " " + DB[i][2] + System.lineSeparator());
                correct_num++;
            }
        } catch (IOException e) {
            System.out.println("發生錯誤: " + e.getMessage());
        }
    }
}
