package Project;
// Last edit: 2024/04/29 by Huan
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 2024.07.06 @ HUAN : EI_list的型態也是EP，但名字還是EI
// 2024.07.25 @ 窩肥 : 新增pattern_mining_result留下的結果
public class Main {
    static boolean first_open = true;
    public static void main() {
        Main_Procedure(1600);
    }
    public static ArrayList<Character> Main_Procedure(int MGT){
        System.out.println("⫸ Round 1 (Original database");
        ArrayList<Product> Product_DB = input_file();   // This funciton can build Database from text file.
        ArrayList<LINE_table> LINE_table = new ArrayList<>();
        ArrayList<EP> EI_list = new ArrayList<>();

        Construct_PDB(LINE_table, EI_list, Product_DB); // After this function, we get LINE_table, EI_list according to previous Database.
        // Calculate MGT (ps.已經當參數傳入了)

        // 把EI_list中小於MGT的item放進新的EI_list_new
        ArrayList<EP> EI_list_new = new ArrayList<EP>();
        for(EP ei:EI_list){     // EI_list已經sort過了
            if(ei.gain < MGT){
                EI_list_new.add(ei);
            }
            else{
                System.out.println("\033[33;4mRemove item " + ei.pattern + " (Gain of item over MGT).\033[0m");
            }
        }
        // R: Erasable Patterns , R <- R U EI_list_new
        ArrayList<EP> R = new ArrayList<EP>();
        for(EP ei:EI_list_new){     
            R.add(new EP(ei.pattern, ei.dPIDs, ei.gain));
        }
        // 印出EI_list_new與R的內容
        show_EI(EI_list_new, "EI_list_new");
        show_EP(R, "R");
        Mine_Patterns(LINE_table, EI_list_new, MGT, R);
        return null;
    }


    public static void Construct_PDB(ArrayList<LINE_table> LINE_table, ArrayList<EP> EI_list, ArrayList<Product> Product_DB){
        System.out.print("(Construct PDB)");
        // 印出內容
        for(Product p:Product_DB){
            for(char it:p.items){
                // p 是每一個 product , it 是每一個item
                
                // 判斷有沒有在EI list裡
                boolean found = false;
                for(EP ei: EI_list){
                    if(ei.pattern.get(0) == it){        // EI-list的pattern只有一個item
                        // 把PID 加進此EInode的PIDs[]裡
                        ei.dPIDs.add(p.PID);
                        // 更新gain
                        ei.gain += p.profit;
                        found = true;
                        break;
                    }
                }
                if(!found){
                    ArrayList<Integer> temp_PIDs = new ArrayList<Integer>();
                    temp_PIDs.add(p.PID);
                    ArrayList<Character> temp_item = new ArrayList<Character>();
                    temp_item.add(it);
                    EI_list.add(new EP(temp_item, temp_PIDs, p.profit));
                }
            }
        }
        // Build LINE_table
        for(Product p:Product_DB){
            LINE_table.add(new LINE_table(p.PID,p.profit));
        }

        // Sort EI_list
        Collections.sort(EI_list, new Comparator<EP>() {
            @Override
            public int compare(EP ei1, EP ei2) {
                // 根据 gain 比较
                return ei2.gain - ei1.gain;
            }
        });

        // 印出Data Base
        show_DB(Product_DB, "Product_DB");
        // 印出LINE_table內容
        show_LINE_table(LINE_table, "LINE_table");
        // 印出EI_list內容
        show_EI(EI_list, "EI_list");
        whole_EI_list_output(null, EI_list);
    }

    public static ArrayList<EP> Mine_Patterns(ArrayList<LINE_table> LINE_table, ArrayList<EP> EP_list_input, int MGT, ArrayList<EP>R){
        if(EP_list_input.size() == 0) return null;
        System.out.println("\n(Mine Pattern)");
        // Build EP_list
        ArrayList<EP> EP_list_erase = new ArrayList<>();
        ArrayList<EP> EP_list_total = new ArrayList<>();
        //  inter_cnt 是交集數量
        int inter_cnt = EP_list_input.get(0).pattern.size()-1;

        // Select two items from input_EI_EP
        for(int i=0; i< EP_list_input.size()-1; i++){
            ArrayList<Character> intersection = new ArrayList<>();
            for (int k=0;k<inter_cnt;k++){
                intersection.add(EP_list_input.get(i).pattern.get(k));
            }
            System.out.print("intersection:");
            System.out.println(intersection);
            for(int j=i+1; j<EP_list_input.size(); j++){
                // 找兩node的pattern的交集
                System.out.print("i:");
                System.out.println(EP_list_input.get(i).pattern);
                System.out.print("j:");
                System.out.println(EP_list_input.get(j).pattern);

                ArrayList<Character> temp_pattern = new ArrayList<Character>();
                // System.out.println(intersection.size());
                // System.out.println(round-1);
                if(EP_list_input.get(j).pattern.containsAll(intersection)){ // 結合產生新node
                    // pattern
                    
                    // 這要改 (第二輪Patten Mining要產生三個item的pattern)
                    temp_pattern.addAll(EP_list_input.get(i).pattern);
                    ArrayList<Character> temp_j_pattern = new ArrayList<Character>();
                    temp_j_pattern.addAll(EP_list_input.get(j).pattern);
                    temp_j_pattern.removeAll(intersection);
                    temp_pattern.addAll(temp_j_pattern);
                    System.out.println(temp_pattern);
                    // dPIDs
                    ArrayList<Integer> temp_dPIDs =  new ArrayList<Integer>(EP_list_input.get(j).dPIDs);
                    temp_dPIDs.removeAll(EP_list_input.get(i).dPIDs);

                    //gain
                    int temp_gain = EP_list_input.get(i).gain;
                    for(int k=0; k<LINE_table.size(); k++){
                        if(temp_dPIDs.contains(LINE_table.get(k).PID))
                            temp_gain += LINE_table.get(k).profit;
                    }
                    EP_list_total.add(new EP(temp_pattern, temp_dPIDs, temp_gain));
                    if(temp_gain >= MGT){   // 要移除的pattern
                        EP_list_erase.add(new EP(temp_pattern, temp_dPIDs, temp_gain));
                        R.add(new EP(temp_pattern, temp_dPIDs, temp_gain));
                    }
                }
                else break;

            }
        }
        show_EP(EP_list_total, "EP_list");
        for(EP ep:EP_list_erase){
            System.out.println("\033[33;4mRemove pattern " + ep.pattern + " (Gain of pattern over MGT).\033[0m");
            EP_list_total.removeIf(it -> ep.pattern == it.pattern); // 因為兩個ArrayList的ep是不同物件，所以無法直接EP_list_total.remove(ep)，需要判斷pattern以刪除
        }
        show_EP(EP_list_total, "EP_list_remain");   // Total在此階段儲存小於MGT的ep
        if(EP_list_total.size() != 1){
            whole_EI_list_output(EP_list_total, null);
            //pattern_mining_result_output_file(EP_list_total);
            Mine_Patterns(LINE_table, EP_list_total, MGT, R);}
        return R;
    }
/////////////////////////////////////////////////////////
////                                                 ////
////          functions to display contents          ////
////                                                 ////
/////////////////////////////////////////////////////////
    public static void show_DB(ArrayList<Product> Product_DB, String name){
        System.out.printf("\n\033[31;4m< %s >\033[0m\n",name);
        System.out.println("\033[41;4mPID    items                profit\033[0m");
        for(Product p:Product_DB){
            p.show();
        }
    }
    public static void show_LINE_table(ArrayList<LINE_table> LINE_table, String name){
        System.out.printf("\n\033[31;4m< %s >\033[0m\n",name);
        System.out.println("\033[41;4mPID    profit\033[0m");
        for(LINE_table line:LINE_table){
            line.show();
        }
    }
    public static void show_EI(ArrayList<EP> EI_list, String name){
        System.out.printf("\n\033[31;4m< %s >\033[0m\n",name);
        System.out.printf("\033[41;4mitem    PIDs                gain\033[0m\n");
        for(EP ei:EI_list){
            ei.show();
        }
    }
    public static void show_EP(ArrayList<EP> EP_list, String name){
        System.out.printf("\n\033[31;4m< %s >\033[0m\n",name);
        System.out.println("\033[41;4mpattern dPIDs               gain\033[0m");
        for(EP ep:EP_list){
            ep.show();
        }
        System.out.println();
    }
/////////////////////////////////////////////////////////
////                                                 ////
////          functions to access contents           ////
////                                                 ////
/////////////////////////////////////////////////////////
    public static ArrayList<Product> input_file(){
        String InfileName = "database_2.txt";
        // Build DataBase
        ArrayList<Product> Product_DB = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(InfileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int firstInt = Integer.parseInt(parts[0]);
                ArrayList<Character> charList = new ArrayList<>();
                for (char c : parts[1].toCharArray()) {
                    charList.add(c);
                }
                int secondInt = Integer.parseInt(parts[2]);
                Product data = new Product(firstInt, charList, secondInt);
                Product_DB.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Product_DB;
    }

    public static String[] LToutput_file(ArrayList<LINE_table> LINE_table){
        String Line_table_fileName = "Line_table.txt";
        try (FileWriter writer = new FileWriter(Line_table_fileName)) {
            // 寫入檔案
            writer.write("<LINE_table>\nPID\tprofit\n");
            System.out.println("\n<LINE_table>");
            System.out.println("PID\tprofit");
            // 印出內容
            for(LINE_table line:LINE_table){
                writer.write(line.PID + "\t" + line.profit + System.lineSeparator());
            }
        }catch (IOException e) {
                System.out.println("發生錯誤: " + e.getMessage());
            }
        return null;
    }

    public static String[] EIoutput_file(ArrayList<EP> EI_list){
        String EI_list_fileName = "EI_list.txt";
        try (FileWriter writer = new FileWriter(EI_list_fileName)) {
            // 寫入檔案
            writer.write( "item\tPID\tgain" + System.lineSeparator());
            for(EP ei:EI_list){
                writer.write(ei.pattern + "\t" + ei.dPIDs + "\t" + ei.gain + System.lineSeparator());
            }
        }
        catch (IOException e) {
            System.out.println("發生錯誤: " + e.getMessage());
        }
        return null;
    }

    public static String[] EPoutput_file(ArrayList<EP> EP_list){
        String EP_list_fileName = "EP_list.txt";
        try (FileWriter writer = new FileWriter(EP_list_fileName)) {
            writer.write("<EP_list>" + System.lineSeparator() + "pattern\tPIDs\tgain" + System.lineSeparator());
            for(EP ep:EP_list){
                writer.write(ep.pattern + "\t" + ep.dPIDs + "\t" + ep.gain + System.lineSeparator());
            }
        }
        catch (IOException e) {
            System.out.println("發生錯誤: " + e.getMessage());
        }
        return null;
    }
    public static String[] pattern_mining_result_output_file(ArrayList<EP> R){
        String R_fileName = "pattern_mining_result.txt";
        if(first_open){
            try {
                FileWriter writer = new FileWriter(R_fileName, false); // 覆寫模式，清空文件
                writer.write("");
                writer.close();
            }   
            catch (IOException e) {
                e.printStackTrace();
            }
            first_open = false;
        }
        if(R.size() != 0){
            try (FileWriter writer = new FileWriter(R_fileName,true)) { //續寫
                writer.write("pattern\t\tPIDs\t\tgain" + System.lineSeparator());
                for(EP ep:R){
                    writer.write(ep.pattern + "\t" + ep.dPIDs + "\t" + ep.gain + System.lineSeparator());
                }
                writer.write("----------------------------------" + System.lineSeparator());
            }
            catch (IOException e) {
                System.out.println("發生錯誤: " + e.getMessage());
            }
        }
        return null;
    }
    public static String[] whole_EI_list_output(ArrayList<EP> R , ArrayList<EP>EI_list){
        String R_fileName = "pattern_mining_result.txt";
        if(first_open){
            try {
                FileWriter writer = new FileWriter(R_fileName, false); // 覆寫模式，清空文件
                    writer.write( "pattern\t\tPIDs\t\tgain" + System.lineSeparator());
                if (EI_list != null){
                    for(EP ei:EI_list){
                        writer.write(ei.pattern + "\t" + ei.dPIDs + "\t" + ei.gain + System.lineSeparator());
                    }
                    writer.write("----------------------------------" + System.lineSeparator());
                }
                writer.close();
            }   
            catch (IOException e) {
                e.printStackTrace();
            }
            first_open = false;
        }
        else{
            try (FileWriter writer = new FileWriter(R_fileName,true)) { //續寫
                writer.write("pattern\t\tPIDs\t\tgain" + System.lineSeparator());
                if ( R != null){
                    for(EP ep:R){
                        writer.write(ep.pattern + "\t" + ep.dPIDs + "\t" + ep.gain + System.lineSeparator());
                    }
                    writer.write("----------------------------------" + System.lineSeparator());
                }
            }
            catch (IOException e) {
                System.out.println("發生錯誤: " + e.getMessage());
            }
        }
        return null;
    }
}
