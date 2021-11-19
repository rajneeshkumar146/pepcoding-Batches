import java.util.Scanner;

public class l005_loops {
    public static void main(String[] args) {
        // to print number from 1 to 10 in increasing order
        // for (int num = 1; num <= 10; num++) {
        // System.out.println(num);
        // }

        // to print number from 10 to 1 in decreasing order
        // for (int num = 10; num >= 1; num--) {
        // System.out.println(num);
        // }

        // to print table
        // int tableNumber = 13;
        // for (int num = 1; num <= 10; num++) {
        // System.out.println(tableNumber + " X " + num + " = " + tableNumber * num);
        // }

        Scanner scn = new Scanner(System.in);
        boolean isPrime = true;
        int num = scn.nextInt();
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

       if(isPrime){
           System.out.println(num + " is prime");
       }else{
           System.out.println(num + " is not prime");
       }
    }

}