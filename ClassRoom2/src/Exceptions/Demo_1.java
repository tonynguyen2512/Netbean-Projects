package Exceptions;

public class Demo_1 {
    public static void main (String args[]) {
    int[] a = {1, 2, 3, 4, 5};
    int n = 10;
    try {
    for (int i = 0; i < n; i++)
        System.out.print ("" + a[i] + ", ");
    }
    catch (Exception e)//general exception
    {
        System.out.println (e);
    }
    }
}
