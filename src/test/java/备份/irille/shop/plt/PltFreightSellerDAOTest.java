package 备份.irille.shop.plt;

import irille.shop.plt.PltFreightSeller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import irille.pub.bean.Query;

public class PltFreightSellerDAOTest {

	public static void main(String[] args) {
		Query.SELECT(PltFreightSeller.class).queryList();
	}
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/shoestp";
	    String username = "root";
	    String password = "root";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
