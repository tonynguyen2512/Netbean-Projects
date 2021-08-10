package Test;

public class LaoCong extends Session05 {

    public double gioLamViec;

    public LaoCong(String hoTen, double luong, double gioLamViec) {
        super(hoTen, luong);
        this.gioLamViec = gioLamViec;
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Giờ làm việc: " + gioLamViec);
        System.out.println("Đây là lao công!");
    }
}
