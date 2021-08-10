package Test;

public class Session05 {

    public String hoTen;
    public double luong;

    public Session05(String hoTen, double luong) {
        this.hoTen = hoTen;
        this.luong = luong;
    }

    public double getThuNhap() {
        return this.luong * 0.1;
    }

    public void xuat() {
        System.out.println("Họ và tên: " + this.hoTen);
        System.out.println("Lương: " + this.luong);
        System.out.println("Thuế thu nhập: " + getThuNhap());
        System.out.println("Đây là nhân viên!");

    }
}
