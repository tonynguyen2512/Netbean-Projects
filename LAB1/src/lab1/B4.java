package lab1;

import java.util.Random;
import java.util.Scanner;

public class B4 {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int number = rd.nextInt(10) + 1;
        int x;
        System.out.print("Nhap so tu 1 toi 10 : ");
        x = sc.nextInt();
        int i = 2, d = 3;
        do {
            if (x == number) {
                System.out.print("Win!");
                break;
            } else {
                System.out.print("That bai, nhap lai: ");
                x = sc.nextInt();
            }
            if (i == 3) {
                System.out.print("Game over!\n");
                break;
            }
            i++;
        } while (i <= d);
    }
}
