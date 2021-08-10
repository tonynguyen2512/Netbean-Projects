package Test;

public class Test7 {
    static int N = 10;
    int x = 120;

    static {
        N = 7;
        System.out.print("A" + N);
        x = 500; //Instance if lose can run!
    }

    public void M() {
        System.out.print(x);
    }

    public static void main(String[] args) {
        Test7 obj = new Test7();
        obj.M();
    }

    
}
