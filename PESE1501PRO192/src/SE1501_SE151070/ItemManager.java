package SE1501_SE151070;

import java.util.Scanner;

public class ItemManager {

    public static void main(String[] args) {
        String filename = "Data.txt";
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(6);
        menu.add("Thêm 1 sinh viên mới");
        menu.add("Xóa 1 sinh viên");
        menu.add("Chỉnh sửa thông tin sinh viên");
        menu.add("Hiển thị ra màn hình danh sách sinh viên");
        menu.add("Ghi danh sách sinh viên xuống file");
        menu.add("Thoát");
        int Choice;
        NewItems list = new NewItems();
        list.loadStoredCodes(filename);
        do {
            System.out.println("\nQUẢN LÍ DANH SÁCH SINH VIÊN");
            Choice = menu.getChoice();
            switch (Choice) {
                case 1:
                    list.addNewItem();
                    break;
                case 2:
                    list.removeItem();
                    break;
                case 3:
                    list.updateItem();
                    break;
                case 4:
                    list.print();
                    break;
                case 5:
                    list.appendToFile(filename);
                    break;
                default:
                    if (list.size() > 0) {
                        System.out.println("Lưu thay đồi? Y/N? ");
                        String response = sc.nextLine().toUpperCase();
                        if (response.startsWith("Y")) {
                            list.appendToFile(filename);
                        }
                    }
            }
        } while (Choice > 0 && Choice < 6);
    }

}
