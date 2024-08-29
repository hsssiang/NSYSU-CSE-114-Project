package Project;

public class LINE_table {
    public int PID;
    public int profit;

    public LINE_table(){
        this.PID = 0;
        this.profit = 0;

    }
    public LINE_table(int PID, int profit){
        this.PID = PID;
        this.profit = profit;
    }
    public void show(){
        System.out.print(this.PID); System.out.print('\t');
        System.out.println(this.profit);
    }
}
