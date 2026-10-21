package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Topic_06_Loop {
    static void main(String[] args) {
        // Trong trường hợp mình cần duyệt qua 1 danh sách dữ liệu. Duyệt qua hết toàn bộ. Gặp điều kiện thỏa mãn thì dừng lại. Kết hợp cả vòng lặp với điều kiện
        int employeeSize = 10;
        //for (classic) - vòng lặp cổ điển
        for (int i = 1; i <= employeeSize; i++){
            System.out.println("Nhân viên thứ " + i);
        }

        List<String> ProductName = new ArrayList<>();
        ProductName.add("Sản phẩm A1");
        ProductName.add("Sản phẩm A2");
        ProductName.add("Sản phẩm A3");
        ProductName.add("Sản phẩm A4");
        ProductName.add("Sản phẩm A5");

        for (int i = 0; i < ProductName.size(); i++) {
            System.out.println(ProductName.get(i));
        }
        // for-each
        for (String name:   ProductName){
            System.out.println(name);
        }

        //while
        employeeSize = 10;
        int x = 1;
        while (x < employeeSize){
            System.out.println("Nhân viên thư " + x);
            x++;

        }

        //do while
        int y = 1;
        do {
            System.out.println("Nhân viên thứ " + y);
            y++;
        } while (y <= employeeSize);
        
    }

}
