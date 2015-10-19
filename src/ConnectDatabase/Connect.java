/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author thanglt
 */
public class Connect {

    
    private Connection cn;
    //Hàm dựng
    public Connect() {
        
    }
    // Nhiệm vụ là Connect đến database mang tên: manager-coffee
    public Connection connected(){
        try {
            //tạo tên truy cập cho driver:
            Class.forName("com.mysql.jdbc.Driver");
            //tạo lệnh truy cập đến cơ sở dữ liệu
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager-coffee?user=root&password=");
            if (cn != null) {
                System.out.println("Connect Success !!!");
            }
            if(cn == null){
                System.out.println("Connect Failed !!!");
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return cn;
    }
    
}
