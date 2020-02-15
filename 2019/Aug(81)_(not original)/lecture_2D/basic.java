public class basic {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static void input(int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                arr[row][col] = scn.nextInt();
            }
        }
    }

    public static void print(int[][] arr) {
        for (int[] ar : arr) {
            for (int i : ar) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static boolean find(int[][] arr, int data) {
        for (int[] ar : arr) {
            for (int i : ar) {
                if (i == data)
                    return true;
            }
        }

        return false;
    }

    public static void spiral(int[][] arr) {
        int minr = 0;
        int minc = 0;
        int maxr = arr.length - 1;
        int maxc = arr[0].length - 1;

        int tne = arr.length * arr[0].length;
        int count = 0;

        while (count < tne) {

            for (int col = minc; col <= maxc && count < tne; col++) {
                System.out.print(arr[minr][col] + " ");
                count++;
            }
            minr++;

            for (int row = minr; row <= maxr && count < tne; row++) {
                System.out.print(arr[row][maxc] + " ");
                count++;
            }
            maxc--;

            for (int col = maxc; col >= minc && count < tne; col--) {
                System.out.print(arr[maxr][col] + " ");
                count++;
            }
            maxr--;

            for (int row = maxr; row >= minr && count < tne; row--) {
                System.out.print(arr[row][minc] + " ");
                count++;
            }
            minc++;

        }

    }

    public static void addMatrix(int[][] mat1, int[][] mat2) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                mat1[i][j] += mat2[i][j];
            }
        }
    }

    public static void diagonal() {
        char[][] arr = { { 'a', 'b', 'c', 'd' }, { 'e', 'f', 'g', 'h' }, { 'i', 'j', 'k', 'l' },
                { 'm', 'n', 'o', 'p' } };

        for (int gap = 0; gap < arr[0].length; gap++) {
            for (int i = 0, j = gap; i < arr.length && j < arr[0].length; i++, j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}