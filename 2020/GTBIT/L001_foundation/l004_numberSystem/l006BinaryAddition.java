public class l006BinaryAddition{
    public static int l006BinaryAddition(int n,int m){

        int res = 0, pow = 1, carry = 0;
        while(n != 0 || m !=0 || carry != 0){
            int r1 = n % 10;
            int r2 = m % 10;

            n /= 10;
            m /= 10;

            int sum = r1 + r2 + carry;
            int r = sum % 2;
            carry = sum / 2;

            res += r * pow;
            pow *= 10;
        }
    }


    public static void main(String[] args){
        int n = scn.nextInt();
        int m = scn.nextInt();
     }
}