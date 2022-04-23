public class l001_basic {

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printEvenOdd(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printEvenOdd(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);

    }

    public static int fact(int n) {
        return n == 0 ? 1 : n * fact(n - 1);
    }

    public static int pow(int a, int b) {
        return b == 0 ? 1 : a * pow(a, b - 1);
    }

    public static int getNthFib(int n) {
        if (n <= 1)
            return n;
        return getNthFib(n - 1) + getNthFib(n - 2);
    }

    public static int check(int n) {
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 3;
        }

        int count = 0;

        System.out.println("pre: " + n);
        count += check(n - 1);

        System.out.println("in: " + n);

        count += check(n - 2);
        System.out.println("post: " + n);

        return count + 3;

    }

    public static void main(String... args) {
        printDecreasing(5, 15);
        System.out.println(check(5));
    }

}