package lab1;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;

public class B1 {

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
        int a, b;
        String c, d;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap chi so dong ho dien thang truoc: ");
        c = sc.nextLine();
        System.out.print("Nhap chi so dong ho dien thang sau: ");
        d = sc.nextLine();
        while (isNumber(c) == 0 || isNumber(d) == 0) {
            System.out.print("Nhap lai chi so dong ho dien thang truoc: ");
            c = sc.nextLine();
            System.out.print("Nhap lai chi so dong ho dien thang sau: ");
            d = sc.nextLine();
        }
        if (isNumber(c) == 1 && isNumber(d) == 1) {
            a = Integer.parseInt(c);
            b = Integer.parseInt(d);
            while (b <= a) {
                System.out.print("Nhap khong hop le! ");
                System.out.print("\n\nNhap lai chi so dong ho dien thang truoc: ");
                a = sc.nextInt();
                System.out.print("Nhap lai chi so dong ho dien thang sau: ");
                b = sc.nextInt();
            }
            System.out.print("So kWh tieu thu la: " + (b - a) + " kWh\n");
        }
    }
}
