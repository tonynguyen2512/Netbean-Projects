package pkg2;
import java.util.Scanner;
public class Btap2 {
    public static void main (String [] arg) {
        int n, i, t = 0, s = 0;
        Scanner sc = new Scanner (System.in);
        System.out.print (" Nhap vao so nguyen n: ");
        n = sc.nextInt ();
        for (i = 0; i <= n; i+=2) s += i;
        for (i = 1; i <= n; i+=2) t += i;        
        System.out.println (" Tong cac so chan tu 0 den n la: " + s);
        System.out.println (" Tong cac so le tu 1 den n la: " + t);
    }
}
