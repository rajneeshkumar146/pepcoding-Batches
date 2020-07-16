public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        // String str = "";
        for (int i = 0; i < 1000000; i++) {
            // str += i;
            sb.append(i);
        }
        System.out.println(sb.toString());

    }

}