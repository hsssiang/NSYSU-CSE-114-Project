package Project;

import java.util.Arrays;

public class QuickSort {
    static int []arr  = {19,4,5,21,19,4};
    public static void main(String[] args) {
        System.out.println("原序列為："+Arrays.toString(arr));
        quickSort(arr);
    }
    public static void quickSort(int [] arr) {
        sort(0,arr.length-1);
        System.out.println("排序後為："+Arrays.toString(arr));
    }
    public static void sort(int left,int right) {
        if(left < right) {
            int i = left; // 由左至右的索引
            int j = right + 1; // 由右至左的索引
            while(true) {
                while( i+1 < arr.length && arr[++i] < arr[left]); // 向右找, 直到找到比基準值大的數
                while( j-1 >= 0 && arr[--j] > arr[left]); // 向左找, 值到找到比基準值小的數
                if( i >= j) break; // 若i,j重疊或i超過j後則退出循環
                swap(i , j);
            }
            swap(left , j); // 基準點與 j 交換
            sort(left , j - 1); // 遞迴排序基準點左子序列
            sort(j + 1 , right); // 遞迴排序基準點右子序列
            
        }
    }

    public static void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
