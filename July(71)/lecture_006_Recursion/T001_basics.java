public class T001_basics {
    public static void main(String[] args) {
        // System.out.println(powerLessOptimal(2, 10));
        System.out.println(powerOptimal(2, 30));
        System.out.println(steps);

    }

    static int steps = 0;

    public static int powerLessOptimal(int a, int b) {
        if (b == 0)
            return 1;

        steps++;
        int ans = a * powerLessOptimal(a, b - 1);
        return ans;
    }

    public static int powerOptimal(int a, int b) {
        if (b == 0)
            return 1;

        steps++;
        int ans = powerOptimal(a, b / 2);
        ans *= ans;
        if (b % 2 != 0)
            ans *= a;
        return ans;
    }

    public static void TOH(int n, String src, String desti, String help) {
        if (n == 0)
            return;

        TOH(n - 1, src, help, desti);
        System.out.println(n + "th block from Source -> " + src + " to destination ->" + desti);
        TOH(n - 1, help, desti, src);

    }
}