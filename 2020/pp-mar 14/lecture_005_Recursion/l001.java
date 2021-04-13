public class l001 {
    public static void ppppppp(int a, int b) {
        return;
    }

    public static void pppppp(int a, int b) {
        System.out.println(a);
        ppppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasing(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasingEvenOdd(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printIncreasingDecreasingEvenOdd(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);
    }

    public static int factorial(int n) {
        if (n == 0)
            return 1;
        int recAns = factorial(n - 1);

        return recAns * n;
    }

    public static int factorial_01(int n) {
        return n == 0 ? 1 : factorial_01(n - 1) * n;
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;

        int ans = power(a, b - 1);
        return ans * a;
    }

    public static int power_01(int a, int b) {
        return b == 0 ? 1 : power_01(a, b - 1) * a;
    }

    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;

        int recAns = powerBtr(a, b / 2);
        recAns *= recAns;

        return b % 2 == 0 ? recAns : recAns * a;
    }

    // n = 5
    public static int recursionTree(int n) {
        if (n <= 1) {
            System.out.println("Base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += recursionTree(n - 1);

        System.out.println("In: " + n);
        count += recursionTree(n - 2);

        System.out.println("Post: " + n);

        return n + 3;
    }

    // n = 6
    public static int recursionTree2(int n) {
        if (n <= 1) {
            System.out.println("Base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += recursionTree2(n - 1);

        System.out.println("In1: " + n);
        count += recursionTree2(n - 2);

        System.out.println("In2: " + n);
        count += recursionTree2(n - 3);

        System.out.println("Post: " + n);
        return n + 3;
    }

    public static int fibo(int n) {
        if (n <= 1)
            return n;
        int count = 0;
        count += fibo(n - 1);
        count += fibo(n - 2);

        return count;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = 20;
        System.out.println(power(a, b));
    }
}