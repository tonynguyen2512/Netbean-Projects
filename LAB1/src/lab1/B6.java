package lab1;

import java.util.Scanner;

public class B6 {
//-----------------------------------------------------

    public static boolean Kiemtra_SNT (int so) {
        if (so < 2) {
            return false;
        }
        for (int i = 2; i < so; i++) {
            if (so % i == 0) {
                return false;
            }
        }
        return true;
    }
//-----------------------------------------------------

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, i;
        int sum = 0, temp = 0;
        System.out.print("Nhap vao so nguyen duong : ");
        while (true) {
            n = sc.nextInt ();
            if (n > 0) {
                break;
            } else {
                System.out.print("Nhap sai, Nhap lai so nguyen duong ");
            }
        }
        System.out.println("------ Cac so nho hon N co tong binh phuong la so nguyen to ------");
        for (int index = 1; index < n; index++) {
            i = index;
            sum = 0;
            while (i != 0) {
                temp = i % 10;
                sum += (temp * temp);
                i = i / 10;
                if (Kiemtra_SNT(sum) == true) {
                    System.out.printf ("%d\n", index);
                }
            }
        }
        System.out.println ();
    }
}
