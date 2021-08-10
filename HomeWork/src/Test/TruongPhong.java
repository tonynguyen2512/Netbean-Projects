package Test;

   public class TruongPhong extends Session05 {

    public double trachNhiem;

    public TruongPhong(String hoTen, double luong, double trachNhiem) {
        super(hoTen, luong);
        this.trachNhiem = trachNhiem;
    }

    public void xuat(){
        super.xuat();
        System.out.println("Trach Nhiem: "+ trachNhiem);
        System.out.println("Đây là trưởng phòng!");
    }
   
}
