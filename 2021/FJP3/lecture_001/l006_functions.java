import java.util.Scanner;

public class l006_functions {

    public static Scanner scn = new Scanner(System.in);

    // no Return and no arguments
    public static void printMessage() {
        System.out.println("Hello class!!!");
    }

    public static void printTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " X " + i + " = " + (num * i));
        }
    }

    public static void printTableInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
            printTable(i);
            System.out.println("=====================");
        }
    }

    public static int sumOfTwoNumber() {
        int a = scn.nextInt();
        int b = scn.nextInt();

        return (a + b);
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0)
                return false;
        }

        return true;
    }

    public static void allPrimeInRange(int lo, int hi) {
        for (int num = lo; num <= hi; num++) {
            if (isPrime(num)) {
                System.out.println(num);
            }
        }
    }

    public static int countDigitOfNumber(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    public static int reverseOfNumber(int num) {
        int ans = 0;
        while (num != 0) {
            int digit = num % 10;
            num /= 10;
            ans = ans * 10 + digit;
        }
        return ans;
    }

    public static void digitsOfNumber(int num) {
        int len = countDigitOfNumber(num) - 1;
        while (len >= 0) {
            int digit = num / (int) Math.pow(10, len);
            num = num % (int) Math.pow(10, len);
            System.out.println(digit);
            len--;
        }
    }

    public static void printInReverseOrder(int num) {
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            System.out.println(lastDigit);
        }
    }

    public static int inverseOfNumber(int num) {
        int totalDigit = countDigitOfNumber(num);
        int lastIndex = totalDigit, res = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            res += lastIndex * (int) Math.pow(10, totalDigit - lastDigit);
            lastIndex--;
        }

        return res;
    }

    public static int inverseOfNumber_Easy(int num) {
        int lastIndex = 1, res = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            res += lastIndex * (int) Math.pow(10, lastDigit - 1);
            lastIndex++;
        }

        return res;
    }

    public static void BenjaminBulbs(int n) {
        for (int i = 1; i * i <= n; i++) {
            System.out.println(i * i);
        }
    }

    public static boolean Pythagorean(int a, int b, int c) {
        if (a * a == b * b + c * c)
            return true;
        else if (b * b == a * a + c * c)
            return true;
        else if (c * c == b * b + a * a)
            return true;
        else
            return false;

    }

    public static int rotateNumber(int num, int r) {
        int len = countDigitOfNumber(num);

        r = (r % len + len) % len;

        int a = num / (int) Math.pow(10, r);
        int b = num % (int) Math.pow(10, r);

        return (b * (int) Math.pow(10, len - r) + a);
    }

    public static int rotateNumber2(int num, int r) {
        int len = countDigitOfNumber(num);

        r = (r % len + len) % len;

        int div = 1, mul = 1;
        for (int i = 1; i <= len; i++)
            if (i <= r)
                div *= 10;
            else
                mul *= 10;

        int a = num / div;
        int b = num % div;

        return (b * mul + a);
    }

    public static void primeFactor(int n) {
        for (int pn = 2; pn * pn <= n; pn++) {
            while (n % pn == 0) {
                System.out.print(pn + " ");
                n /= pn;
            }
        }

        if (n != 1)
            System.out.print(n);

    }

    public static void main(String[] args) {
        // for (int i = 0; i < 10; i++)
        // printMessage();

        int a = scn.nextInt(), b = scn.nextInt();
        // printTableInRange(a, b);
        allPrimeInRange(a, b);

        // int ans = sumOfTwoNumber();
        // System.out.println(ans);
    }

}