package Test;

public class Test8 {

    static int N = 2;
    int x = 10;
    public static int y;

    static {
        N = 5;
        y = 7;
        System.out.print("A" + (N + y));
    }

    public void M() {
        System.out.print(x + y);
        
    }

    public static void main(String[] args) {
        Test8 obj = new Test8();
        obj.M();
    }

}
