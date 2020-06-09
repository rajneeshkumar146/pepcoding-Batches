import java.util.Scanner;

public class T02_SI{
    
   public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
    System.out.println("please input value of a ,b and c");    
      int a=scn.nextInt();     
      int b=scn.nextInt();     
      int c=scn.nextInt();
    
       int ans=-1;
      if (a > b && a > c)
        ans=a;
      else if (b > a && b > c)
        ans=b;
      else
        ans=c;

    System.out.println("Your largest No. is: " + ans); 

    }
}