package pkg1;
public class ArrayDemo {
    public static void main (String[] arg){
        int [] arrInt1 = new int [10];
        int [] arrInt2 = { 2, 3, 4, 5 };
        int [] [] arrInt3 = new int [2] [3];
        String [] arrString = new String [5];
        ArrayDemo [] arrDemos = new ArrayDemo [5];
        for ( int i = 0; i < 2; i++) {
            for ( int j = 0; j < 3; j++)
                System.out.print ( arrInt3 [i] [j] + " ");
        }
        System.out.println ("");
    }  
}
