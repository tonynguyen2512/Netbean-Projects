package Test;

public class Test5 {
     public static void main(String[] args) {
        Study_5C obj = new Study_5C();
        obj.M();
        obj =  new Study_5B();
        obj.M();
        obj =  new Study_5A(); // 1A and 1C are inconvertible
        obj.M();
    }

}

class Study_5A {

    void M() {
        System.out.print("A");
    }
}

class Study_5B extends Study_5A {

    @Override
    void M() {
        System.out.print("B");
    }
}

class Study_5C extends Study_5B { //must herritage when using 1A

    @Override
    void M() {
        System.out.print("C");
    }
    
}
