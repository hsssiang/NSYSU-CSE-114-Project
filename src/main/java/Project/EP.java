package Project;

import java.util.ArrayList;

public class EP{
    public ArrayList<Character> pattern = new ArrayList<Character>();
    public ArrayList<Integer> dPIDs = new ArrayList<Integer>();
    public int gain;

    public EP(){
        this.pattern = null;
        this.dPIDs = null;
        this.gain = 0;
    }
    public EP(ArrayList<Character> pattern, ArrayList<Integer> dPIDs, int gain){
        this.pattern = pattern;
        this.dPIDs = dPIDs;
        this.gain = gain;
    }
    public void show(){
        System.out.print(this.pattern);System.out.print('\t');
        System.out.printf("%-20s", this.dPIDs.toString());
        System.out.println(this.gain);
    }
}