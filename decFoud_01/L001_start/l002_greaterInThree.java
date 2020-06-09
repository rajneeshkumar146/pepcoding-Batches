import java.util.Scanner;
public class l002_greaterInThree{
    
    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
        System.out.print("Please! Input Three numbers with spaces: ");
        int a=scn.nextInt();
        int b=scn.nextInt();
        int c=scn.nextInt();

        int maxNum=-1;
        if(a>=b && a>=c) maxNum=a;
        else if(b>=a && b>=c) maxNum=b;
        else maxNum=c;

        System.out.println("Your maxNumber is: " + maxNum);
    }



}