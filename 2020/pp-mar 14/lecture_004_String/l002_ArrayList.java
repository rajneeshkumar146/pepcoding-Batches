import java.util.Scanner;
import java.util.ArrayList;

public class l002_ArrayList {
    public static Scanner scn = new Scanner(System.in);

    public static void basicFunction() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);

        System.out.println(arr);
        for (Integer e : arr)
            System.out.println(e);
        System.out.println(arr.size());
        System.out.println(arr.get(1));
        arr.remove(1);
        System.out.println(arr);
    }

    public static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void removeData() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int a = scn.nextInt();
            arr.add(a);
        }
        int data = scn.nextInt();

        int i = arr.size() - 1;
        while (i >= 0) {
            if (arr.get(i) == 10) {
                swap(arr, i, arr.size() - 1);
                arr.remove(arr.size() - 1);
            }
            i--;
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    public static void removePrimes(ArrayList<Integer> arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer ele : arr)
            if (!isPrime(ele))
                ans.add(ele);

        // arr.clear();
        while(arr.size() != 0) arr.remove(arr.size() - 1);
        
        for (int ele : ans)
            arr.add(ele);
    }

    public static void main(String[] args) {
        basicFunction();
    }
}