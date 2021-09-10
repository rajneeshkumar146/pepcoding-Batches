public class l001 {
    public static void pppppp(int n) {
        System.out.println("Base Case: " + n);
        return;
    }

    public static void ppppp(int n) {
        System.out.println("hi: " + n);
        pppppp(n - 1);
        System.out.println("Bye: " + n);
    }

    public static void pppp(int n) {
        System.out.println("hi: " + n);
        ppppp(n - 1);
        System.out.println("Bye: " + n);
    }

    public static void ppp(int n) {
        System.out.println("hi: " + n);
        pppp(n - 1);
        System.out.println("Bye: " + n);
    }

    public static void pp(int n) {
        System.out.println("hi: " + n);
        ppp(n - 1);
        System.out.println("Bye: " + n);
    }

    public static void p(int n) {
        System.out.println("hi: " + n);
        pp(n - 1);
        System.out.println("Bye: " + n);
    }

    public static void printIncDec(int n) {
        if (n == 0) {
            System.out.println("Base Case: " + n);
            return;
        }

        System.out.println("Hi: " + n);
        printIncDec(n - 1);
        if (n == 2)
            n += 10;
        System.out.println("Bye: " + n);
    }

    public static void printDecreasing(int n) {
        if (n == 0)
            return;
        System.out.println(n);
        printDecreasing(n - 1);
    }

    public static void printIncreasing(int n) {
        if (n == 0)
            return;
        printIncreasing(n - 1);
        System.out.println(n);
    }

    public static void printDecreasingIncreasing(int n) {
        if (n == 0)
            return;

        System.out.println(n);
        printDecreasingIncreasing(n - 1);
        System.out.println(n);
    }

    public static void printoddEven(int n) {
        if (n == 0)
            return;

        if (n % 2 != 0)
            System.out.println(n);
        printoddEven(n - 1);
        if (n % 2 == 0)
            System.out.println(n);
    }

    public static int fact(int n) {
        if (n <= 1)
            return 1;
        int smallAns = fact(n - 1);
        return smallAns * n;
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = power(a, b - 1);
        return smallAns * a;
    }

    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;
        return (b % 2 == 0 ? smallAns : smallAns * a);
    }

    // 5
    public static int expr(int n) {
        if (n <= 1) {
            System.out.println("Base Case : " + n);
            return n;
        }
        int count = 0;
        System.out.println("Pre Order: " + n);
        
        count += expr(n - 1);
        System.out.println("In Order: " + n);
        count += expr(n - 2);

        System.out.println("Post Order: " + n);
        return count + 3;
    }

    public static void main(String[] args) {
        System.out.println(expr(5));
    }

}