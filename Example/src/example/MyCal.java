package example;

public class MyCal {
    public static int Plus (int a, int b) {
        return a + b;
    }

    public static int Plus (int a, int b, int c) {
        return a + b + c;
    }
    public static void main(String[] args) {
        System.out.println(Plus(1,2,3));
    }
}
