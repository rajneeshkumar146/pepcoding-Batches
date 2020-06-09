import java.util.Scanner;
import java.util.Arrays;

public class T002_ArrayQuestion {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        // int[] arr = { -7, 1, 5, 2, -4, 3, 0, 0, 0, 0, 0, 0 };
        // equilIndex(arr);

        // int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // targetSum(arr, 12);

        int[] arr = { 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6, 6, 6, 6, 6, 7, 8, 9, 10, 10, 10, 10 };
        uniquePairWithTarget(arr, 12);
    }

    public static void equilIndex(int[] arr) {
        int Sum = 0;
        for (int i : arr) {
            Sum += i;
        }
        Sum -= arr[0];
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            Sum -= arr[i];
            if (leftSum == Sum) {
                System.out.println("Equilibrium Index: " + i);
            }
            leftSum += arr[i];
        }
    }

    public static void targetSum(int[] arr, int target) {
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        boolean flag = false;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                System.out.println("(" + left + ", " + right + "), ");
                left++;
                right--;
                flag = true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        if (flag == false)
            System.out.println("No such pair exist: " + -1);

    }

    public static void uniquePairWithTarget(int[] arr, int target) {
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        boolean flag = false;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                flag = true;
                System.out.print("(" + arr[left] + ", " + arr[right] + "), ");
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1])
                    left++;

                while (left < right && arr[right] == arr[right + 1])
                    right--;

            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        if (flag == false)
            System.out.println("No such pair exist: " + -1);

    }

   public static void maximumWaterContainer(int[] arr)
{
    int maxAns = 0;
    int left = 0;
    int right = arr.length - 1;
    while (left < right)
    {
        int width = right - left;
        int height = arr[left];
        if (arr[left] < arr[right])
        {
            height = arr[left];
            left++;
        }
        else
        {
            height = arr[right];
            right--;
        }
        int potentialAns = height * width;

        maxAns = Math.max(maxAns, potentialAns);
    
    }
}