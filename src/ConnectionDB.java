import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private Connection conn = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.90:1521:air3";
	private String db_id = "tokki2";
	private String db_pwd="so";
	
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url,db_id,db_pwd);
			System.out.println("접속완료");
		}
		catch(ClassNotFoundException e) {
		
			System.out.println("db오류.1");
		}
		catch(SQLException se) {
			System.out.println("db오류.2");
		}
		
	
}
	public static void main(String[] args) {
		new ConnectionDB().connect();
	}
}
