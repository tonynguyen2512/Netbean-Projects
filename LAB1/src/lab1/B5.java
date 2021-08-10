package lab1;

import java.util.Scanner;

public class B5 {
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
        int count = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0;
        int n;
        int[] Array = new int[1000];
        System.out.print("So luong phan tu cua mang : ");
        while (true) {
            n = sc.nextInt();
            if (n <= 0) {
                System.out.print("Ban nhap sai, vui long nhap so lon 0 : ");
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.printf ("Array[%d] = ", i);
            Array[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (Array[i] % 2 == 0) {
                count++;
            }
            if (Array[i] % 2 != 0) {
                count1++;
            }
            if (Array[i] > 0) {
                count2++;
            }
            if (Array[i] == 0) {
                count3++;
            }
            if (Kiemtra_SNT(Array[i]) == true) {
                count4++;
            }
        }
        System.out.printf("Co %d so chan vua nhap \n", count);
        System.out.printf("Co %d so le vua nhap \n", count1);
        System.out.printf("Co %d so duong vua nhap \n", count2);
        System.out.printf("Co %d so 0 vua nhap \n", count3);
        System.out.printf("Co %d so nguyen to vua nhap \n", count4);
    }
}
