package adapters;

public abstract class MyAdapter {
    public void M1() {System.out.println ("M1"); }
    public void M2() {System.out.println ("M2"); }
}

class Program {
    public static void main (String[] args) {
        MyAdapter o = new MyAdapter () {
            public void M1() {System.out.println ("M1 - override"); }
    };
                o.M1();
                o.M2();
}
}