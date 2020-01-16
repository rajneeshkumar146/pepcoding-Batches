import java.util.Scanner;

public class SI{
 static  Scanner scn=new Scanner(System.in);
public static void main(String[] args){
    // int p=1000;
    // int r=2;
    // int t=3;

    System.out.print("entre p: ");
    int p=scn.nextInt();

    System.out.println("entre r: ");
    int r=scn.nextInt();

    System.out.println("entre t: ");
    int t=scn.nextInt();


 int si=(p*r*t)/100;
   System.out.println("Simple Int: " + si);
}

}