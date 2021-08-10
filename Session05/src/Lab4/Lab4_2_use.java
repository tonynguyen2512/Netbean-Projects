package Lab4;

public class Lab4_2_use {

    public static void main(String[] args) {
        System.out.println("Bài lab kế thừa bài 2:");
        HinhChuNhat a = new HinhChuNhat(12, 24);
        System.out.println(a.XemChuViDienTich());
        HinhTron b = new HinhTron(12);
        System.out.println(b.XemChuViDienTich());
    }
}
