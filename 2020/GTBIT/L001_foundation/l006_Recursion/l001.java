public class l001 {

    // nor : number of rows
    public static void printRectangle(int cst, int nst, int nor, int n, int m) {
        if (nor == n + 1)
            return;

        if (cst == nst + 1) {
            System.out.println();
            printRectangle(1, nst, nor + 1, n, m);
            return;
        }

        System.out.print("*");
        printRectangle(cst + 1, nst, nor, n, m);
    }

    public static void printTriangle(int cst, int nst, int nor, int n, int m) {
        if (nor == n + 1)
            return;

        if (cst == nst + 1) {
            System.out.println();
            printTriangle(1, nst + 1, nor + 1, n, m);
            return;
        }

        System.out.print("*");
        printTriangle(cst + 1, nst, nor, n, m);
    }

    

    public static void main(String[] args) {

    }

}