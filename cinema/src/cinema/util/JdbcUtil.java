package cinema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {
	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "kbkb";
		String pwd = "1234";
		
		return DriverManager.getConnection(url,user,pwd);
	}

	public static void close(PreparedStatement ps, Connection con) {
			try {
				if(ps != null) 	ps.close();
			} catch (SQLException e) { }
			try {
				if(con != null)	con.close();
			} catch (SQLException e) {}
	}
}