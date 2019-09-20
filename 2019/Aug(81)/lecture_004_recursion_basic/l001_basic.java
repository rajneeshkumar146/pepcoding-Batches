public class l001_basic {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        incresing(1,10);
        // decresing(1,10);
    }

    public static void incresing(int a, int b) {
        if (b == a - 1)
            return; // baseCase

        incresing(a, b - 1); // faith
        System.out.println(b);
    }


    public static void decresing(int a, int b) {
        if (b == a - 1)
            return; // baseCase

        System.out.println(b);
        decresing(a, b - 1); // faith
        
    }

}