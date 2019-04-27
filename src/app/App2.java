package app;

import java.sql.*;

public class App2{
    public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hellow_world?serverTimezone=GMT", "root", "wcf12345"); 
			// 时区错误???
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from db1");
			while (rs.next()) {
				for (int i = 1; i < 4; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println("");
            }
            stmt.close();
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}