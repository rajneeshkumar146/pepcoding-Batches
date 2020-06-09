import java.util.Scanner;

public class l002_recursionWithArray {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static int[] allIndex(int[] arr, int vidx, int data, int size) {
        if (vidx == arr.length) {
            int[] baseaArray = new int[size];
            return baseaArray;
        }

        if (arr[vidx] == data)
            size++;

        int[] recAns = allIndex(arr, vidx + 1, data, size);

        if (arr[vidx] == data)
            recAns[size - 1] = vidx;

        return recAns;

    }

    

}