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

    public static void printEvenOdd(int a,int b){

    }

    public static int fact(int n){

    }

    public static int pow(int a,int b){

    }

    public static void main(String... args) {
        printDecreasing(5, 15);
    }

}