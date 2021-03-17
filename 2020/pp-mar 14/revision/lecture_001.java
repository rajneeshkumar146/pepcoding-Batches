import java.util.*;

public class lecture_001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printMsg() {

        System.out.print("Hello, World");
        System.out.println("hi");
        System.out.println("friends");
    }

    public static void printOddEven(int n) {
        if (n % 2 == 0)
            System.out.println("Even");
        else
            System.out.println("odd");
    }

    public static boolean isEvenNumber(int n) {
        if (n % 2 == 0)
            return true;
        else if(n%2 != 0)
            return false;
        else{
            return false;
        }
        
    }

    public static void main(String[] args) {
        
        int n = scn.nextInt();
        printOddEven(n);
        // boolean ans = isEvenNumber(n);
        // System.out.println(ans);
        System.out.println(isEvenNumber(n));
    }

}