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
                ei = cakeArea - 1e-5;
            else
                si = cakeArea;

        }

        return si;
    }

    public static boolean itIsCorrectPenatly(int[] arr, double distance, int k) {
        int noOfGasStation = 0, n = arr.length;
        for (int i = 1; i < n; i++) {
            noOfGasStation += (arr[i] - arr[i - 1]) / distance;
            if (noOfGasStation > k)
                return false;
        }

        return true;
    }

    public static double minmaxGasDist(int[] stations, int k) {
        double si = 0.0, ei = 1e9;
        while ((ei - si) > 1e-6) {
            double distance = (ei + si) / 2.0;
            if (!itIsCorrectPenatly(stations, distance, k))
                si = distance + 1e-6;
            else
                ei = distance;
        }

        return ei;
    }

    public static void main(String[] args) {
        int[] arr = { 23,24,36,39,46,56,57,65,84,98};
        System.out.println(minmaxGasDist(arr, 1));
    }
}