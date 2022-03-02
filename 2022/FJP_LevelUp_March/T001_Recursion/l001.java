public class l001 {
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

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static int fact(int n){
        return n == 0 ? 1 : fact(n - 1) * n;
    }

    public static int power(int a,int b){
        return b == 0 ? 1 : power(a,b - 1) * a;
    }

    public static void main(String... args) {

        int a = 1, b = 10;
        printIncreasingDecreasing(a, b);
    }
}