package Project;
import java.util.ArrayList;

public class EI{
    public ArrayList<Character> item= new ArrayList<Character>();
    public ArrayList<Integer> PIDs = new ArrayList<Integer>();
    public int gain;

    public EI(){
        this.item = null;
        this.PIDs = null;
        this.gain = 0;
    }
    public EI(ArrayList<Character> item, ArrayList<Integer> PIDs, int gain){
        this.item = item;
        this.PIDs = PIDs;
        this.gain = gain;
    }
    
    public void show(){
        System.out.print(this.item);    System.out.print('\t');
        System.out.printf("%-20s", this.PIDs.toString());
        System.out.println(this.gain);
    }


}

// 這個Object沒用到
// 現在EI_list也是用EP型態