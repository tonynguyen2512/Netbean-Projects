/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;
import java.lang.Integer;
/**
 *
 * @author ASUS
 */
public class BoxingExample {
    public static void main (String args[]) {
        int num = 1;
        Integer o1 = new Integer(num);
        Integer o2 = 2;
        System.out.println (num + " " + o1 + " " + o2);
    }
}

class WrapperExample2 {
    public static void main (String args[]) {
        Integer a = new Integer(3);
        int i = a.intValue();
        int j = a;
        System.out.println (a + " " + i + " " + j);
    }
}
