/**
 * Nguyễn Phi Hoàng - 19443211 - Nhóm 03
 * 
 * mô tả lớp : thực hiện kết nối chương trình với database bên sql server 
 */
package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=CTQLSach";
		String user = "sa";
		String password = "123456";
		con = DriverManager.getConnection(url, user, password);

	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnetction() {
		return con;
	}
	
	
	
//	 public static Connection getConnection() {
//	        Connection cons = null;
//	        try {
//	            Class.forName("com.mysql.jdbc.Driver");
//	            cons = DriverManager.getConnection(
//	                    "jdbc:mysql://localhost:3306/db_qlhv", "root", "");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        return cons;
//	    }
//
	    
}
