import java.util.Scanner;
public class l001{ 
    public static Scanner scn = new Scanner(System.in);

    public static void printHello(){
        System.out.println("Hi There!");
        System.out.println("This is Rajneesh Kumar");
    }

    public static void workWithDataType(){
        int a = 10;
        int b = 20;

        System.out.println("Value of A: " + a);
        System.out.println("Value of B: " + b);
    }

    public static void printZ(){
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    public static void gradingSystem(int n){
        if(n > 90){
            System.out.println("excellent");
        }else if(n > 80){
            System.out.println("good");
        }else if(n > 70){
            System.out.println("fair");
        }else if(n > 60){
            System.out.println("meet expectations");
        }else{
            System.out.println("below par");
        }
    }

    public static void oddEven(int n){
        if(n % 2 == 0){
            System.out.println("Even");
        }else{
            System.out.println("Odd");
        }
    }

    public static boolean isPrime_(int n){
        boolean prime = true;
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                prime = false;
                break;
            }
        }
        return prime;
    }
    
    public static void isPrime(){
        int n = scn.nextInt();
        boolean ans = isPrime_(n);
    
        if(ans) System.out.println("prime");
        else System.out.println("not prime");
    }
    
    public static void printAllPrime(int a,int b){
        for(int i=a;i<=b;i++){
            boolean ans = isPrime_(i);
            if(ans) System.out.println(i); 
        }
    }

    public static void printFibo(int n){
        int a = 0,b = 1;
        for(int i = 1;i <= n;i++){
            System.out.println(a);
            int sum = a + b;
            a = b;
            b = sum;
        }
    }

    public static int countDigit(int n){
        int count = 0;
        int num = n;
        
        while(num != 0){
            num /= 10;
            count++;
        }
        return count;
    }
    
    public static void lineWiseDigit(int n){
        int num = n;
        int len = countDigit(num);
        
        int div = 1;
        while(len-->1){
            div *= 10;
        }
        
        while(div != 0){
            System.out.println(n/div);
            n = n % div;
            div /=10;
        }   
    }

    
public static int inverse(int n){
    // int len = countDigit(n);
    int res = 0;
    for(int i=1;n!=0;i++){   // other possible condition : i <= len
        int digit = n % 10;
        n /=10;
        res += i * Math.pow(10,digit-1);
    }
    
    return res;
}

    public static void main(String[] args){
        // printHello();
        // workWithDataType();
        int n = scn.nextInt();
        oddEven(n);
    }
}