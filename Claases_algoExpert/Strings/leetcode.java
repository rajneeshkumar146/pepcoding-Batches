public class leetcode{

    // 205
    public String runLengthEncoding(String str) {
        StringBuilder code = new StringBuilder();
        int i = 0, n = str.length();
        while (i < n) {
            int count = 1;
            while (i + 1 < n && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }

            breakString(count, str.charAt(i), code);
            i++;
        }
        return code.toString();
    }

}