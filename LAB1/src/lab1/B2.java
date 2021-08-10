package lab1;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;
import static lab1.B1.isNumber;

public class B2 {

    public static int isNumber(String a) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(a, pos);
        if (a.length() == pos.getIndex()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        long n, i = 0;
        String c;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so nguyen n: ");
        c = sc.nextLine();
        while (isNumber(c) == 0) {
            System.out.print("Nhap lai so nguyen n: ");
            c = sc.nextLine();
        }
        n = Integer.parseInt(c);
        while (n < 0) {
            System.out.print("Nhap lai so nguyen n: ");
            n = sc.nextInt();
        }
        System.out.print("So nguoc lai cua " + n + " la: ");
        while (n != 0) {
            i = i + (n % 10);
            n = n / 10;
            i *= 10;
            //System.out.print (i);
        }
        System.out.println(i = i / 10);
    }
}
