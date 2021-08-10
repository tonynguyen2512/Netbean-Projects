package SE1501_SE151070;

import java.util.Scanner;
import java.util.Vector;
import java.io.*;
import java.util.Collections;

public class NewItems extends Vector<Item> {

    Scanner sc = new Scanner(System.in);
    Vector<String> storedCodes = new Vector<String>();

    public NewItems() {
        super();
    }

    public void loadStoredCodes(String fName) {
        if (storedCodes.size() > 0) {
            storedCodes.clear();
        }
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String code, name, priceStr;
            while ((code = bf.readLine()) != null
                    && (name = bf.readLine()) != null
                    && (priceStr = bf.readLine()) != null) {
                storedCodes.add(code);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean valid(String aCode) {
        for (int i = 0; i < storedCodes.size(); i++) {
            if (aCode.equals(storedCodes.get(i))) {
                return false;
            }
        }
        for (int i = 0; i < this.size(); i++) {
            if (aCode.equals(this.get(i).getCode())) {
                return false;
            }
        }
        return true;
    }

    private int find(String aCode) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCode().equals(aCode)) {
                return i;
            }
        }
        return -1;
    }

    public void appendToFile(String fName) {
        if (this.size() == 0) {
            System.out.println("Empty List");
            return;
        }
        try {
            boolean append = true;
            File f = new File(fName);
            FileWriter fw = new FileWriter(f, append);
            PrintWriter pw = new PrintWriter(fw);
            for (Item x : this) {
                pw.println(x.getCode());
                pw.println(x.getName());
                pw.println(x.getAge());
                pw.println(x.getGender());
                pw.println(x.getScore());
                pw.flush();
            }
            pw.close();
            fw.close();
            this.loadStoredCodes(fName);
            this.clear();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void addNewItem() {
        String newCode, newName, gender;
        int age;
        double score;
        boolean duplicated = false, matched = true;
        System.out.println("Nhập mã sinh viên mới: ");
        do {
            System.out.print("   Định dạng mã Sinh Viên (S****): ");
            newCode = sc.nextLine().toUpperCase();
            duplicated = !valid(newCode);
            matched = newCode.matches("^S\\d{4}$");
            if (duplicated) {
                System.out.println(" Mã sinh viên đã tồn tại!.");
            }
            if (!matched) {
                System.out.println("  Mã Sinh viên bao gồm: 'S' và '4 kí tự khác'");
            }
        } while (duplicated || (!matched));
        System.out.print("   Tên: ");
        newName = sc.nextLine().toUpperCase();
        System.out.print("   Tuổi: ");
        age = Integer.parseInt(sc.nextLine());
        System.out.print("   Giới tính: ");
        gender = sc.nextLine().toUpperCase();
        System.out.print("   Điểm: ");
        score = sc.nextDouble();
        this.add(new Item(newCode, newName, age, gender, score));
        System.out.println("Sinh viên mới đã được thêm:");

    }

    public void removeItem() {
        String code;
        System.out.println("Nhập mã sinh viên muốn xóa: ");
        code = sc.nextLine().toUpperCase();
        int pos = find(code);
        if (pos < 0) {
            System.out.println("Mã này không tồn tại!");
        } else {
            this.remove(pos);
            System.out.println("Sinh viên " + code + " đã được xóa!");

        }
    }

    public void updateItem() {
        if (this.size() == 0) {
            System.out.println("DANH SÁCH TRỐNG.");
            return;
        }
        String code = null;
        String newName, newGender;
        int age = 0;
        double score = 0;
        System.out.print("Nhập mã sinh viên cần chỉnh sửa: ");
        code = sc.nextLine().toUpperCase();
        int pos = find(code);
        if (pos < 0) {
            System.out.println("Mã này không tồn tại!");
        } else {
            do {
                System.out.print(" Tên: ");
                newName = sc.nextLine().toUpperCase();
                if ("".equals(newName)) {
                    System.out.println("Bạn không nhập gì, vui lòng nhập lại!");
                }
            } while ("".equals(newName));
            do {
                System.out.print(" Giới tính: ");
                newGender = sc.nextLine().toUpperCase();
                if ("".equals(newGender)) {
                    System.out.println("Bạn không nhập gì, vui lòng nhập lại!");
                }
            } while ("".equals(newGender));
            int i;
            do {
                try {
                    System.out.print("   Tuổi: ");
                    age = Integer.parseInt(sc.nextLine());
                    if (age < 0 || age > 90) {
                        System.out.println("Nhập sai, tuổi phải >=0 và <=90!");
                    }
                    i = 1;
                } catch (Exception e) {
                    System.out.println("Tuổi không hợp lệ, vui lòng thử lại!!");
                    i = -1;
                }
            } while (i == -1 || age < 0 || age > 90);
            do {
                try {
                    System.out.print(" Điểm: ");
                    score = Double.parseDouble(sc.nextLine());
                    if (score < 0 || score > 10) {
                        System.out.println("Nhập sai, điểm phải >=0 và <=10!");
                    }
                    i = 1;
                } catch (Exception e) {
                    System.out.println("Điểm không hợp lệ, vui lòng thử lại!!");
                    i = -1;
                }
            } while (i == -1 || score < 0 || score > 10);
            this.get(pos).setName(newName);
            this.get(pos).setGender(newGender);
            this.get(pos).setAge(age);
            this.get(pos).setScore(score);
            System.out.println("Sinh viên " + code + " đã được cập nhật!");
        }
    }

    public void print() {
        if (this.size() == 0) {
            System.out.println("DANH SÁCH TRỐNG.");
            return;
        }

        System.out.println("\nDANH SÁCH SINH VIÊN");
        System.out.println("----------------------------");
        for (Item x : this) {
            x.print();
        }
    }
}
