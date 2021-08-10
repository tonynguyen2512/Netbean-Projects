package SE1501_SE151070;

import java.util.Scanner;
import java.util.Vector;

public class Menu {

    String[] hints;
    int n = 0;

    public Menu(int size) {
        if (size < 1) {
            size = 10;
        }
        hints = new String[size];
    }

    public void add(String aHint) {
        if (n < hints.length) {
            hints[n++] = aHint;
        }
    }

    public int getChoice() {
        int result = 0;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + "-" + hints[i]);
            }
            while (true) {
                try {
                    do {
                        System.out.print("Chọn 1 lệnh: ");
                        Scanner sc = new Scanner(System.in);
                        result = Integer.parseInt(sc.nextLine());
                        if ((result < 1) | (result > 6)) {
                            System.out.println("Số không hợp lệ, vui lòng thử lại!");
                        }
                    } while ((result < 1) | (result > 6));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Phương thức nhập sai, vui lòng thử lại!");
                }
            }
        }
        return result;
    }
}
