package Test;

public class Test1 {

    public static void main(String[] args) {
        Study_1A obj = new Study_1A();
        obj.M();
        obj = new Study_1B();
        obj.M();
        obj = new Study_1C (); // 1A and 1C are inconvertible
        obj.M();
    }

}

class Study_1A {

    void M() {
        System.out.print("A");
    }
}

class Study_1B extends Study_1A {

    void M() {
        System.out.print("B");
    }
}

class Study_1C { //must herritage when using 1A

    void M() {
        System.out.print("C");
    }
}
