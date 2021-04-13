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

    public static void main(String[] args) {
        int a = 2;
        int b = 7;
        printIncreasingDecreasingEvenOdd(a, b);
    }
}