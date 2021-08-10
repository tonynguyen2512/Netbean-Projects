package Test;

public class Test2 {

    public static void main(String[] args) {
        Object obj = new Object(); //Object class does not have M()
        obj.M();
        obj = new Study_2B();
        obj.M();
        obj = new Study_2C(); 
        obj.M();
    }

}

class Study_2A {

    void M() {
        System.out.print("A");
    }
}

class Study_2B extends Study_2A {

    void M() {
        System.out.print("B");
    }
}

class Study_2C {

    void M() {
        System.out.print("C");
    }
    
}
