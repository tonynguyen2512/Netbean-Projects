/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class VD4 {
    public static void main (String [] arg) {
        int age;
        Scanner sc = new Scanner (System.in);
        age = sc.nextInt ();
        switch (age) {
            case 0:
                System.out.println ("Zero");
                break;
            case 10:
                System.out.println ("Ten");
                break;
            case 20:
                System.out.println ("Twenty");
                break; 
            default:
                System.out.println ("Default");
                break;
        }
    }
}
