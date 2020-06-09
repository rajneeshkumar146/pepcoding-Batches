public class pointerBasic {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int a=10;
        int sum=++a + a++ + ++a;
        System.out.print(sum);
    }
}