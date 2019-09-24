import java.util.scanner;

public class NthFibonacci{
    public static Scanner scn=new Scanner(System.in);
public static void main(String[] args){
int n=scn.nextInt();

  int a = 0,b = 1, sum = 0;
    if (n <= 1)
    {
        cout << n << endl;
        return;
    }

    for (int i = 2; i <= n; i++)
    {
        sum = a + b;
        a = b;
        b = sum;
    }

    System.out.println(sum);  
}

}