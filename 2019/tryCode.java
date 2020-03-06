import java.util.HashMap;
import java.util.ArrayList;

public class tryCode {
    public static void main(String[] args) {
        int n = 4;
        int arr1[] = { 1, 1, 2, 2, 2 };
        int arr2[] = { 2, 2, 3, 3, 4 };
        int arr3[] = { 2, 3, 1, 3, 4 };

        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String edge = arr1[i] + "@" + arr2[i];
            if (map.containsKey(edge))
                map.get(edge).add(arr3[i]);
            else map.put(edge,new ArrayList<Integer>());
        }

        String edge = "";
        int maxIntrest = 0;
        for (String p : map.keySet()) {
            if (map.get(p).size() > maxIntrest) {
                edge = p;
                maxIntrest = map.get(p).size();
            }
        }

        int ans = 1;
        String[] s = edge.split("@");
        for (int i = 0; i < s.length; i++) {
            if (!s[i].equals("@"))
                ans *= Integer.parseInt(s[i]);
        }

        System.out.println(ans);
    }
}