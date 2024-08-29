package gui;
import java.util.ArrayList;

public class Product {
    public int PID;
    public ArrayList<Character> items = new ArrayList<Character>();
    public int profit;

    public int get_profit(){
        return this.profit;
    }
    public void set_profit(int profit){
        this.profit = profit;
    }

    public int get_PID(){
        return this.PID;
    }
    public String get_item(){return this.items.toString();}

    public Product(){}
    public Product(int PID, ArrayList<Character> items, int profit){
        this.PID = PID;
        this.items = items;
        this.profit = profit;
    }
    public void show(){
        System.out.print(this.PID);    System.out.print('\t');
        System.out.printf("%-20s", this.items.toString());
        System.out.println(this.profit);
    }
}
