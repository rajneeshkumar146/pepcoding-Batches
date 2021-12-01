import java.util.Scanner;

public class primeNumber {
    public static void main(String [] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int factor = 0;         // [2,35] how many factors??



        for(int i = 2; i*i<=n; i++){
            if(n % i == 0){
                factor++;
                break;
            }

            
        }

        if(factor > 0){
            System.out.println("It is not prime");
        }
        else{
            System.out.println("It is prime");
        }

    }
}
