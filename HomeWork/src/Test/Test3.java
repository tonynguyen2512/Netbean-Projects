package Test;

public class Test3 {

    public static void main(String[] args) {
        Study_3A obj = new Study_3A();
        obj.M();
        obj = new Study_3B();
        obj.M();
        Object obj2 = new Study_3C(); // 3A and 3C are inconvertible so does Object.
        ((Study_3A)obj2).M();
    }

}

class Study_3A {

    void M() {
        System.out.print("A");
    }
}

class Study_3B extends Study_3A {

    @Override
    void M() {
        System.out.print("B");
    }
}

class Study_3C {

    void M() {
        System.out.print("C");
    }
}
