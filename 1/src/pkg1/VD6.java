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
public class VD6 {
    public static void main (String[] arg){
        char[] copyFrom = {'d', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd'};
        char[] copyTo = new char [7];
        System.arraycopy (copyFrom, 2, copyTo, 0 ,7); //copy phần từ tử 2 đến phần tử 0 7 kí tự 
        System.out.println (new String(copyTo)); // caffein
    }
}
