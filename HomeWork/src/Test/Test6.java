package Test;

public class Test6 {
    static int N = 10;
    int x = 120;

    static {
        N = 50;
        System.out.print("A");
    }

    public void M() {
        System.out.print(x);
    }

    public static void main(String[] args) {
        Test6 obj = new Test6();
        obj.M();
    }
}
