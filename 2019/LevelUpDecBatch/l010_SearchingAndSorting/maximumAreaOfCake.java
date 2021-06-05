import java.util.Arrays;

public class maximumAreaOfCake {

    public static boolean isPossibleToServeCake(int[] radiusArray, double cakeArea, int guest) {
        int g = 0;
        for (int i = radiusArray.length - 1; i >= 0; i--) {
            double area = Math.PI * radiusArray[i] * radiusArray[i];
            g += Math.floor(area / cakeArea);
            if (g >= guest)
                return true;

        }
        return false;
    }

    public static double maximumAreaCake(int[] radius, int guest) {
        // Arrays.sort(radius);
        int n = radius.length;
        // double si = (Math.PI * radius[0] * radius[0]) / guest, ei = Math.PI *
        // radius[n - 1] * radius[n - 1];
        double si = 0.0, ei = 1e7;
        while ((ei - si) > 1e-5) {
            double cakeArea = (si + ei) / 2.0;
            if (!isPossibleToServeCake(radius, cakeArea, guest))
                ei = cakeArea;
            else
                si = cakeArea;

        }

        return si;
    }

    public static void main(String[] args) {
        int[] arr = { 6, 7 };
        System.out.println(maximumAreaCake(arr, 12));
    }
}