import java.util.*;

public class q001_digitFreqForQuery {
    public static Scanner scn = new Scanner(System.in);

    public static void digitFreq(long n, int[] query) {
        int[] freq = new int[10];
        while (n != 0) {
            long d = n % 10;
            n /= 10;

            freq[(int) d]++;
        }

        for (int q : query) {
            System.out.println(freq[q]);
        }
    }

    public static void main(String[] args) {
        long n = scn.nextLong();
        int[] query = new int[scn.nextInt()];
        for (int i = 0; i < query.length; i++) {
            query[i] = scn.nextInt();
        }

        digitFreq(n, query);
    }

}