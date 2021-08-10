package Test;

public class Test4 {

    public static void main(String[] args) {
        Study_4A obj = new Study_4A();
        obj.M();
        obj = new Study_4B();
        obj.M();
        obj = new Study_4C(); // 1A and 1C are inconvertible
        obj.M();
    }

}

class Study_4A {

    void M() {
        System.out.print("A");
    }
}

class Study_4B extends Study_4A {

    void M() {
        System.out.print("B");
    }
}

class Study_4C extends Study_4B { //must herritage when using 1A

    void M() {
        System.out.print("C");
    }
}
