import java.util.Scanner;

public class rotation {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int r = scn.nextInt();

        int digit = 0;
        int temp = n;
        while (temp != 0) {
            digit++;
            temp /= 10;
        }

        r %= digit;
        if (r < 0) {
            r += digit;
        }

        // r+=(r<0)?digit:0;

        int mult = 1;
        int div = 1;

        for (int i = 1; i <= digit; i++) {
            if (i <= r) {
                mult *= 10;
            } else {
                div *= 10;
            }
        }

        int rem = n % div;
        int oldVal = n / div;

        n = rem * mult + oldVal;
p
        System.out.println(n);

    }

}