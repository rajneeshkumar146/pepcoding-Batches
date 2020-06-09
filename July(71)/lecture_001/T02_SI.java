import java.util.Scanner;

public class T02_SI{
    
   public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
    System.out.println("please input value of p ,t and r");    
      int p=scn.nextInt();     
      int t=scn.nextInt();     
      int r=scn.nextInt();

      int si=(p*t*r)/100;
      System.out.println("SI is: " + si);    
    }
}