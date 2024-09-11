package gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CallPythonScript {
    public static void main() {
        try {
            // 建立 ProcessBuilder 並設定命令
            ProcessBuilder builder = new ProcessBuilder(
                    "py", "animation.py"
            );

            // 開始進程
            Process process = builder.start();

            // 讀取 Python 腳本的輸出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待 Python 腳本執行完成
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}