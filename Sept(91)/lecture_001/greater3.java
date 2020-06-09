import java.util.Scanner;
public class greater3 {

    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Entre a: ");
        int a = scn.nextInt();
        System.out.println();

        System.out.println("Entre b: ");
        int b = scn.nextInt();
        System.out.println();
        

        System.out.print("Entre c: ");
        int c = scn.nextInt();
        System.out.println();

        if (a > b && a > c) {
            System.out.println(a);
        } else if (b > a && b > c) {
            System.out.println(b);
        } else {
            System.out.println(c);
        }
    }

}