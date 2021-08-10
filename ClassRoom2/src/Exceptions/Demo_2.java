package Exceptions;

public class Demo_2 {
    public static void main (String [] args) {
    int x = 6, y = 0;
    try {
        System.out.println (x/y);
    }
    catch (Exception e) {
        //System.out.println (e);  
        e.printStackTrace();
        y = 2;
    }
    finally {
        System.out.println ("Hi");
        System.out.println (x/y);
    }
}
}
