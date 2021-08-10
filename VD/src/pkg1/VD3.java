/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1;

/**
 *
 * @author ASUS
 */
public class VD3 {
    public static void main (String[] arg) {
        int a = 0, b = 3;
        if ((a > 10) && (b > 5)) {
            System.out.println ("True");
        } else { System.out.println ("False"); }
        if  ((a < 10) || (b > 5))
            System.out.println ("True");
        else System.out.println ("Flase");
        a = 0; b = 3;
        if ((a++ > 10) && (b-- < 5)) // 1 dấu & xét cả 2 trường hợp rồi cho kết quả
            //2 dấu && thì xét trường hợp đầu trc, nếu sai thì ko cần xét cái thứ 2
            System.out.println ("a: " + a + "\nb: " + b);
        else 
            System.out.println ("a: " + a + "\nb: " + b);
        }        
    }
