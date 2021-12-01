import java.util.Scanner;

public class inputDemo {
    public static void main(String [] args){
        
        Scanner scn = new Scanner(System.in); 
        
        // int a = scn.nextInt();
        // int b = scn.nextInt();
        // int c = scn.nextInt();
        // int d = scn.nextInt();
        // int e = scn.nextInt();

        // System.out.println("A is " + a);
        // System.out.println("B is " + b);
        // System.out.println("C is " + c);
        // System.out.println("D is " + d);
        // System.out.println("E is " + e);


        // String Input
        // String name = scn.next();   //scn.next(); -- scn.nextLine();
        // System.out.println("His name is --> " + name);


        String name = scn.nextLine();   // scn.nextLine();
        System.out.println("His statement is --> " + name);

        
    }

}
