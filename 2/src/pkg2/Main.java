package pkg2;
import java.util.Scanner;
public class Main {
    public static void main (String [] arg) {
        int n, i, s = 1;
        Scanner sc = new Scanner (System.in);
        System.out.print (" Nhap vao so nguyen n: ");
        n = sc.next().charAt(0); 
        if (n < 48 || n > 57);
        n = (int)n - '0';
        for (i = 1; i <= n; i++)
            s *= i;
        System.out.println (n + " giai thua la: " + s);
    }
}