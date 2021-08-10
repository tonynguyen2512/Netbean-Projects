package lab1;

import java.util.Scanner;

public class B3 {
//-----------------------------------        

    public static int dg1(int a) {
        a *= 1242;
        return a;
    }
//-----------------------------------    

    public static int dg2(int a) {
        int i, s = 0;
        i = a - 100;
        s = dg1(100);
        i *= 1304;
        return s + i;
    }
//-----------------------------------    

    public static int dg3(int a) {
        int i, s = 0;
        i = a - 150;
        s = dg2(150);
        i *= 1651;
        return s + i;
    }
//-----------------------------------      

    public static int dg4(int a) {
        int i, s = 0;
        i = a - 200;
        s = dg3(200);
        i *= 1651;
        return s + i;
    }
//-----------------------------------      

    public static int dg4b(int a) {
        int i, m = 1651, s = 0;
        for (i = 1; i <= a; i++) {
            m += 500;
            s += m;
        }
        return s;

    }
//-----------------------------------      

    public static int in(int a) {
        System.out.print("So tien phai tra la: " + a + " VND\n");
        return 0;
    }
//-----------------------------------      

    public static void main(String[] args) {
        int n, t, s = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so kWh: ");
        n = sc.nextInt();
        if ((n > 0) && (n <= 100)) {
            in (s = dg1(n));
        }
        if ((n > 100) && (n <= 150)) {
            in (s = dg2(n));
        }
        if ((n > 150) && (n <= 200)) {
            in (s = dg3(n));
        }
        if ((n > 200)){
            s = dg4(200);
            t = n - 200;
            in (s += dg4b(t));
        }
    }
}
